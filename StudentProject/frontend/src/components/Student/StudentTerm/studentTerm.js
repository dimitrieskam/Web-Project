import React from 'react';
import { Link } from 'react-router-dom';
import authService from "../../../repository/Authentication/auth_service";

const StudentTerm = (props) => {
    const role = authService.getCurrentUser()?.role;
    console.log("User role:", role);

    return (
        <tr>
            <td>{props.term.index}</td>
            <td>{props.term.name}</td>
            <td>{props.term.lastname}</td>
            <td>{props.term.username}</td>
            <td>{props.term.email}</td>

            <td className="text-right">
                {role === "ROLE_ADMIN" && (
                    <>
                        <button
                            className="btn btn-danger"
                            title="Delete"
                            onClick={() => props.onDelete(props.term.index)}
                        >
                            Delete
                        </button>
                        <Link
                            className="btn btn-info ml-2"
                            to={`/students/edit-student/${props.term.index}`}
                            onClick={() => props.onEdit(props.term.index)}
                        >
                            Edit
                        </Link>
                    </>
                )}
            </td>
        </tr>
    );
};

export default StudentTerm;
