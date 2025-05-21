import React, { Component } from "react";
import { Link, useNavigate } from "react-router-dom";
import AppService from "../../repository/appRepository";

// Using a HOC pattern to add navigation functionality
const withNavigate = (Component) => {
    return (props) => {
        const navigate = useNavigate();
        return <Component {...props} navigate={navigate} />;
    };
};

class Register extends Component {
    constructor(props) {
        super(props);
        this.state = {
            username: "",
            password: "",
            repeatedPassword: "",
            email: "",
            error: null,
        };
    }

    handleChange = (e) => {
        this.setState({
            [e.target.name]: e.target.value,
        });
    };

    handleSubmit = (e) => {
        e.preventDefault();
        const { username, password, repeatedPassword, email } = this.state;

        // Basic validation
        if (password !== repeatedPassword) {
            this.setState({ error: "Passwords do not match" });
            return;
        }

        // Clear any previous errors
        this.setState({ error: null });

        // Call registration API
        AppService.register(username, password, email)
            .then((response) => {
                // Handle successful registration
                console.log("Registration successful", response);

                // If the API returns a token directly
                if (response.data && response.data.token) {
                    // Call the onRegisterSuccess function passed from App.js
                    if (this.props.onRegisterSuccess) {
                        this.props.onRegisterSuccess(response.data.token);
                    }
                    // Navigate to home/login page
                    this.props.navigate("/");
                } else {
                    // If registration is successful but login is required separately
                    this.props.navigate("/login");
                }
            })
            .catch((error) => {
                console.error("Registration error:", error);
                // Set error message from API response if available
                if (error.response && error.response.data) {
                    this.setState({ error: error.response.data.message || "Registration failed" });
                } else {
                    this.setState({ error: "Registration failed. Please try again." });
                }
            });
    };

    render() {
        return (
            <div className="container mt-5">
                <div className="row justify-content-center">
                    <div className="col-md-6">
                        <div className="card">
                            <div className="card-header">Register</div>
                            <div className="card-body">
                                {this.state.error && (
                                    <div className="alert alert-danger" role="alert">
                                        {this.state.error}
                                    </div>
                                )}
                                <form onSubmit={this.handleSubmit}>
                                    <div className="form-group mb-3">
                                        <label htmlFor="username">Username</label>
                                        <input
                                            type="text"
                                            className="form-control"
                                            id="username"
                                            name="username"
                                            value={this.state.username}
                                            onChange={this.handleChange}
                                            required
                                        />
                                    </div>
                                    <div className="form-group mb-3">
                                        <label htmlFor="email">Email</label>
                                        <input
                                            type="email"
                                            className="form-control"
                                            id="email"
                                            name="email"
                                            value={this.state.email}
                                            onChange={this.handleChange}
                                            required
                                        />
                                    </div>
                                    <div className="form-group mb-3">
                                        <label htmlFor="password">Password</label>
                                        <input
                                            type="password"
                                            className="form-control"
                                            id="password"
                                            name="password"
                                            value={this.state.password}
                                            onChange={this.handleChange}
                                            required
                                        />
                                    </div>
                                    <div className="form-group mb-3">
                                        <label htmlFor="repeatedPassword">Confirm Password</label>
                                        <input
                                            type="password"
                                            className="form-control"
                                            id="repeatedPassword"
                                            name="repeatedPassword"
                                            value={this.state.repeatedPassword}
                                            onChange={this.handleChange}
                                            required
                                        />
                                    </div>
                                    <button type="submit" className="btn btn-primary">
                                        Register
                                    </button>
                                    <div className="mt-3">
                                        Already have an account? <Link to="/login">Login</Link>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default withNavigate(Register);