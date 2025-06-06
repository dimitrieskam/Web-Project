package org.example.model.DTOs.topicDTO;

import org.example.model.DTOs.teamDTO.DisplayTeamDTO;
import org.example.model.Subject;
import org.example.model.Topic;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public record DisplayTopicDTO(
        String id,
        String name,
        LocalDate fromDate,
        LocalDate toDate,
        String description,
        int groupCount,
        int membersPerGroup,
        String subjectId,
        List<DisplayTeamDTO> teams
) {
    public static DisplayTopicDTO from(Topic topic) {
        return new DisplayTopicDTO(
                topic.getId(),
                topic.getName(),
                topic.getFromDate(),
                topic.getToDate(),
                topic.getDescription(),
                topic.getGroupCount(),
                topic.getMembersPerGroup(),
                topic.getSubject().getId(),
                topic.getTeams().stream()
                        .map(DisplayTeamDTO::from)
                        .collect(Collectors.toList())
        );
    }

    public static List<DisplayTopicDTO> from(List<Topic> topics) {
        return topics.stream()
                .map(DisplayTopicDTO::from)
                .collect(Collectors.toList());
    }

    public Topic toTopic(Subject subject) {
        return new Topic(id, name, fromDate, toDate, description, groupCount, membersPerGroup, subject);
    }
}
