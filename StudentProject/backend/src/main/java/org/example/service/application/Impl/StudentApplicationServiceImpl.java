package org.example.service.application.Impl;

import org.example.model.DTOs.studentDTO.CreateStudentDTO;
import org.example.model.DTOs.studentDTO.DisplayStudentDTO;
import org.example.service.application.StudentApplicationService;
import org.example.service.domain.StudentDomainService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentApplicationServiceImpl implements StudentApplicationService {

    private final StudentDomainService studentDomainService;

    public StudentApplicationServiceImpl(StudentDomainService studentDomainService) {
        this.studentDomainService = studentDomainService;
    }

    @Override
    public List<DisplayStudentDTO> findAll() {
        return this.studentDomainService.findAll().stream()
                .map(DisplayStudentDTO::from)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DisplayStudentDTO> findByID(Long id) {
        return this.studentDomainService.findByID(id)
                .map(DisplayStudentDTO::from);
    }

    @Override
    public Optional<DisplayStudentDTO> create(CreateStudentDTO createStudentDTO) {
        return this.studentDomainService.create(createStudentDTO.toStudent())
                .map(DisplayStudentDTO::from);
    }

    @Override
    public Optional<DisplayStudentDTO> update(Long id, CreateStudentDTO createStudentDTO) {
        return this.studentDomainService.update(id, createStudentDTO.toStudent())
                .map(DisplayStudentDTO::from);
    }

    @Override
    public void delete(Long id) {
        this.studentDomainService.delete(id);
    }
}
