package org.example.service.domain;

import org.example.model.Student;
import org.example.model.Topic;

import java.util.List;
import java.util.Optional;

public interface TopicDomainService {
    List<Topic> findAll();

    Optional<Topic> findByID(String id);

    Optional<Topic> create(Topic topic);

    Optional<Topic> update(String id, Topic topic);

    void delete(String id);

    boolean isClosed(String topicId);

    Optional<Topic> chooseTopic(String topicId, List<Student> students);
}
