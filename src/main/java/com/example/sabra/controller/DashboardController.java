package com.example.sabra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashboardController {

    @GetMapping("/seeker/dashboard")
    public String seekerDashboard() {
        return "seeker/Dashboard"; // Points to Dashboard.html in templates/seeker folder
    }

    @GetMapping("/poster/dashboard")
    public String posterDashboard() {
        return "poster/Dashboard"; // Points to Dashboard.html in templates/poster folder
    }
} 