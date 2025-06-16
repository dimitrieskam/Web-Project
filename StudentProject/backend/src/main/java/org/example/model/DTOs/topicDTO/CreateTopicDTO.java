package org.example.model.DTOs.topicDTO;

import org.example.model.JoinedSubject;
import org.example.model.Professor;
import org.example.model.Subject;
import org.example.model.Topic;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public record CreateTopicDTO(
        String name,
        String description,
        LocalDate fromDate,
        LocalDate toDate,
        int groupCount,
        int membersPerGroup,
        String professorId,
        String subjectId
) {
    public static CreateTopicDTO from(Topic topic) {
        return new CreateTopicDTO(
                topic.getName(),
                topic.getDescription(),
                topic.getFromDate(),
                topic.getToDate(),
                topic.getGroupCount(),
                topic.getMembersPerGroup(),
                topic.getProfessor().getId(),
                topic.getJoinedSubject().getMainSubject().getId()
        );
    }

    public static List<CreateTopicDTO> from(List<Topic> topics) {
        return topics.stream()
                .map(CreateTopicDTO::from)
                .collect(Collectors.toList());
    }

    public Topic toTopic(Professor professor) {
        return new Topic(name, description, fromDate, toDate, groupCount, membersPerGroup, professor);
    }
}
