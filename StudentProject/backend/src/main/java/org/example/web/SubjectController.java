package org.example.web;

import org.example.model.DTOs.subjectDTO.DisplaySubjectDTO;
import org.example.model.enumerations.SemesterType;
import org.example.service.application.SubjectApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/subjects")
public class SubjectController {

    private final SubjectApplicationService subjectApplicationService;

    public SubjectController(SubjectApplicationService subjectApplicationService) {
        this.subjectApplicationService = subjectApplicationService;
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<DisplaySubjectDTO>> findAll() {
        return ResponseEntity.ok(this.subjectApplicationService.findAll());
    }

    @GetMapping("/search-subject")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<DisplaySubjectDTO>> searchSubjects(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "semesterType", required = false) SemesterType semesterType) {
        return ResponseEntity.ok(subjectApplicationService.findByNameAndSemester(name, semesterType));
    }
}
