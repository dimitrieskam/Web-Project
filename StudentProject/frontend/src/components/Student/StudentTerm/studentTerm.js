import React from 'react';
import {Link} from 'react-router-dom';

const StudentTerm = (props) => {
    const handleDelete = () => {
        if (window.confirm('Are you sure you want to delete this student?')) {
            props.onDelete(props.term.id);
        }
    };
    return (
        <tr>
            <td>{props.term.name}</td>
            <td>{props.term.surname}</td>
            <td>{props.term.index}</td>
            <td>{props.term.email}</td>
            <td className={"text-right"}>
                <a title={"Delete"} className={"btn btn-danger"}
                   onClick={() => props.onDelete(props.term.id)}>
                    Delete
                </a>
                <Link className={"btn btn-info ml-2"}
                      onClick={() => props.onEdit(props.term.id)}
                      to={`/students/edit-student/${props.term.id}`}>
                    Edit
                </Link>
            </td>
        </tr>
    )
}

export default StudentTerm;
