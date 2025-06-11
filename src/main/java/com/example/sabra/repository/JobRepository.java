package com.example.sabra.repository;

import com.example.sabra.model.Job;
import com.example.sabra.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByPoster(User poster);
    long countByPoster(User poster);
} 