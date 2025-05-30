package org.example.web;

import org.example.model.DTOs.professorDTO.CreateProfessorDTO;
import org.example.model.DTOs.professorDTO.DisplayProfessorDTO;
import org.example.service.application.ProfessorApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/professors")
public class ProfessorController {

    private final ProfessorApplicationService professorApplicationService;

    public ProfessorController(ProfessorApplicationService professorApplicationService) {
        this.professorApplicationService = professorApplicationService;
    }

    @GetMapping
    public ResponseEntity<List<DisplayProfessorDTO>> findAll() {
        return ResponseEntity.ok(this.professorApplicationService.findAll());
    }

    @PostMapping("/add-professor")
    public ResponseEntity<Optional<DisplayProfessorDTO>> addProfessor(@RequestBody CreateProfessorDTO createProfessorDTO) {
        return ResponseEntity.ok(this.professorApplicationService.create(createProfessorDTO));
    }

    @PutMapping("/edit-professor/{id}")
    public ResponseEntity<Optional<DisplayProfessorDTO>> editProfessor(@PathVariable String id,
                                                                     @RequestBody CreateProfessorDTO createProfessorDTO) {
        return ResponseEntity.ok(this.professorApplicationService.update(id, createProfessorDTO));
    }

    @DeleteMapping("delete-professor/{id}")
    public ResponseEntity<Void> deleteProfessor(@PathVariable String id) {
        this.professorApplicationService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
