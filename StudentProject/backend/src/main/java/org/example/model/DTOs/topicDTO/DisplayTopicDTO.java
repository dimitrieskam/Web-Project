package org.example.model.DTOs.topicDTO;

import org.example.model.Subject;
import org.example.model.Topic;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public record DisplayTopicDTO(
        Long id,
        String name,
        LocalDate fromDate,
        LocalDate toDate,
        int groupCount,
        int membersPerGroup,
        Long subjectId
) {
    public static DisplayTopicDTO from(Topic topic) {
        return new DisplayTopicDTO(
                topic.getId(),
                topic.getName(),
                topic.getFromDate(),
                topic.getToDate(),
                topic.getGroupCount(),
                topic.getMembersPerGroup(),
                topic.getSubject().getId()
        );
    }

    public static List<DisplayTopicDTO> from(List<Topic> topics) {
        return topics.stream()
                .map(DisplayTopicDTO::from)
                .collect(Collectors.toList());
    }

    public Topic toTopic(Subject subject) {
        return new Topic(id, name, fromDate, toDate, groupCount, membersPerGroup, subject);
    }
}
