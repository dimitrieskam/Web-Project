import React, { useEffect, useState } from "react";
import api from "../../custom-axios/axios";
import {useParams, Link} from "react-router-dom";

function ProfessorSubjects({ professorId: propProfessorId }) {
    const { professorId: urlProfessorId } = useParams();
    const professorId = propProfessorId || urlProfessorId;

    console.log("professorId from URL:", urlProfessorId);
    console.log("professorId from props:", propProfessorId);
    console.log("Using professorId:", professorId);

    const [subjects, setSubjects] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

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
        <div className="container mt-4">
            <h2 className="mb-4">Professor's Subjects</h2>
            <div className="row">
                {subjects.map((allocation, index) => {
                    const subject = allocation.subject || allocation.subjectName || 'Unknown Subject';
                    const abbreviation = allocation.abbreviation || allocation.subjectCode || 'N/A';
                    const semester = allocation.semester || 'N/A';
                    const semesterCode = allocation.semesterCode || 'N/A';

                    return (
                        <div key={allocation.id || index} className="col-md-4 mb-4">
                            <div className="card h-100 shadow-sm">
                                <div className="card-body d-flex flex-column">
                                    <h5 className="card-title">{subject}</h5>
                                    <h6 className="card-subtitle mb-2 text-muted">{abbreviation}</h6>
                                    <p className="card-text mb-1">
                                        <strong>Semester:</strong> {semester}
                                    </p>
                                    <p className="card-text">
                                        <strong>Semester Code:</strong> {semesterCode}
                                    </p>
                                    <Link
                                         to={`/subject-allocations/professors/${professorId}/subjects/${semesterCode}/topics`}
                                        className="btn btn-primary mt-auto"
                                    >
                                       Topics
                                    </Link>
                                    
                                </div>
                            </div>
                        </div>
                    );
                })}
            </div>
        </div>
    );
}

export default ProfessorSubjects;
