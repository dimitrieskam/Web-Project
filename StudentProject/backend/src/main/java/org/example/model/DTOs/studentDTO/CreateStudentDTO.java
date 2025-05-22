package org.example.model.DTOs.studentDTO;

import org.example.model.Student;

import java.util.List;
import java.util.stream.Collectors;

public record CreateStudentDTO(
        String name,
        String surname,
        Integer index,
        String email
) {
    public static CreateStudentDTO from(Student student) {
        return new CreateStudentDTO(
                student.getName(),
                student.getSurname(),
                student.getIndex(),
                student.getEmail()
        );
    }

    public static List<CreateStudentDTO> from(List<Student> students) {
        return students.stream()
                .map(CreateStudentDTO::from)
                .collect(Collectors.toList());
    }

    public Student toStudent() {
        return new Student(name, surname, index, email);
    }
}
