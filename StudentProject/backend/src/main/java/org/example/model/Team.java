package org.example.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TEAMS")
public class Team {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private String id = java.util.UUID.randomUUID().toString();

    @Column(name = "team_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(
            name = "teams_members",
            joinColumns = @JoinColumn(name = "teams_id"),
            inverseJoinColumns = @JoinColumn(name = "members_id")
    )
    private List<Student> members = new ArrayList<>();

    public Team() {
    }

    public Team(String name, Topic topic, List<Student> members) {
        this.name = name;
        this.topic = topic;
        this.members = members;
    }

    public Team(String id, String name, Topic topic, List<Student> members) {
        this.id = id;
        this.name = name;
        this.topic = topic;
        this.members = members;
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

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public List<Student> getMembers() {
        return members;
    }

    public void setMembers(List<Student> members) {
        this.members = members;
    }
}
