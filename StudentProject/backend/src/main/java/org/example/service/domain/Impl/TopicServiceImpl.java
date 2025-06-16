package org.example.service.domain.Impl;

import jakarta.transaction.Transactional;
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

import java.beans.Transient;
import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
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
    public Optional<Topic> chooseTopic(String topicId, String index) {
//        User user = userRepository.findByUsernameAndRoleContaining(username, Role.ROLE_STUDENT)
//                .orElseThrow(() -> new AccessDeniedException("Only students can choose topics."));

        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new IllegalArgumentException("Topic not found"));

        if (isClosed(topicId)) {
            throw new TopicIsAlreadyFullException();
        }

        Student student = new Student();
        student.setIndex(index);

        boolean alreadyExists = topic.getTeams().stream()
                .flatMap(t -> t.getMembers().stream())
                .anyMatch(s -> s.getIndex().equals(index));
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
    @Transactional
    public Team createTeam(String topicId, CreateTeamDTO createTeamDTO) {
        System.out.println("Creating team for topic: " + topicId);
        System.out.println("Team name: " + createTeamDTO.name());
        System.out.println("Student IDs: " + createTeamDTO.studentIds());

        // Find the topic
        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new RuntimeException("Topic not found with ID: " + topicId));

        // Check if topic is still accepting teams
        if (topic.getTeams().size() >= topic.getGroupCount()) {
            throw new RuntimeException("Topic has reached maximum number of teams");
        }

        // Validate team name is not empty
        if (createTeamDTO.name() == null || createTeamDTO.name().trim().isEmpty()) {
            throw new RuntimeException("Team name cannot be empty");
        }

        // Check if team name already exists for this topic
        boolean teamNameExists = topic.getTeams().stream()
                .anyMatch(team -> team.getName().equalsIgnoreCase(createTeamDTO.name().trim()));
        if (teamNameExists) {
            throw new RuntimeException("Team name already exists for this topic");
        }

        // Find all students
        List<Student> members = createTeamDTO.studentIds().stream()
                .map(index -> {
                    System.out.println("Looking for student with index: " + index);
                    Optional<Student> studentOpt = studentRepository.findByIndex(index);
                    if (studentOpt.isEmpty()) {
                        System.out.println("Student not found with index: " + index);
                        throw new RuntimeException("Student with index " + index + " not found");
                    }

                    Student student = studentOpt.get();

                    // Check if student is already in a team for this topic
                    boolean alreadyInTeam = topic.getTeams().stream()
                            .flatMap(team -> team.getMembers().stream())
                            .anyMatch(member -> member.getIndex().equals(index));

                    if (alreadyInTeam) {
                        throw new RuntimeException("Student with index " + index + " is already in a team for this topic");
                    }

                    return student;
                })
                .toList();

        // Validate team size
        if (members.isEmpty()) {
            throw new RuntimeException("Team must have at least one member");
        }

        if (members.size() > topic.getMembersPerGroup()) {
            throw new RuntimeException("Team size (" + members.size() + ") exceeds maximum allowed (" + topic.getMembersPerGroup() + ")");
        }

        // Create the team
        Team team = new Team();
        team.setName(createTeamDTO.name().trim());
        team.setTopic(topic);
        team.setMembers(new ArrayList<>(members)); // Use ArrayList for ManyToMany

        // For ManyToMany relationship, add this team to each student's teams list
        members.forEach(student -> {
            if (student.getTeams() == null) {
                student.setTeams(new ArrayList<>());
            }
            student.getTeams().add(team);
        });

        // Add team to topic
        if (topic.getTeams() == null) {
            topic.setTeams(new ArrayList<>());
        }
        topic.getTeams().add(team);

        // Save the topic (which should cascade to save the team due to CascadeType.ALL)
        Topic savedTopic = topicRepository.save(topic);

        // Save each student to persist the relationship
        members.forEach(student -> studentRepository.save(student));

        // Return the team from the saved topic
        return savedTopic.getTeams().stream()
                .filter(t -> t.getName().equals(team.getName()))
                .findFirst()
                .orElse(team);
    }
}
