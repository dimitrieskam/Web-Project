package org.example.model.DTOs.topicDTO;

import org.example.model.Professor;
import org.example.model.Team;
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
        String subjectId,
        String professorName,
        String subjectName,
        String creatorProfessorUsername,
        TeamInfo studentTeamInfo
) {
    public record TeamInfo(
            String teamId,
            String teamName,
            String status,
            String followUpComment
    ) {}

    public static DisplayTopicDTO from(Topic topic, Team studentTeam) {
        // Get subjectId and subjectName using JoinedSubject, if available
        String subjectId = null;
        String subjectName = "Unknown Subject";
        TeamInfo teamInfo=null;

        if (studentTeam != null) {
            String status = studentTeam.getStatus() != null ? studentTeam.getStatus().name() : "UNKNOWN";
            teamInfo = new TeamInfo(
                    studentTeam.getId(),
                    studentTeam.getName(),
                    status,
                    studentTeam.getFollowUpComment()
            );
        }


        if (topic.getJoinedSubject() != null && topic.getJoinedSubject().getMainSubject() != null) {
            subjectId = topic.getJoinedSubject().getMainSubject().getId();
            subjectName = topic.getJoinedSubject().getMainSubject().getName();
        } else {
            System.err.println("[WARNING] Topic with ID " + topic.getId() + " has null JoinedSubject or MainSubject.");
        }

        // Get professor details if available
        String professorId = null;
        String professorName = "Unknown Professor";
        String creatorProfessorUsername = null;
        if (topic.getProfessor() != null) {
            professorId = topic.getProfessor().getId();
            professorName = topic.getProfessor().getName();
            creatorProfessorUsername = topic.getProfessor().getId();
        } else {
            System.err.println("[WARNING] Topic with ID " + topic.getId() + " has null Professor.");
        }

        return new DisplayTopicDTO(
                topic.getId(),
                topic.getName(),
                topic.getDescription(),
                topic.getFromDate(),
                topic.getToDate(),
                topic.getGroupCount(),
                topic.getMembersPerGroup(),
                professorId,
                subjectId,
                professorName,
                subjectName,
                creatorProfessorUsername,
                teamInfo
        );
    }

    public static DisplayTopicDTO from(Topic topic) {
        return from(topic, null);
    }

    public static List<DisplayTopicDTO> from(List<Topic> topics) {
        return topics.stream()
                .map(DisplayTopicDTO::from) // calls from(Topic)
                .collect(Collectors.toList());
    }


    public Topic toTopic(Professor professor) {
        return new Topic(id, name, description, fromDate, toDate, groupCount, membersPerGroup, professor);
    }
}
