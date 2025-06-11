package com.example.sabra.controller;

import com.example.sabra.model.Application;
import com.example.sabra.model.Job;
import com.example.sabra.model.User;
import com.example.sabra.service.ApplicationService;
import com.example.sabra.service.JobService;
import com.example.sabra.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private JobService jobService;

    @Autowired
    private UserService userService;

    // Seeker: Apply to a job
    @PostMapping("/apply/{jobId}")
    public String applyToJob(@PathVariable Long jobId, @RequestParam("cv") MultipartFile cvFile, 
                             Authentication authentication, RedirectAttributes redirectAttributes) {
        String email = authentication.getName();
        User user = userService.findByEmail(email);
        Job job = jobService.getJobById(jobId);

        if (job == null) {
            redirectAttributes.addFlashAttribute("error", "Job not found.");
            return "redirect:/seeker/dashboard";
        }

        if (applicationService.hasApplied(user, job)) {
            redirectAttributes.addFlashAttribute("error", "You have already applied to this job.");
            return "redirect:/jobs/" + jobId;
        }

        // Handle CV file upload
        String cvPath = saveCvFile(cvFile, user.getId(), jobId);
        Application application = new Application(job, user, cvPath);
        applicationService.saveApplication(application);

        redirectAttributes.addFlashAttribute("message", "Application submitted successfully!");
        return "redirect:/jobs/" + jobId;
    }

    // Employer: View applications for a job
    @GetMapping("/job/{jobId}")
    public String viewApplications(@PathVariable Long jobId, Model model, Authentication authentication) {
        Job job = jobService.getJobById(jobId);
        if (job == null || !job.getPoster().getEmail().equals(authentication.getName())) {
            return "redirect:/poster/dashboard";
        }
        model.addAttribute("job", job);
        model.addAttribute("applications", applicationService.getApplicationsByJob(job));
        return "poster/ViewApplications";
    }

    // Employer: Accept or reject an application
    @PostMapping("/{id}/status")
    public String updateApplicationStatus(@PathVariable Long id, @RequestParam String action, 
                                          RedirectAttributes redirectAttributes) {
        Application application = applicationService.getApplicationById(id);
        if (application == null) {
            redirectAttributes.addFlashAttribute("error", "Application not found.");
            return "redirect:/poster/dashboard";
        }

        if ("accept".equalsIgnoreCase(action)) {
            applicationService.updateApplicationStatus(id, Application.Status.ACCEPTED);
            redirectAttributes.addFlashAttribute("message", "Application accepted.");
        } else if ("reject".equalsIgnoreCase(action)) {
            applicationService.updateApplicationStatus(id, Application.Status.REJECTED);
            redirectAttributes.addFlashAttribute("message", "Application rejected.");
        }

        return "redirect:/applications/job/" + application.getJob().getId();
    }

    // Seeker: View my applications
    @GetMapping("/my-applications")
    public String viewMyApplications(Model model, Authentication authentication) {
        User user = userService.findByEmail(authentication.getName());
        model.addAttribute("applications", applicationService.getApplicationsByUser(user));
        return "seeker/MyApplications";
    }

    // Helper method to save CV file
    private String saveCvFile(MultipartFile file, Long userId, Long jobId) {
        if (file.isEmpty()) {
            return null;
        }
        try {
            // Define a path to save CVs (adjust based on your environment)
            String uploadDir = "uploads/cvs/" + userId + "/";
            String fileName = "cv_job_" + jobId + "_" + System.currentTimeMillis() + "_" + file.getOriginalFilename();
            File destination = new File(uploadDir + fileName);
            destination.getParentFile().mkdirs(); // Create directories if they don't exist
            file.transferTo(destination);
            return destination.getPath();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
} 