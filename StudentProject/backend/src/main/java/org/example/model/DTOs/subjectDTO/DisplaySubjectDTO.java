package org.example.model.DTOs.subjectDTO;

import org.example.model.Professor;
import org.example.model.Student;
import org.example.model.Subject;

import java.util.List;
import java.util.stream.Collectors;

public record DisplaySubjectDTO(
        Long id,
        String name,
        String code,
        List<Long> studentIds,
        List<Long> professorIds
) {
    public static DisplaySubjectDTO from(Subject subject) {
        List<Long> studentIds = subject.getStudents()
                .stream()
                .map(Student::getId)
                .toList();

        List<Long> professorIds = subject.getProfessors()
                .stream()
                .map(Professor::getId)
                .toList();

        return new DisplaySubjectDTO(
                subject.getId(),
                subject.getName(),
                subject.getCode(),
                studentIds,
                professorIds
        );
    }

    public static List<DisplaySubjectDTO> from(List<Subject> subjects) {
        return subjects.stream()
                .map(DisplaySubjectDTO::from)
                .collect(Collectors.toList());
    }

    public Subject toSubject(List<Student> students, List<Professor> professors) {
        return new Subject(id, name, code, students, professors);
    }
}
