package org.example.model.DTOs;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.example.model.JoinedSubject;
import jakarta.persistence.*;
import lombok.*;


@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"abbreviation", "name", "codes", "semesterType", "mainSubject"})
public class JoinedSubjectDTO {
    private String abbreviation;
    private String name;
    private String codes;
    private String semesterType;
    private String mainSubjectCode;

    /*public JoinedSubjectDTO(JoinedSubject subject) {
        this.abbreviation = subject.getAbbreviation();
        this.name = subject.getName();
        this.codes = subject.getCodes();
        this.semesterType = subject.getSemesterType().name();
        this.mainSubjectCode = subject.getMainSubject() != null ? subject.getMainSubject().getId() : null;
    }*/

}

