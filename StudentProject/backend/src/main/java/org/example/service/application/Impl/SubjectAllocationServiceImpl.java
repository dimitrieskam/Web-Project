package org.example.service.application.Impl;

import org.example.model.DTOs.SubjectAllocationDTO;
import org.example.service.application.SubjectAllocationService;
import org.example.model.DTOs.SubjectAllocationDTO;
import org.example.repository.TeacherSubjectAllocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.List;
@Service
public class SubjectAllocationServiceImpl implements SubjectAllocationService {
    private final TeacherSubjectAllocationRepository allocationRepository;

    public SubjectAllocationServiceImpl(TeacherSubjectAllocationRepository allocationRepository) {
        this.allocationRepository = allocationRepository;
    }

    @Override
    public List<SubjectAllocationDTO> getSubjectAllocationsForProfessor(String professorId) {
        return allocationRepository.findByProfessorId(professorId).stream()
                .map(SubjectAllocationDTO::new)
                .toList();
    }
}
