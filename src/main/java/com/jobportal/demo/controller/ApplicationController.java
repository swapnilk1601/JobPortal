package com.jobportal.demo.controller;

import com.jobportal.demo.dto.ApplicationDetails;
import com.jobportal.demo.entity.Application;
import com.jobportal.demo.entity.User;
import com.jobportal.demo.repository.UserRepository;
import com.jobportal.demo.service.ApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    private final ApplicationService applicationService;
    private final UserRepository userRepository;

    public ApplicationController(
            ApplicationService applicationService,
            UserRepository userRepository) {

        this.applicationService = applicationService;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<?> applyForJob(
            @RequestBody Application application,
            Authentication authentication) {

        try {
            String email = authentication.getName();

            User user = userRepository.findByEmail(email)
                    .orElseThrow(() ->
                            new RuntimeException("User not found"));

            // Always use the logged-in user's ID
            application.setUserId(user.getId());

            Application savedApplication =
                    applicationService.applyForJob(application);

            return ResponseEntity.ok(savedApplication);

        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @GetMapping("/details")
    public List<ApplicationDetails> getApplicationDetails() {
        return applicationService.getApplicationDetails();
    }

    @GetMapping
    public List<Application> getAllApplications(
            @RequestParam(required = false) Long userId,
            Authentication authentication) {

        String email = authentication.getName();

        User loggedInUser = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        // Ignore userId supplied by frontend.
        // Always return applications of logged-in user.
        return applicationService.getApplicationsByUser(
                loggedInUser.getId()
        );
    }
}