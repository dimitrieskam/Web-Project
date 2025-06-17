import axios from '../custom-axios/axios';

const AppRepository = {

    // ====== PROFESSORS ======
    fetchProfessors: () => {
        return axios.get("/professors");
    },

    // ====== STUDENTS ======
    fetchStudents: () => {
        return axios.get("/students");
    },

    addStudent: (index, name, lastname, username, email) => {
        return axios.post("/students/add-student", {
            "index": index,
            "name": name,
            "lastname": lastname,
            "username": username,
            "email": email
        });
    },
    // TODO
    updateStudent: (id, index, name, lastname, username, email) => {
        return axios.put(`/students/edit-student/${id}`, {
            "index":index,
            "name": name,
            "lastname": lastname,
            "username": username,
            "email": email
        });
    },
    // TODO
    deleteStudent: (id) => {
        return axios.delete(`/students/delete-student/${id}`);
    },

    // ====== SUBJECTS ======
    fetchSubjects: () => {
        return axios.get("/subjects");
    },

    // ====== TOPICS ======
    fetchTopics: () => {
        return axios.get("/subject-allocations/topics");
    },

    addTopic: (name, description, fromDate, toDate, groupCount, membersPerGroup, professorId, subjectId) => {
        return axios.post(`/subject-allocations/${professorId}/subjects/${subjectId}/topics/add-topic`, {
            "name": name,
            "description": description,
            "fromDate": fromDate,
            "toDate": toDate,
            "groupCount": groupCount,
            "membersPerGroup": membersPerGroup,
            "professorId": professorId,
            "subjectId": subjectId
        });
    },

    updateTopic: (id, name, description, fromDate, toDate, groupCount, membersPerGroup, professorId, subjectId) => {
        return axios.put(`subject-allocations/topics/${id}/professors/${professorId}/subjects/${subjectId}/edit-topic`, {
            "name": name,
            "description": description,
            "fromDate": fromDate,
            "toDate": toDate,
            "groupCount": groupCount,
            "membersPerGroup": membersPerGroup,
            "professorId": professorId,
            "subjectId": subjectId
        });
    },

    deleteTopic: (id) => {
        return axios.delete(`subject-allocations/topics/delete-topic/${id}`);
    },

    // ====== TEAMS ======
    createTeam: (topicId, name, studentIds) => {
        return axios.post(`/teams/create-team/${topicId}`, {
            name,
            studentIds
        });
    },
};

export default AppRepository;
