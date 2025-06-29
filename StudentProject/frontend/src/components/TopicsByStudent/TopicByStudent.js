import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import AppService from "../../repository/appRepository";
import "./TopicsByStudent.css";

function TopicsByStudent() {
    const { studentId } = useParams();

    const [topics, setTopics] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        if (!studentId) {
            setError("No student ID provided");
            setLoading(false);
            return;
        }

        setLoading(true);
        setError(null);

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

    if (!topics.length) {
        return (
            <div className="container mt-4">
                <div className="alert alert-info">No topics found for this student.</div>
            </div>
        );
    }

    return (
        <div className="topics-student-container mt-4">
            <div className="topics-student-title mb-4">
                <h4>Topics <small>for Student <strong>{studentId}</strong></small></h4>
            </div>
            <div className="row justify-content-center">
                {topics.map((topic) => (
                    <div key={topic.id} className="col-12 col-md-6 col-lg-4 mb-4 d-flex justify-content-center">
                        <div className="card topic-card">
                            <div className="card-header">{topic.name}</div>
                            <div className="card-body d-flex flex-column">
                                <p className="card-text"><strong>Description:</strong> {topic.description}</p>
                                <p className="card-text"><strong>From:</strong> {topic.fromDate ? new Date(topic.fromDate).toLocaleDateString() : "N/A"}</p>
                                <p className="card-text"><strong>To:</strong> {topic.toDate ? new Date(topic.toDate).toLocaleDateString() : "N/A"}</p>
                                <p className="card-text"><strong>Professor:</strong> {topic.professorName || "N/A"}</p>
                                <p className="card-text"><strong>Subject:</strong> {topic.subjectName || "N/A"}</p>
                                <p className="card-text"><strong>Status:</strong> {topic.studentTeamInfo?.status || "N/A"}</p>
                                <p className="card-text"><strong>Follow-up Comment:</strong> {topic.studentTeamInfo?.followUpComment || "None"}</p>
                            </div>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
}

export default TopicsByStudent;
