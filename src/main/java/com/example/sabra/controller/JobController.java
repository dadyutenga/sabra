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

    @GetMapping("/my-jobs")
    public String viewMyJobs(Model model, Authentication authentication) {
        String email = authentication.getName();
        User user = userService.findByEmail(email);
        model.addAttribute("jobs", jobService.getJobsByPoster(user));
        return "poster/MyJobs";
    }

    @GetMapping("/update/{id}")
    public String showUpdateJobForm(@PathVariable Long id, Model model, Authentication authentication) {
        Job job = jobService.getJobById(id);
        if (job == null || !job.getPoster().getEmail().equals(authentication.getName())) {
            return "redirect:/poster/dashboard";
        }
        model.addAttribute("job", job);
        return "poster/UpdateJob";
    }

    @PostMapping("/update/{id}")
    public String updateJob(@PathVariable Long id, @ModelAttribute Job updatedJob, Authentication authentication, RedirectAttributes redirectAttributes) {
        Job job = jobService.getJobById(id);
        if (job == null || !job.getPoster().getEmail().equals(authentication.getName())) {
            redirectAttributes.addFlashAttribute("error", "Job not found or you are not authorized to update it.");
            return "redirect:/poster/dashboard";
        }
        updatedJob.setId(id);
        updatedJob.setPoster(job.getPoster());
        updatedJob.setPostedDate(job.getPostedDate());
        jobService.saveJob(updatedJob);
        redirectAttributes.addFlashAttribute("message", "Job updated successfully!");
        return "redirect:/jobs/my-jobs";
    }

    @GetMapping("/delete/{id}")
    public String deleteJob(@PathVariable Long id, Authentication authentication, RedirectAttributes redirectAttributes) {
        Job job = jobService.getJobById(id);
        if (job == null || !job.getPoster().getEmail().equals(authentication.getName())) {
            redirectAttributes.addFlashAttribute("error", "Job not found or you are not authorized to delete it.");
            return "redirect:/jobs/my-jobs";
        }
        try {
            jobService.deleteJob(id);
            redirectAttributes.addFlashAttribute("message", "Job deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "An error occurred while deleting the job. Please try again.");
        }
        return "redirect:/jobs/my-jobs";
    }
} 