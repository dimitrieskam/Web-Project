package org.example.model.DTOs.teamDTO;

import java.util.List;


public record CreateTeamDTO(String topicId, List<String> studentIds) { }

