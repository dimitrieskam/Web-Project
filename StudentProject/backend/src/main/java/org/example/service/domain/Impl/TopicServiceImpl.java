package org.example.service.domain.Impl;

import org.example.model.Topic;
import org.example.repository.TopicRepository;
import org.example.service.domain.TopicDomainService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicServiceImpl implements TopicDomainService {

    private final TopicRepository topicRepository;

    public TopicServiceImpl(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Override
    public List<Topic> findAll() {
        return this.topicRepository.findAll();
    }

    @Override
    public Optional<Topic> findByID(Long id) {
        return this.topicRepository.findById(id);
    }

    @Override
    public Optional<Topic> create(Topic topic) {
        Topic topic_obj = this.topicRepository.save(topic);

        return Optional.of(topic_obj);
    }

    @Override
    public Optional<Topic> update(Long id, Topic topic) {
        Optional<Topic> topic_obj = this.topicRepository.findById(id);

        if (topic_obj.isPresent()) {
            Topic existing_topic = topic_obj.get();
            existing_topic.setName(topic.getName());
            existing_topic.setFromDate(topic.getFromDate());
            existing_topic.setToDate(topic.getToDate());
            existing_topic.setGroupCount(topic.getGroupCount());
            existing_topic.setMembersPerGroup(topic.getMembersPerGroup());
            existing_topic.setSubject(topic.getSubject());

            this.topicRepository.save(existing_topic);

            return Optional.of(existing_topic);
        }

        return Optional.empty();
    }

    @Override
    public void delete(Long id) {
        this.topicRepository.deleteById(id);
    }
}
