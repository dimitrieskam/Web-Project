import React, { useEffect, useState } from "react";
import api from "../../custom-axios/axios";

function ProfessorSubjects({ professorId }) {
  const [subjects, setSubjects] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    if (!professorId) return;

    setLoading(true);
    api
      .get(`/professors/${professorId}/subjects`)
      .then((res) => {
        console.log("Subjects data:", res.data);
        setSubjects(res.data);
        setLoading(false);
      })
      .catch(() => {
        setError("Failed to load subjects");
        setLoading(false);
      });
  }, [professorId]);

  if (loading) return <div className="text-center mt-4">Loading...</div>;
  if (error) return <div className="alert alert-danger mt-4">{error}</div>;
  if (subjects.length === 0) return <div className="mt-4">No subjects found.</div>;

  return (
    <div className="container mt-4">
      <div className="row">
        {subjects.map(({ subject, abbreviation, semester, semesterCode }, index) => (
          <div key={index} className="col-md-4 mb-4">
            <div className="card h-100 shadow-sm">
              <div className="card-body d-flex flex-column">
                <h5 className="card-title">{subject}</h5>
                <h6 className="card-subtitle mb-2 text-muted">{abbreviation}</h6>
                <p className="card-text mb-1"><strong>Semester:</strong> {semester}</p>
                <p className="card-text"><strong>Semester Code:</strong> {semesterCode}</p>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}

export default ProfessorSubjects;
