package org.example.service.application.Impl;

import org.example.model.DTOs.topicDTO.CreateTopicDTO;
import org.example.model.DTOs.topicDTO.DisplayTopicDTO;
import org.example.service.application.TopicApplicationService;
import org.example.service.domain.SubjectDomainService;
import org.example.service.domain.TopicDomainService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TopicApplicationServiceImpl implements TopicApplicationService {

    private final TopicDomainService topicDomainService;
    private final SubjectDomainService subjectDomainService;

    public TopicApplicationServiceImpl(TopicDomainService topicDomainService, SubjectDomainService subjectDomainService) {
        this.topicDomainService = topicDomainService;
        this.subjectDomainService = subjectDomainService;
    }

    @Override
    public List<DisplayTopicDTO> findAll() {
        return this.topicDomainService.findAll().stream()
                .map(DisplayTopicDTO::from)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DisplayTopicDTO> findByID(Long id) {
        return this.topicDomainService.findByID(id)
                .map(DisplayTopicDTO::from);
    }

    @Override
    public Optional<DisplayTopicDTO> create(CreateTopicDTO createTopicDTO) {
        return subjectDomainService.findByID(createTopicDTO.subjectId())
                .flatMap(subject -> topicDomainService.create(createTopicDTO.toTopic(subject)))
                .map(DisplayTopicDTO::from);
    }

    @Override
    public Optional<DisplayTopicDTO> update(Long id, CreateTopicDTO createTopicDTO) {
        return subjectDomainService.findByID(createTopicDTO.subjectId())
                .flatMap(subject -> topicDomainService.update(id, createTopicDTO.toTopic(subject)))
                .map(DisplayTopicDTO::from);
    }

    @Override
    public void delete(Long id) {
        this.topicDomainService.delete(id);
    }
}
