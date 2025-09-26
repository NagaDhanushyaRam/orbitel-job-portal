package com.orbitel.jobportal.service;

import com.orbitel.jobportal.entity.Application;
import java.util.List;
import java.util.Optional;

public interface ApplicationService {
    Application apply(Application app);
    Optional<Application> findById(Long id);
    List<Application> findByJob(Long jobId);
    List<Application> findBySeeker(Long seekerId);
}
