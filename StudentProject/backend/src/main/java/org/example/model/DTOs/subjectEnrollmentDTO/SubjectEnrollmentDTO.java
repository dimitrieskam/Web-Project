package org.example.model.DTOs.subjectEnrollmentDTO;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"index", "code", "numEnrollments", "professor", "subjectName", "message"})
public class SubjectEnrollmentDTO {
    private String index;
    private String code;
    private Integer numEnrollments;
    private String professor;
    private String subjectName;
    private String message;
}
