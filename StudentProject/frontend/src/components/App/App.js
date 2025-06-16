import React, {Component} from "react";
import {BrowserRouter as Router, Route, Routes, Navigate} from "react-router-dom";
import Header from "../Header/header";
import AppService from "../../repository/appRepository";

import Login from "../Login/login";
import Register from "../Register/register"

import Subject from "../Subject/SubjectList/subject"
import Professors from "../Professor/ProfessorList/professor"
import Topic from "../Topic/TopicList/topic"
import Student from "../Student/StudentList/student"

import TopicAdd from "../Topic/TopicAdd/topicAdd";
import TopicEdit from "../Topic/TopicEdit/topicEdit";
import ProfessorSubjectsPage from "../ProfessorSubjects/ProfessorSubjectsPage";
import ProfessorTopics from "../ProfessorTopic/ProfessorTopic";
import SubjectTopicPage from "../SubjectTopic/SubjectTopicPage";
import CreateTeam from "../Team/CreateTeam";
import StudentAdd from "../Student/StudentAdd/studentAdd";
import TeamsByTopic from "../Team/TeamsByTopic";

class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            professors: [],
            students: [],
            subjects: [],
            topics: [],
            selectedProfessor: null,
            selectedStudent: null,
            selectedSubject: null,
            selectedTopic: null,
        };
    }

    componentDidMount() {
        this.fetchData();
    }

    fetchData = () => {
        this.loadProfessors();
        this.loadStudents();
        this.loadSubjects();
        this.loadTopics();
    };

    // ====== PROFESSORS ======//
    loadProfessors = () => {
        AppService.fetchProfessors()
            .then((data) => {
                this.setState({professors: data.data});
            })
            .catch((error) => console.error("Error fetching professors:", error));
    };

    // ====== STUDENTS ======
    loadStudents = () => {

        AppService.fetchStudents()
            .then((data) => {
                this.setState({students: data.data});
            })
            .catch((error) => console.error("Error fetching students:", error));
    };
    addStudent = (index, name, lastname, username, email) => {
        AppService.addStudent(index, name, lastname, username, email)
            .then(() => {
                this.loadStudents();
            })
            .catch((error) => console.error("Error adding student:", error));
    };

    // ====== SUBJECTS ======
    loadSubjects = () => {
        AppService.fetchSubjects()
            .then((data) => {
                this.setState({subjects: data.data});
            })
            .catch((error) => console.error("Error fetching subjects:", error));
    };

    // ====== TOPICS ======
    loadTopics = () => {
        AppService.fetchTopics()
            .then((data) => {
                this.setState({topics: data.data});
            })
            .catch((error) => console.error("Error fetching topics:", error));
    };

    getTopic = (id) => {
        AppService.getTopic(id)
            .then((data) => {
                this.setState({
                    selectedTopic: data.data,
                });
            })
            .catch((error) => console.error("Error fetching topic:", error));
    };

    addTopic = (name, description, fromDate, toDate, groupCount, membersPerGroup, professorId, subjectId) => {
        AppService.addTopic(name, description, fromDate, toDate, groupCount, membersPerGroup, professorId, subjectId)
            .then(() => {
                this.loadTopics();
            })
            .catch((error) => console.error("Error adding topic:", error));
    };


    updateTopic = (id, name, description, fromDate, toDate, groupCount, membersPerGroup, professorId, subjectId) => {
        AppService.updateTopic(id, name, description, fromDate, toDate, groupCount, membersPerGroup, professorId, subjectId)
            .then(() => {
                this.loadTopics();
            })
            .catch((error) => console.error("Error updating topic:", error));
    };

    deleteTopic = (id) => {
        AppService.deleteTopic(id)
            .then(() => {
                this.loadTopics();
            })
            .catch((error) => console.error("Error deleting topic:", error));
    };

    render() {
        return (
            <Router>
                <Header/>
                <main>
                    <div>
                        <Routes>
                            {/*PROFESSORS*/}
                            <Route
                                path="/professors"
                                element={
                                    <Professors
                                        professors={this.state.professors}
                                    />
                                }
                            />

                            {/*STUDENTS*/}
                            <Route
                                path="/students"
                                element={
                                    <Student
                                        students={this.state.students}
                                    />
                                }
                            />
                            <Route
                                path="/students/add-student"
                                element={
                                    <StudentAdd
                                        onAddStudent={this.addStudent}
                                    />
                                }
                            />

                            {/*SUBJECTS*/}
                            <Route
                                path="/subjects"
                                element={
                                    <Subject
                                        subjects={this.state.subjects}
                                    />
                                }
                            />

                            {/*TOPICS*/}
                            <Route
                                path="/topics"
                                element={
                                    <Topic
                                        topics={this.state.topics}
                                        onEdit={this.getTopic}
                                        onDelete={this.deleteTopic}
                                    />
                                }
                            />
                            {/*<Route*/}
                            {/*    path="/topics/add-topic"*/}
                            {/*    element={*/}
                            {/*        <TopicAdd*/}
                            {/*            onAddTopic={this.addTopic}*/}
                            {/*        />*/}
                            {/*    }*/}
                            {/*/>*/}
                            {/*<Route*/}
                            {/*    path="/topics/edit-topic/:id"*/}
                            {/*    element={*/}
                            {/*        <TopicEdit*/}
                            {/*            topics={this.state.topics}*/}
                            {/*            onEditTopic={this.updateTopic}*/}
                            {/*        />*/}
                            {/*    }*/}
                            {/*/>*/}
                            <Route
                                path="/allocations/:professorId/subjects"
                                element={<ProfessorSubjectsPage/>}/>
                            <Route
                                path="/allocations/professors/:professorId/topics"
                                element={<ProfessorTopics/>}/>

                            <Route path="/allocations/subjects/:subjectId/topics"
                                   element={<SubjectTopicPage/>}/>

                            <Route
                                path="/allocations/:professorId/subjects/:subjectId/topics/add-topic"
                                element={<TopicAdd onAddTopic={this.addTopic}/>}/>

                            <Route
                                path="allocations/topics/:id/professors/:professorId/subjects/:subjectId/edit-topic"
                                element={<TopicEdit topics={this.state.topics} onEditTopic={this.updateTopic}/>}/>

                            <Route
                                path="/teams/create-team/:topicId"
                                element={<CreateTeam/>}/>

                            <Route path="/teams/topic/:topicId" element={<TeamsByTopic />} />


                            {/*PATHS*/}
                            <Route path="/" element={<Navigate to="/subjects"/>}/>
                            <Route path="/login" element={<Login/>}/>
                            <Route path="/register" element={<Register/>}/>
                        </Routes>
                    </div>
                </main>
            </Router>
        );
    }
}

export default App;

// TODO ako ti treba, ako ne izbrishi go Angela,
//  kaj kartichkata za profesor ima kopche, pa tamu da se listaat predmetite po profesor
// import React from "react";
// import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
// import ProfessorSubjectsPage from "../ProfessorSubjects/ProfessorSubjectsPage";
//
// function App() {
//   return (
//     <Router>
//       <Routes>
//         <Route path="/professors/:professorId/subjects" element={<ProfessorSubjectsPage />} />
//       </Routes>
//     </Router>
//   );
// }
//
// export default App;
