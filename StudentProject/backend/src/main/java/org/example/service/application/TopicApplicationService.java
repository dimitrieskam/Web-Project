package org.example.service.application;

import org.example.model.DTOs.teamDTO.CreateTeamDTO;
import org.example.model.DTOs.topicDTO.CreateTopicDTO;
import org.example.model.DTOs.topicDTO.DisplayTopicDTO;

import java.util.List;
import java.util.Optional;

public interface TopicApplicationService {
    List<DisplayTopicDTO> findAll();

    Optional<DisplayTopicDTO> findByID(String id);

    Optional<DisplayTopicDTO> create(CreateTopicDTO createTopicDTO);

    Optional<DisplayTopicDTO> update(String id, CreateTopicDTO createTopicDTO);

    void delete(String id);
    Optional<DisplayTopicDTO> chooseTopic(CreateTeamDTO dto);
}
