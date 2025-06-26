package org.example.model.DTOs.teamDTO;

import org.example.model.DTOs.studentDTO.DisplayStudentDTO;
import org.example.model.Team;
import java.util.List;
import java.util.stream.Collectors;

public record DisplayTeamDTO(
        String id,
        String name,
        String topicName,
        String subjectName,
        List<DisplayStudentDTO> students

        ) {

    public static DisplayTeamDTO from(Team team) {
        return new DisplayTeamDTO(
                team.getId(),
                team.getName(),
                team.getTopic().getName(),
                team.getTopic().getJoinedSubject().getMainSubject().getName(),
                team.getMembers().stream()
                        .map(DisplayStudentDTO::from)
                        .collect(Collectors.toList())
        );
    }
}
