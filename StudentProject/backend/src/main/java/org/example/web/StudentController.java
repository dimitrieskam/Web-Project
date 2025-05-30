package org.example.web;

import org.example.model.DTOs.professorDTO.CreateProfessorDTO;
import org.example.model.DTOs.professorDTO.DisplayProfessorDTO;
import org.example.model.DTOs.studentDTO.CreateStudentDTO;
import org.example.model.DTOs.studentDTO.DisplayStudentDTO;
import org.example.service.application.StudentApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/students")
public class StudentController {

    private final StudentApplicationService studentApplicationService;

    public StudentController(StudentApplicationService studentApplicationService) {
        this.studentApplicationService = studentApplicationService;
    }

    @GetMapping
    public ResponseEntity<List<DisplayStudentDTO>> findAll() {
        return ResponseEntity.ok(this.studentApplicationService.findAll());
    }

    @PostMapping("/add-student")
    public ResponseEntity<Optional<DisplayStudentDTO>> addStudent(@RequestBody CreateStudentDTO createStudentDTO) {
        return ResponseEntity.ok(this.studentApplicationService.create(createStudentDTO));
    }

    @PutMapping("/edit-student/{id}")
    public ResponseEntity<Optional<DisplayStudentDTO>> editStudent(@PathVariable String index,
                                                                   @RequestBody CreateStudentDTO createStudentDTO) {
        return ResponseEntity.ok(this.studentApplicationService.update(index, createStudentDTO));
    }

    @DeleteMapping("delete-student/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable String index) {
        this.studentApplicationService.delete(index);

        return ResponseEntity.noContent().build();
    }
}
