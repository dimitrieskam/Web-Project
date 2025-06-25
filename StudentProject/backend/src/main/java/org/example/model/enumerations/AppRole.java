package org.example.model.enumerations;

public enum AppRole {
    STUDENT, PROFESSOR, ADMIN;

    public String roleName() {
        return "ROLE_" + this.name();
    }
}