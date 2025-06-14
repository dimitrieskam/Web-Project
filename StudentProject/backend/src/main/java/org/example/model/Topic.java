package org.example.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "TOPICS")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private String id;

    private String name;

    private LocalDate fromDate;

    private LocalDate toDate;

    private String description;

    private int groupCount;

    private int membersPerGroup;

    @ManyToOne
    private Subject subject;

    @ManyToOne
    private Professor professor;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Team> teams = new ArrayList<>();

    public Topic() {
    }

    public Topic(String name, LocalDate fromDate, LocalDate toDate, String description, int groupCount, int membersPerGroup, Subject subject) {
        this.name = name;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.groupCount = groupCount;
        this.membersPerGroup = membersPerGroup;
        this.subject = subject;
    }

    public Topic(String name, LocalDate fromDate, LocalDate toDate, String description, int groupCount, int membersPerGroup, Professor professor) {
        this.name = name;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.description = description;
        this.groupCount = groupCount;
        this.membersPerGroup = membersPerGroup;
        this.professor = professor;
    }

    public Topic(String id, String name, LocalDate fromDate, LocalDate toDate, String description, int groupCount, int membersPerGroup, Subject subject) {
        this.id = id;
        this.name = name;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.groupCount = groupCount;
        this.membersPerGroup = membersPerGroup;
        this.subject = subject;
    }

    public Topic(String id, String name, LocalDate fromDate, LocalDate toDate, String description, int groupCount, int membersPerGroup, Professor professor) {
        this.id = id;
        this.name = name;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.description = description;
        this.groupCount = groupCount;
        this.membersPerGroup = membersPerGroup;
        this.professor = professor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getGroupCount() {
        return groupCount;
    }

    public void setGroupCount(int groupCount) {
        this.groupCount = groupCount;
    }

    public int getMembersPerGroup() {
        return membersPerGroup;
    }

    public void setMembersPerGroup(int membersPerGroup) {
        this.membersPerGroup = membersPerGroup;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }
}
