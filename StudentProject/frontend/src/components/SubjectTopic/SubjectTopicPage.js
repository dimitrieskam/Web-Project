import React from "react";
import { useParams } from "react-router-dom";
import SubjectTopic from "../SubjectTopic/SubjectTopic";

function SubjectTopicPage() {
  const { subjectId } = useParams();

  return <SubjectTopic subjectId={subjectId} />;
}

export default SubjectTopicPage;
