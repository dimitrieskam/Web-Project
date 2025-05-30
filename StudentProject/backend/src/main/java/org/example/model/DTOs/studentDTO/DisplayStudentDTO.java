package org.example.model.DTOs.studentDTO;

import org.example.model.Student;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayStudentDTO(
        String index,
        String name,
        String lastname,
        String email
) {
    public static DisplayStudentDTO from(Student student) {
        return new DisplayStudentDTO(
                student.getIndex(),
                student.getName(),
                student.getLastName(),
                student.getEmail()
        );
    }

    public static List<DisplayStudentDTO> from(List<Student> students) {
        return students.stream()
                .map(DisplayStudentDTO::from)
                .collect(Collectors.toList());
    }

    public Student toStudent() {
        return new Student(index, name, lastname, email);
    }
}
