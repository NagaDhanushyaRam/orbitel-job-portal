package com.orbitel.jobportal.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping({"/dashboard"})
    public String dashboard(Authentication auth, Model model) {
        
        String role = auth.getAuthorities()
                          .stream()
                          .findFirst()
                          .map(a -> a.getAuthority())
                          .orElse("ROLE_JOB_SEEKER");

        model.addAttribute("username", auth.getName());
        model.addAttribute("role", role);

        
        switch (role) {
            case "ROLE_EMPLOYER":
                return "dashboard-employer";
            case "ROLE_ADMIN":
                return "dashboard-admin";
            default:
                return "dashboard-seeker";
        }
    }
}
