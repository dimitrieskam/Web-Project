package org.example.service.application.Impl;

import org.example.model.DTOs.teamDTO.DisplayTeamDTO;
import org.example.model.Team;
import org.example.repository.TeamRepository;
import org.example.service.application.TeamApplicationService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamApplicationServiceImpl implements TeamApplicationService {

    private final TeamRepository teamRepository;

    public TeamApplicationServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public List<DisplayTeamDTO> getTeamsByTopicId(String topicId) {
        List<Team> teams = teamRepository.findByTopicId(topicId);
        return teams.stream()
                .map(DisplayTeamDTO::from)
                .collect(Collectors.toList());
    }
}
