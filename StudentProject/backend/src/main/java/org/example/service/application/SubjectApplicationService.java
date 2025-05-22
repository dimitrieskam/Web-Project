package org.example.service.application;

import org.example.model.DTOs.subjectDTO.CreateSubjectDTO;
import org.example.model.DTOs.subjectDTO.DisplaySubjectDTO;

import java.util.List;
import java.util.Optional;

public interface SubjectApplicationService {
    List<DisplaySubjectDTO> findAll();

    Optional<DisplaySubjectDTO> findByID(Long id);

    Optional<DisplaySubjectDTO> create(CreateSubjectDTO createSubjectDTO);

    Optional<DisplaySubjectDTO> update(Long id, CreateSubjectDTO createSubjectDTO);

    void delete(Long id);
}
