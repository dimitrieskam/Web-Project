import React, { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import api from "../../custom-axios/axios"; // your configured axios instance

function StudentSubjects({studentId: propStudentId}) {
    const {studentId: urlStudentId} = useParams();
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
  }, []);

  if (loading) return <div className="text-center mt-4">Loading enrolled subjects...</div>;
  if (error) return <div className="alert alert-danger mt-4">{error}</div>;

  if (!subjects.length) {
    return <div className="alert alert-info mt-4">No enrolled subjects found.</div>;
  }

  return (
    <div className="container mt-4">
      <h3>Your Enrolled Subjects</h3>
      <div className="row">
        {subjects.map((enrollment, index) => {
          const subjectName = enrollment.subjectName || "Unknown Subject";
          const subjectCode = enrollment.code || "N/A";
          const professor = enrollment.professor || "N/A";
          const message = enrollment.message || null;

          return (
            <div key={subjectCode + index} className="col-md-4 mb-4">
              <div className="card p-3 h-100 d-flex flex-column">
                <h5>{subjectName}</h5>
                <p><strong>Code:</strong> {subjectCode}</p>
                <p><strong>Professor:</strong> {professor}</p>
                {message && <p className="text-danger">{message}</p>}
                <button
                  className="btn btn-primary mt-auto"
                  onClick={() => navigate(`/subject-allocations/subjects/${subjectCode}/topics`,
                    {state: {subjectName}}
                  )}
                >
                  View Topics
                </button>
              </div>
            </div>
          );
        })}
      </div>
    </div>
  );
}

export default StudentSubjects;
