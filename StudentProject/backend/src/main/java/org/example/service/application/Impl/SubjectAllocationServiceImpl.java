package org.example.service.application.Impl;

import org.example.model.*;
import org.example.model.DTOs.TeacherSubjectAllocationDTO;
import org.example.model.DTOs.topicDTO.CreateTopicDTO;
import org.example.model.DTOs.topicDTO.DisplayTopicDTO;
import org.example.repository.JoinedSubjectRepository;
import org.example.repository.TopicRepository;
import org.example.service.application.SubjectAllocationService;
import org.example.repository.TeacherSubjectAllocationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
public class SubjectAllocationServiceImpl implements SubjectAllocationService {

    private final TeacherSubjectAllocationRepository allocationRepository;
    private final TopicRepository topicRepository;
    private final JoinedSubjectRepository joinedSubjectRepository;
    public SubjectAllocationServiceImpl(TeacherSubjectAllocationRepository allocationRepository, TopicRepository topicRepository, JoinedSubjectRepository joinedSubjectRepository) {
        this.allocationRepository = allocationRepository;
        this.topicRepository = topicRepository;
        this.joinedSubjectRepository = joinedSubjectRepository;
    }
    public List<DisplayTopicDTO> getTopicsByProfessorAndSubject(String professorId, String subjectId) {
        List<Topic> topics = topicRepository.findByProfessor_IdAndSubject_Id(professorId, subjectId);
        return DisplayTopicDTO.from(topics);
    }

    @Override
    public List<TeacherSubjectAllocation> getAllTeacherSubjectAllocationsBySemester(String semesterCode) {
        return allocationRepository.findBySemesterCode(semesterCode);
    }

    @Override
    public List<TeacherSubjectAllocation> getAllBySubject(String id) {
        // Implementation depends on your requirements
        return List.of();
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

        // Set professor - assuming only ID needed here
        Professor professor = new Professor();
        professor.setId(professorId);
        topic.setProfessor(professor);

        // Fetch JoinedSubject from DB by abbreviation (subjectId)
        JoinedSubject subject = joinedSubjectRepository.findByMainSubject_Id(subjectId);
        topic.setJoinedSubject(subject);

        topicRepository.save(topic);
        return DisplayTopicDTO.from(topic);
    }

    @Override
    public List<DisplayTopicDTO> getAllTopics() {
        List<Topic> topics = topicRepository.findAll();
        return DisplayTopicDTO.from(topics);
    }

    @Override
    public List<DisplayTopicDTO> getTopicsByProfessor(String professorId) {
        List<Topic> topics = topicRepository.findByProfessorId(professorId);
        return DisplayTopicDTO.from(topics);
    }

    @Override
    public Optional<DisplayTopicDTO> getTopicById(String topicId) {
        return topicRepository.findById(topicId)
                .map(DisplayTopicDTO::from);
    }

    @Override
    public DisplayTopicDTO updateTopic(String topicId, String professorId, String subjectId, CreateTopicDTO topicDTO) {
        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new RuntimeException("Topic not found with id " + topicId));

        topic.setName(topicDTO.name());
        topic.setFromDate(topicDTO.fromDate());
        topic.setToDate(topicDTO.toDate());
        topic.setGroupCount(topicDTO.groupCount());
        topic.setMembersPerGroup(topicDTO.membersPerGroup());

        Professor professor = new Professor();
        professor.setId(professorId);
        topic.setProfessor(professor);

        JoinedSubject subject = joinedSubjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found: " + subjectId));
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

    @Override
    public void editTeacherSubjectAllocation(TeacherSubjectAllocation editedAllocation) {
        allocationRepository.save(editedAllocation);
    }

    @Override
    public TeacherSubjectAllocation addTeacherSubjectAllocation(TeacherSubjectAllocationDTO newAllocation, String semester) {
        // Implementation depends on your requirements
        return null;
    }

    @Override
    public void deleteTeacherSubjectAllocations(Long id) {
        allocationRepository.deleteById(id);
    }

    @Override
    public List<TeacherSubjectAllocation> getAllocationsForSubject(JoinedSubject joinedSubject) {
        return allocationRepository.findBySubject(joinedSubject, null);
    }

    @Override
    public List<TeacherSubjectAllocation> getAllocationsForSubjectInSemester(JoinedSubject joinedSubject, Semester semester) {
        // Implementation depends on your requirements
        return List.of();
    }

    @Override
    public void save(TeacherSubjectAllocation allocation) {
        allocationRepository.save(allocation);
    }

    @Override
    public Optional<TeacherSubjectAllocation> findById(Long id) {
        return allocationRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        allocationRepository.deleteById(id);
    }

    @Override
    public void cloneTeacherSubjectAllocations(String semesterFrom, String semesterTo) {
        // Implementation depends on your requirements
    }

    @Override
    public Page<TeacherSubjectAllocation> findAll(Specification<TeacherSubjectAllocation> filter, Integer pageNum, Integer results) {
        return allocationRepository.findAll(filter, PageRequest.of(pageNum, results));
    }

    @Override
    public void deleteBySemester(String semesterCode) {
        allocationRepository.deleteBySemesterCode(semesterCode);
    }

    @Override
    public boolean validateAllocationsForSemester(String semesterCode) {
        // Implementation depends on your validation logic
        return true;
    }

    @Override
    public List<TeacherSubjectAllocation> importFromList(List<TeacherSubjectAllocation> tsa) {
        return allocationRepository.saveAll(tsa);
    }

    @Override
    public List<TeacherSubjectAllocationDTO> getTeacherSubjectAllocationsByProfessorId(String professorId) {
        List<TeacherSubjectAllocation> allocations = allocationRepository.findByProfessorId(professorId);
        return allocations.stream()
                .map(TeacherSubjectAllocationDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<TeacherSubjectAllocation> getTeacherSubjectAllocationsByProfessorIdAndSemester(String professorId, String semesterCode) {
        return allocationRepository.findByProfessorIdAndSemesterCode(professorId, semesterCode);
    }

    @Override
    public List<Professor> findProfessorsBySubjectAndSemester(String subjectAbbreviation, String semesterCode) {
        return allocationRepository.findProfessorsBySubjectAndSemester(subjectAbbreviation, semesterCode);
    }
}