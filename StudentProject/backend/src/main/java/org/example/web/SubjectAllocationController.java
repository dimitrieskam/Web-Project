package org.example.web;

import org.example.model.DTOs.TeacherSubjectAllocationDTO;

import org.example.model.DTOs.topicDTO.CreateTopicDTO;
import org.example.model.DTOs.topicDTO.DisplayTopicDTO;
import org.example.model.TeacherSubjectAllocation;
import org.example.service.application.Impl.SubjectAllocationServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/allocations")
public class SubjectAllocationController {

    private final SubjectAllocationServiceImpl subjectAllocationService;

    public SubjectAllocationController(SubjectAllocationServiceImpl subjectAllocationService) {
        this.subjectAllocationService = subjectAllocationService;
    }

    @GetMapping("/topics")
    public List<DisplayTopicDTO> getAllTopics() {
        return subjectAllocationService.getAllTopics();
    }

    @GetMapping("/topics/{topicId}")
    public ResponseEntity<DisplayTopicDTO> getTopicById(@PathVariable String topicId) {
        return subjectAllocationService.getTopicById(topicId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/professors/{professorId}/topics")
    public List<DisplayTopicDTO> getTopicsByProfessor(@PathVariable String professorId) {
        return subjectAllocationService.getTopicsByProfessor(professorId);
    }


    // GET /api/professors/{professorId}/subjects
    @GetMapping("/{professorId}/subjects")
    public ResponseEntity<List<TeacherSubjectAllocationDTO>> getSubjectsForProfessor(@PathVariable("professorId") String professorId) {
        System.out.println("Looking for allocations for professorId: " + professorId);
        try {
            List<TeacherSubjectAllocationDTO> subjects = subjectAllocationService.getTeacherSubjectAllocationsByProfessorId(professorId);
            return ResponseEntity.ok(subjects);
        } catch (Exception e) {
            e.printStackTrace();  // Logs stacktrace to console
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/{professorId}/subjects/{subjectId}/topics")
    public ResponseEntity<List<DisplayTopicDTO>> getTopicsForProfessorAndSubject(
            @PathVariable String professorId,
            @PathVariable String subjectId
    ) {
        List<DisplayTopicDTO> topics = subjectAllocationService.getTopicsByProfessorAndSubject(professorId, subjectId);
        return ResponseEntity.ok(topics);
    }

    @PostMapping("/{professorId}/subjects/{subjectId}/topics/add-topic")
    public ResponseEntity<DisplayTopicDTO> addTopicForProfessorAndSubject(
            @PathVariable String professorId,
            @PathVariable String subjectId,
            @RequestBody CreateTopicDTO topicDTO) {


            DisplayTopicDTO createdTopic = subjectAllocationService.addTopic(professorId, subjectId, topicDTO);
            return ResponseEntity.ok(createdTopic);
    }
    @PutMapping("/topics/{topicId}/professors/{professorId}/subjects/{subjectId}")
    public ResponseEntity<DisplayTopicDTO> updateTopic(@PathVariable String topicId,
                                                       @PathVariable String professorId,
                                                       @PathVariable String subjectId,
                                                       @RequestBody CreateTopicDTO topicDTO) {
        DisplayTopicDTO updated = subjectAllocationService.updateTopic(topicId, professorId, subjectId, topicDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/topics/{topicId}")
    public ResponseEntity<Void> deleteTopic(@PathVariable String topicId) {
        subjectAllocationService.deleteTopic(topicId);
        return ResponseEntity.noContent().build();
    }

}

