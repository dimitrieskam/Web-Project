import React, {useEffect, useState} from "react";
import {useParams, Link, useNavigate, useLocation} from "react-router-dom";
import api from "../../custom-axios/axios";
import './SubjectTopic.css';
import subject from "../Subject/SubjectList/subject";

function SubjectTopics({subjectId: propSubjectId, professorId}) {
    const {subjectId: urlSubjectId} = useParams();
    const location =useLocation();
    const subjectId = propSubjectId || urlSubjectId;
    
    const loggedUser = JSON.parse(localStorage.getItem("user"));
    const studentId = loggedUser?.username;
    console.log("Logged user:", loggedUser);


    const navigate = useNavigate();
    const subjectNameFromState = location.state?.subjectName || "";

    const [topics, setTopics] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [subjectName, setSubjectName] = useState(subjectNameFromState);

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
    if (error)
        return (
            <div className="alert alert-danger mt-4">{error}</div>
        );
    if (!topics.length)
        return (
            <div className="subject-topics-container mt-4">
                <div className="alert alert-info subject-topics-alert">No topics found for this subject.</div>

                <div className="d-flex justify-content-center mb-3 back-button-topics-by-subject text-center">
                    <button
                        className="btn text-white"
                        onClick={() => {
                            if (loggedUser?.role === "ROLE_STUDENT") {
                                navigate(`/student/${loggedUser.username}/subjects`);
                            } else if (loggedUser?.role === "ROLE_PROFESSOR") {
                                navigate(`/subject-allocations/${loggedUser.username}/subjects`);
                            }
                        }}
                    >
                        ⬅ Back to Subjects!
                    </button>
                </div>
            </div>
        );

    return (
        <div className="subject-topics-container mt-4">
            <div className="subject-topics-title mb-4">
                <h2 className="mb-4">Topics <small>by Subject <strong>{subjectName}</strong></small></h2>
            </div>
            <div className="row justify-content-center">
                {topics.map((topic) => (
                    <div key={topic.id} className="col-12 col-md-6 mb-4 d-flex justify-content-center">
                        <div className="topic-card">
                            <div className="topic-card-body">
                                <h5 className="topic-card-title">{topic.name}</h5>
                                <p className="topic-card-text">{topic.description}</p>
                                <p className="topic-card-text mb-1">
                                    <strong>Professor:</strong> {topic.professorName || "N/A"}
                                </p>
                                <p className="topic-card-text mb-1">
                                    <strong>From:</strong>{" "}
                                    {topic.fromDate
                                        ? new Date(topic.fromDate).toLocaleDateString()
                                        : "N/A"}
                                </p>
                                <p className="topic-card-text mb-1">
                                    <strong>To:</strong>{" "}
                                    {topic.toDate
                                        ? new Date(topic.toDate).toLocaleDateString()
                                        : "N/A"}
                                </p>
                                <p className="topic-card-text">
                                    <strong>Groups:</strong> {topic.groupCount} |{" "}
                                    <strong>Members per Group:</strong> {topic.membersPerGroup}
                                </p>
                                {(loggedUser?.role === "ROLE_STUDENT" ||
                                    (loggedUser?.role === "ROLE_PROFESSOR" && topic.creatorProfessorUsername === loggedUser.username)) && (
                                        <Link
                                            className="btn-topic-primary mt-auto"
                                            to={`/teams/create-team/${topic.id}`}
                                        >
                                            Choose Topic
                                        </Link>
                                )}
                            </div>
                        </div>
                    </div>
                ))}
            </div>
            <div className="d-flex justify-content-center mb-3 back-button-topics-by-subject text-center">
                <button
                        className="btn text-white"
                        onClick={() => {
                            if (loggedUser?.role === "ROLE_STUDENT") {
                                navigate(`/student/${loggedUser.username}/subjects`);
                            } else if (loggedUser?.role === "ROLE_PROFESSOR") {
                                navigate(`/subject-allocations/${loggedUser.username}/subjects`);
                            }
                        }}
                    >
                        ⬅ Back to Subjects!
                    </button>
            </div>
        </div>
    );
}

export default SubjectTopics;
