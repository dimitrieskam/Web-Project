package org.example.service.application;
import org.example.model.DTOs.SubjectAllocationDTO;

import java.util.List;
public interface SubjectAllocationService {
    List<SubjectAllocationDTO> getSubjectAllocationsForProfessor(String professorId);
}
