package com.orbitel.jobportal.controller;

import com.orbitel.jobportal.dto.AdminDashboardDTO;
import com.orbitel.jobportal.service.AdminService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminSvc;

    public AdminController(AdminService adminSvc) {
        this.adminSvc = adminSvc;
    }

    @GetMapping("/dashboard") 
    public String dashboard(Authentication auth, Model model) {
        AdminDashboardDTO metrics = adminSvc.getDashboardMetrics();
        model.addAttribute("metrics", metrics);
        model.addAttribute("adminEmail", auth.getName());
        return "admin-dashboard";
    }
}