package com.orbitel.jobportal.controller;

import com.orbitel.jobportal.dto.UserRegistrationDTO;
import com.orbitel.jobportal.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserRegistrationDTO());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") @Valid UserRegistrationDTO userDto,
                               BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register";
        }
        if (userService.findByEmail(userDto.getEmail()).isPresent()) {
            result.rejectValue("email", "error.email", "An account with that email already exists");
            return "register";
        }
        userService.register(userDto);
        return "redirect:/login?success";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
}
