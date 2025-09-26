package com.orbitel.jobportal.repository;

import com.orbitel.jobportal.entity.EmployerProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployerProfileRepository 
        extends JpaRepository<EmployerProfile, Long> {}