package org.example.service.application.Impl;

import org.example.model.DTOs.professorDTO.CreateProfessorDTO;
import org.example.model.DTOs.professorDTO.DisplayProfessorDTO;
import org.example.service.application.ProfessorApplicationService;
import org.example.service.domain.ProfessorDomainService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfessorApplicationServiceImpl implements ProfessorApplicationService {

    private final ProfessorDomainService professorDomainService;

    public ProfessorApplicationServiceImpl(ProfessorDomainService professorDomainService) {
        this.professorDomainService = professorDomainService;
    }


    @Override
    public List<DisplayProfessorDTO> findAll() {
        return this.professorDomainService.findAll().stream()
                .map(DisplayProfessorDTO::from)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DisplayProfessorDTO> findByID(Long id) {
        return this.professorDomainService.findByID(id)
                .map(DisplayProfessorDTO::from);
    }

    @Override
    public Optional<DisplayProfessorDTO> create(CreateProfessorDTO createProfessorDTO) {
        return this.professorDomainService.create(createProfessorDTO.toProfessor())
                .map(DisplayProfessorDTO::from);
    }

    @Override
    public Optional<DisplayProfessorDTO> update(Long id, CreateProfessorDTO createProfessorDTO) {
        return this.professorDomainService.update(id, createProfessorDTO.toProfessor())
                .map(DisplayProfessorDTO::from);
    }

    @Override
    public void delete(Long id) {
        this.professorDomainService.delete(id);
    }
}
