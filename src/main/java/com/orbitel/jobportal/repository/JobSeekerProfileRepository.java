package com.orbitel.jobportal.repository;

import com.orbitel.jobportal.entity.JobSeekerProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobSeekerProfileRepository 
        extends JpaRepository<JobSeekerProfile, Long> {}

