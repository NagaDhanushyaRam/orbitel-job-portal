package com.orbitel.jobportal.controller;

import com.orbitel.jobportal.entity.*;
import com.orbitel.jobportal.entity.Application.RSVPStatus;
import com.orbitel.jobportal.service.*;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/applications")
public class ApplicationController {

    @Autowired
    private JobService jobService;
    @Autowired
    private UserService userService;
    @Autowired
    private ApplicationService appService;

    
    @GetMapping("/apply/{jobId}")
    public String showApplyForm(@PathVariable Long jobId, Model model) {
        Job job = jobService.findById(jobId).orElseThrow();
        model.addAttribute("job", job);
        model.addAttribute("app", new Application());
        return "application-form";
    }

    
    @PostMapping("/apply/{jobId}")
    public String apply(
            @PathVariable Long jobId,
            @ModelAttribute Application app,
            Authentication auth) {
        User seeker = userService.findByEmail(auth.getName()).orElseThrow();
        Job job = jobService.findById(jobId).orElseThrow();
        app.setSeeker(seeker);
        app.setJob(job);
        appService.apply(app);
        return "redirect:/applications/my";
    }

    
    @GetMapping("/my")
    public String myApplications(Authentication auth, Model model) {
        User seeker = userService.findByEmail(auth.getName()).orElseThrow();
        model.addAttribute("applications", appService.findBySeeker(seeker.getId()));
        return "applications-list-seeker";
    }

    
    @GetMapping("/job/{jobId}")
    public String viewApplicants(
            @PathVariable Long jobId, Authentication auth, Model model) {
        
        Job job = jobService.findById(jobId).orElseThrow();
        User me = userService.findByEmail(auth.getName()).orElseThrow();
        if (!job.getEmployer().getId().equals(me.getId())) {
            return "redirect:/dashboard";
        }
        model.addAttribute("job", job);
        model.addAttribute("applications", appService.findByJob(jobId));
        return "applications-list-employer";
    }

    @GetMapping("/{appId}/manage")
    public String manageApplication(@PathVariable Long appId, Authentication auth, Model model) {
        Application app = appService.findById(appId).orElseThrow();
        
        if (!app.getJob().getEmployer().getEmail().equals(auth.getName())) {
            return "redirect:/dashboard";
        }
        model.addAttribute("app", app);
        model.addAttribute("statuses", ApplicationStatus.values());
        return "application-manage";
    }

    
    @PostMapping("/{appId}/manage")
    public String updateApplication(
            @PathVariable Long appId,
            @RequestParam ApplicationStatus status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime interviewAt,
            Authentication auth) {

        Application app = appService.findById(appId).orElseThrow();
        if (!app.getJob().getEmployer().getEmail().equals(auth.getName())) {
            return "redirect:/dashboard";
        }
        app.setStatus(status);
        app.setInterviewAt(interviewAt);
        appService.apply(app); 
        return "redirect:/applications/job/" + app.getJob().getId();
    }

    @GetMapping("/rsvp/{appId}")
    public String showRsvpForm(@PathVariable Long appId,
            Authentication auth,
            Model model) {
        Application app = appService.findById(appId).orElseThrow();
        
        if (!app.getSeeker().getEmail().equals(auth.getName())) {
            return "redirect:/applications/my";
        }
        model.addAttribute("app", app);
        model.addAttribute("rsvps", RSVPStatus.values());
        return "application-rsvp";
    }

    
    @PostMapping("/rsvp/{appId}")
    public String submitRsvp(@PathVariable Long appId,
            @RequestParam RSVPStatus rsvp,
            Authentication auth) {
        Application app = appService.findById(appId).orElseThrow();
        if (!app.getSeeker().getEmail().equals(auth.getName())) {
            return "redirect:/applications/my";
        }
        app.setRsvp(rsvp);
        appService.apply(app);
        return "redirect:/applications/my";
    }
}
