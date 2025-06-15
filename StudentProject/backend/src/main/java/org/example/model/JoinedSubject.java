package org.example.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.example.model.enumerations.SemesterType;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class JoinedSubject {

    @Id
    private String abbreviation;

    @Column(length = 1000)
    private String name;

    private String codes;

    @Enumerated(EnumType.STRING)
    private SemesterType semesterType;

    @ManyToOne
    private Subject mainSubject;


    public JoinedSubject(String id, String name, String codes, SemesterType semesterType,
                         Subject mainSubject) {
        this.abbreviation = id;
        this.name = name;
        this.codes = codes;
        this.semesterType = semesterType;
        this.mainSubject = mainSubject;

    }

    @Transient
    public List<String> codesList() {
        return codes != null ? List.of(codes.split(";")).stream().map(String::trim).toList() : List.of();
    }

    @Transient
    public String displayName() {
        return String.format("%s [%s] (%d+%d+%d)", name, abbreviation);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        JoinedSubject other = (JoinedSubject) o;
        return getAbbreviation() != null && Objects.equals(getAbbreviation(), other.getAbbreviation());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

