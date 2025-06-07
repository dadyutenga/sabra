package com.example.sabra.model;

import jakarta.persistence.*;

@Entity
public class Job {
    public enum JobType {
        REMOTE,
        IN_PERSON,
        CONTRACT
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String location;

    @Enumerated(EnumType.STRING)
    private JobType jobType;

    // Constructors
    public Job() {}

    public Job(String title, String description, String location, JobType jobType) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.jobType = jobType;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public JobType getJobType() { return jobType; }
    public void setJobType(JobType jobType) { this.jobType = jobType; }
}
