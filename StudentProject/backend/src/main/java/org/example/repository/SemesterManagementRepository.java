//package org.example.repository;
//
//
//import org.example.model.Semester;
//import org.example.model.enumerations.SemesterState;
//import org.example.model.enumerations.SemesterType;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public interface SemesterManagementRepository extends JpaSpecificationRepository<Semester, String> {
//
//    Semester findFirstByOrderByCodeDesc();
//
//    Optional<Semester> findFirstBySemesterTypeAndStartDateLessThanOrderByStartDateDesc(
//            SemesterType semesterType,
//            LocalDate startDate);
//
//    Optional<Semester> findFirstByStateIn(List<SemesterState> states);
//
//    @Query("SELECT s FROM Semester s WHERE s.code = ?1 AND s.state != 'INACTIVE'")
//    List<Semester> findActiveSemesterByCode(String semesterCode);
//}
