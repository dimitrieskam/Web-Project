import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import api from "../../custom-axios/axios";

function TeamsByTopic() {
    const { topicId } = useParams();
    const navigate = useNavigate();

    const [teams, setTeams] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        setLoading(true);
        api.get(`/teams/topic/${topicId}`) // Adjust endpoint if needed
            .then(res => {
                setTeams(res.data); // Expecting an array of DisplayTeamDTOs
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
                <div className="spinner-border" role="status" />
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
        <div className="container mt-4">
            <h2>Teams for Topic: {teams[0]?.topicName || topicId}</h2>
            <h4>Subject: {teams[0]?.subjectName}</h4>

            {teams.map(team => (
                <div key={team.id} className="card mb-3">
                    <div className="card-header">
                        <strong>{team.name}</strong>
                    </div>
                    <div className="card-body">
                        <p><strong>Team ID:</strong> {team.id}</p>
                        <p><strong>Members:</strong></p>
                        <ul>
                            {team.studentIndexes.map(index => (
                                <li key={index}>{index}</li>
                            ))}
                        </ul>
                    </div>
                </div>
            ))}
        </div>
    );
}

export default TeamsByTopic;
