package org.example.web;

import org.example.model.DTOs.subjectDTO.DisplaySubjectDTO;
import org.example.model.enumerations.SemesterType;
import org.example.service.application.SubjectApplicationService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<DisplaySubjectDTO>> findAll() {
        return ResponseEntity.ok(this.subjectApplicationService.findAll());
    }

    @GetMapping("/search-subject")
    public ResponseEntity<List<DisplaySubjectDTO>> searchSubjects(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) SemesterType semesterType) {
        return ResponseEntity.ok(subjectApplicationService.findByNameAndSemester(name, semesterType));
    }
}
