import axios from '../../custom-axios/axios';

const API_URL = "/auth/";

const register = (name, surname, username, password, role) => {
    return axios.post(API_URL + "register", {
        name,
        surname,
        username,
        password,
        role,
    }).then((response) => {
        if (response.data.access_token) {
            // Extract user info and role safely
            const userData = {
                access_token: response.data.access_token,
                username: response.data.user?.username || username,
                role: response.data.user?.role || role || null,
                // add any other user fields you need here
            };
            localStorage.setItem("user", JSON.stringify(userData));
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
            const userData = {
                access_token: response.data.access_token,
                username: response.data.user?.username || username,
                role: response.data.user?.role || null,
                // add other fields if needed
            };
            localStorage.setItem("user", JSON.stringify(userData));
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
