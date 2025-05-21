import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import authService from '../../repository/Authentication/auth_service';

const Header = () => {
    const [currentUser, setCurrentUser] = useState(null);

    useEffect(() => {
        const user = authService.getCurrentUser();
        if (user) {
            setCurrentUser(user);
        }
    }, []);

    const logout = () => {
        authService.logout();
        setCurrentUser(null);
        window.location.href = "/login"; 
    };

    return (
        <header>
            <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
                <div className="container-fluid">
                    <a className="navbar-brand" href="/">Projects Management System</a>
                    <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                        aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                        <span className="navbar-toggler-icon" />
                    </button>
                    <div className="collapse navbar-collapse" id="navbarNav">
                        <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                            <li className="nav-item">
                                <Link className="nav-link" to="/students">Students</Link>
                            </li>
                            <li className="nav-item">
                                <Link className="nav-link" to="/professors">Professors</Link>
                            </li>
                            <li className="nav-item">
                                <Link className="nav-link" to="/subjects">Subjects</Link>
                            </li>
                            <li className="nav-item">
                                <Link className="nav-link" to="/topics">Topics</Link>
                            </li>
                        </ul>
                        <ul className="navbar-nav ms-auto me-3">
                            {!currentUser && (
                                <>
                                    <li className="nav-item">
                                        <a className="nav-link" href="/register">Register</a>
                                    </li>
                                    <li className="nav-item">
                                        <a className="nav-link" href="/login">Login</a>
                                    </li>
                                </>
                            )}
                            {currentUser && (
                                <li className="nav-item">
                                    <a className="nav-link" href="/home" onClick={logout}>Logout</a>
                                </li>
                            )}
                        </ul>
                    </div>
                </div>
            </nav>
        </header>
    );
};

export default Header;
