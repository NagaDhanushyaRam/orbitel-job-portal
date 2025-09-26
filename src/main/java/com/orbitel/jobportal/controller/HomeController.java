package com.orbitel.jobportal.controller;

import com.orbitel.jobportal.entity.Job;
import com.orbitel.jobportal.service.JobService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final JobService jobService;

    public HomeController(JobService jobService) {
        this.jobService = jobService;
    }

   
    @GetMapping({"/", "/home"})
    public String home(Model model) {
        List<Job> jobs = jobService.findAll();
        model.addAttribute("jobs", jobs);
        return "home";     
    }
}
