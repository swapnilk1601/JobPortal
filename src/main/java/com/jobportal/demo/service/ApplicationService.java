package com.jobportal.demo.service;

import com.jobportal.demo.dto.ApplicationDetails;
import com.jobportal.demo.entity.Application;
import com.jobportal.demo.entity.Job;
import com.jobportal.demo.entity.User;
import com.jobportal.demo.repository.ApplicationRepository;
import com.jobportal.demo.repository.JobRepository;
import com.jobportal.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    private final JobRepository jobRepository;

    public ApplicationService(
            ApplicationRepository applicationRepository,
            UserRepository userRepository,
            JobRepository jobRepository) {

        this.applicationRepository = applicationRepository;
        this.userRepository = userRepository;
        this.jobRepository = jobRepository;
    }

    public Application applyForJob(Application application) {

        boolean alreadyApplied =
                applicationRepository.existsByUserIdAndJobId(
                        application.getUserId(),
                        application.getJobId()
                );

        if (alreadyApplied) {
            throw new RuntimeException(
                    "You have already applied for this job."
            );
        }

        application.setStatus("Applied");

        return applicationRepository.save(application);
    }

    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    public List<Application> getApplicationsByUser(Long userId) {
        return applicationRepository.findByUserId(userId);
    }

    public List<ApplicationDetails> getApplicationDetails() {

        List<Application> applications =
                applicationRepository.findAll();

        List<ApplicationDetails> details = new ArrayList<>();

        for (Application application : applications) {

            Optional<User> user =
                    userRepository.findById(application.getUserId());

            Optional<Job> job =
                    jobRepository.findById(application.getJobId());

            if (user.isPresent() && job.isPresent()) {

                User u = user.get();
                Job j = job.get();

                details.add(
                        new ApplicationDetails(
                                application.getId(),
                                u.getName(),
                                u.getEmail(),
                                j.getTitle(),
                                j.getCompany(),
                                application.getStatus()
                        )
                );
            }
        }

        return details;
    }
}