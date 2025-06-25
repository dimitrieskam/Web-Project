//package org.example.service.application.Impl;
//
//import jakarta.transaction.Transactional;
//import org.example.model.*;
//import org.example.model.DTOs.subjectEnrollmentDTO.SubjectEnrollmentDTO;
//import org.example.repository.*;
//import org.example.service.application.StudentSubjectEnrollmentService;
//import org.springframework.data.domain.Page;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//import static java.util.stream.Collectors.groupingBy;
//
//@Service
//public class StudentSubjectEnrollmentServiceImpl implements StudentSubjectEnrollmentService {
//    private final StudentSubjectEnrollmentRepository studentSubjectEnrollmentRepository;
//    private final StudentRepository studentRepository;
//    private final SubjectRepository subjectRepository;
//    private final SemesterManagementRepository semesterRepository;
////    private final StudentGroupRepository studentGroupRepository;
////    private final SubjectAllocationStatsRepository subjectAllocationStatsRepository;
//    private final CourseRepository courseRepository;
//
//    public StudentSubjectEnrollmentServiceImpl(StudentSubjectEnrollmentRepository studentSubjectEnrollmentRepository, StudentRepository studentRepository, SubjectRepository subjectRepository, SemesterManagementRepository semesterRepository, CourseRepository courseRepository) {
//        this.studentSubjectEnrollmentRepository = studentSubjectEnrollmentRepository;
//        this.studentRepository = studentRepository;
//        this.subjectRepository = subjectRepository;
//        this.semesterRepository = semesterRepository;
//        this.courseRepository = courseRepository;
//    }
//
//    @Override
//    public List<SubjectEnrollmentDTO> importStudentSubjects(List<SubjectEnrollmentDTO> importEnrollments, String semester) {
//        return importEnrollments.stream()
//                .map(dto->saveStudentEnrollment(dto,semester))
//                .filter(Optional::isPresent)
//                .map(Optional::get)
//                .collect(Collectors.toList());
//    }
//
//    private Optional<SubjectEnrollmentDTO> saveStudentEnrollment(SubjectEnrollmentDTO dto, String semester){
//        Student student = studentRepository.getReferenceById(dto.getCode());
//        Subject subject = subjectRepository.getReferenceById(dto.getCode());
//
//        try {
//            Semester semesterObj = semesterRepository.findById(semester).orElse(null);
//
//            if (semesterObj != null){
//                studentSubjectEnrollmentRepository.save(new StudentSubjectEnrollment(semesterObj, student, subject, dto.getNumEnrollments().shortValue()));
//                return Optional.empty();
//            }
//        } catch (Exception e){
//            dto.setMessage(e.getMessage());
//        }
//        return Optional.of(dto);
//    }
//
//    public List<StudentSubjectEnrollment> getEnrollmentsByStudentAndSubjectAndSemester(Student student, Subject subject, Semester semester){
//        return studentSubjectEnrollmentRepository.findByStudentAndSubjectAndSemester(student, subject, semester);
//    }
//
//    @Override
//    public void deleteBySemester(String semester) {
//        List<StudentSubjectEnrollment> enrollmentsToDelete = studentSubjectEnrollmentRepository.findBySemester(semesterRepository.findById(semester).get());
//        studentSubjectEnrollmentRepository.deleteAll(enrollmentsToDelete);
//    }
//
//    @Transactional
//    @Override
//    public void allocateSubjects(String semesterCode) {
//        studentSubjectEnrollmentRepository.updateJoinedSubjects(semesterCode);
//    }
//
//    @Override
//    public void allocateEnrollmentProfessors(String semesterCode) {
//        studentSubjectEnrollmentRepository.resetProfessorsInSemester(semesterCode);
//        courseRepository.findBySemesterCode(semesterCode)
//                .stream()
//                .filter(it->it.getJoinedSubject()!=null)
//                .collect(groupingBy(Course::getJoinedSubject))
//                .values()
//                .forEach(this::allocateProfessor);
//    }
//
//    @Override
//    public void allocateProfessor(List<Course> subjectCourses) {
//        List<JoinedSubject> subjects = subjectCourses.stream()
//                .map(Course::getJoinedSubject)
//                .distinct()
//                .toList();
//        if (subjects.size()!=1){
//            throw new IllegalArgumentException("All courses must be for same subject");
//        }
//
//        JoinedSubject subject = subjects.get(0);
//        List<StudentSubjectEnrollment> enrollments = studentSubjectEnrollmentRepository
//                .findByJoinedSubjectAndSemesterOrderByStudentName(
//                        subject,
//                        subjectCourses.get(0).getSemester());
//
//
//    }
//}
