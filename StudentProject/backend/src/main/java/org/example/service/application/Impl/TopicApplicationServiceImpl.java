package org.example.service.application.Impl;

import org.example.model.DTOs.teamDTO.CreateTeamDTO;
import org.example.model.DTOs.teamDTO.DisplayTeamDTO;
import org.example.model.DTOs.topicDTO.CreateTopicDTO;
import org.example.model.DTOs.topicDTO.DisplayTopicDTO;
import org.example.model.Student;
import org.example.model.exceptions.TopicIsClosedException;
import org.example.service.application.TopicApplicationService;
import org.example.service.domain.ProfessorDomainService;
import org.example.service.domain.StudentDomainService;
import org.example.service.domain.SubjectDomainService;
import org.example.service.domain.TopicDomainService;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TopicApplicationServiceImpl implements TopicApplicationService {

    private final TopicDomainService topicDomainService;
    private final SubjectDomainService subjectDomainService;
    private final ProfessorDomainService professorDomainService;
    private final StudentDomainService studentDomainService;

    public TopicApplicationServiceImpl(TopicDomainService topicDomainService, SubjectDomainService subjectDomainService, ProfessorDomainService professorDomainService, StudentDomainService studentDomainService) {
        this.topicDomainService = topicDomainService;
        this.subjectDomainService = subjectDomainService;
        this.professorDomainService = professorDomainService;
        this.studentDomainService = studentDomainService;
    }


    @Override
    public List<DisplayTopicDTO> findAll() {
        return this.topicDomainService.findAll().stream()
                .map(DisplayTopicDTO::from)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DisplayTopicDTO> findByID(String id) {
        return this.topicDomainService.findByID(id)
                .map(DisplayTopicDTO::from);
    }

    @Override
    public Optional<DisplayTopicDTO> create(CreateTopicDTO createTopicDTO) {
        return this.professorDomainService.findByID(createTopicDTO.professorId())
                .flatMap(subject -> topicDomainService.create(createTopicDTO.toTopic(subject)))
                .map(DisplayTopicDTO::from);
    }

    @Override
    public Optional<DisplayTopicDTO> update(String id, CreateTopicDTO createTopicDTO) {
        return this.professorDomainService.findByID(createTopicDTO.professorId())
                .flatMap(subject -> topicDomainService.update(id, createTopicDTO.toTopic(subject)))
                .map(DisplayTopicDTO::from);
    }

    @Override
    public void delete(String id) {
        this.topicDomainService.delete(id);
    }

    @Override
    public boolean isClosed(String topicId) {
        return topicDomainService.isClosed(topicId);
    }

    @Override
    public Optional<DisplayTopicDTO> chooseTopic(String topicId, String username) throws AccessDeniedException {
        return topicDomainService.chooseTopic(topicId, username)
                .map(DisplayTopicDTO::from);
    }

    @Override
    public DisplayTeamDTO createTeam(String creatorUsername, CreateTeamDTO createTeamDTO) {
        return DisplayTeamDTO.from(topicDomainService.createTeam(creatorUsername, createTeamDTO));
    }
}
