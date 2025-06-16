import React from "react";
import { useParams } from "react-router-dom";
import ProfessorTopics from "../ProfessorTopic/ProfessorTopics";

function ProfessorTopicPage() {
  const { professorId } = useParams();

  return <ProfessorTopics professorId={professorId} />;
}

export default ProfessorTopicPage;
