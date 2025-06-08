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
        return "redirect:/auth/poster/login";
    }
    
    @GetMapping("/job-seekers")
    public String jobSeekers() {
        return "redirect:/auth/seeker/login";
    }
}
