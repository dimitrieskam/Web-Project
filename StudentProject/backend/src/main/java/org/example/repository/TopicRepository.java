package org.example.repository;

import org.example.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TopicRepository extends JpaRepository<Topic, String> {

    List<Topic> findByProfessor_IdAndJoinedSubject_MainSubject_Id(String professorId, String subjectId);

    List<Topic> findByProfessor_Id(String professorId);

    List<Topic> findByJoinedSubject_MainSubject_Id(String subjectId);

    @Query("SELECT DISTINCT t FROM Topic t JOIN t.teams team JOIN team.members student WHERE student.index = :studentIndex")
    List<Topic> findTopicsByStudentIndex(@Param("studentIndex") String studentIndex);
}
