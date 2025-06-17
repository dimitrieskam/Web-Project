package org.example.service.application;

import org.example.model.DTOs.teamDTO.DisplayTeamDTO;

import java.util.List;

public interface TeamApplicationService {
    List<DisplayTeamDTO> getTeamsByTopicId(String topicId);
}
