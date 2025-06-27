package org.example.service.application;

import org.example.model.DTOs.studentDTO.DisplayStudentDTO;
import org.example.model.DTOs.teamDTO.DisplayTeamDTO;
import org.example.model.enumerations.TeamStatus;

import java.util.List;

public interface TeamApplicationService {

    List<DisplayTeamDTO> findAll();
    List<DisplayTeamDTO> getTeamsByTopicId(String topicId);
    void delete(String id);
    void updateTeamStatus(String teamId, TeamStatus status, String followUpComment);
}
