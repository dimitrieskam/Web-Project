import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import api from "../../../custom-axios/axios";
import AppRepository from "../../../repository/appRepository";
import authService from "../../../repository/Authentication/auth_service";
import "./TeamsByTopic.css";

function TeamsByTopic() {
  const { topicId } = useParams();
  const navigate = useNavigate();

  const [teams, setTeams] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const role = authService.getCurrentUser()?.role;

  /* ------------------------------------------------------------------
     Fetch teams for the selected topic
  ------------------------------------------------------------------ */
  useEffect(() => {
    setLoading(true);
    api
      .get(`/teams/topic/${topicId}`)
      .then((res) => {
        setTeams(res.data);
        setError(null);
      })
      .catch(() => setError("Failed to load teams."))
      .finally(() => setLoading(false));
  }, [topicId]);

  /* ------------------------------------------------------------------
     Delete a team (professor only)
  ------------------------------------------------------------------ */
  const handleDeleteTeam = (teamId) => {
    if (!window.confirm("Are you sure you want to delete this team?")) return;

    AppRepository.deleteTeam(teamId)
      .then(() =>
        setTeams((prev) => prev.filter((t) => t.id !== teamId))
      )
      .catch(() => alert("Could not delete team. Check console for more info."));
  };

  /* ------------------------------------------------------------------
     Helper for the “Back to Subjects” button
  ------------------------------------------------------------------ */
  const goBack = () => {
    const user = JSON.parse(localStorage.getItem("user"));
    if (user?.role === "ROLE_PROFESSOR") {
      navigate(`/subject-allocations/${user.username}/subjects`);
    } else if (user?.role === "ROLE_STUDENT") {
      navigate(`/student/${user.username}/subjects`);
    } else {
      navigate("/subjects");
    }
  };

  /* ------------------------------------------------------------------
     Render helpers
  ------------------------------------------------------------------ */
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
        <button className="btn btn-secondary" onClick={() => navigate("/")}>
          Back to Home
        </button>
      </div>
    );
  }

  if (teams.length === 0) {
    return (
      <div className="container mt-4 text-center">
        <h3 className="alert alert-info">No teams found for this topic.</h3>
        <button className="btn back-button-teams" onClick={goBack}>
          ⬅ Back to Subjects!
        </button>
      </div>
    );
  }

  /* ------------------------------------------------------------------
     Main render
  ------------------------------------------------------------------ */
  return (
    <div className="container my-5 teams-by-topic-container">
      <div className="text-center">
        <h2 className="topic-title-h4">
          <strong>{teams[0]?.subjectName}</strong>
        </h2>
        <h4 className="topic-title-h4">
          <small>Topic: </small>
          <strong>{teams[0]?.topicName || topicId}</strong>
        </h4>
      </div>

      {/* Responsive grid using Bootstrap utilities */}
      <div className="row">
        {teams.map((team) => (
          <div key={team.id} className="col-6 col-md-4">
            <div className="card border-0 shadow-sm w-100">
              <div className="card-header team-name">
                <h5 className="mb-0">{team.name}</h5>
              </div>

              <div className="card-body">
                <p className="fw-semibold mb-2">Members:</p>
                <ul className="list-group list-group-flush">
                  {team.students.map((s) => (
                    <li key={s.index} className="list-group-item px-0">
                      <i className="bi bi-person-fill me-2" />
                      {s.index} — {s.name} {s.lastname}
                    </li>
                  ))}
                </ul>

                <p className="mt-2">
                  <strong>Status:</strong> {team.status}
                </p>
                <p>
                  <strong>Comment:</strong>{" "}
                  {team.followUpComment || "No comments yet."}
                </p>

                {/* Professor-only status form */}
                {role === "ROLE_PROFESSOR" && (
                  <form
                    className="mt-3"
                    onSubmit={(e) => {
                      e.preventDefault();
                      const { status, followUpComment } = e.target;

                      AppRepository.updateTeamStatus(
                        team.id,
                        status.value,
                        followUpComment.value
                      )
                        .then(() =>
                          setTeams((prev) =>
                            prev.map((t) =>
                              t.id === team.id
                                ? {
                                    ...t,
                                    status: status.value,
                                    followUpComment: followUpComment.value,
                                  }
                                : t
                            )
                          )
                        )
                        .catch(() =>
                          alert("Failed to update team status.")
                        );
                    }}
                  >
                    <div className="mb-2">
                      <label className="form-label">Status:</label>
                      <select
                        name="status"
                        defaultValue={team.status}
                        className="form-select"
                      >
                        <option value="OPEN">Open</option>
                        <option value="IN_PROGRESS">In Progress</option>
                        <option value="DONE">Done</option>
                      </select>
                    </div>

                    <div className="mb-2">
                      <label className="form-label">Follow‑Up Comment:</label>
                      <input
                        type="text"
                        name="followUpComment"
                        className="form-control"
                        defaultValue={team.followUpComment || ""}
                      />
                    </div>

                    <button type="submit" className="btn btn-secondary btn-sm">
                      Update Status
                    </button>
                  </form>
                )}
              </div>

              {role === "ROLE_PROFESSOR" && (
                <button
                  className="btn btn-danger btn-sm delete-team-button"
                  onClick={() => handleDeleteTeam(team.id)}
                >
                  🗑 Delete Team
                </button>
              )}
            </div>
          </div>
        ))}
      </div>

      <div className="text-center">
        <button className="btn back-button-teams" onClick={goBack}>
          ⬅ Back to Subjects!
        </button>
      </div>
    </div>
  );
}

export default TeamsByTopic;
