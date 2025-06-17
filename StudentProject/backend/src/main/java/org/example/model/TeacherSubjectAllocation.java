package org.example.model;

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
