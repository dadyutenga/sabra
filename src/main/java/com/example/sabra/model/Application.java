package com.example.sabra.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Application {
    public enum Status {
        PENDING,
        ACCEPTED,
        REJECTED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // The job seeker who applied

    private String cvPath; // Path to the uploaded CV file
    private LocalDateTime appliedDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Application() {}
    public Application(Job job, User user, String cvPath) {
        this.job = job;
        this.user = user;
        this.cvPath = cvPath;
        this.appliedDate = LocalDateTime.now();
        this.status = Status.PENDING;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Job getJob() { return job; }
    public void setJob(Job job) { this.job = job; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public String getCvPath() { return cvPath; }
    public void setCvPath(String cvPath) { this.cvPath = cvPath; }
    public LocalDateTime getAppliedDate() { return appliedDate; }
    public void setAppliedDate(LocalDateTime appliedDate) { this.appliedDate = appliedDate; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
}
