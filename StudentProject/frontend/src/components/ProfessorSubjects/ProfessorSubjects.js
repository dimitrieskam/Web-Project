import React, { useEffect, useState } from "react";
import api from "../../custom-axios/axios";
import {useParams, Link, useNavigate} from "react-router-dom";
import './ProfessorSubjects.css';

function ProfessorSubjects({ professorId: propProfessorId }) {
    const { professorId: urlProfessorId } = useParams();
    const professorId = propProfessorId || urlProfessorId;

    console.log("professorId from URL:", urlProfessorId);
    console.log("professorId from props:", propProfessorId);
    console.log("Using professorId:", professorId);

    const [subjects, setSubjects] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const navigate = useNavigate();

    useEffect(() => {
        if (!professorId) {
            setError("No professor ID provided");
            setLoading(false);
            return;
        }

        setLoading(true);
        setError(null);

        console.log("Making API call for professorId:", professorId);

        api
            .get(`/subject-allocations/${professorId}/subjects`)
            .then((res) => {
                console.log("API Response:", res.data);
                setSubjects(res.data || []);
                setLoading(false);
            })
            .catch((err) => {
                console.error("API Error:", err);
                setError(`Failed to load subjects: ${err.message || 'Unknown error'}`);
                setLoading(false);
            });
    }, [professorId]);

    if (loading) return <div className="text-center mt-4">Loading subjects...</div>;
    if (error) return <div className="alert alert-danger mt-4">{error}</div>;
    if (!subjects || subjects.length === 0) {
        return (
            <div className="container mt-4">
                <div className="alert alert-info">
                    No subjects found for this professor.
                </div>
            </div>
        );
    }

    return (
        <div className="professor-subjects-container container mt-4">
            <div className="title">
                <h4 className="ps-title">Professor's Subjects</h4>
            </div>
            <div className="row">
                {subjects.map((allocation, index) => {
                    const subject = allocation.subject || allocation.subjectName || 'Unknown Subject';
                    const abbreviation = allocation.abbreviation || allocation.subjectCode || 'N/A';
                    const semester = allocation.semester || 'N/A';
                    const semesterCode = allocation.semesterCode || 'N/A';

                    return (
                        <div key={allocation.id || index} className="col-md-4 mb-4">
                            <div className="card h-100 shadow-sm">
                                <div className="card-header">
                                    {subject}
                                </div>
                                <div className="card-body d-flex flex-column">
                                    <div>
                                        <h6 className="card-subtitle mb-2"><span className="abbreviation-text">{abbreviation}</span></h6>
                                        <div className="card-details">
                                            <p className="card-text mb-1">
                                                <strong className="card-text-text">Semester:</strong> <span>{semester}</span>
                                            </p>
                                            <p className="card-text">
                                                <strong className="card-text-text">Subject Code:</strong> <span>{semesterCode}</span>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                                <div>
                                    <Link
                                        to={`/subject-allocations/professors/${professorId}/subjects/${semesterCode}/topics`}
                                        className="btn btn-primary mt-auto topics-button"
                                    >
                                        View Topics!
                                    </Link>
                                </div>
                            </div>
                        </div>
                    );
                })}
            </div>
            <div className="d-flex justify-content-end mb-3 back-button-professors text-center">
                <button className="btn text-white" onClick={() => navigate(`/professors`)}>
                    ⬅ Back to Professors!
                </button>
            </div>
        </div>
    );
}

export default ProfessorSubjects;