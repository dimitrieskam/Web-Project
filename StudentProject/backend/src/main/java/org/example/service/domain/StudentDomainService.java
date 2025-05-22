package org.example.service.domain;

import org.example.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentDomainService {
    List<Student> findAll();

    Optional<Student> findByID(Long id);

    Optional<List<Student>> findAllByIds(List<Long> ids);

    Optional<Student> create(Student student);

    Optional<Student> update(Long id, Student student);

    void delete(Long id);
}
