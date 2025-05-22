import React from 'react';
import {Link} from 'react-router-dom';

const ProfessorTerm = (props) => {
    const handleDelete = () => {
        if (window.confirm('Are you sure you want to delete this professor?')) {
            props.onDelete(props.term.id);
        }
    };
    return (
        <tr>
            <td>{props.term.name}</td>
            <td>{props.term.surname}</td>
            <td>{props.term.email}</td>
            <td className={"text-right"}>
                <a title={"Delete"} className={"btn btn-danger"}
                   onClick={() => props.onDelete(props.term.id)}>
                    Delete
                </a>
                <Link className={"btn btn-info ml-2"}
                      onClick={() => props.onEdit(props.term.id)}
                      to={`/professors/edit-professor/${props.term.id}`}>
                    Edit
                </Link>
            </td>
        </tr>
    )
}

export default ProfessorTerm;
