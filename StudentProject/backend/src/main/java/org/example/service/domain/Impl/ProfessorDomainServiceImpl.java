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
    public Optional<List<Professor>> findAllByIds(List<String> ids) {
        return Optional.of(this.professorRepository.findAllById(ids));
    }

    @Override
    public Optional<Professor> create(Professor professor) {
        Professor professor_obj = this.professorRepository.save(professor);

        return Optional.of(professor_obj);
    }

    @Override
    public Optional<Professor> update(String id, Professor professor) {
        Optional<Professor> professor_obj = this.professorRepository.findById(id);

        if (professor_obj.isPresent()) {
            Professor existing_professor = professor_obj.get();
            existing_professor.setName(professor.getName());
            existing_professor.setEmail(professor.getEmail());

            this.professorRepository.save(existing_professor);

            return Optional.of(existing_professor);
        }

        return Optional.empty();
    }

    @Override
    public void delete(String id) {
        this.professorRepository.deleteById(id);
    }
}
