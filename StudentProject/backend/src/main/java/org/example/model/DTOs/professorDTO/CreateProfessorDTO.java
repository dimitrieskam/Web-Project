package org.example.model.DTOs.professorDTO;

import org.example.model.Professor;

import java.util.List;
import java.util.stream.Collectors;

public record CreateProfessorDTO(
        String name,
        String surname,
        String email
) {
    public static CreateProfessorDTO from(Professor professor) {
        return new CreateProfessorDTO(
                professor.getName(),
                professor.getSurname(),
                professor.getEmail()
        );
    }

    public static List<CreateProfessorDTO> from(List<Professor> professors) {
        return professors.stream()
                .map(CreateProfessorDTO::from)
                .collect(Collectors.toList());
    }

    public Professor toProfessor() {
        return new Professor(name, surname, email);
    }
}
