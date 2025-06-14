package org.example.model.DTOs.topicDTO;

import org.example.model.Professor;
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
        String professorId
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
                topic.getProfessor().getId()
        );
    }

    public static List<DisplayTopicDTO> from(List<Topic> topics) {
        return topics.stream()
                .map(DisplayTopicDTO::from)
                .collect(Collectors.toList());
    }

    public Topic toTopic(Professor professor) {
        return new Topic(id, name, fromDate, toDate, description, groupCount, membersPerGroup, professor);
    }
}
