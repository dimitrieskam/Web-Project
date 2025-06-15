package org.example.service.application;
import org.example.model.DTOs.TeacherSubjectAllocationDTO;
import org.example.model.JoinedSubject;
import org.example.model.Professor;
import org.example.model.Semester;
import org.example.model.TeacherSubjectAllocation;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;


import java.util.List;
import java.util.Optional;

import java.util.List;
public interface SubjectAllocationService {
    List<TeacherSubjectAllocation> getAllTeacherSubjectAllocationsBySemester(String semesterCode);

    List<TeacherSubjectAllocation> getAllBySubject(String id);

    void editTeacherSubjectAllocation(TeacherSubjectAllocation editedAllocation);

    TeacherSubjectAllocation addTeacherSubjectAllocation(TeacherSubjectAllocationDTO newAllocation, String semester);

    void deleteTeacherSubjectAllocations(Long id);

    List<TeacherSubjectAllocation> getAllocationsForSubject(JoinedSubject joinedSubject);

    List<TeacherSubjectAllocation> getAllocationsForSubjectInSemester(JoinedSubject joinedSubject, Semester semester);

    void save(TeacherSubjectAllocation allocation);

    Optional<TeacherSubjectAllocation> findById(Long id);

    void delete(Long id);

    void cloneTeacherSubjectAllocations(String semesterFrom, String semesterTo);

    Page<TeacherSubjectAllocation> findAll(Specification<TeacherSubjectAllocation> filter, Integer pageNum, Integer results);

    void deleteBySemester(String semesterCode);

    boolean validateAllocationsForSemester(String semesterCode);

    List<TeacherSubjectAllocation> importFromList(List<TeacherSubjectAllocation> tsa);

    List<TeacherSubjectAllocationDTO> getTeacherSubjectAllocationsByProfessorId(String professorId);

    List<TeacherSubjectAllocation> getTeacherSubjectAllocationsByProfessorIdAndSemester(String professorId, String semesterCode);

    List<Professor> findProfessorsBySubjectAndSemester(String subjectAbbreviation, String semesterCode);


}

