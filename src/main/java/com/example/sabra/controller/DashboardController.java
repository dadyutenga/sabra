package com.example.sabra.controller;

import com.example.sabra.model.User;
import com.example.sabra.service.JobService;
import com.example.sabra.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @Autowired
    private JobService jobService;

    @Autowired
    private UserService userService;

    @GetMapping("/seeker/dashboard")
    public String seekerDashboard(Authentication authentication, Model model) {
        String email = authentication.getName();
        User user = userService.findByEmail(email);
        model.addAttribute("user", user);
        return "seeker/Dashboard";
    }

    @GetMapping("/poster/dashboard")
    public String posterDashboard(Authentication authentication, Model model) {
        String email = authentication.getName();
        User user = userService.findByEmail(email);
        long jobCount = jobService.getJobCountByPoster(user);
        model.addAttribute("user", user);
        model.addAttribute("jobCount", jobCount);
        // Placeholder for application count - will be updated with ApplicationService
        model.addAttribute("applicationCount", 0);
        return "poster/Dashboard";
    }
} 