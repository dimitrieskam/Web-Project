import React from "react";
import { useParams } from "react-router-dom";
import ProfessorSubjects from "../ProfessorSubjects/ProfessorSubjects";

function ProfessorSubjectsPage() {
  const { professorId } = useParams();

  return <ProfessorSubjects professorId={professorId} />;
}

export default ProfessorSubjectsPage;
