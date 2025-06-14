package org.example.repository;
import org.example.model.TeacherSubjectAllocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface TeacherSubjectAllocationRepository extends JpaRepository<TeacherSubjectAllocation, Long>{
    List<TeacherSubjectAllocation> findByProfessorId(String professorId);
}
