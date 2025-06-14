package org.example.web;

import org.example.model.DTOs.TeacherSubjectAllocationDTO;

import org.example.model.TeacherSubjectAllocation;
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
    public ResponseEntity<List<TeacherSubjectAllocation>> getSubjectsForProfessor(@PathVariable String professorId) {
        System.out.println("Looking for allocations for professorId: " + professorId);
        List<TeacherSubjectAllocation> subjects = subjectAllocationService.getTeacherSubjectAllocationsByProfessorId(professorId);
        return ResponseEntity.ok(subjects);
    }
}
