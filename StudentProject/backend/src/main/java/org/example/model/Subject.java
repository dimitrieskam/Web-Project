package org.example.model;

import jakarta.persistence.*;
import org.example.model.enumerations.SemesterType;

import java.util.List;

@Entity
@Table(name = "subject")
public class Subject {

    @Id
    private String id; // code

    @Column(nullable = false)
    private String name;

    private String abbreviation;

    @Column(name = "semester")
    @Enumerated(EnumType.STRING)
    private SemesterType semesterType;

    @ManyToMany
    private List<Student> students;

    @ManyToMany
    private List<Professor> professors;

    public Subject() {
    }

    public Subject(String name, SemesterType semesterType, List<Student> students, List<Professor> professors) {
        this.name = name;
        this.semesterType = semesterType;
        this.students = students;
        this.professors = professors;
    }

    public Subject(String id, String name, SemesterType semesterType, List<Student> students, List<Professor> professors) {
        this.id = id;
        this.name = name;
        this.semesterType = semesterType;
        this.students = students;
        this.professors = professors;
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

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public SemesterType getSemesterType() {
        return semesterType;
    }

    public void setSemesterType(SemesterType semesterType) {
        this.semesterType = semesterType;
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