package org.example.model.DTOs.professorDTO;

import org.example.model.Professor;
import java.util.List;
import java.util.stream.Collectors;

public record DisplayProfessorDTO(
        String id,
        String name,
        String email
) {
    public static DisplayProfessorDTO from(Professor professor) {
        return new DisplayProfessorDTO(
                professor.getId(),
                professor.getName(),
                professor.getEmail()
        );
    }

    public static List<DisplayProfessorDTO> from(List<Professor> professors) {
        return professors.stream()
                .map(DisplayProfessorDTO::from)
                .collect(Collectors.toList());
    }

    public Professor toProfessor() {
        return new Professor(id, name, email);
    }
}