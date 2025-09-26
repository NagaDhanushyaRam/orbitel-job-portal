package com.orbitel.jobportal.repository;

import com.orbitel.jobportal.entity.Application;
import com.orbitel.jobportal.entity.ApplicationStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ApplicationRepository 
        extends JpaRepository<Application, Long> {
    List<Application> findByJobId(Long jobId);
    List<Application> findBySeekerId(Long seekerId);
    long countByStatus(ApplicationStatus status);
}
