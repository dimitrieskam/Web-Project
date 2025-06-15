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
        if (allocation.getProfessor() != null) {
            this.professor = allocation.getProfessor().getId();
        }
        if (allocation.getSubject() != null) {
            this.subject = allocation.getSubject().getName();
            this.abbreviation = allocation.getSubject().getAbbreviation();
            if (allocation.getSubject().getSemesterType() != null) {
                this.semester = allocation.getSubject().getSemesterType().name();
            }
            if (allocation.getSubject().getMainSubject() != null) {
                this.semesterCode = allocation.getSubject().getMainSubject().getId();
            }
        }
    }
}
