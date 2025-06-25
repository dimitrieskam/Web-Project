package org.example.web;
import org.example.model.DTOs.subjectEnrollmentDTO.SubjectEnrollmentDTO;
import org.example.model.StudentSubjectEnrollment;
import org.example.service.application.Impl.StudentSubjectEnrollmentServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/student")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentSubjectEnrollmentController {

    private final StudentSubjectEnrollmentServiceImpl studentSubjectEnrollmentService;

    public StudentSubjectEnrollmentController(StudentSubjectEnrollmentServiceImpl studentSubjectEnrollmentService) {
        this.studentSubjectEnrollmentService = studentSubjectEnrollmentService;
    }

    @GetMapping("/{studentId}/subjects")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<List<SubjectEnrollmentDTO>> getSubjectsForStudent(
            @PathVariable("studentId") String studentId,
            Principal principal) {
        if (!studentId.equals(principal.getName())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        try {
            List<SubjectEnrollmentDTO> enrollments = studentSubjectEnrollmentService.getEnrollmentsForStudent(studentId);
            return ResponseEntity.ok(enrollments);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

}
