package org.example.service.application.Impl;

import org.example.model.DTOs.TeacherSubjectAllocationDTO.TeacherSubjectAllocationDTO;
import org.example.model.DTOs.professorDTO.DisplayProfessorDTO;
import org.example.model.Professor;
import org.example.model.TeacherSubjectAllocation;
import org.example.repository.ProfessorRepository;
import org.example.repository.TeacherSubjectAllocationRepository;
import org.example.service.application.ProfessorApplicationService;
import org.example.service.domain.ProfessorDomainService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfessorApplicationServiceImpl implements ProfessorApplicationService {

    private final ProfessorDomainService professorDomainService;
    private final ProfessorRepository professorRepository;
    private final TeacherSubjectAllocationRepository teacherSubjectAllocationRepository;
    public ProfessorApplicationServiceImpl(ProfessorDomainService professorDomainService, ProfessorRepository professorRepository, TeacherSubjectAllocationRepository teacherSubjectAllocationRepository) {
        this.professorDomainService = professorDomainService;
        this.professorRepository = professorRepository;
        this.teacherSubjectAllocationRepository = teacherSubjectAllocationRepository;
    }

    @Override
    public List<DisplayProfessorDTO> findAll() {
        return this.professorDomainService.findAll().stream()
                .map(DisplayProfessorDTO::from)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DisplayProfessorDTO> findByID(String id) {
        return this.professorDomainService.findByID(id)
                .map(DisplayProfessorDTO::from);
    }
    @Override
    public List<DisplayProfessorDTO> findByName(String name) {
        return professorDomainService.findByNameContainingIgnoreCase(name)
                .stream()
                .map(prof -> new DisplayProfessorDTO(prof.getId(), prof.getName(), prof.getEmail()))
                .toList();
    }
    @Override
    public Optional<DisplayProfessorDTO> getProfessorByUsername(String id) {
        return professorDomainService.findByID(id)
                .map(DisplayProfessorDTO::from);
    }

}
