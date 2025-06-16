package org.example.web;

import org.example.model.DTOs.TeacherSubjectAllocationDTO;

import org.example.model.DTOs.topicDTO.CreateTopicDTO;
import org.example.model.DTOs.topicDTO.DisplayTopicDTO;
import org.example.model.TeacherSubjectAllocation;
import org.example.service.application.Impl.SubjectAllocationServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
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
    public ResponseEntity<DisplayTopicDTO> getTopicById(@PathVariable("topicId") String topicId) {
        return subjectAllocationService.getTopicById(topicId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/professors/{professorId}/topics")
    public List<DisplayTopicDTO> getTopicsByProfessor(@PathVariable("professorId") String professorId) {
        return subjectAllocationService.getTopicsByProfessor(professorId);
    }

    @GetMapping("/subjects/{subjectId}/topics")
    public List<DisplayTopicDTO> getTopicsBySubject(@PathVariable("subjectId") String subjectId) {
        return subjectAllocationService.getTopicsBySubject(subjectId);
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
            @PathVariable("professorId") String professorId,
            @PathVariable("subjectId") String subjectId
    ) {
        List<DisplayTopicDTO> topics = subjectAllocationService.getTopicsByProfessorAndSubject(professorId, subjectId);
        return ResponseEntity.ok(topics);
    }

    @PostMapping("/{professorId}/subjects/{subjectId}/topics/add-topic")
    public ResponseEntity<DisplayTopicDTO> addTopicForProfessorAndSubject(
            @PathVariable("professorId") String professorId,
            @PathVariable("subjectId") String subjectId,
            @RequestBody CreateTopicDTO topicDTO) {
        DisplayTopicDTO createdTopic = subjectAllocationService.addTopic(professorId, subjectId, topicDTO);
        return ResponseEntity.ok(createdTopic);
    }

    @PutMapping("/topics/{id}/professors/{professorId}/subjects/{subjectId}/edit-topic")
    public ResponseEntity<DisplayTopicDTO> updateTopic(@PathVariable("id") String id,
                                                       @PathVariable("professorId") String professorId,
                                                       @PathVariable("subjectId") String subjectId,
                                                       @RequestBody CreateTopicDTO topicDTO) {
        DisplayTopicDTO updated = subjectAllocationService.updateTopic(id, professorId, subjectId, topicDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/topics/delete-topic/{id}")
    public ResponseEntity<Void> deleteTopic(@PathVariable("id") String id) {
        subjectAllocationService.deleteTopic(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/topics/{topicId}/choose")
    public ResponseEntity<DisplayTopicDTO> chooseTopic(@PathVariable("topicId") String topicId) throws AccessDeniedException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        return subjectAllocationService.chooseTopic(topicId, username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
