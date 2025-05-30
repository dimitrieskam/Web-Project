package org.example.model;

import jakarta.persistence.*;
import org.example.model.enumerations.ProfessorTitle;

@Entity
@Table(name = "professor")
public class Professor {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private ProfessorTitle title;

    private Short orderingRank;

    @ManyToOne
    private Room office;

    public Professor() {
    }

    public Professor(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Professor(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Professor(String id, String name, String email, ProfessorTitle title, Short orderingRank, Room office) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.title = title;
        this.orderingRank = orderingRank;
        this.office = office;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ProfessorTitle getTitle() {
        return title;
    }

    public void setTitle(ProfessorTitle title) {
        this.title = title;
    }

    public Short getOrderingRank() {
        return orderingRank;
    }

    public void setOrderingRank(Short orderingRank) {
        this.orderingRank = orderingRank;
    }

    public Room getOffice() {
        return office;
    }

    public void setOffice(Room office) {
        this.office = office;
    }
}
