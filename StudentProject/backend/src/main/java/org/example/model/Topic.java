package org.example.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
@Table(name = "TOPICS")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String name;

    private LocalDate fromDate;

    private LocalDate toDate;

    private int groupCount;

    private int membersPerGroup;

    @ManyToOne
    private Subject subject;

    public Topic() {
    }

    public Topic(String name, LocalDate fromDate, LocalDate toDate, int groupCount, int membersPerGroup, Subject subject) {
        this.name = name;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.groupCount = groupCount;
        this.membersPerGroup = membersPerGroup;
        this.subject = subject;
    }

    public Topic(Long id, String name, LocalDate fromDate, LocalDate toDate, int groupCount, int membersPerGroup, Subject subject) {
        this.id = id;
        this.name = name;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.groupCount = groupCount;
        this.membersPerGroup = membersPerGroup;
        this.subject = subject;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
}
