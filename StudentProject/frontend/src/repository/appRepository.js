import axios from '../custom-axios/axios';

const AppRepository = {

    // ====== PROFESSORS ======
    fetchProfessors: () => {
        return axios.get("/professors");
    },
    addProfessor: (name, surname, email) => {
        return axios.post("/professors/add-professor", {
            "name": name,
            "surname": surname,
            "email": email
        });
    },
    updateProfessor: (id, name, surname, email) => {
        return axios.put(`/professors/edit-professor/${id}`, {
            "name": name,
            "surname": surname,
            "email": email
        });
    },
    deleteProfessor: (id) => {
        return axios.delete(`/professors/delete-professor/${id}`);
    },


    // ====== STUDENTS ======
    fetchStudents: () => {
        return axios.get("/students");
    },
    addStudent: (name, surname, index, email) => {
        return axios.post("/students/add-student", {
            "name": name,
            "surname": surname,
            "index": index,
            "email": email
        });
    },
    updateStudent: (id, name, surname, index, email) => {
        return axios.put(`/students/edit-student/${id}`, {
            "name": name,
            "surname": surname,
            "index": index,
            "email": email
        });
    },
    deleteStudent: (id) => {
        return axios.delete(`/students/delete-student/${id}`);
    },


    // ====== SUBJECTS ======
    fetchSubjects: () => {
        return axios.get("/subjects");
    },

    // ====== TOPICS ======
    fetchTopics: () => {
        return axios.get("/allocations/topics");
    },

    addTopic: (name, description, fromDate, toDate, groupCount, membersPerGroup, professorId, subjectId) => {
        return axios.post(`/allocations/${professorId}/subjects/${subjectId}/topics/add-topic`, {
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
        return axios.put(`allocations/topics/${id}/professors/${professorId}/subjects/${subjectId}/edit-topic`, {
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
        return axios.delete(`allocations/topics/delete-topic/${id}`);
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
