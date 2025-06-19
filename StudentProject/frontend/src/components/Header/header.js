import React, { useState, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import authService from '../../repository/Authentication/auth_service';
import finkilogo from '../../images/finki-logo.png';
import './header.css';

const Header = () => {
    const [currentUser, setCurrentUser] = useState(authService.getCurrentUser());
    const navigate = useNavigate();

    useEffect(() => {
        const unsubscribe = authService.subscribe((user) => {
            setCurrentUser(user);
        });

        return () => unsubscribe();
    }, []);

    const handleLogout = () => {
        authService.logout();
        navigate("/login");
    };

    return (
        <header>
            <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
                <div className="container-fluid">
                    <a className="navbar-brand" href="/">
                        <img src={finkilogo} alt="Finki Logo" style={{ width: '40px', height: '40px', marginRight: '10px' }} />
                        Projects Management System
                    </a>
                    <div className="collapse navbar-collapse" id="navbarNav">
                        <ul className="navbar-nav me-auto mb-2 mb-lg-0 nav-tabs">
                            <li className="nav-item"><Link className="nav-link" to="/subjects">Subjects</Link></li>
                            <li className="nav-item"><Link className="nav-link" to="/professors">Professors</Link></li>
                            <li className="nav-item"><Link className="nav-link" to="/students">Students</Link></li>
                            <li className="nav-item"><Link className="nav-link" to="/topics">Topics</Link></li>
                        </ul>
                        <ul className="navbar-nav ms-auto me-3 nav-tabs">
                            {!currentUser ? (
                                <>
                                    <li className="nav-item"><Link className="nav-link" to="/register">Register</Link></li>
                                    <li className="nav-item"><Link className="nav-link" to="/login">Login</Link></li>
                                </>
                            ) : (
                                <li className="nav-item"><button className="nav-link btn btn-link" onClick={handleLogout}>Logout</button></li>
                            )}
                        </ul>
                    </div>
                </div>
            </nav>
        </header>
    );
};

export default Header;
