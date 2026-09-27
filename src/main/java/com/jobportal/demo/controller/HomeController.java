package com.jobportal.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/jobs")
    public String jobs() {
        return "jobs";
    }

    @GetMapping("/applications")
    public String applications() {
        return "applications";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/job-details")
    public String jobDetails() {
        return "job-details";
    }
    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }
    @GetMapping("/admin-login")
    public String adminLogin() {
        return "admin-login";
    }
}