package org.example.model.DTOs.topicDTO;

import org.example.model.Professor;
import org.example.model.Topic;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public record DisplayTopicDTO(
        String id,
        String name,
        String description,
        LocalDate fromDate,
        LocalDate toDate,
        int groupCount,
        int membersPerGroup,
        String professorId,
        String subjectId
) {
    public static DisplayTopicDTO from(Topic topic) {
        String subjectId = null;

        if (topic.getJoinedSubject() != null && topic.getJoinedSubject().getMainSubject() != null) {
            subjectId = topic.getJoinedSubject().getMainSubject().getId();
        } else {
            System.err.println("[WARNING] Topic with ID " + topic.getId() + " has null JoinedSubject or MainSubject.");
        }

        return new DisplayTopicDTO(
                topic.getId(),
                topic.getName(),
                topic.getDescription(),
                topic.getFromDate(),
                topic.getToDate(),
                topic.getGroupCount(),
                topic.getMembersPerGroup(),
                topic.getProfessor() != null ? topic.getProfessor().getId() : null,
                subjectId
        );
    }

    public static List<DisplayTopicDTO> from(List<Topic> topics) {
        return topics.stream()
                .map(DisplayTopicDTO::from)
                .collect(Collectors.toList());
    }

    public Topic toTopic(Professor professor) {
        return new Topic(id, name, description, fromDate, toDate, groupCount, membersPerGroup, professor);
    }
}
