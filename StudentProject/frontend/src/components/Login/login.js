import React, { useState } from 'react';
import authService from '../../repository/Authentication/auth_service';
import { useNavigate } from 'react-router-dom';
import './login.css';

const Login = () => {
    const [formData, setFormData] = useState({
        username: '',
        password: '',
    });

    const navigate = useNavigate();

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        authService.login(formData.username, formData.password)
            .then(() => {
                navigate('/subjects');
            })
            .catch((error) => {
                console.error('Error logging in:', error);
                alert('Login failed. Please check your credentials and try again.');
            });
    };

    return (
        <div className="container mt-5">
            <h2 className={"login-title"}>Login</h2>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label>Username</label>
                    <input type="text" name="username" className="form-control" value={formData.username} onChange={handleChange} required />
                </div>
                <div className="form-group">
                    <label>Password</label>
                    <input type="password" name="password" className="form-control" value={formData.password} onChange={handleChange} required />
                </div>
                <button type="submit" className="btn btn-primary login-btn">Login!</button>
            </form>
        </div>
    );
};

export default Login;
