import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import AppService from "../../repository/appRepository"; // Your API service

function TopicsByStudent() {
    const { studentId } = useParams();

    const [topics, setTopics] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    const navigate = useNavigate();

    useEffect(() => {
        if (!studentId) {
            setError("No student ID provided");
            setLoading(false);
            return;
        }

        setLoading(true);
        setError(null);

        // Call your backend API to get topics where this student is a member
        AppService.fetchTopicsByStudent(studentId)
            .then((res) => {
                setTopics(res.data || []);
                setLoading(false);
            })
            .catch((err) => {
                setError(`Failed to load topics: ${err.message || "Unknown error"}`);
                setLoading(false);
            });
    }, [studentId]);

    if (loading) return <div className="text-center mt-4">Loading topics...</div>;
    if (error) return <div className="alert alert-danger mt-4">{error}</div>;
    if (!topics.length)
        return (
            <div className="container mt-4">
                <div className="alert alert-info">No topics found for this student.</div>
            </div>
        );

    return (
        <div className="container mt-4">
            <h2 className="mb-4">Topics chosen by Student {studentId}</h2>
            <div className="row">
                {topics.map((topic) => (
                    <div key={topic.id} className="col-md-4 mb-4">
                        <div className="card h-100 shadow-sm">
                            <div className="card-body d-flex flex-column">
                                <h5 className="card-title">{topic.name}</h5>
                                <p className="card-text">{topic.description}</p>
                                <p className="card-text mb-1">
                                    <strong>From:</strong>{" "}
                                    {topic.fromDate ? new Date(topic.fromDate).toLocaleDateString() : "N/A"}
                                </p>
                                <p className="card-text mb-1">
                                    <strong>To:</strong>{" "}
                                    {topic.toDate ? new Date(topic.toDate).toLocaleDateString() : "N/A"}
                                </p>
                                <p className="card-text mb-1">
                                    <strong>Professor:</strong> {topic.professorName || "N/A"}
                                </p>
                                <p className="card-text mb-1">
                                    <strong>Subject:</strong> {topic.subjectName || "N/A"}
                                </p>
                                
                                {/* New fields for status and comment */}
                                <p className="card-text mb-1">
                                    <strong>Status:</strong> {topic.studentTeamInfo.status || "N/A"}
                                </p>
                                <p className="card-text">
                                    <strong>Follow-up Comment:</strong> {topic.studentTeamInfo.followUpComment || "None"}
                                </p>
                            </div>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
}

export default TopicsByStudent;
