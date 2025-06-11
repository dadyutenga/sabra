package com.example.sabra.service;

import com.example.sabra.model.Application;
import com.example.sabra.model.Job;
import com.example.sabra.model.User;
import com.example.sabra.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    public Application saveApplication(Application application) {
        return applicationRepository.save(application);
    }

    public List<Application> getApplicationsByUser(User user) {
        return applicationRepository.findByUser(user);
    }

    public List<Application> getApplicationsByJob(Job job) {
        return applicationRepository.findByJob(job);
    }

    public boolean hasApplied(User user, Job job) {
        return applicationRepository.existsByUserAndJob(user, job);
    }

    public Application getApplicationById(Long id) {
        return applicationRepository.findById(id).orElse(null);
    }

    public void updateApplicationStatus(Long applicationId, Application.Status status) {
        Application application = getApplicationById(applicationId);
        if (application != null) {
            application.setStatus(status);
            applicationRepository.save(application);
        }
    }
} 