package com.orbitel.jobportal.repository;

import com.orbitel.jobportal.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
    @Query("""
      SELECT j FROM Job j
      WHERE (:q IS NULL OR LOWER(j.title) LIKE LOWER(CONCAT('%', :q, '%'))
         OR LOWER(j.description) LIKE LOWER(CONCAT('%', :q, '%')))
        AND (:location IS NULL OR LOWER(j.location) = LOWER(:location))
        AND (:minSalary IS NULL OR j.salary >= :minSalary)
        AND (:maxSalary IS NULL OR j.salary <= :maxSalary)
    """)
    List<Job> search(
      @Param("q") String q,
      @Param("location") String location,
      @Param("minSalary") Double minSalary,
      @Param("maxSalary") Double maxSalary
    );
    
    List<Job> findByEmployerId(Long employerId);
}
