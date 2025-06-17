package org.example.model;

import jakarta.persistence.*;
import org.example.model.enumerations.SemesterType;

@Entity
@Table(name = "semester")
public class Semester {

    @Id
    private String code;

    private String year;

    @Enumerated(EnumType.STRING)
    private SemesterType semesterType;

    public Semester() {

    }

    public Semester(String code, String year, SemesterType semesterType) {
        this.code = code;
        this.year = year;
        this.semesterType = semesterType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public SemesterType getSemesterType() {
        return semesterType;
    }

    public void setSemesterType(SemesterType semesterType) {
        this.semesterType = semesterType;
    }
}
