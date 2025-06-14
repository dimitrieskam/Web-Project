package org.example.model.DTOs;
import org.example.model.TeacherSubjectAllocation;
public class SubjectAllocationDTO {
    private String subjectId;
    private String subjectName;
    private String abbreviation;
    private String semester;
    private String semesterCode;

    public SubjectAllocationDTO(TeacherSubjectAllocation allocation) {
        this.subjectId = allocation.getSubject().getId();
        this.subjectName = allocation.getSubject().getName();
        this.abbreviation = allocation.getSubject().getAbbreviation();
        this.semester = allocation.getSubject().getSemesterType().name();
        this.semesterCode = allocation.getSemesterCode();
    }

    // Getters
    public String getSubjectId() { return subjectId; }
    public String getSubjectName() { return subjectName; }
    public String getAbbreviation() { return abbreviation; }
    public String getSemester() { return semester; }
    public String getSemesterCode() { return semesterCode; }
}
