package org.example.web;

import org.example.model.DTOs.studentDTO.CreateStudentDTO;
import org.example.model.DTOs.studentDTO.DisplayStudentDTO;
import org.example.model.DTOs.subjectDTO.CreateSubjectDTO;
import org.example.model.DTOs.subjectDTO.DisplaySubjectDTO;
import org.example.service.application.SubjectApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
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

    @PostMapping("/add-subject")
    public ResponseEntity<Optional<DisplaySubjectDTO>> addSubject(@RequestBody CreateSubjectDTO createSubjectDTO) {
        return ResponseEntity.ok(this.subjectApplicationService.create(createSubjectDTO));
    }

    @PutMapping("/edit-subject/{id}")
    public ResponseEntity<Optional<DisplaySubjectDTO>> editSubject(@PathVariable String id,
                                                                   @RequestBody CreateSubjectDTO createSubjectDTO) {
        return ResponseEntity.ok(this.subjectApplicationService.update(id, createSubjectDTO));
    }

    @DeleteMapping("delete-subject/{id}")
    public ResponseEntity<Void> deleteSubject(@PathVariable String id) {
        this.subjectApplicationService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
