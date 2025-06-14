package org.example.service.application.Impl;

import org.example.model.DTOs.subjectDTO.CreateSubjectDTO;
import org.example.model.DTOs.subjectDTO.DisplaySubjectDTO;
import org.example.model.Professor;
import org.example.model.Semester;
import org.example.model.Student;
import org.example.model.Subject;
import org.example.service.application.SubjectApplicationService;
import org.example.service.domain.ProfessorDomainService;
import org.example.service.domain.StudentDomainService;
import org.example.service.domain.SubjectDomainService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubjectApplicationServiceImpl implements SubjectApplicationService {

    private final SubjectDomainService subjectDomainService;
    private final StudentDomainService studentDomainService;
    private final ProfessorDomainService professorDomainService;

    public SubjectApplicationServiceImpl(SubjectDomainService subjectDomainService, StudentDomainService studentDomainService, ProfessorDomainService professorDomainService) {
        this.subjectDomainService = subjectDomainService;
        this.studentDomainService = studentDomainService;
        this.professorDomainService = professorDomainService;
    }

    @Override
    public List<DisplaySubjectDTO> findAll() {
        return this.subjectDomainService.findAll().stream()
                .map(DisplaySubjectDTO::from)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DisplaySubjectDTO> findByID(String id) {
        return this.subjectDomainService.findByID(id)
                .map(DisplaySubjectDTO::from);
    }

    @Override
    public Optional<DisplaySubjectDTO> create(CreateSubjectDTO createSubjectDTO) {
        Optional<List<Student>> studentsOpt = studentDomainService.findAllByIndexes(createSubjectDTO.studentIds());
        Optional<List<Professor>> professorsOpt = professorDomainService.findAllByIds(createSubjectDTO.professorIds());

        if (studentsOpt.isPresent() && professorsOpt.isPresent()) {
            Subject subject = createSubjectDTO.toSubject(
                    studentsOpt.get(),
                    professorsOpt.get()
            );

            return subjectDomainService.create(subject)
                    .map(DisplaySubjectDTO::from);
        }

        return Optional.empty();
    }


    @Override
    public Optional<DisplaySubjectDTO> update(String id, CreateSubjectDTO createSubjectDTO) {
        Optional<List<Student>> studentsOpt = studentDomainService.findAllByIndexes(createSubjectDTO.studentIds());
        Optional<List<Professor>> professorsOpt = professorDomainService.findAllByIds(createSubjectDTO.professorIds());

        if (studentsOpt.isPresent() && professorsOpt.isPresent()) {
            Subject subject = createSubjectDTO.toSubject(
                    studentsOpt.get(),
                    professorsOpt.get()
            );

            return subjectDomainService.create(subject)
                    .map(DisplaySubjectDTO::from);
        }

        return Optional.empty();
    }

    @Override
    public void delete(String id) {
        this.subjectDomainService.delete(id);
    }
}
