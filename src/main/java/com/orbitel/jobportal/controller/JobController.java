package com.orbitel.jobportal.controller;

import com.orbitel.jobportal.entity.Job;
import com.orbitel.jobportal.entity.Role;
import com.orbitel.jobportal.entity.User;
import com.orbitel.jobportal.service.JobService;
import com.orbitel.jobportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobService jobService;
    @Autowired
    private UserService userService;
    public JobController(JobService jobService) {
        this.jobService = jobService;
      }

    
    @GetMapping
    public String listJobs(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) Double minSalary,
            @RequestParam(required = false) Double maxSalary,
            Model model) {
        List<Job> results = jobService.searchJobs(q, location, minSalary, maxSalary);
        model.addAttribute("jobs", results);
        
        model.addAttribute("q", q);
        model.addAttribute("location", location);
        model.addAttribute("minSalary", minSalary);
        model.addAttribute("maxSalary", maxSalary);
        return "jobs-list";
    }

    
    @GetMapping("/create")
    public String showCreateForm(Authentication auth, Model model) {
        // only employers can post
        String email = auth.getName();
        User user = userService.findByEmail(email).orElseThrow();
        if (user.getRole() != Role.EMPLOYER) {
            return "redirect:/jobs";
        }
        model.addAttribute("job", new Job());
        return "job-create";
    }

    @PostMapping("/create")
    public String createJob(Authentication auth, @ModelAttribute Job job) {
        User user = userService.findByEmail(auth.getName()).orElseThrow();
        job.setEmployer(user);
        jobService.create(job);
        return "redirect:/jobs/my";
    }

    
    @GetMapping("/my")
    public String myJobs(Authentication auth, Model model) {
        User user = userService.findByEmail(auth.getName()).orElseThrow();
        List<Job> mine = jobService.findByEmployer(user.getId());
        model.addAttribute("jobs", mine);
        return "jobs-list"; 
    }

    
    @GetMapping("/{id}")
    public String viewJob(@PathVariable Long id, Model model) {
        Job job = jobService.findById(id).orElseThrow();
        model.addAttribute("job", job);
        return "job-detail";
    }
}
