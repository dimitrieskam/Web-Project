package org.example.service.domain;

import org.example.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentDomainService {
    List<Student> findAll();

    Optional<Student> findByIndex(String index);

    Optional<List<Student>> findAllByIndexes(List<String> indexes);

    Optional<Student> create(Student student);

    Optional<Student> update(String index, Student student);
    List<Student> searchStudentsByIndex(String query);
    void delete(String index);
}
