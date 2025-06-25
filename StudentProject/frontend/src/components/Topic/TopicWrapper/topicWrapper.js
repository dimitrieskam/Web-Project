import React from 'react';
import { useParams } from 'react-router-dom';
import Topic from '../TopicList/topic';

const TopicWrapper = (props) => {
    const { professorId, subjectId } = useParams();
    return <Topic {...props} professorId={professorId} subjectId={subjectId} />;
};

export default TopicWrapper;
