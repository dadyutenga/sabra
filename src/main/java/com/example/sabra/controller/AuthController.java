package com.example.sabra.controller;

import com.example.sabra.model.User;
import com.example.sabra.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/poster/login")
    public String posterLogin() {
        return "Auth/poster/login"; // Points to login.html in templates/Auth/poster folder
    }

    @GetMapping("/seeker/login")
    public String seekerLogin() {
        return "Auth/seeker/login"; // Points to login.html in templates/Auth/seeker folder
    }

    @GetMapping("/poster/register")
    public String posterRegister() {
        return "Auth/poster/register"; // Points to register.html in templates/Auth/poster folder
    }

    @GetMapping("/seeker/register")
    public String seekerRegister() {
        return "Auth/seeker/register"; // Points to register.html in templates/Auth/seeker folder
    }

    @PostMapping("/seeker/register")
    public String registerSeeker(
            @RequestParam String fullName,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String profession,
            RedirectAttributes redirectAttributes) {
        try {
            User user = new User(email, email, password, fullName, profession, null, User.Role.ROLE_SEEKER);
            userService.registerUser(user);
            redirectAttributes.addFlashAttribute("message", "Registration successful! Please login.");
            return "redirect:/auth/seeker/login";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/auth/seeker/register";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Registration failed. Please try again.");
            return "redirect:/auth/seeker/register";
        }
    }

    @PostMapping("/poster/register")
    public String registerPoster(
            @RequestParam String companyName,
            @RequestParam String email,
            @RequestParam String password,
            RedirectAttributes redirectAttributes) {
        try {
            User user = new User(email, email, password, null, null, companyName, User.Role.ROLE_POSTER);
            userService.registerUser(user);
            redirectAttributes.addFlashAttribute("message", "Registration successful! Please login.");
            return "redirect:/auth/poster/login";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/auth/poster/register";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Registration failed. Please try again.");
            return "redirect:/auth/poster/register";
        }
    }
} 