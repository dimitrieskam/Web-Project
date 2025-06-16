package org.example.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @Column(name = "student_index")
    private String index;

    private String name;

    private String lastName;

    private String username;

    private String email;

    @ManyToMany(mappedBy = "members")
    private List<Team> teams;

    public Student() {
    }

    public Student(String index, String name, String lastName, String username, String email) {
        this.index = index;
        this.name = name;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
    }

    public Student(String index, String name, String lastName, String username, String email, List<Team> teams) {
        this.index = index;
        this.name = name;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.teams = teams;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }
}
