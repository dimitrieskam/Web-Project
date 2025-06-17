package org.example.repository;
import org.example.model.JoinedSubject;
import org.example.model.Professor;
import org.example.model.TeacherSubjectAllocation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface TeacherSubjectAllocationRepository extends JpaSpecificationRepository<TeacherSubjectAllocation, Long>{
    List<TeacherSubjectAllocation> findBySubject(JoinedSubject joinedSubject, Sort sort);

    List<TeacherSubjectAllocation> findBySemesterCode(String semesterCode);

    List<TeacherSubjectAllocation> findByProfessorId(String professorId);

    List<TeacherSubjectAllocation> findByProfessorIdAndSemesterCode(String professorId, String semesterCode);

    Page<TeacherSubjectAllocation> findAll(Specification<TeacherSubjectAllocation> filter, Pageable page);

    @Modifying
    @Transactional
    void deleteBySemesterCode(String semesterCode);

    @Query("SELECT DISTINCT p FROM TeacherSubjectAllocation t " +
            "JOIN Professor p ON t.professor.id = p.id " +
            "WHERE t.subject.abbreviation = :subjectAbbreviation " +
            "AND t.semesterCode = :semesterCode ")
    List<Professor> findProfessorsBySubjectAndSemester(@Param("subjectAbbreviation") String subjectAbbreviation, @Param("semesterCode") String semesterCode);
}
