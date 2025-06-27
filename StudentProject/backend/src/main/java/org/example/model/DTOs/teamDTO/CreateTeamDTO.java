package org.example.model.DTOs.teamDTO;

import org.example.model.enumerations.TeamStatus;

import java.util.List;

public record CreateTeamDTO(
        String name,
        String topicName,
        String subjectName,
        List<String> studentIds,
        TeamStatus status,
        String followUpComment
        ) {
}
