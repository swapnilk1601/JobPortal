package com.jobportal.demo.controller;

import com.jobportal.demo.entity.Job;
import com.jobportal.demo.service.JobService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping
    public Job createJob(@RequestBody Job job) {
        return jobService.createJob(job);
    }

    @GetMapping
    public List<Job> getAllJobs() {
        return jobService.getAllJobs();
    }

    @GetMapping("/search")
    public List<Job> searchJobs(@RequestParam String keyword) {
        return jobService.searchJobs(keyword);
    }
    @DeleteMapping("/{id}")
    public String deleteJob(@PathVariable Long id) {

        jobService.deleteJob(id);

        return "Job deleted successfully";
    }
}