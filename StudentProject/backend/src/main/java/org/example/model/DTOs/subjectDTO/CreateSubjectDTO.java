package org.example.model.DTOs.subjectDTO;

import org.example.model.Professor;
import org.example.model.Semester;
import org.example.model.Student;
import org.example.model.Subject;
import org.example.model.enumerations.SemesterType;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public record CreateSubjectDTO(
        String name,
        SemesterType semesterType,
        List<String> studentIds,
        List<String> professorIds
) {
    public static CreateSubjectDTO from(Subject subject) {
        List<String> studentIds = subject.getStudents()
                .stream()
                .map(Student::getIndex)
                .toList();

        List<String> professorIds = subject.getProfessors()
                .stream()
                .map(Professor::getId)
                .toList();

        return new CreateSubjectDTO(
                subject.getName(),
                subject.getSemesterType(),
                studentIds,
                professorIds
        );
    }

    public static List<CreateSubjectDTO> from(List<Subject> subjects) {
        return subjects.stream()
                .map(CreateSubjectDTO::from)
                .collect(Collectors.toList());
    }

    public Subject toSubject(List<Student> students, List<Professor> professors) {
        return new Subject(name, semesterType, students, professors);
    }
}
