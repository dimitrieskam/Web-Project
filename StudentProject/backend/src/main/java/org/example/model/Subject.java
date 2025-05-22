package org.example.model;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SUBJECTS")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String code;

    @ManyToMany
    private List<Student> students;

    @ManyToMany
    private List<Professor> professors;

    public Subject() {
    }

    public Subject(String name, String code, List<Student> students, List<Professor> professors) {
        this.name = name;
        this.code = code;
        this.students = students;
        this.professors = professors;
    }

    public Subject(Long id, String name, String code, List<Student> students, List<Professor> professors) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.students = students;
        this.professors = professors;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Professor> getProfessors() {
        return professors;
    }

    public void setProfessors(List<Professor> professors) {
        this.professors = professors;
    }
}
