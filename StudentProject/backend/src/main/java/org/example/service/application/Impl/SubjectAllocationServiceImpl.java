package org.example.service.application.Impl;

import org.example.model.DTOs.TeacherSubjectAllocationDTO;
import org.example.model.JoinedSubject;
import org.example.model.Professor;
import org.example.model.Semester;
import org.example.model.TeacherSubjectAllocation;
import org.example.service.application.SubjectAllocationService;
import org.example.repository.TeacherSubjectAllocationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
public class SubjectAllocationServiceImpl implements SubjectAllocationService {

    private final TeacherSubjectAllocationRepository allocationRepository;

    public SubjectAllocationServiceImpl(TeacherSubjectAllocationRepository allocationRepository) {
        this.allocationRepository = allocationRepository;
    }

    @Override
    public List<TeacherSubjectAllocation> getAllTeacherSubjectAllocationsBySemester(String semesterCode) {
        return allocationRepository.findBySemesterCode(semesterCode);
    }

    @Override
    public List<TeacherSubjectAllocation> getAllBySubject(String id) {
        // Implementation depends on your requirements
        return List.of();
    }

    @Override
    public void editTeacherSubjectAllocation(TeacherSubjectAllocation editedAllocation) {
        allocationRepository.save(editedAllocation);
    }

    @Override
    public TeacherSubjectAllocation addTeacherSubjectAllocation(TeacherSubjectAllocationDTO newAllocation, String semester) {
        // Implementation depends on your requirements
        return null;
    }

    @Override
    public void deleteTeacherSubjectAllocations(Long id) {
        allocationRepository.deleteById(id);
    }

    @Override
    public List<TeacherSubjectAllocation> getAllocationsForSubject(JoinedSubject joinedSubject) {
        return allocationRepository.findBySubject(joinedSubject, null);
    }

    @Override
    public List<TeacherSubjectAllocation> getAllocationsForSubjectInSemester(JoinedSubject joinedSubject, Semester semester) {
        // Implementation depends on your requirements
        return List.of();
    }

    @Override
    public void save(TeacherSubjectAllocation allocation) {
        allocationRepository.save(allocation);
    }

    @Override
    public Optional<TeacherSubjectAllocation> findById(Long id) {
        return allocationRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        allocationRepository.deleteById(id);
    }

    @Override
    public void cloneTeacherSubjectAllocations(String semesterFrom, String semesterTo) {
        // Implementation depends on your requirements
    }

    @Override
    public Page<TeacherSubjectAllocation> findAll(Specification<TeacherSubjectAllocation> filter, Integer pageNum, Integer results) {
        return allocationRepository.findAll(filter, PageRequest.of(pageNum, results));
    }

    @Override
    public void deleteBySemester(String semesterCode) {
        allocationRepository.deleteBySemesterCode(semesterCode);
    }

    @Override
    public boolean validateAllocationsForSemester(String semesterCode) {
        // Implementation depends on your validation logic
        return true;
    }

    @Override
    public List<TeacherSubjectAllocation> importFromList(List<TeacherSubjectAllocation> tsa) {
        return allocationRepository.saveAll(tsa);
    }

    @Override
    public List<TeacherSubjectAllocationDTO> getTeacherSubjectAllocationsByProfessorId(String professorId) {
        List<TeacherSubjectAllocation> allocations = allocationRepository.findByProfessorId(professorId);
        return allocations.stream()
                .map(TeacherSubjectAllocationDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<TeacherSubjectAllocation> getTeacherSubjectAllocationsByProfessorIdAndSemester(String professorId, String semesterCode) {
        return allocationRepository.findByProfessorIdAndSemesterCode(professorId, semesterCode);
    }

    @Override
    public List<Professor> findProfessorsBySubjectAndSemester(String subjectAbbreviation, String semesterCode) {
        return allocationRepository.findProfessorsBySubjectAndSemester(subjectAbbreviation, semesterCode);
    }
}