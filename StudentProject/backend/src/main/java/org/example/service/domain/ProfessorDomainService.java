package org.example.service.domain;

import org.example.model.Professor;

import java.util.List;
import java.util.Optional;

public interface ProfessorDomainService {
    List<Professor> findAll();

    Optional<Professor> findByID(Long id);

    Optional<List<Professor>> findAllByIds(List<Long> ids);

    Optional<Professor> create(Professor professor);

    Optional<Professor> update(Long id, Professor professor);

    void delete(Long id);
}
