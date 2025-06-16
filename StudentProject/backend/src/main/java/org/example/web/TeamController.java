package org.example.web;

import org.springframework.security.core.Authentication;
import org.example.model.DTOs.teamDTO.CreateTeamDTO;
import org.example.model.DTOs.teamDTO.DisplayTeamDTO;
import org.example.service.application.TopicApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teams")
@CrossOrigin(origins = "http://localhost:3000")
public class TeamController {

    private final TopicApplicationService topicApplicationService;

    public TeamController(TopicApplicationService topicApplicationService) {
        this.topicApplicationService = topicApplicationService;
    }

    @PostMapping("/create-team/{topicId}")
//    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public ResponseEntity<DisplayTeamDTO> createTeam(@PathVariable("topicId") String topicId,
                                                      @RequestBody CreateTeamDTO createTeamDTO,
                                                      Authentication authentication) {
        String creatorUsername = authentication.getName();
        DisplayTeamDTO teamDTO = topicApplicationService.createTeam(creatorUsername, createTeamDTO);
        return ResponseEntity.ok(teamDTO);
    }
}
