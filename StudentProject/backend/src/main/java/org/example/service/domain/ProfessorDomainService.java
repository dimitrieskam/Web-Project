package org.example.service.domain;

import org.example.model.Professor;

import java.util.List;
import java.util.Optional;

public interface ProfessorDomainService {
    List<Professor> findAll();

    Optional<Professor> findByID(String id);

    Optional<List<Professor>> findAllByIds(List<String> ids);

    Optional<Professor> create(Professor professor);

    Optional<Professor> update(String id, Professor professor);

    void delete(String id);
}
