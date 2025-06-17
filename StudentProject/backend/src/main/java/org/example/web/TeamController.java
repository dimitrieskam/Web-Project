package org.example.web;

import org.example.service.application.TeamApplicationService;
import org.springframework.http.HttpStatus;
import org.example.model.DTOs.teamDTO.CreateTeamDTO;
import org.example.model.DTOs.teamDTO.DisplayTeamDTO;
import org.example.service.application.TopicApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/teams")
@CrossOrigin(origins = "http://localhost:3000")
public class TeamController {

    private final TopicApplicationService topicApplicationService;

    public TeamController(TopicApplicationService topicApplicationService) {
        this.topicApplicationService = topicApplicationService;
    }

    @PostMapping("/create-team/{topicId}")
    public ResponseEntity<DisplayTeamDTO> createTeam(
            @PathVariable("topicId") String topicId,
            @RequestBody CreateTeamDTO createTeamDTO) {

        try {
            System.out.println("Creating team for topic: " + topicId);
            System.out.println("Team data: " + createTeamDTO);

            DisplayTeamDTO createdTeam = topicApplicationService.createTeam(topicId, createTeamDTO, "anonymous");
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTeam);

        } catch (IllegalArgumentException e) {
            System.err.println("Invalid argument: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            System.err.println("Unexpected error creating team: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/topic/{topicId}")
    public ResponseEntity<List<DisplayTeamDTO>> getTeamsByTopic(@PathVariable("topicId") String topicId) {
        try {
            List<DisplayTeamDTO> teams = topicApplicationService.getTeamsByTopic(topicId);
            return ResponseEntity.ok(teams);
        } catch (Exception e) {
            System.err.println("Error fetching teams for topic: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{teamId}")
    public ResponseEntity<DisplayTeamDTO> getTeamById(@PathVariable("teamId") String teamId) {
        try {
            DisplayTeamDTO team = topicApplicationService.getTeamById(teamId);
            return ResponseEntity.ok(team);
        } catch (Exception e) {
            System.err.println("Error fetching team: " + e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{teamId}")
    public ResponseEntity<Void> deleteTeam(@PathVariable("teamId") String teamId) {
        try {
            topicApplicationService.deleteTeam(teamId, "anonymous");
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            System.err.println("Error deleting team: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
