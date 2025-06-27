package org.example.model.enumerations;

import lombok.Getter;

public enum Role {
    STUDENT(false, true, AppRole.STUDENT),

    PROFESSOR(true, false, AppRole.PROFESSOR),

    ADMIN (true, false, AppRole.ADMIN),

    ACADEMIC_AFFAIR_VICE_DEAN(true, false, AppRole.ADMIN),
    SCIENCE_AND_COOPERATION_VICE_DEAN(true, false, AppRole.ADMIN),
    FINANCES_VICE_DEAN(true, false, AppRole.ADMIN),
    DEAN(true, false, AppRole.ADMIN),

    STUDENT_ADMINISTRATION(false, false, AppRole.ADMIN),
    STUDENT_ADMINISTRATION_MANAGER(false, false, AppRole.ADMIN),

    FINANCE_ADMINISTRATION(false, false, AppRole.ADMIN),
    FINANCE_ADMINISTRATION_MANAGER(false, false, AppRole.ADMIN),
    LEGAL_ADMINISTRATION(false, false, AppRole.ADMIN),
    ARCHIVE_ADMINISTRATION(false, false, AppRole.ADMIN),
    ADMINISTRATION_MANAGER(false, false, AppRole.ADMIN),

    EXTERNAL(true, false, AppRole.PROFESSOR);  // assuming EXTERNAL counts as PROFESSOR role

    private final Boolean professor;
    private final Boolean student;

    @Getter
    private final AppRole applicationRole;

    Role(Boolean professor, Boolean student, AppRole applicationRole) {
        this.professor = professor;
        this.student = student;
        this.applicationRole = applicationRole;
    }

    public Boolean isProfessor() {
        return professor;
    }

    public Boolean isStudent() {
        return student;
    }

    public String roleName() {
        return "ROLE_" + this.name();
    }
}
