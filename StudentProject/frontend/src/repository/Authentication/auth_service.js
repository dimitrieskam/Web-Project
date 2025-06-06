import axios from '../../custom-axios/axios';
import { redirect } from "react-router-dom";

const API_URL = "/";

const register = (name, surname, username, password, role) => {
    return axios.post(API_URL + "register", {
        name,
        surname,
        username,
        password,
        role,
    }).then((response) => {
        if (response.data.access_token) {
            localStorage.setItem("user", JSON.stringify(response.data));
        }
        return response.data;
    });
};

const login = (username, password) => {
    return axios.post(API_URL + "login", {
        username,
        password,
    }).then((response) => {
        if (response.data.access_token) {
            localStorage.setItem("user", JSON.stringify(response.data));
            window.location.reload();
        }
        return response.data;
    });
};

const logout = () => {
    localStorage.removeItem("user");
    window.location.reload();
};

const getCurrentUser = () => {
    return localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : null;
};

const authService = {
    register,
    login,
    logout,
    getCurrentUser,
};

export default authService;
