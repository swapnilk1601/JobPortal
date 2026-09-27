package com.jobportal.demo.dto;

public class ApplicationDetails {

    private Long applicationId;
    private String userName;
    private String userEmail;
    private String jobTitle;
    private String company;
    private String status;

    public ApplicationDetails() {
    }

    public ApplicationDetails(
            Long applicationId,
            String userName,
            String userEmail,
            String jobTitle,
            String company,
            String status) {

        this.applicationId = applicationId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.jobTitle = jobTitle;
        this.company = company;
        this.status = status;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getCompany() {
        return company;
    }

    public String getStatus() {
        return status;
    }
}