package org.example.service.application.Impl;

import org.example.model.DTOs.TeacherSubjectAllocationDTO;
import org.example.model.JoinedSubject;
import org.example.model.Professor;
import org.example.model.Semester;
import org.example.model.TeacherSubjectAllocation;
import org.example.service.application.SubjectAllocationService;
import org.example.repository.TeacherSubjectAllocationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectAllocationServiceImpl implements SubjectAllocationService {
    private final TeacherSubjectAllocationRepository allocationRepository;

    public SubjectAllocationServiceImpl(TeacherSubjectAllocationRepository allocationRepository) {
        this.allocationRepository = allocationRepository;
    }

    @Override
    public List<TeacherSubjectAllocation> getAllTeacherSubjectAllocationsBySemester(String semesterCode) {
        return null;
    }

    @Override
    public List<TeacherSubjectAllocation> getAllBySubject(String id) {
        return null;
    }

    @Override
    public void editTeacherSubjectAllocation(TeacherSubjectAllocation editedAllocation) {

    }

    @Override
    public TeacherSubjectAllocation addTeacherSubjectAllocation(TeacherSubjectAllocationDTO newAllocation, String semester) {
        return null;
    }

    @Override
    public void deleteTeacherSubjectAllocations(Long id) {

    }

    @Override
    public List<TeacherSubjectAllocation> getAllocationsForSubject(JoinedSubject joinedSubject) {
        return null;
    }

    @Override
    public List<TeacherSubjectAllocation> getAllocationsForSubjectInSemester(JoinedSubject joinedSubject, Semester semester) {
        return null;
    }

    @Override
    public void save(TeacherSubjectAllocation allocation) {

    }

    @Override
    public Optional<TeacherSubjectAllocation> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void cloneTeacherSubjectAllocations(String semesterFrom, String semesterTo) {

    }

    @Override
    public Page<TeacherSubjectAllocation> findAll(Specification<TeacherSubjectAllocation> filter, Integer pageNum, Integer results) {
        return null;
    }

    @Override
    public void deleteBySemester(String semesterCode) {

    }

    @Override
    public boolean validateAllocationsForSemester(String semesterCode) {
        return false;
    }

    @Override
    public List<TeacherSubjectAllocation> importFromList(List<TeacherSubjectAllocation> tsa) {
        return null;
    }

    @Override
    public List<TeacherSubjectAllocation> getTeacherSubjectAllocationsByProfessorId(String professorId) {
        return allocationRepository.findByProfessorId(professorId);
    }

    @Override
    public List<TeacherSubjectAllocation> getTeacherSubjectAllocationsByProfessorIdAndSemester(String professorId, String semesterCode) {
        return null;
    }

    @Override
    public List<Professor> findProfessorsBySubjectAndSemester(String subjectAbbreviation, String semesterCode) {
        return null;
    }
}
