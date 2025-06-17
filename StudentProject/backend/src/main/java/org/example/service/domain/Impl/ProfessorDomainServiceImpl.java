package org.example.service.domain.Impl;

import org.example.model.Professor;
import org.example.repository.ProfessorRepository;
import org.example.service.domain.ProfessorDomainService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProfessorDomainServiceImpl implements ProfessorDomainService {

    private final ProfessorRepository professorRepository;

    public ProfessorDomainServiceImpl(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @Override
    public List<Professor> findAll() {
        return this.professorRepository.findAll();
    }

    @Override
    public Optional<Professor> findByID(String id) {
        return this.professorRepository.findById(id);
    }

    @Override
    public List<Professor> findByNameContainingIgnoreCase(String name) {
        return professorRepository.findByNameContainingIgnoreCase(name);
    }
}
