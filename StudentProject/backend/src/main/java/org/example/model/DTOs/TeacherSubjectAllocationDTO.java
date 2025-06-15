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

    public TeacherSubjectAllocationDTO(TeacherSubjectAllocation allocation) {
        this.professor = allocation.getProfessor().getId(); // Or .getName() if needed
        this.subject = allocation.getSubject().getName();
        this.abbreviation = allocation.getSubject().getAbbreviation();
        this.semester = allocation.getSubject().getSemesterType().name(); // Or allocation.getSemester().getType().name()
        this.semesterCode = allocation.getSubject().getMainSubject().getId();
    }

}