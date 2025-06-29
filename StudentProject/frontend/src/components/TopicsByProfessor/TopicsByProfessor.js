import React, { useEffect, useState } from "react";
import { useParams, Link, useNavigate } from "react-router-dom";
import AppService from "../../repository/appRepository";
import "./TopicsByProfessor.css";

function TopicsByProfessor() {
  const { professorId } = useParams();

  const [topics, setTopics] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [professorName, setProfessorName] = useState("");

  const navigate = useNavigate();

  useEffect(() => {
    if (!professorId) {
      setError("No professor ID provided");
      setLoading(false);
      return;
    }

    setLoading(true);
    setError(null);

    AppService.fetchProfessorById(professorId)
      .then((res) => {
        setProfessorName(res.data.name);
      })
      .catch(() => {
        setProfessorName(professorId);
      });

    AppService.fetchTopicsByProfessor(professorId)
      .then((res) => {
        setTopics(res.data || []);
        setLoading(false);
      })
      .catch((err) => {
        setError(`Failed to load topics: ${err.message || "Unknown error"}`);
        setLoading(false);
      });
  }, [professorId]);

  const handleDelete = (topicId) => {
    if (window.confirm("Are you sure you want to delete this topic?")) {
      AppService.deleteTopic(topicId)
        .then(() => {
          setTopics((prev) => prev.filter((t) => t.id !== topicId));
        })
        .catch((err) => {
          alert(`Failed to delete topic: ${err.message || "Unknown error"}`);
        });
    }
  };

  if (loading) return <div className="text-center mt-4">Loading topics...</div>;
  if (error) return <div className="alert alert-danger mt-4">{error}</div>;
  if (!topics.length)
    return (
      <div className="container mt-4">
        <div className="alert alert-info">No topics found for this professor.</div>
      </div>
    );

  return (
    <div className="professor-subjects-container mt-4">
      <div className="professor-subjects-title mb-4">
        <h4>All Topics by <strong>{professorName}</strong></h4>
      </div>
      <div className="row justify-content-center">
        {topics.map((topic) => (
          <div key={topic.id} className="col-12 col-md-6 col-lg-4 mb-4 d-flex justify-content-center">
            <div className="card">
              <div className="card-header">{topic.name}</div>
              <div className="card-body d-flex flex-column">
                <p className="card-text">{topic.description}</p>
                <p className="card-text"><strong>From:</strong> {topic.fromDate ? new Date(topic.fromDate).toLocaleDateString() : "N/A"}</p>
                <p className="card-text"><strong>To:</strong> {topic.toDate ? new Date(topic.toDate).toLocaleDateString() : "N/A"}</p>
                <p className="card-text">
                  <strong>Groups:</strong> {topic.groupCount} | <strong>Members per Group:</strong> {topic.membersPerGroup}
                </p>
                <Link to={`/subject-allocations/topics/${topic.id}/professors/${professorId}/subjects/${topic.subjectId}/edit-topic`} className="btn btn-success mt-2">
                  Edit Topic
                </Link>
                <button className="btn btn-danger mt-2" onClick={() => handleDelete(topic.id)}>
                  Delete Topic
                </button>
                <Link className="topics-button mt-2" to={`/teams/create-team/${topic.id}`}>
                  Choose Topic
                </Link>
                <Link className="btn btn-purple mt-2" to={`/teams/topic/${topic.id}`}>
                  View Teams
                </Link>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}

export default TopicsByProfessor;
