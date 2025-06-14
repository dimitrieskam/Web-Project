package org.example.web;

import org.example.model.DTOs.SubjectAllocationDTO;

import org.example.service.application.Impl.SubjectAllocationServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/professors")
public class SubjectAllocationController {

    private final SubjectAllocationServiceImpl subjectAllocationService;

    public SubjectAllocationController(SubjectAllocationServiceImpl subjectAllocationService) {
        this.subjectAllocationService = subjectAllocationService;
    }

    // GET /api/professors/{professorId}/subjects
    @GetMapping("/{professorId}/subjects")
    public ResponseEntity<List<SubjectAllocationDTO>> getSubjectsForProfessor(@PathVariable String professorId) {
        List<SubjectAllocationDTO> subjects = subjectAllocationService.getSubjectAllocationsForProfessor(professorId);
        return ResponseEntity.ok(subjects);
    }
}
