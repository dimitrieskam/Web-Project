package org.example.model.DTOs.teamDTO;

import java.util.List;

public record CreateTeamDTO(
        String name,
        String topicName,
        String subjectName,
        List<String> studentIds) {
}
