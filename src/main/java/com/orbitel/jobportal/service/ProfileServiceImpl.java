// ProfileServiceImpl.java
package com.orbitel.jobportal.service;

import com.orbitel.jobportal.entity.*;
import com.orbitel.jobportal.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired private JobSeekerProfileRepository seekerRepo;
    @Autowired private EmployerProfileRepository employerRepo;

    @Override
    public Optional<JobSeekerProfile> getSeekerProfile(Long userId) {
        return seekerRepo.findById(userId);
    }

    @Override
    public JobSeekerProfile saveSeekerProfile(JobSeekerProfile profile) {
        return seekerRepo.save(profile);
    }

    @Override
    public Optional<EmployerProfile> getEmployerProfile(Long userId) {
        return employerRepo.findById(userId);
    }

    @Override
    public EmployerProfile saveEmployerProfile(EmployerProfile profile) {
        return employerRepo.save(profile);
    }
}
