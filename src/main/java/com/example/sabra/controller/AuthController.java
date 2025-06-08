package com.example.sabra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/poster/login")
    public String posterLogin() {
        return "Auth/login"; // Points to login.html in templates/Auth folder
    }

    @GetMapping("/seeker/login")
    public String seekerLogin() {
        return "Auth/login"; // Points to login.html in templates/Auth folder
    }

    @GetMapping("/poster/register")
    public String posterRegister() {
        return "Auth/register"; // Points to register.html in templates/Auth folder
    }

    @GetMapping("/seeker/register")
    public String seekerRegister() {
        return "Auth/register"; // Points to register.html in templates/Auth folder
    }
} 