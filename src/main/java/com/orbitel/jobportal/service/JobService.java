package com.orbitel.jobportal.service;

import com.orbitel.jobportal.entity.Job;

import java.util.List;
import java.util.Optional;

public interface JobService {
    Job create(Job job);
    Optional<Job> findById(Long id);
    List<Job> findAll();
    List<Job> findByEmployer(Long employerId);
    List<Job> searchJobs(String q, String location, Double minSalary, Double maxSalary);
}
