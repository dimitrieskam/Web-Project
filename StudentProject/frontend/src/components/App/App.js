import React, { Component } from "react";
import { BrowserRouter as Router, Route, Routes, Navigate } from "react-router-dom";
import Header from "../Header/header";
import AppService from "../../repository/appRepository";
import Login from "../Login/login";
import Register from "../Register/register"
import Professors from "../Professor/ProfessorList/professor"
import ProfessorAdd from "../Professor/ProfessorAdd/professorAdd";
import ProfessorEdit from "../Professor/ProfessorEdit/professorEdit";
import Student from "../Student/StudentList/student"
import StudentAdd from "../Student/StudentAdd/studentAdd";
import StudentEdit from "../Student/StudentEdit/studentEdit";
import Subject from "../Subject/SubjectList/subject"
import SubjectAdd from "../Subject/SubjectAdd/subjectAdd";
import SubjectEdit from "../Subject/SubjectEdit/subjectEdit";
import Topic from "../Topic/TopicList/topic"
import TopicAdd from "../Topic/TopicAdd/topicAdd";
import TopicEdit from "../Topic/TopicEdit/topicEdit";



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

  
    //Professors
    loadProfessors = () => {
        AppService.fetchProfessors()
            .then((data) => {
                this.setState({ professors: data.data });
            })
            .catch((error) => console.error("Error fetching professors:", error));
    };
    deleteProfessor = (id) => {
      AppService.deleteProfessor(id)
          .then(() => {
              this.loadProfessors();
          })
          .catch((error) => console.error("Error deleting professor:", error));
  };

  addProfessor = (name, surname, email) => {
      AppService.addProfessor(name, surname, email)
          .then(() => {
              this.loadProfessors();
          })
          .catch((error) => console.error("Error adding professor:", error));
  };

  updateProfessor = (id, name, surname, email) => {
      AppService.updateProfessor(id, name, surname, email)
          .then(() => {
              this.loadProfessors();
          })
          .catch((error) => console.error("Error updating professor:", error));
  };

  getProfessor = (id) => {
      AppService.getProfessor(id)
          .then((data) => {
              this.setState({
                  selectedProfessor: data.data,
              });
          })
          .catch((error) => console.error("Error fetching professor:", error));
  };

  //Students
    loadStudents = () => {
       
        AppService.fetchStudents()
            .then((data) => {
                this.setState({ students: data.data });
            })
            .catch((error) => console.error("Error fetching students:", error));
    };

    deleteStudet = (id) => {
        AppService.deleteStudent(id)
            .then(() => {
                this.loadStudents();
            })
            .catch((error) => console.error("Error deleting student:", error));
    };

    addStudent = (name, surname, index, email) => {
        AppService.addStudent(name, surname, index, email)
            .then(() => {
                this.loadStudents();
            })
            .catch((error) => console.error("Error adding student:", error));
    };

    updateStudent = (id, name, surname, index, email) => {
        AppService.updateStudent(id, name, surname, index, email)
            .then(() => {
                this.loadStudents();
            })
            .catch((error) => console.error("Error updating student:", error));
    };

    getStudent = (id) => {
        AppService.getStudent(id)
            .then((data) => {
                this.setState({
                    selectedStudent: data.data,
                });
            })
            .catch((error) => console.error("Error fetching student:", error));
    };

    //Subjects
    loadSubjects = () => {
        AppService.fetchSubjects()
            .then((data) => {
                this.setState({ subjects: data.data });
            })
            .catch((error) => console.error("Error fetching subjects:", error));
    };
    deleteSubject = (id) => {
        AppService.deleteSubject(id)
            .then(() => {
                this.loadSubjects();
            })
            .catch((error) => console.error("Error deleting subject:", error));
    };

    addSubject = (name, code, students, professors) => {
        AppService.addSubject(name, code, students, professors)
            .then(() => {
                this.loadSubjects();
            })
            .catch((error) => console.error("Error adding subject:", error));
    };

    updateSubject = (id, name, code, students, professors) => {
        AppService.updateSubject(id, name, code, students, professors)
            .then(() => {
                this.loadSubjects();
            })
            .catch((error) => console.error("Error updating subject:", error));
    };

    getSubject = (id) => {
        AppService.getSubject(id)
            .then((data) => {
                this.setState({
                    selectedSubject: data.data,
                });
            })
            .catch((error) => console.error("Error fetching subject:", error));
    };

    //Topics
    loadTopics = () => {
      AppService.fetchTopics()
          .then((data) => {
              this.setState({ topics: data.data });
          })
          .catch((error) => console.error("Error fetching topics:", error));
  };
    
    deleteTopic = (id) => {
        AppService.deleteTopic(id)
            .then(() => {
                this.loadTopics();
            })
            .catch((error) => console.error("Error deleting topic:", error));
    };

    addTopic = (name, fromDate, toDate, groupCount, membersPerGroup, subjectId) => {
        AppService.addTopic(name, fromDate, toDate, groupCount, membersPerGroup, subjectId)
            .then(() => {
                this.loadTopics();
            })
            .catch((error) => console.error("Error adding topic:", error));
    };

    updateTopic = (id,name, fromDate, toDate, groupCount, membersPerGroup, subjectId) => {
        AppService.updateTopic(id, name, fromDate, toDate, groupCount, membersPerGroup, subjectId)
            .then(() => {
                this.loadTopics();
            })
            .catch((error) => console.error("Error updating topic:", error));
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

    
    render() {
        return (

            <Router>


                <Header />
                <main>
                    <div >
                        <Routes>
                            <Route
                                path="/professors"
                                element={<Professors
                                    professors={this.state.professors}
                                    onDelete={this.deleteProfessor}
                                    onEdit={this.getProfessor}/>}
                            />
                            <Route
                                path="/professors/add-professor"
                                element={<ProfessorAdd onAddProfessor={this.addProfessor} />}
                            />
                            <Route
                                path="/professors/edit-professor/:id"
                                element={
                                    <ProfessorEdit
                                        onEditProfessor={this.updateProfessor}
                                        professor={this.state.selectedProfessor}
                                    />
                                }
                            />

                            <Route path="/students" element={<Student
                                students={this.state.students}
                                onDelete={this.deleteStudet} 
                                onEdit={this.getStudent}   
                            />} />
                            <Route
                                path="/students/add-student"
                                element={<StudentAdd onAddStudent={this.addStudent} />}
                            />
                            <Route
                                path="/students/edit-student/:id"
                                element={
                                    <StudentEdit
                                        onEditStudent={this.updateStudent}
                                        student={this.state.selectedStudent}
                                    />
                                }
                            />

                            <Route path="/subjects" element={<Subject
                                subjects={this.state.subjects}
                                onEdit={this.getSubject}
                                onDelete={this.deleteSubject}
                                />}
                                />
                            <Route
                                path="/subjects/add-subject"
                                element={<SubjectAdd subjects={this.state.subjects} onAddSubject={this.addSubject} />}
                            />
                            <Route
                                path="/subjects/edit-subject/:id"
                                element={
                                    <SubjectEdit
                                        subjects={this.state.subjects}
                                        onEditSubject={this.updateSubject} 
                                    />
                                }
                            />
                            <Route path="/topics" element={<Topic
                                topics={this.state.topics}
                                onEdit={this.getTopic}
                                onDelete={this.deleteTopic}
                            />} />
                            <Route
                                path="/topics/add-topic"
                                element={<TopicAdd onAddTopic={this.addTopic} />}
                            />
                            <Route
                                path="/topics/edit-topic/:id"
                                element={
                                    <TopicEdit
                                        topics={this.state.topics}
                                        onEditTopic={this.updateTopic}
                                    />
                                }
                            />

                            <Route path="/" element={<Navigate to="/professors" />} />
                            <Route path="/login" element={<Login />} />
                            <Route path="/register" element={<Register />} />


                        </Routes>
                    </div>
                </main>

            </Router>
        );
    }
}

export default App;