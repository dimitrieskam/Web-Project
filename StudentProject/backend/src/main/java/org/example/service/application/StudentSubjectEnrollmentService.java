//package org.example.service.application;
//
//import jakarta.transaction.Transactional;
//import org.example.model.*;
//import org.example.model.DTOs.subjectEnrollmentDTO.SubjectEnrollmentDTO;
//import org.springframework.data.domain.Page;
//
//import java.util.List;
//
//public interface StudentSubjectEnrollmentService {
//    List<SubjectEnrollmentDTO> importStudentSubjects(List<SubjectEnrollmentDTO> importEnrollments, String semester);
//    List<StudentSubjectEnrollment> getEnrollmentsByStudentAndSubjectAndSemester(Student student, Subject subject, Semester semester);
//    void deleteBySemester(String semester);
//    void allocateSubjects(String semesterCode);
//    @Transactional
//    void allocateEnrollmentProfessors(String semesterCode);
//    @Transactional
//    void allocateProfessor(List<Course> subjectCourses);
//}
