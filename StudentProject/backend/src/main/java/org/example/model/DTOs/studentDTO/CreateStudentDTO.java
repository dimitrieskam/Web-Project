package org.example.model.DTOs.studentDTO;

import org.example.model.Student;
import java.util.List;
import java.util.stream.Collectors;

public record CreateStudentDTO(
        String index,
        String name,
        String lastname,
        String username,
        String email
) {
    public static CreateStudentDTO from(Student student) {
        return new CreateStudentDTO(
                student.getIndex(),
                student.getName(),
                student.getLastName(),
                student.getUsername(),
                student.getEmail()
        );
    }

    public static List<CreateStudentDTO> from(List<Student> students) {
        return students.stream()
                .map(CreateStudentDTO::from)
                .collect(Collectors.toList());
    }

    public Student toStudent() {
        return new Student(index, name, lastname, username, email);
    }
}
