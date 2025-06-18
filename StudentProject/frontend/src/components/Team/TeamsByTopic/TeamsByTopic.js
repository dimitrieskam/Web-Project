import React, {useState, useEffect} from "react";
import {useParams, useNavigate} from "react-router-dom";
import api from "../../../custom-axios/axios";
import './TeamsByTopic.css';

function TeamsByTopic() {
    const {topicId} = useParams();
    const navigate = useNavigate();

    const [teams, setTeams] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        setLoading(true);
        api.get(`/teams/topic/${topicId}`)
            .then(res => {
                setTeams(res.data);
                setError(null);
            })
            .catch(err => {
                setError("Failed to load teams.");
                console.error(err);
            })
            .finally(() => setLoading(false));
    }, [topicId]);

    if (loading) {
        return (
            <div className="container mt-4 text-center">
                <div className="spinner-border" role="status"/>
                <p>Loading teams...</p>
            </div>
        );
    }

    if (error) {
        return (
            <div className="container mt-4">
                <div className="alert alert-danger">{error}</div>
                <button className="btn btn-secondary" onClick={() => navigate("/")}>Back to Home</button>
            </div>
        );
    }

    if (teams.length === 0) {
        return (
            <div className="container mt-4">
                <h3>No teams found for this topic.</h3>
                <button className="btn btn-secondary" onClick={() => navigate("/")}>Back to Home</button>
            </div>
        );
    }

    return (
        <div className="container my-5 teams-by-topic-container">
            <div className="text-center mb-4 title">
                <h2>
                    Topic: <span>{teams[0]?.topicName || topicId}</span>
                </h2>
                <h4 className="title">
                    <span className="fw-semibold"> {teams[0]?.subjectName}</span>
                </h4>
            </div>
            <div className="row g-4">
                {teams.map(team => (
                    <div key={team.id} className="col-md-6 col-lg-4">
                        <div className="card border-0 shadow-sm h-100 rounded-4">
                            <div className="card-header bg-primary text-white rounded-top-4 team-name">
                                <h5 className="mb-0">{team.name}</h5>
                            </div>
                            <div className="card-body">
                                <p className="fw-semibold mb-2">Members:</p>
                                <ul className="list-group list-group-flush">
                                    {team.studentIndexes.map(index => (
                                        <li key={index} className="list-group-item px-0">
                                            <i className="bi bi-person-fill text-secondary me-2" /> {index}
                                        </li>
                                    ))}
                                </ul>
                            </div>
                        </div>
                    </div>
                ))}
            </div>
            <div className="d-flex justify-content-end mb-3 back-button">
                <button className="btn text-white" onClick={() => navigate("/subjects")}>
                    ⬅ Back to Subjects!
                </button>
            </div>
        </div>
    );
}

export default TeamsByTopic;
