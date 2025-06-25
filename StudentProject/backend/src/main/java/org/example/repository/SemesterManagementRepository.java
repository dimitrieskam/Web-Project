package org.example.repository;


import org.example.model.Semester;
import org.example.model.enumerations.SemesterState;
import org.example.model.enumerations.SemesterType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface SemesterManagementRepository extends JpaSpecificationRepository<Semester, String> {

}
