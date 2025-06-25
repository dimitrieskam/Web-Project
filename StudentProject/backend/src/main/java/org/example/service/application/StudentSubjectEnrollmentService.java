package org.example.service.application;

import jakarta.transaction.Transactional;
import org.example.model.*;
import org.example.model.DTOs.subjectEnrollmentDTO.SubjectEnrollmentDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentSubjectEnrollmentService {
    public List<SubjectEnrollmentDTO> getEnrollmentsForStudent(String studentIndex);
    @Transactional
    void allocateProfessor(List<Course> subjectCourses);
}
