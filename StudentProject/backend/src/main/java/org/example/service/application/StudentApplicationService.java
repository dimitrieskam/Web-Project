package org.example.service.application;

import org.example.model.DTOs.studentDTO.CreateStudentDTO;
import org.example.model.DTOs.studentDTO.DisplayStudentDTO;

import java.util.List;
import java.util.Optional;

public interface StudentApplicationService {
    List<DisplayStudentDTO> findAll();

    Optional<DisplayStudentDTO> findByID(Long id);

    Optional<DisplayStudentDTO> create(CreateStudentDTO createStudentDTO);

    Optional<DisplayStudentDTO> update(Long id, CreateStudentDTO createStudentDTO);

    void delete(Long id);
}
