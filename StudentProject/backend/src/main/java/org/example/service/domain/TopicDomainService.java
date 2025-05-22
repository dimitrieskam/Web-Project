package org.example.service.domain;

import org.example.model.Topic;

import java.util.List;
import java.util.Optional;

public interface TopicDomainService {
    List<Topic> findAll();

    Optional<Topic> findByID(Long id);

    Optional<Topic> create(Topic topic);

    Optional<Topic> update(Long id, Topic topic);

    void delete(Long id);
}
