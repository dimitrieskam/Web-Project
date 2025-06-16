package org.example.web;

import org.example.model.DTOs.topicDTO.CreateTopicDTO;
import org.example.model.DTOs.topicDTO.DisplayTopicDTO;
import org.example.service.application.SubjectAllocationService;
import org.example.service.application.TopicApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/topics")
public class TopicController {

    private final TopicApplicationService topicApplicationService;
    private final SubjectAllocationService subjectAllocationService;

    public TopicController(TopicApplicationService topicApplicationService, SubjectAllocationService subjectAllocationService) {
        this.topicApplicationService = topicApplicationService;
        this.subjectAllocationService = subjectAllocationService;
    }

    @GetMapping
    public ResponseEntity<List<DisplayTopicDTO>> findAll() {
        return ResponseEntity.ok(this.topicApplicationService.findAll());
    }

    @PostMapping("/add-topic")
    public ResponseEntity<Optional<DisplayTopicDTO>> addTopic(@RequestBody CreateTopicDTO createTopicDTO) {
        return ResponseEntity.ok(this.topicApplicationService.create(createTopicDTO));
    }

    @PutMapping("/edit-topic/{id}")
    public ResponseEntity<Optional<DisplayTopicDTO>> editTopic(@PathVariable String id,
                                                               @RequestBody CreateTopicDTO createTopicDTO) {
        return ResponseEntity.ok(this.topicApplicationService.update(id, createTopicDTO));
    }

    @DeleteMapping("/delete-topic/{id}")
    public ResponseEntity<Void> deleteTopic(@PathVariable String id) {
        this.topicApplicationService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{topicId}/is-closed")
    public ResponseEntity<Boolean> isClosed(@PathVariable String topicId) {
        return ResponseEntity.ok(subjectAllocationService.isClosed(topicId));
    }

    @PostMapping("/{topicId}/choose")
    public ResponseEntity<DisplayTopicDTO> chooseTopic(@PathVariable String topicId,
                                                       @RequestParam String username) throws AccessDeniedException {
        return subjectAllocationService.chooseTopic(topicId, username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
