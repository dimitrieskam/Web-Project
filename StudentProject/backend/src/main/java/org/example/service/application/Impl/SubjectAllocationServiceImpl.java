package org.example.service.application.Impl;

import org.example.model.*;
import org.example.model.DTOs.TeacherSubjectAllocationDTO.TeacherSubjectAllocationDTO;
import org.example.model.DTOs.topicDTO.CreateTopicDTO;
import org.example.model.DTOs.topicDTO.DisplayTopicDTO;
import org.example.repository.JoinedSubjectRepository;
import org.example.repository.TopicRepository;
import org.example.service.application.SubjectAllocationService;
import org.example.repository.TeacherSubjectAllocationRepository;
import org.example.service.application.TopicApplicationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class SubjectAllocationServiceImpl implements SubjectAllocationService {

    private final TeacherSubjectAllocationRepository allocationRepository;
    private final TopicRepository topicRepository;
    private final JoinedSubjectRepository joinedSubjectRepository;
    private final TopicApplicationService topicApplicationService;

    public SubjectAllocationServiceImpl(TeacherSubjectAllocationRepository allocationRepository, TopicRepository topicRepository, JoinedSubjectRepository joinedSubjectRepository, TopicApplicationService topicApplicationService) {
        this.allocationRepository = allocationRepository;
        this.topicRepository = topicRepository;
        this.joinedSubjectRepository = joinedSubjectRepository;
        this.topicApplicationService = topicApplicationService;
    }

    @Override
    public List<DisplayTopicDTO> getAllTopics() {
        List<Topic> topics = topicRepository.findAll();
        return DisplayTopicDTO.from(topics);
    }

    @Override
    public Optional<DisplayTopicDTO> getTopicById(String topicId) {
        return topicRepository.findById(topicId)
                .map(DisplayTopicDTO::from);
    }

    @Override
    public List<DisplayTopicDTO> getTopicsByProfessor(String professorId) {
        List<Topic> topics = topicRepository.findByProfessorId(professorId);
        return DisplayTopicDTO.from(topics);
    }

    @Override
    public List<DisplayTopicDTO> getTopicsBySubject(String subjectId) {
        List<Topic> topics = topicRepository.findByJoinedSubject_MainSubject_Id(subjectId);
        return DisplayTopicDTO.from(topics);
    }

    @Override
    public DisplayTopicDTO addTopic(String professorId, String subjectId, CreateTopicDTO topicDTO) {
        Topic topic = new Topic();
        topic.setName(topicDTO.name());
        topic.setDescription(topicDTO.description());
        topic.setFromDate(topicDTO.fromDate());
        topic.setToDate(topicDTO.toDate());
        topic.setGroupCount(topicDTO.groupCount());
        topic.setMembersPerGroup(topicDTO.membersPerGroup());

        Professor professor = new Professor();
        professor.setId(professorId);
        topic.setProfessor(professor);

        JoinedSubject subject = joinedSubjectRepository.findByMainSubject_Id(subjectId);
        topic.setJoinedSubject(subject);


        topic.setId(UUID.randomUUID().toString());
        topicRepository.save(topic);
        return DisplayTopicDTO.from(topic);
    }

    @Override
    public DisplayTopicDTO updateTopic(String topicId, String professorId, String subjectId, CreateTopicDTO topicDTO) {
        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new RuntimeException("Topic not found with id " + topicId));

        topic.setName(topicDTO.name());
        topic.setDescription(topicDTO.description());
        topic.setFromDate(topicDTO.fromDate());
        topic.setToDate(topicDTO.toDate());
        topic.setGroupCount(topicDTO.groupCount());
        topic.setMembersPerGroup(topicDTO.membersPerGroup());

        Professor professor = new Professor();
        professor.setId(professorId);
        topic.setProfessor(professor);

        JoinedSubject subject = joinedSubjectRepository.findByMainSubject_Id(subjectId);
        topic.setJoinedSubject(subject);

        topicRepository.save(topic);
        return DisplayTopicDTO.from(topic);
    }

    @Override
    public void deleteTopic(String topicId) {
        if (!topicRepository.existsById(topicId)) {
            throw new RuntimeException("Topic not found with id " + topicId);
        }
        topicRepository.deleteById(topicId);
    }

    public List<DisplayTopicDTO> getTopicsByProfessorAndSubject(String professorId, String subjectId) {
        List<Topic> topics = topicRepository.findByProfessor_IdAndJoinedSubject_MainSubject_Id(professorId, subjectId);
        return DisplayTopicDTO.from(topics);
    }

    @Override
    public void delete(Long id) {
        allocationRepository.deleteById(id);
    }

    @Override
    public Page<TeacherSubjectAllocation> findAll(Specification<TeacherSubjectAllocation> filter, Integer pageNum, Integer results) {
        return allocationRepository.findAll(filter, PageRequest.of(pageNum, results));
    }

    @Override
    public List<TeacherSubjectAllocationDTO> getTeacherSubjectAllocationsByProfessorId(String professorId) {
        List<TeacherSubjectAllocation> allocations = allocationRepository.findByProfessorId(professorId);
        return allocations.stream()
                .map(TeacherSubjectAllocationDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isClosed(String topicId) {
        return topicApplicationService.isClosed(topicId);
    }

    @Override
    public Optional<DisplayTopicDTO> chooseTopic(String topicId, String username) throws AccessDeniedException {
        return topicApplicationService.chooseTopic(topicId, username);
    }
}
