package org.example.service.domain;

import org.example.model.Subject;
import java.util.List;
import java.util.Optional;

public interface SubjectDomainService {
    List<Subject> findAll();

    Optional<Subject> findByID(String id);
}
