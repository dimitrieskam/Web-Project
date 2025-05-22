package org.example.service.application;

import org.example.model.DTOs.professorDTO.CreateProfessorDTO;
import org.example.model.DTOs.professorDTO.DisplayProfessorDTO;

import java.util.List;
import java.util.Optional;

public interface ProfessorApplicationService {
    List<DisplayProfessorDTO> findAll();

    Optional<DisplayProfessorDTO> findByID(Long id);

    Optional<DisplayProfessorDTO> create(CreateProfessorDTO createProfessorDTO);

    Optional<DisplayProfessorDTO> update(Long id, CreateProfessorDTO createProfessorDTO);

    void delete(Long id);
}
