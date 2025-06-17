import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import api from "../../custom-axios/axios";

function CreateTeam() {
    const {topicId} = useParams();
    const navigate = useNavigate();

    const [teamName, setTeamName] = useState("");
    const [topicName, setTopicName] = useState("");
    const [studentQuery, setStudentQuery] = useState("");
    const [suggestedStudents, setSuggestedStudents] = useState([]);
    const [selectedStudents, setSelectedStudents] = useState([]);
    const [maxMembers, setMaxMembers] = useState(null);

    const [error, setError] = useState(null);
    const [loading, setLoading] = useState(false);
    const [topicLoading, setTopicLoading] = useState(true);

    const [isClosed, setIsClosed] = useState(false);

    useEffect(() => {
        setTopicLoading(true);
        setError(null);

        api.get(`/subject-allocations/topics/${topicId}`)
            .then(res => {
                setTopicName(res.data.name);
                setMaxMembers(res.data.membersPerGroup);
                setError(null);
            })
            .catch(err => {
                console.error("Error fetching topic:", err);
                if (err.response?.status === 404) {
                    setError(`Topic with ID ${topicId} not found. Please check the URL or contact your administrator.`);
                } else {
                    setError("Failed to load topic info. Please try again.");
                }
            })
            .finally(() => {
                setTopicLoading(false);
            });

        api.get(`/topics/${topicId}/is-closed`)
            .then(res => setIsClosed(res.data))
            .catch(err => {
                console.error("Error fetching topic closed status:", err);
                setIsClosed(false);
            });
    }, [topicId]);

    useEffect(() => {
        if (studentQuery.trim() !== "") {
            api.get(`/students/search?q=${studentQuery}`)
                .then(res => setSuggestedStudents(res.data))
                .catch(() => setSuggestedStudents([]));
        } else {
            setSuggestedStudents([]);
        }
    }, [studentQuery]);

    const addStudent = (student) => {
        if (
            !selectedStudents.some(s => s.index === student.index) &&
            (maxMembers === null || selectedStudents.length < maxMembers)
        ) {
            setSelectedStudents(prev => [...prev, student]);
        }
        setStudentQuery("");
        setSuggestedStudents([]);
    };

    const removeStudent = (studentIndex) => {
        setSelectedStudents(prev => prev.filter(s => s.index !== studentIndex));
    };

    const handleSubmit = (e) => {
        e.preventDefault();

        if (isClosed) {
            setError("Cannot create team: Topic is closed (max number of teams reached).");
            return;
        }

        setLoading(true);
        setError(null);

        const createTeamDTO = {
            name: teamName,
            studentIds: selectedStudents.map(s => s.index),
        };

        console.log("Submitting team creation:", createTeamDTO);

        api.post(`/teams/create-team/${topicId}`, createTeamDTO)
            .then(() => {
                setLoading(false);
                navigate(`/teams/topic/${topicId}`);
            })
            .catch((err) => {
                setLoading(false);
                console.error("Error creating team:", err);

                let errorMessage = "Failed to create team";
                if (err.response?.data?.message) {
                    errorMessage = err.response.data.message;
                } else if (err.response?.data) {
                    errorMessage = typeof err.response.data === 'string'
                        ? err.response.data
                        : JSON.stringify(err.response.data);
                } else if (err.message) {
                    errorMessage = err.message;
                }

                setError(errorMessage);
            });
    };

    if (topicLoading) {
        return (
            <div className="container mt-4">
                <div className="text-center">
                    <div className="spinner-border" role="status">
                        <span className="visually-hidden">Loading...</span>
                    </div>
                    <p className="mt-2">Loading topic information...</p>
                </div>
            </div>
        );
    }

    if (error && !topicName) {
        return (
            <div className="container mt-4">
                <div className="alert alert-danger">
                    <h4 className="alert-heading">Error Loading Topic</h4>
                    <p>{error}</p>
                    <hr />
                    <button
                        className="btn btn-secondary"
                        onClick={() => navigate("/")}
                    >
                        Go Back to Home
                    </button>
                </div>
            </div>
        );
    }

    return (
        <div className="container mt-4">
            <h2>Create Team for Topic: {topicName || topicId}</h2>

            {error && topicName && <div className="alert alert-danger">{error}</div>}

            {isClosed && (
                <div className="alert alert-warning">
                    Topic is closed: maximum number of teams has been reached.
                </div>
            )}

            <form onSubmit={handleSubmit}>
                <div className="mb-3">
                    <label htmlFor="teamName" className="form-label">Team Name</label>
                    <input
                        type="text"
                        className="form-control"
                        id="teamName"
                        value={teamName}
                        onChange={(e) => setTeamName(e.target.value)}
                        required
                        disabled={isClosed}
                    />
                </div>

                <div className="mb-3 position-relative">
                    <label htmlFor="search" className="form-label">Search Student by Index</label>
                    <input
                        type="text"
                        className="form-control"
                        id="search"
                        value={studentQuery}
                        onChange={(e) => setStudentQuery(e.target.value)}
                        placeholder="Enter student index..."
                        disabled={isClosed}
                    />
                    {suggestedStudents.length > 0 && (
                        <ul className="list-group position-absolute w-100" style={{ zIndex: 10, maxHeight: "150px", overflowY: "auto" }}>
                            {suggestedStudents.map(student => (
                                <li
                                    key={student.index}
                                    className="list-group-item list-group-item-action"
                                    onClick={() => addStudent(student)}
                                    style={{ cursor: "pointer" }}
                                >
                                    {student.index} - {student.name} {student.lastname}
                                </li>
                            ))}
                        </ul>
                    )}
                </div>

                <div className="mb-3">
                    <label className="form-label">Selected Students ({selectedStudents.length}/{maxMembers ?? "?"})</label>
                    {selectedStudents.length === 0 && <p>No students selected</p>}
                    <ul className="list-group">
                        {selectedStudents.map(student => (
                            <li key={student.index} className="list-group-item d-flex justify-content-between align-items-center">
                                {student.index} - {student.name} {student.lastname}
                                <button
                                    type="button"
                                    className="btn btn-danger btn-sm"
                                    onClick={() => removeStudent(student.index)}
                                    disabled={isClosed}
                                >
                                    Remove
                                </button>
                            </li>
                        ))}
                    </ul>
                </div>

                {maxMembers !== null && selectedStudents.length >= maxMembers && (
                    <div className="alert alert-info">Maximum number of team members reached.</div>
                )}

                <button type="submit" className="btn btn-primary" disabled={loading || selectedStudents.length === 0 || !topicName}>
                    {loading ? "Creating..." : "Create Team"}
                </button>

                <button
                    type="button"
                    className="btn btn-secondary ms-2"
                    onClick={() => navigate("/")}
                    disabled={loading}
                >
                    Cancel
                </button>
            </form>
        </div>
    );
}

export default CreateTeam;