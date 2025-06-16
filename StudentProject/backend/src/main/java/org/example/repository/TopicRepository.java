package org.example.repository;

import org.example.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicRepository extends JpaRepository<Topic, String> {
    List<Topic> findByProfessor_IdAndSubject_Id(String professorId, String subjectId);
    List<Topic> findByProfessorId(String professorId);
}
