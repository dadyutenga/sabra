package com.example.sabra.controller;

import com.example.sabra.model.Job;
import com.example.sabra.model.User;
import com.example.sabra.service.JobService;
import com.example.sabra.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @Autowired
    private UserService userService;

    // Employer: Show form to post a new job
    @GetMapping("/post")
    public String showPostJobForm(Model model) {
        model.addAttribute("job", new Job());
        return "poster/PostJob";
    }

    // Employer: Handle job posting
    @PostMapping("/post")
    public String postJob(@ModelAttribute Job job, Authentication authentication, RedirectAttributes redirectAttributes) {
        String email = authentication.getName();
        User poster = userService.findByEmail(email);
        job.setPoster(poster);
        jobService.saveJob(job);
        redirectAttributes.addFlashAttribute("message", "Job posted successfully!");
        return "redirect:/poster/dashboard";
    }

    // Seeker: View all available jobs
    @GetMapping("/search")
    public String searchJobs(Model model) {
        model.addAttribute("jobs", jobService.getAllJobs());
        return "seeker/SearchJobs";
    }

    // Seeker: View job details and apply
    @GetMapping("/{id}")
    public String viewJob(@PathVariable Long id, Model model, Authentication authentication) {
        Job job = jobService.getJobById(id);
        if (job == null) {
            return "redirect:/seeker/dashboard";
        }
        model.addAttribute("job", job);
        if (authentication != null) {
            User user = userService.findByEmail(authentication.getName());
            // Check if user has already applied - requires ApplicationService
            model.addAttribute("user", user);
        }
        return "seeker/JobDetails";
    }
} 