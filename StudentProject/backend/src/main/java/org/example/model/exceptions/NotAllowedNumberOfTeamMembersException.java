package org.example.model.exceptions;

public class NotAllowedNumberOfTeamMembersException extends RuntimeException{
    public NotAllowedNumberOfTeamMembersException() {
        super("Number of team members exceeds allowed members per group");
    }
}
