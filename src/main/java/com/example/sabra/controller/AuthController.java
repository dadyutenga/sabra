package com.example.sabra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/poster/login")
    public String posterLogin() {
        return "Auth/poster/login"; // Points to login.html in templates/Auth/poster folder
    }

    @GetMapping("/seeker/login")
    public String seekerLogin() {
        return "Auth/seeker/login"; // Points to login.html in templates/Auth/seeker folder
    }

    @GetMapping("/poster/register")
    public String posterRegister() {
        return "Auth/poster/register"; // Points to register.html in templates/Auth/poster folder
    }

    @GetMapping("/seeker/register")
    public String seekerRegister() {
        return "Auth/seeker/register"; // Points to register.html in templates/Auth/seeker folder
    }

    @PostMapping("/seeker/register")
    public String registerSeeker(
            @RequestParam String fullName,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String profession,
            RedirectAttributes redirectAttributes) {
        // TODO: Implement user registration logic (e.g., save to database with role as JOB_SEEKER)
        // For now, simulate success
        redirectAttributes.addFlashAttribute("message", "Registration successful! Please login.");
        return "redirect:/auth/seeker/login";
    }

    @PostMapping("/poster/register")
    public String registerPoster(
            @RequestParam String companyName,
            @RequestParam String email,
            @RequestParam String password,
            RedirectAttributes redirectAttributes) {
        // TODO: Implement user registration logic (e.g., save to database with role as JOB_POSTER)
        // For now, simulate success
        redirectAttributes.addFlashAttribute("message", "Registration successful! Please login.");
        return "redirect:/auth/poster/login";
    }
} 