package org.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = "STUDENTS")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    private Integer index;

    private String email;

    public Student() {
    }

    public Student(String name, String surname, Integer index, String email) {
        this.name = name;
        this.surname = surname;
        this.index = index;
        this.email = email;
    }

    public Student(Long id, String name, String surname, Integer index, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.index = index;
        this.email = email;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
