package org.example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


import java.util.List;
import java.util.Optional;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity

public class Course {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Semester semester;

    @ManyToOne
    private JoinedSubject joinedSubject;

    private String professors;

    private String assistants;
    private Integer numberOfFirstEnrollments;

    private Integer numberOfReEnrollments;

    public Integer getTotalStudents() {
        return Optional.ofNullable(numberOfFirstEnrollments).orElse(0) +
                Optional.ofNullable(numberOfReEnrollments).orElse(0);
    }

}
