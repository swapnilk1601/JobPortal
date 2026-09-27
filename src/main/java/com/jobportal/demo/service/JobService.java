package com.jobportal.demo.service;

import com.jobportal.demo.entity.Job;
import com.jobportal.demo.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public Job createJob(Job job) {
        return jobRepository.save(job);
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public List<Job> searchJobs(String keyword) {
        return jobRepository
                .findByTitleContainingIgnoreCaseOrCompanyContainingIgnoreCaseOrLocationContainingIgnoreCase(
                        keyword, keyword, keyword
                );
    }

    // Delete Job
    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }
}