package org.example.model.DTOs.JoinedSubjectDTO;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"abbreviation", "name", "codes", "semesterType", "mainSubjectCode"})
public class JoinedSubjectDTO {
    private String abbreviation;
    private String name;
    private String codes;
    private String semesterType;
    private String mainSubjectCode;
}
