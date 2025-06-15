package org.example.model;
/*import jakarta.persistence.*;
@Entity
@Table(name = "teacher_subject_allocations")
public class TeacherSubjectAllocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    private String semesterCode;

    // Add any other fields if needed (e.g., numberOfExerciseGroups...)

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Professor getProfessor() { return professor; }
    public void setProfessor(Professor professor) { this.professor = professor; }

    public Subject getSubject() { return subject; }
    public void setSubject(Subject subject) { this.subject = subject; }

    public String getSemesterCode() { return semesterCode; }
    public void setSemesterCode(String semesterCode) { this.semesterCode = semesterCode; }
}
*/
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@JsonPropertyOrder({"semesterCode", "subjectId", "professorId", "englishGroup", "numberOfLectureGroups", "numberOfExerciseGroups", "numberOfLabGroups"})
@Table(name = "teacher_subject_allocations")

public class TeacherSubjectAllocation {

    @Id
    @GeneratedValue
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @Column(name = "professor_id", insertable = false, updatable = false)
    private String professorId;



    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private JoinedSubject subject;

    @Column(name = "subject_id", insertable = false, updatable = false)
    private String subjectId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "semester_code")
    private Semester semester;

    @Column(name = "semester_code", insertable = false, updatable = false)
    private String semesterCode;



    private Boolean englishGroup;

    @Column(length = 4_000)
    private String validationMessage;


    private Float numberOfLectureGroups;
    private Float numberOfExerciseGroups;
    private Float numberOfLabGroups;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TeacherSubjectAllocation that = (TeacherSubjectAllocation) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}