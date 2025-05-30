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

    @Enumerated(EnumType.STRING)
    private SemesterType semester;
    @ManyToMany
    private List<Student> students;

    @ManyToMany
    private List<Professor> professors;

    public Subject() {
    }

    public Subject(String id, String name, String abbreviation, SemesterType semester, List<Student> students, List<Professor> professors) {
        this.id = id;
        this.name = name;
        this.abbreviation = abbreviation;
        this.semester = semester;
        this.students = students;
        this.professors = professors;
    }

    public Subject(String name, String abbreviation, SemesterType semester, List<Student> students, List<Professor> professors) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.semester = semester;
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

    public SemesterType getSemester() {
        return semester;
    }

    public void setSemester(SemesterType semester) {
        this.semester = semester;
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
