package org.example.model.DTOs.subjectDTO;

import org.example.model.Professor;
import org.example.model.Student;
import org.example.model.Subject;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public record CreateSubjectDTO(
        String name,
        String code,
        List<Long> studentIds,
        List<Long> professorIds
) {
    public static CreateSubjectDTO from(Subject subject) {
        List<Long> studentIds = subject.getStudents()
                .stream()
                .map(Student::getId)
                .toList();

        List<Long> professorIds = subject.getProfessors()
                .stream()
                .map(Professor::getId)
                .toList();

        return new CreateSubjectDTO(
                subject.getName(),
                subject.getCode(),
                studentIds,
                professorIds
        );
    }

    public static List<CreateSubjectDTO> from(List<Subject> subjects) {
        return subjects.stream()
                .map(CreateSubjectDTO::from)
                .collect(Collectors.toList());
    }

    public Subject toSubject(Optional<List<Student>> students, Optional<List<Professor>> professors) {
        return new Subject(name, code, students.orElse(List.of()), professors.orElse(List.of()));
    }
}
