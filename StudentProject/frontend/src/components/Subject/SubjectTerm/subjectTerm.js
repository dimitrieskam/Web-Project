import React from 'react';
import { useNavigate } from 'react-router-dom';

const SubjectTerm = ({ term }) => {
    const navigate = useNavigate();

    const handleDetailsClick = () => {
        // TODO topics by subject
        navigate(`/subjects/${term.id}`);
    };

    return (
        <div className="col-sm-6 col-md-4 col-lg-3 mb-4">
            <div className="card shadow-sm rounded-3 h-100">
                <div className="card-body d-flex flex-column">
                    <h5 className="card-title">{term.name}</h5>
                    <p className="card-text"><strong>Semester Type:</strong> {term.semesterType || "-"}</p>
                    <p className="card-text"><strong>Students:</strong> {term.studentIds?.join(', ') || "-"}</p>
                    <p className="card-text"><strong>Professors:</strong> {term.professorIds?.join(', ') || "-"}</p>
                    <button onClick={handleDetailsClick} className="btn btn-primary mt-auto">
                        View Topics!
                    </button>
                </div>
            </div>
        </div>
    );
};

export default SubjectTerm;
