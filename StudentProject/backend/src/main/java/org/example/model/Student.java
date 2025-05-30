package org.example.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @Column(name = "student_index")
    private String index;

    private String email;

    private String name;

    private String lastName;

    @ManyToMany(mappedBy = "members")
    private List<Team> teams;

    public Student() {
    }

    public Student(String index, String email, String name, String lastName) {
        this.index = index;
        this.email = email;
        this.name = name;
        this.lastName = lastName;
    }

    public Student(String index, String email, String name, String lastName, List<Team> teams) {
        this.index = index;
        this.email = email;
        this.name = name;
        this.lastName = lastName;
        this.teams = teams;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
