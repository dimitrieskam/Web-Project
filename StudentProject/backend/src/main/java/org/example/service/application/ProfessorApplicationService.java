package org.example.service.application;

import org.example.model.DTOs.professorDTO.CreateProfessorDTO;
import org.example.model.DTOs.professorDTO.DisplayProfessorDTO;

import java.util.List;
import java.util.Optional;

public interface ProfessorApplicationService {
    List<DisplayProfessorDTO> findAll();

    Optional<DisplayProfessorDTO> findByID(String id);

    Optional<DisplayProfessorDTO> create(CreateProfessorDTO createProfessorDTO);

    Optional<DisplayProfessorDTO> update(String id, CreateProfessorDTO createProfessorDTO);

    void delete(String id);
}
