import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import api from "../../custom-axios/axios";

function CreateTeam() {
    const { topicId } = useParams();
    const navigate = useNavigate();

    const [teamName, setTeamName] = useState("");
    const [topicName, setTopicName] = useState("");
    const [availableStudents, setAvailableStudents] = useState([]);
    const [studentIds, setStudentIds] = useState([]);

    const [error, setError] = useState(null);
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        if (topicId) {
            api.get(`/allocations/topics/${topicId}`)
                .then(res => {
                    setTopicName(res.data.name);
                    setAvailableStudents(res.data.availableStudents || []);
                })
                .catch(err => {
                    setError("Failed to load topic details");
                });
        }
    }, [topicId]);

    const handleStudentToggle = (studentId) => {
        setStudentIds(prev =>
            prev.includes(studentId)
                ? prev.filter(id => id !== studentId)
                : [...prev, studentId]
        );
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        setLoading(true);
        setError(null);

        // ✅ Only required fields
        const createTeamDTO = {
            name: teamName,
            studentIds,
        };

        api
            .post(`/teams/create-team/${topicId}`, createTeamDTO) // ✅ Matches backend
            .then(() => {
                setLoading(false);
                alert("Team created successfully!");
                navigate("/"); // 👈 Replace with your dashboard or summary page
            })
            .catch((err) => {
                setLoading(false);
                setError(err.response?.data?.message || "Failed to create team");
            });
    };

    return (
        <div className="container mt-4">
            <h2>Create Team for Topic: {topicName || topicId}</h2>

            {error && <div className="alert alert-danger">{error}</div>}

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
                    />
                </div>

                <div className="mb-3">
                    <label className="form-label">Select Students</label>
                    <div style={{ maxHeight: "200px", overflowY: "scroll", border: "1px solid #ddd", padding: "10px" }}>
                        {availableStudents.length === 0 && <p>No students available</p>}
                        {availableStudents.map(student => (
                            <div key={student.id}>
                                <input
                                    type="checkbox"
                                    id={`student-${student.id}`}
                                    checked={studentIds.includes(student.id)}
                                    onChange={() => handleStudentToggle(student.id)}
                                />
                                <label htmlFor={`student-${student.id}`}> {student.name}</label>
                            </div>
                        ))}
                    </div>
                </div>

                <button type="submit" className="btn btn-primary" disabled={loading}>
                    {loading ? "Creating..." : "Create Team"}
                </button>
            </form>
        </div>
    );
}

export default CreateTeam;
