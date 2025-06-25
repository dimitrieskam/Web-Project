//package org.example.repository;
//
//import org.example.model.*;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface StudentSubjectEnrollmentRepository extends JpaSpecificationRepository<StudentSubjectEnrollment, String> {
//    List<StudentSubjectEnrollment> findByStudentAndSubjectAndSemester(Student student, Subject subject, Semester semester);
//    List<StudentSubjectEnrollment> findByStudent(Student student);
//    List<StudentSubjectEnrollment> findBySubject(Subject subject);
//    List<StudentSubjectEnrollment> findBySemester(Semester semester);
//    List<StudentSubjectEnrollment> findByStudentAndSubject(Student student, Subject subject);
//    List<StudentSubjectEnrollment> findBySubjectAndSemester(Subject subject, Semester semester);
//;
//
//    List<StudentSubjectEnrollment> findByJoinedSubjectAndSemesterOrderByStudentName(JoinedSubject subject, Semester semester);
//
//    Long countBySubjectIdInAndSemesterCodeAndNumEnrollmentsBetween(List<String> codes, String semesterCode, int enrolmentsFrom, int enrollmentsTo);
//
//    @Modifying
//    @Query(value = "update student_subject_enrollment sse\n" +
//            "set joined_subject_abbreviation=(select js.abbreviation\n" +
//            "                                 from joined_subject js\n" +
//            "                                 where js.codes like '%' || sse.subject_id || '%'" +
//            "                                 limit 1)\n" +
//            "where sse.semester_code = ?1", nativeQuery = true)
//    void updateJoinedSubjects(@Param("semesterCode") String semesterCode);
//
//    @Modifying
//    @Query(value = "update student_subject_enrollment \n" +
//            "set professor_id=null\n" +
//            "where semester_code = ?1", nativeQuery = true)
//    void resetProfessorsInSemester(@Param("semesterCode") String semesterCode);
//
//
//
//
//}
