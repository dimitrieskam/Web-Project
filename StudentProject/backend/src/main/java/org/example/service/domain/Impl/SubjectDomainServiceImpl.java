package org.example.service.domain.Impl;

import org.example.model.Subject;
import org.example.repository.SubjectRepository;
import org.example.service.domain.SubjectDomainService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectDomainServiceImpl implements SubjectDomainService {

    private final SubjectRepository subjectRepository;

    public SubjectDomainServiceImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public List<Subject> findAll() {
        return this.subjectRepository.findAll();
    }

    @Override
    public Optional<Subject> findByID(Long id) {
        return this.subjectRepository.findById(id);
    }

    @Override
    public Optional<Subject> create(Subject subject) {
        Subject subject_obj = this.subjectRepository.save(subject);

        return Optional.of(subject_obj);
    }

    @Override
    public Optional<Subject> update(Long id, Subject subject) {
        Optional<Subject> subject_obj = this.subjectRepository.findById(id);

        if (subject_obj.isPresent()) {
            Subject existing_subject = subject_obj.get();
            existing_subject.setName(subject.getName());
            existing_subject.setCode(subject.getCode());
            existing_subject.setStudents(subject.getStudents());
            existing_subject.setProfessors(subject.getProfessors());

            this.subjectRepository.save(existing_subject);

            return Optional.of(existing_subject);
        }

        return Optional.empty();
    }

    @Override
    public void delete(Long id) {
        this.subjectRepository.deleteById(id);
    }
}
