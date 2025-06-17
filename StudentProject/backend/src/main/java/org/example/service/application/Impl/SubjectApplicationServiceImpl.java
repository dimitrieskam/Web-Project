package org.example.service.application.Impl;

import org.example.model.DTOs.subjectDTO.DisplaySubjectDTO;
import org.example.model.Subject;
import org.example.model.enumerations.SemesterType;
import org.example.service.application.SubjectApplicationService;
import org.example.service.domain.SubjectDomainService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubjectApplicationServiceImpl implements SubjectApplicationService {

    private final SubjectDomainService subjectDomainService;

    public SubjectApplicationServiceImpl(SubjectDomainService subjectDomainService) {
        this.subjectDomainService = subjectDomainService;
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
    public List<DisplaySubjectDTO> findByNameAndSemester(String name, SemesterType semesterType) {
        List<Subject> subjects = subjectDomainService.findByNameAndSemester(name, semesterType);
        return DisplaySubjectDTO.from(subjects);
    }
}
