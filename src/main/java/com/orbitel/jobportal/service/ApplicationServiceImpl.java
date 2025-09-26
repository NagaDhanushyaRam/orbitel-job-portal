package com.orbitel.jobportal.service;

import com.orbitel.jobportal.entity.Application;
import com.orbitel.jobportal.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired private ApplicationRepository appRepo;

    @Override
    public Application apply(Application app) {
        return appRepo.save(app);
    }

    @Override
    public Optional<Application> findById(Long id) {
        return appRepo.findById(id);
    }

    @Override
    public List<Application> findByJob(Long jobId) {
        return appRepo.findByJobId(jobId);
    }

    @Override
    public List<Application> findBySeeker(Long seekerId) {
        return appRepo.findBySeekerId(seekerId);
    }
}
