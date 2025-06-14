package org.example.model.DTOs.topicDTO;

import org.example.model.Professor;
import org.example.model.Subject;
import org.example.model.Topic;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public record CreateTopicDTO(
        String name,
        LocalDate fromDate,
        LocalDate toDate,
        String description,
        int groupCount,
        int membersPerGroup,
        String professorId
) {
    public static CreateTopicDTO from(Topic topic) {
        return new CreateTopicDTO(
                topic.getName(),
                topic.getFromDate(),
                topic.getToDate(),
                topic.getDescription(),
                topic.getGroupCount(),
                topic.getMembersPerGroup(),
                topic.getProfessor().getId()
        );
    }

    public static List<CreateTopicDTO> from(List<Topic> topics) {
        return topics.stream()
                .map(CreateTopicDTO::from)
                .collect(Collectors.toList());
    }

    public Topic toTopic(Professor professor) {
        return new Topic(name, fromDate, toDate, description, groupCount, membersPerGroup, professor);
    }
}
