package com.orbitel.jobportal.controller;

import com.orbitel.jobportal.entity.*;
import com.orbitel.jobportal.service.ProfileService;
import com.orbitel.jobportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private UserService userService;

    
    @GetMapping
    public String viewProfile(Authentication auth, Model model) {
        User user = userService.findByEmail(auth.getName())
                .orElseThrow(() -> new IllegalStateException("User not found"));

        switch (user.getRole()) {
            case JOB_SEEKER: {
                JobSeekerProfile seeker = profileService
                        .getSeekerProfile(user.getId())
                        .orElseGet(() -> {
                            JobSeekerProfile p = new JobSeekerProfile();
                            p.setUser(user);
                            
                            p.setEducation(new ArrayList<>(List.of("")));
                            p.setExperience(new ArrayList<>(List.of("")));
                            p.setSkills(new ArrayList<>(List.of("")));
                            return p;
                        });

                
                String edCsv = String.join(", ", seeker.getEducation());
                String expCsv = String.join(", ", seeker.getExperience());
                String skCsv = String.join(", ", seeker.getSkills());

                seeker.setEducation(new ArrayList<>(List.of(edCsv)));
                seeker.setExperience(new ArrayList<>(List.of(expCsv)));
                seeker.setSkills(new ArrayList<>(List.of(skCsv)));

                model.addAttribute("seekerProfile", seeker);
                return "profile-seeker";
            }

            case EMPLOYER: {
                EmployerProfile employer = profileService
                        .getEmployerProfile(user.getId())
                        .orElseGet(() -> {
                            EmployerProfile p = new EmployerProfile();
                            p.setUser(user);
                            return p;
                        });
                model.addAttribute("employerProfile", employer);
                return "profile-employer";
            }

            default:
                return "redirect:/dashboard";
        }
    }

    
    @PostMapping
    public String saveProfile(
            Authentication auth,
            @ModelAttribute("seekerProfile") JobSeekerProfile seekerForm,
            @ModelAttribute("employerProfile") EmployerProfile employerForm) {
        User user = userService.findByEmail(auth.getName())
                .orElseThrow(() -> new IllegalStateException("User not found"));

        if (user.getRole() == Role.JOB_SEEKER) {
            JobSeekerProfile profile = profileService
                    .getSeekerProfile(user.getId())
                    .orElseGet(() -> {
                        JobSeekerProfile p = new JobSeekerProfile();
                        p.setUser(user);
                        
                        p.setEducation(new ArrayList<>());
                        p.setExperience(new ArrayList<>());
                        p.setSkills(new ArrayList<>());
                        return p;
                    });

            
            profile.setHeadline(seekerForm.getHeadline());
            profile.setSummary(seekerForm.getSummary());

            
            List<String> ed = Arrays.stream(seekerForm.getEducation().get(0).split(","))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .collect(Collectors.toList());
            List<String> ex = Arrays.stream(seekerForm.getExperience().get(0).split(","))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .collect(Collectors.toList());
            List<String> sk = Arrays.stream(seekerForm.getSkills().get(0).split(","))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .collect(Collectors.toList());

            
            profile.getEducation().clear();
            profile.getExperience().clear();
            profile.getSkills().clear();

            
            profile.getEducation().addAll(ed);
            profile.getExperience().addAll(ex);
            profile.getSkills().addAll(sk);

            
            profileService.saveSeekerProfile(profile);

        } else if (user.getRole() == Role.EMPLOYER) {
           
            EmployerProfile profile = profileService
                    .getEmployerProfile(user.getId())
                    .orElseGet(() -> {
                        EmployerProfile p = new EmployerProfile();
                        p.setUser(user);
                        return p;
                    });

            
            profile.setCompanyName(employerForm.getCompanyName());
            profile.setWebsite(employerForm.getWebsite());
            profile.setDescription(employerForm.getDescription());
            profile.setIndustry(employerForm.getIndustry());
            profile.setLocation(employerForm.getLocation());

            profileService.saveEmployerProfile(profile);
        }

        return "redirect:/profile?success";
    }
}
