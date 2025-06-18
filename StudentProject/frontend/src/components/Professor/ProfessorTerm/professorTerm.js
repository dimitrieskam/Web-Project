import React from 'react';
import { useNavigate } from 'react-router-dom';

const ProfessorTerm = ({ term }) => {
    const navigate = useNavigate();

    const handleDetailsClick = () => {
        navigate(`/subject-allocations/${term.id}/subjects`);
    };

    return (
        <div className="card shadow-sm rounded-3 h-100">
            <div className="card-body d-flex flex-column">
                <div className="card-body-text">
                    <h5 className="card-title">{term.name}</h5>
                    <p className="card-text">{term.email}</p>
                </div>
                <button onClick={handleDetailsClick} className="btn btn-primary mt-auto">
                    View Subjects!
                </button>
            </div>
        </div>
    );
};

export default ProfessorTerm;
