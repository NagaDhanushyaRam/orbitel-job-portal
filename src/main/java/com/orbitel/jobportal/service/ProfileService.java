
package com.orbitel.jobportal.service;

import com.orbitel.jobportal.entity.*;

import java.util.Optional;

public interface ProfileService {
    Optional<JobSeekerProfile> getSeekerProfile(Long userId);
    JobSeekerProfile saveSeekerProfile(JobSeekerProfile profile);
    Optional<EmployerProfile> getEmployerProfile(Long userId);
    EmployerProfile saveEmployerProfile(EmployerProfile profile);
    default Optional<JobSeekerProfile> getJobSeekerProfile(Long userId) {
        
        return Optional.empty(); 
    }
}
