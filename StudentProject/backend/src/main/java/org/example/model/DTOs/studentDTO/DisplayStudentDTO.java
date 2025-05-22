package org.example.model.DTOs.studentDTO;

import org.example.model.Student;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayStudentDTO(
        Long id,
        String name,
        String surname,
        Integer index,
        String email
) {
    public static DisplayStudentDTO from(Student student) {
        return new DisplayStudentDTO(
                student.getId(),
                student.getName(),
                student.getSurname(),
                student.getIndex(),
                student.getEmail()
        );
    }

    public static List<DisplayStudentDTO> from(List<Student> students) {
        return students.stream()
                .map(DisplayStudentDTO::from)
                .collect(Collectors.toList());
    }

    public Student toStudent() {
        return new Student(id, name, surname, index, email);
    }
}
