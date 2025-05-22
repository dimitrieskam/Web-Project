package org.example.service.application;

import org.example.model.DTOs.topicDTO.CreateTopicDTO;
import org.example.model.DTOs.topicDTO.DisplayTopicDTO;

import java.util.List;
import java.util.Optional;

public interface TopicApplicationService {
    List<DisplayTopicDTO> findAll();

    Optional<DisplayTopicDTO> findByID(Long id);

    Optional<DisplayTopicDTO> create(CreateTopicDTO createTopicDTO);

    Optional<DisplayTopicDTO> update(Long id, CreateTopicDTO createTopicDTO);

    void delete(Long id);
}
