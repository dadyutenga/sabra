package com.example.sabra.repository;

import com.example.sabra.model.Application;
import com.example.sabra.model.Job;
import com.example.sabra.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByUser(User user);
    List<Application> findByJob(Job job);
    boolean existsByUserAndJob(User user, Job job);
} 