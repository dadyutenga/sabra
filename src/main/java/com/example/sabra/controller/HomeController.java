package com.example.sabra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @GetMapping("/")
    public String welcome() {
        return "welcome";
    }
    
    @GetMapping("/job-posters")
    public String jobPosters() {
        return "job-posters"; // This will need a corresponding HTML file
    }
    
    @GetMapping("/job-seekers")
    public String jobSeekers() {
        return "job-seekers"; // This will need a corresponding HTML file
    }
}
