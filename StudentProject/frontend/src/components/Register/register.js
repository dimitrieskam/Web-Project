import React, { useState } from 'react';
import authService from '../../repository/Authentication/auth_service';
 // Check path carefully
import { useNavigate } from 'react-router-dom';

const Register = () => {
    const [formData, setFormData] = useState({
        name: '',
        surname: '',
        username: '',
        password: '',
        repeatedPassword: '',
        role: 'ROLE_STUDENT',
    });

    const [error, setError] = useState(null);
    const navigate = useNavigate();

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();

        const { name, surname, username, password, repeatedPassword, role } = formData;

        if (password !== repeatedPassword) {
            setError("Passwords do not match");
            return;
        }

        setError(null);

        authService.register(name, surname, username, password, role)
            .then((response) => {
                console.log("Registration successful", response);

                // Redirect after registration
                navigate("/login");
            })
            .catch((error) => {
                console.error("Registration error:", error);
                if (error.response && error.response.data) {
                    setError(error.response.data.message || "Registration failed");
                } else {
                    setError("Registration failed. Please try again.");
                }
            });
    };

    return (
        <div className="container mt-5">
            <h2>Register</h2>
            {error && <div className="alert alert-danger">{error}</div>}
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label>Name</label>
                    <input
                        type="text"
                        name="name"
                        className="form-control"
                        value={formData.name}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label>Surname</label>
                    <input
                        type="text"
                        name="surname"
                        className="form-control"
                        value={formData.surname}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label>Username</label>
                    <input
                        type="text"
                        name="username"
                        className="form-control"
                        value={formData.username}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label>Password</label>
                    <input
                        type="password"
                        name="password"
                        className="form-control"
                        value={formData.password}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label>Repeat Password</label>
                    <input
                        type="password"
                        name="repeatedPassword"
                        className="form-control"
                        value={formData.repeatedPassword}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label>Role</label>
                    <select
                        name="role"
                        className="form-control"
                        value={formData.role}
                        onChange={handleChange}
                    >
                        <option value="ROLE_STUDENT">Student</option>
                        <option value="ROLE_ADMIN">Admin</option>
                        <option value="ROLE_PROFESSOR">Professor</option>
                    </select>
                </div>
                <button type="submit" className="btn btn-primary mt-3">
                    Register
                </button>
            </form>
        </div>
    );
};

export default Register;
