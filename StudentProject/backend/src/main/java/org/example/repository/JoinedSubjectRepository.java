package org.example.repository;

import org.example.model.JoinedSubject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JoinedSubjectRepository extends JpaSpecificationRepository<JoinedSubject, String> {
        Page<JoinedSubject> findByNameContainingIgnoreCase(String name, Pageable pageable);
        JoinedSubject findByName(String name);
        JoinedSubject findByAbbreviation(String abbreviation);
        Page<JoinedSubject> findByMainSubject_IdContainingIgnoreCase(String mainSubject, Pageable pageable);
        Page<JoinedSubject> findByNameContainingIgnoreCaseAndMainSubject_IdContainingIgnoreCase(String name, String mainSubject, Pageable pageable);

        @Query(value = "SELECT DISTINCT js.* " +
                "FROM joined_subject js " +
                "LEFT JOIN teacher_subject_requests tsa ON js.abbreviation = tsa.subject_id " +
                "LEFT JOIN study_program_subject sps ON js.main_subject_id = sps.subject_id " +
                "WHERE tsa.id IS NOT NULL OR sps.mandatory = TRUE", nativeQuery = true)
        Page<JoinedSubject> findActivatedSubjects(Pageable pageable);

        @Query(value = "SELECT DISTINCT js.* " +
                "FROM joined_subject js " +
                "LEFT JOIN teacher_subject_requests tsa ON js.abbreviation = tsa.subject_id " +
                "LEFT JOIN study_program_subject sps ON js.main_subject_id = sps.subject_id " +
                "WHERE (tsa.id IS NOT NULL OR sps.mandatory = TRUE) " +
                "AND (js.name LIKE %:name% OR js.codes LIKE %:name%)", nativeQuery = true)
        Page<JoinedSubject> findActivatedSubjectsByName(@Param("name") String name, Pageable pageable);

        @Query(value = "SELECT DISTINCT js.* " +
                "FROM joined_subject js " +
                "LEFT JOIN teacher_subject_requests tsa ON js.abbreviation = tsa.subject_id " +
                "LEFT JOIN study_program_subject sps ON js.main_subject_id = sps.subject_id " +
                "WHERE (tsa.id IS NOT NULL OR sps.mandatory = TRUE) " +
                "AND js.semester_type = :semesterType", nativeQuery = true)
        Page<JoinedSubject> findActivatedSubjectsBySemesterType(@Param("semesterType") String semesterType, Pageable pageable);

        @Query(value = "SELECT DISTINCT js.* " +
                "FROM joined_subject js " +
                "LEFT JOIN teacher_subject_requests tsa ON js.abbreviation = tsa.subject_id " +
                "LEFT JOIN study_program_subject sps ON js.main_subject_id = sps.subject_id " +
                "WHERE (tsa.id IS NOT NULL OR sps.mandatory = TRUE) " +
                "AND (js.name LIKE %:name% OR js.codes LIKE %:name%) " +
                "AND js.semester_type = :semesterType", nativeQuery = true)
        Page<JoinedSubject> findActivatedSubjectsByNameAndSemesterType(@Param("name") String name, @Param("semesterType") String semesterType, Pageable pageable);
    }

