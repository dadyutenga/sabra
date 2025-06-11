package com.example.sabra.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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
    private LocalDateTime postedDate;

    @Enumerated(EnumType.STRING)
    private JobType jobType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User poster; // The employer who posted the job

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
    private List<Application> applications;

    // Constructors
    public Job() {}

    public Job(String title, String description, String location, JobType jobType, User poster) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.jobType = jobType;
        this.poster = poster;
        this.postedDate = LocalDateTime.now();
    }

    // Getters and Setters
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
    public LocalDateTime getPostedDate() { return postedDate; }
    public void setPostedDate(LocalDateTime postedDate) { this.postedDate = postedDate; }
    public User getPoster() { return poster; }
    public void setPoster(User poster) { this.poster = poster; }
    public List<Application> getApplications() { return applications; }
    public void setApplications(List<Application> applications) { this.applications = applications; }
}
