package org.example.model.DTOs.teamDTO;

import org.example.model.Student;
import org.example.model.Team;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayTeamDTO(
        String id,
        List<String> studentIndexes){

    public static DisplayTeamDTO from(Team team){
        return new DisplayTeamDTO(
          team.getId(),
          team.getMembers().stream()
                  .map(Student::getIndex)
                  .collect(Collectors.toList())
        );
    }
}
