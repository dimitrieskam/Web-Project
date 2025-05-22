import React from 'react';
import {Link} from 'react-router-dom';

const SubjectTerm = (props) => {
    const handleDelete = () => {
        if (window.confirm('Are you sure you want to delete this subject?')) {
            props.onDelete(props.term.id);
        }
    };
    return (
        <tr>
            <td>{props.term.name}</td>
            <td>{props.term.code}</td>
            <td>{props.term.student.name}</td>
            <td>{props.term.professor.name}</td>
            <td className={"text-right"}>
                <a title={"Delete"} className={"btn btn-danger"}
                   onClick={() => props.onDelete(props.term.id)}>
                    Delete
                </a>
                <Link className={"btn btn-info ml-2"}
                      onClick={() => props.onEdit(props.term.id)}
                      to={`/subjects/edit-subject/${props.term.id}`}>
                    Edit
                </Link>
            </td>
        </tr>
    )
}

export default SubjectTerm;
