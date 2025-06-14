package org.example.model.DTOs;
import org.example.model.TeacherSubjectAllocation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor

public class TeacherSubjectAllocationDTO {
    private String professor;
    private String subject;
    private String abbreviation;
    private String semester;
    private String semesterCode;

}