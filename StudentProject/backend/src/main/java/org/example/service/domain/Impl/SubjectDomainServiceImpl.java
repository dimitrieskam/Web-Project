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
    public Optional<Subject> findByID(String id) {
        return this.subjectRepository.findById(id);
    }
}
