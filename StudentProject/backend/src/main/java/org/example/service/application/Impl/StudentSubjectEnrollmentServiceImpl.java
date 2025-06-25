package org.example.service.application.Impl;

import jakarta.transaction.Transactional;
import org.example.model.*;
import org.example.model.DTOs.subjectEnrollmentDTO.SubjectEnrollmentDTO;
import org.example.repository.*;
import org.example.service.application.StudentSubjectEnrollmentService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
public class StudentSubjectEnrollmentServiceImpl implements StudentSubjectEnrollmentService {
    private final StudentSubjectEnrollmentRepository studentSubjectEnrollmentRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final SemesterManagementRepository semesterRepository;
    private final CourseRepository courseRepository;

    public StudentSubjectEnrollmentServiceImpl(StudentSubjectEnrollmentRepository studentSubjectEnrollmentRepository, StudentRepository studentRepository, SubjectRepository subjectRepository, SemesterManagementRepository semesterRepository, CourseRepository courseRepository) {
        this.studentSubjectEnrollmentRepository = studentSubjectEnrollmentRepository;
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
        this.semesterRepository = semesterRepository;
        this.courseRepository = courseRepository;
    }

    public List<SubjectEnrollmentDTO> getEnrollmentsForStudent(String studentIndex) {
        Student student = studentRepository.findByIndex(studentIndex);
        List<StudentSubjectEnrollment> enrollments = studentSubjectEnrollmentRepository.findByStudent(student);
        return enrollments.stream()
                .map(enrollment -> new SubjectEnrollmentDTO(
                        enrollment.getStudent().getIndex(),
                        enrollment.getSubject().getId(),
                        enrollment.getNumEnrollments() != null ? enrollment.getNumEnrollments().intValue() : 0,
                        enrollment.getProfessorId(),
                        enrollment.getSubject().getName(),  // or joinedSubject display name
                        null
                ))
                .collect(Collectors.toList());
    }



    @Override
    public void allocateProfessor(List<Course> subjectCourses) {
        List<JoinedSubject> subjects = subjectCourses.stream()
                .map(Course::getJoinedSubject)
                .distinct()
                .toList();
        if (subjects.size()!=1){
            throw new IllegalArgumentException("All courses must be for same subject");
        }

        JoinedSubject subject = subjects.get(0);
        List<StudentSubjectEnrollment> enrollments = studentSubjectEnrollmentRepository
                .findByJoinedSubjectAndSemesterOrderByStudentName(
                        subject,
                        subjectCourses.get(0).getSemester());


    }
}