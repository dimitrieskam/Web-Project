import React, {useEffect, useState} from "react";
import {useParams, Link} from "react-router-dom";
import api from "../../custom-axios/axios";

function SubjectTopics({subjectId: propSubjectId}) {
    const {subjectId: urlSubjectId} = useParams();
    const subjectId = propSubjectId || urlSubjectId;

    const [topics, setTopics] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        if (!subjectId) {
            setError("No subject ID provided");
            setLoading(false);
            return;
        }

        setLoading(true);
        setError(null);

        api
            .get(`/subject-allocations/subjects/${subjectId}/topics`)
            .then((res) => {
                setTopics(res.data || []);
                setLoading(false);
            })
            .catch((err) => {
                setError(`Failed to load topics: ${err.message || "Unknown error"}`);
                setLoading(false);
            });
    }, [subjectId]);

    if (loading) return <div className="text-center mt-4">Loading topics...</div>;
    if (error) return <div className="alert alert-danger mt-4">{error}</div>;
    if (!topics.length)
        return (
            <div className="container mt-4">
                <div className="alert alert-info">No topics found for this subject.</div>
            </div>
        );

    return (
        <div className="container mt-4">
            <h2 className="mb-4">Subject Topics</h2>
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
                                <p className="card-text">
                                    <strong>Groups:</strong> {topic.groupCount} |{" "}
                                    <strong>Members per Group:</strong> {topic.membersPerGroup}
                                </p>
                                 <Link
                                    className="btn btn-primary"
                                     to={`/teams/create-team/${topic.id}`}
                                >
                                     Choose Topic
                                </Link>
                            </div>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
}

export default SubjectTopics;
