package org.example.service.domain;

import org.example.model.Topic;
import java.util.List;
import java.util.Optional;

public interface TopicDomainService {
    List<Topic> findAll();

    Optional<Topic> findByID(String id);

    boolean isClosed(String topicId);

    Optional<Topic> chooseTopic(String topicId, String index);
}
