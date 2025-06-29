import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import api from "../../custom-axios/axios";
import './StudentSubjects.css';

function StudentSubjects({ studentId: propStudentId }) {
    const { studentId: urlStudentId } = useParams();
    const studentId = propStudentId || urlStudentId;

    const [subjects, setSubjects] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const navigate = useNavigate();

    useEffect(() => {
        setLoading(true);
        setError(null);

        api.get(`/student/${studentId}/subjects`)
            .then((res) => {
                setSubjects(res.data || []);
                setLoading(false);
            })
            .catch((err) => {
                setError("Failed to load subjects: " + (err.message || "Unknown error"));
                setLoading(false);
            });
    }, [studentId]);

    if (loading) return <div className="text-center mt-4">Loading enrolled subjects...</div>;
    if (error) return <div className="alert alert-danger mt-4">{error}</div>;

    if (!subjects.length) {
        return (
            <div className="container mt-4">
                <div className="alert alert-info">
                    No enrolled subjects found.
                </div>
                <div className="d-flex justify-content-center mb-3 back-button-subjects-by-student text-center">
                    <button className="btn text-white" onClick={() => navigate(`/`)}>
                        ⬅ Back to Home!
                    </button>
                </div>
            </div>
        );
    }

    return (
        <div className="student-subjects-container mt-4">
            <div className="student-subjects-title mb-4">
                <h4 className="mb-4">Your Enrolled Subjects</h4>
            </div>
            <div className="row justify-content-center">
                {subjects.map((enrollment, index) => {
                    const subjectName = enrollment.subjectName || "Unknown Subject";
                    const subjectCode = enrollment.code || "N/A";
                    const professor = enrollment.professor || "N/A";
                    const message = enrollment.message || null;

                    return (
                        <div key={subjectCode + index} className="col-12 col-md-6 col-lg-4 mb-4 d-flex justify-content-center">
                            <div className="card">
                                <div className="card-header">{subjectName}</div>
                                <div className="card-body d-flex flex-column">
                                    <h6 className="card-subtitle mb-3">{subjectCode}</h6>
                                    <p className="card-text"><strong>Professor:</strong> {professor}</p>
                                    {message && <p className="text-danger">{message}</p>}
                                    <button
                                        className="topics-button mt-auto"
                                        onClick={() => navigate(`/subject-allocations/subjects/${subjectCode}/topics`, {
                                            state: { subjectName },
                                        })}
                                    >
                                        View Topics!
                                    </button>
                                </div>
                            </div>
                        </div>
                    );
                })}
            </div>
        </div>
    );
}

export default StudentSubjects;
