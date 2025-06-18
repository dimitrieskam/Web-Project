package org.example.web;

import org.example.model.DTOs.professorDTO.DisplayProfessorDTO;
import org.example.service.application.ProfessorApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/professors")
public class ProfessorController {

    private final ProfessorApplicationService professorApplicationService;

    public ProfessorController(ProfessorApplicationService professorApplicationService) {
        this.professorApplicationService = professorApplicationService;
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<DisplayProfessorDTO>> findAll() {
        return ResponseEntity.ok(this.professorApplicationService.findAll());
    }
    @GetMapping("/search-professor")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<DisplayProfessorDTO>> searchByName(@RequestParam("name") String name) {
        return ResponseEntity.ok(professorApplicationService.findByName(name));
    }
}
