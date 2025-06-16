package org.example.service.domain.Impl;

import org.example.model.DTOs.teamDTO.CreateTeamDTO;
import org.example.model.Student;
import org.example.model.Team;
import org.example.model.Topic;
import org.example.model.User;
import org.example.model.enumerations.Role;
import org.example.model.exceptions.TopicIsAlreadyFullException;
import org.example.repository.StudentRepository;
import org.example.repository.TopicRepository;
import org.example.repository.UserRepository;
import org.example.service.domain.TopicDomainService;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;

@Service
public class TopicServiceImpl implements TopicDomainService {

    private final TopicRepository topicRepository;
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;

    public TopicServiceImpl(TopicRepository topicRepository, UserRepository userRepository, StudentRepository studentRepository) {
        this.topicRepository = topicRepository;
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Topic> findAll() {
        return this.topicRepository.findAll();
    }

    @Override
    public Optional<Topic> findByID(String id) {
        return this.topicRepository.findById(id);
    }

    @Override
    public Optional<Topic> create(Topic topic) {
        Topic topic_obj = this.topicRepository.save(topic);
        return Optional.of(topic_obj);
    }

    @Override
    public Optional<Topic> update(String id, Topic topic) {
        Optional<Topic> topic_obj = this.topicRepository.findById(id);

        if (topic_obj.isPresent()) {
            Topic existing_topic = topic_obj.get();
            existing_topic.setName(topic.getName());
            existing_topic.setFromDate(topic.getFromDate());
            existing_topic.setToDate(topic.getToDate());
            existing_topic.setDescription(topic.getDescription());
            existing_topic.setGroupCount(topic.getGroupCount());
            existing_topic.setMembersPerGroup(topic.getMembersPerGroup());
            existing_topic.setSubject(topic.getSubject());

            this.topicRepository.save(existing_topic);

            return Optional.of(existing_topic);
        }

        return Optional.empty();
    }

    @Override
    public void delete(String id) {
        this.topicRepository.deleteById(id);
    }

    @Override
    public boolean isClosed(String topicId) {
        return topicRepository.findById(topicId)
                .map(topic -> topic.getTeams() != null && topic.getTeams().size() >= topic.getGroupCount())
                .orElse(true);
    }

    @Override
    public Optional<Topic> chooseTopic(String topicId, String username) throws AccessDeniedException {
        User user = userRepository.findByUsernameAndRoleContaining(username, Role.ROLE_STUDENT)
                .orElseThrow(() -> new AccessDeniedException("Only students can choose topics."));

        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new IllegalArgumentException("Topic not found"));

        if (isClosed(topicId)) {
            throw new TopicIsAlreadyFullException();
        }

        Student student = new Student();
        student.setIndex(username);

        boolean alreadyExists = topic.getTeams().stream()
                .flatMap(t -> t.getMembers().stream())
                .anyMatch(s -> s.getIndex().equals(username));
        if (alreadyExists) {
            throw new IllegalStateException("You are already in a team for this topic.");
        }

        for (Team team : topic.getTeams()) {
            if (team.getMembers().size() < topic.getMembersPerGroup()) {
                team.getMembers().add(student);
                topicRepository.save(topic);
                return Optional.of(topic);
            }
        }

        Team newTeam = new Team();
        newTeam.setTopic(topic);
        newTeam.setMembers(List.of(student));
        topic.getTeams().add(newTeam);
        topicRepository.save(topic);

        return Optional.of(topic);
    }

    @Override
    public Team createTeam(String creatorUsername, CreateTeamDTO createTeamDTO) {
        Topic topic = topicRepository.findByNameAndSubject_Name(createTeamDTO.topicName(), createTeamDTO.subjectName())
                .orElseThrow(() -> new RuntimeException("Topic not found"));

        List<Student> members = createTeamDTO.studentIds().stream()
                .map(index -> studentRepository.findById(index)
                        .orElseThrow(() -> new RuntimeException("Student with index " + index + " not found")))
                .toList();

        if (members.stream().noneMatch(s -> s.getUsername().equals(creatorUsername))) {
            throw new RuntimeException("Creator must be part of the team");
        }

        if (members.size() > topic.getMembersPerGroup()) {
            throw new RuntimeException("Too many team members");
        }

        Team team = new Team();
        team.setName(createTeamDTO.name());
        team.setTopic(topic);
        team.setMembers(members);

        topic.getTeams().add(team);
        topicRepository.save(topic);

        return team;
    }
}
