package org.example.service.domain;

import org.example.model.DTOs.teamDTO.CreateTeamDTO;
import org.example.model.Student;
import org.example.model.Team;
import org.example.model.Topic;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;

public interface TopicDomainService {
    List<Topic> findAll();

    Optional<Topic> findByID(String id);

    Optional<Topic> create(Topic topic);

    Optional<Topic> update(String id, Topic topic);

    void delete(String id);

    boolean isClosed(String topicId);
    Optional<Topic> chooseTopic(String topicId, String username) throws AccessDeniedException;

    Team createTeam(String creatorUsername, CreateTeamDTO createTeamDTO);
}
