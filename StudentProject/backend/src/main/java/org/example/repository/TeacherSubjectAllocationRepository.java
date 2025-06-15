package org.example.repository;
import org.example.model.JoinedSubject;
import org.example.model.Professor;
import org.example.model.Semester;
import org.example.model.TeacherSubjectAllocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.example.repository.JpaSpecificationRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
@Repository
public interface TeacherSubjectAllocationRepository extends JpaSpecificationRepository<TeacherSubjectAllocation, Long>{
    List<TeacherSubjectAllocation> findBySubject(JoinedSubject joinedSubject, Sort sort);

    List<TeacherSubjectAllocation> findBySemesterCode(String semesterCode);

    List<TeacherSubjectAllocation> findTeacherSubjectAllocationsBySemester(Semester semester);

    Page<TeacherSubjectAllocation> findAll(Specification<TeacherSubjectAllocation> filter, Pageable page);

    @Modifying
    void deleteBySemesterCode(String semesterCode);

    @Query("select sum(tsa.numberOfLectureGroups) from TeacherSubjectAllocation tsa where tsa.subject.abbreviation = ?1 and tsa.semester.code=?2")
    Float getCoveredLectureGroupsInSemester(@Param("subject") String subject, @Param("semester") String semester);

    @Query("select sum(tsa.numberOfExerciseGroups) from TeacherSubjectAllocation tsa where tsa.subject.abbreviation = ?1 and tsa.semester.code=?2")
    Float getCoveredExerciseGroupsInSemester(@Param("subject") String subject, @Param("semester") String semester);

    @Query("select sum(tsa.numberOfLabGroups) from TeacherSubjectAllocation tsa where tsa.subject.abbreviation = ?1 and tsa.semester.code=?2")
    Float getCoveredLabGroupsInSemester(@Param("subject") String subject, @Param("semester") String semester);

    List<TeacherSubjectAllocation> findBySemesterCodeAndSubjectAbbreviation(String code, String abbreviation);

    List<TeacherSubjectAllocation> findBySemesterCodeAndSubjectAbbreviationOrderByProfessorOrderingRank(String code, String abbreviation);

    List<TeacherSubjectAllocation> findByProfessorId(String professorId);

    List<TeacherSubjectAllocation> findByProfessorIdAndSemesterCode(String professorId, String semesterCode);

    @Query("SELECT DISTINCT t.professor FROM TeacherSubjectAllocation t WHERE t.numberOfLectureGroups > 0 OR t.numberOfLabGroups > 0")
    List<Professor> findProfessorsWithAllocations();

    @Query("SELECT DISTINCT t.professor FROM TeacherSubjectAllocation t WHERE t.subject.abbreviation = :subjectAbbreviation AND (t.numberOfLectureGroups > 0 OR t.numberOfLabGroups > 0)")
    List<TeacherSubjectAllocation> findProfessorsBySubject(@Param("subjectAbbreviation") String subjectAbbreviation);

    @Query("SELECT DISTINCT p FROM TeacherSubjectAllocation t " +
            "JOIN Professor p ON t.professor.id = p.id " +
            "WHERE t.subject.abbreviation = :subjectAbbreviation " +
            "AND t.semesterCode = :semesterCode " +
            "AND (t.numberOfLectureGroups > 0 OR t.numberOfExerciseGroups > 0)")
    List<Professor> findProfessorsBySubjectAndSemester(@Param("subjectAbbreviation") String subjectAbbreviation,
                                                       @Param("semesterCode") String semesterCode);


}

