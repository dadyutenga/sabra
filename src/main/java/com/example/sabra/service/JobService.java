package com.example.sabra.service;

import com.example.sabra.model.Job;
import com.example.sabra.model.User;
import com.example.sabra.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public Job saveJob(Job job) {
        return jobRepository.save(job);
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public List<Job> getJobsByPoster(User poster) {
        return jobRepository.findByPoster(poster);
    }

    public long getJobCountByPoster(User poster) {
        return jobRepository.countByPoster(poster);
    }

    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }
} 