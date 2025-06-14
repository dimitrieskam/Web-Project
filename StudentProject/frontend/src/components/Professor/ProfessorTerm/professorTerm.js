import React from 'react';
import { useNavigate } from 'react-router-dom';

const ProfessorTerm = ({ term }) => {
    const navigate = useNavigate();

    const handleDetailsClick = () => {
        // TODO subjects by professor
        navigate(`/professors/${term.id}`);
    };

    return (
        <div className="col-sm-6 col-md-4 col-lg-3 mb-4">
            <div className="card shadow-sm rounded-3 h-100">
                <div className="card-body d-flex flex-column">
                    <h5 className="card-title">{term.name}</h5>
                    <p className="card-text text-muted">{term.email}</p>
                    <button onClick={handleDetailsClick} className="btn btn-primary mt-auto">
                        View Subjects!
                    </button>
                </div>
            </div>
        </div>
    );
};

export default ProfessorTerm;
