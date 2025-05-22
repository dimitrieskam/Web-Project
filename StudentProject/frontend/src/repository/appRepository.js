import axios from '../custom-axios/axios';
import authHeader from './Authentication/header/auth_header';


const AppRepository = {

    // ====== Professors ======
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


    // ====== Students ======
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


    // ====== Subjects ======
    fetchSubjects: () => {
        return axios.get("/subjects");
    },
    addSubject: (name, code, studentIds, professorIds) => {
        return axios.post("/subjects/add-subject", {
            "name": name,
            "code": code,
            "studentIds": studentIds,
            "professorIds": professorIds
        });
    },
    updateSubject: (id, name, code, studentIds, professorIds) => {
        return axios.put(`/subjects/edit-subject/${id}`, {
           "name": name,
            "code": code,
            "studentIds": studentIds,
            "professorIds": professorIds
        });
    },
    deleteSubject: (id) => {
        return axios.delete(`/subjects/delete-subject/${id}`);
    },

    // ====== Topics ======
    fetchTopics: () => {
      return axios.get("/topics");
    },
  
    addTopic: (name, fromDate, toDate, groupCount, membersPerGroup, subjectId) => {
      return axios.post("/topics/add-topic", {
        "name": name,
        "fromDate": fromDate,
        "toDate": toDate,
        "groupCount": groupCount,
        "membersPerGroup": membersPerGroup,
        "subjectId": subjectId
      });
    },
  
    updateTopic: (id, name, fromDate, toDate, groupCount, membersPerGroup, subjectId) => {
      return axios.put(`/topics/edit-topic/${id}`, {
        "name": name,
        "fromDate": fromDate,
        "toDate": toDate,
        "groupCount": groupCount,
        "membersPerGroup": membersPerGroup,
        "subjectId": subjectId
      });
    },
  
    deleteTopic: (id) => {
      return axios.delete(`/topics/delete-topic/${id}`);
    }
  };
  

export default AppRepository;
