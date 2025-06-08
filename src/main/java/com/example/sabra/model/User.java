package com.example.sabra.model;

import jakarta.persistence.*;

@Entity
public class User {
    public enum Role {
        ROLE_ADMIN,
        ROLE_SEEKER,  // For job seekers
        ROLE_POSTER   // For job posters/employers
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;
    private String fullName;
    private String profession;
    private String companyName;

    @Enumerated(EnumType.STRING)
    private User.Role role;

    public User() {}
    public User(String username, String email, String password, String fullName, String profession, String companyName, Role role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.profession = profession;
        this.companyName = companyName;
        this.role = role;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getProfession() { return profession; }
    public void setProfession(String profession) { this.profession = profession; }
    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
}
