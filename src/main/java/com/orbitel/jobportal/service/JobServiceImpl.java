package com.orbitel.jobportal.service;

import com.orbitel.jobportal.entity.Job;
import com.orbitel.jobportal.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobs;

    // constructor injection
    public JobServiceImpl(JobRepository jobs) {
        this.jobs = jobs;
    }

    @Override
    public Job create(Job job) {
        return jobs.save(job);
    }

    @Override
    public Optional<Job> findById(Long id) {
        return jobs.findById(id);
    }

    @Override
    public List<Job> findAll() {
        return jobs.findAll();
    }

    @Override
    public List<Job> findByEmployer(Long employerId) {
        return jobs.findByEmployerId(employerId);
    }

    @Override
    public List<Job> searchJobs(String q, String location, Double minSalary, Double maxSalary) {
        
        if (q != null && q.isBlank())        q = null;
        if (location != null && location.isBlank()) location = null;
        return jobs.search(q, location, minSalary, maxSalary);
    }
}
