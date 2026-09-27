# 🚀 JobPortal

A full-stack Job Portal web application built using Java, Spring Boot, Spring Security, MySQL, HTML, CSS and JavaScript.

## Features

- User Registration and Login
- Secure BCrypt Password Hashing
- Browse Jobs
- Search Jobs
- View Job Details
- Apply for Jobs
- Duplicate Application Prevention
- My Applications
- Admin Dashboard
- Add Jobs
- Delete Jobs
- View Candidate Applications
- Role-Based Access Control
- Secure Logout

## Technologies

- Java
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- MySQL
- HTML
- CSS
- JavaScript
- Maven
- IntelliJ IDEA
## 📌 Project Overview

JobPortal is a full-stack web application designed to connect job seekers with available job opportunities.

Users can create an account, securely log in, browse and search for jobs, view job details, apply for jobs, and track their applications.

The application also provides an Admin Dashboard where administrators can add and delete job listings and view candidate applications.

The project demonstrates the use of Spring Boot, Spring Security, Spring Data JPA, MySQL and a responsive web interface.
## 🏗️ Project Structure

```text
JobPortal
│
├── src
│   └── main
│       ├── java
│       │   └── com.jobportal.demo
│       │       ├── config
│       │       ├── controller
│       │       ├── dto
│       │       ├── entity
│       │       ├── repository
│       │       └── service
│       │
│       └── resources
│           ├── static
│           │   ├── index.html
│           │   ├── login.html
│           │   ├── register.html
│           │   ├── jobs.html
│           │   ├── job-details.html
│           │   ├── applications.html
│           │   ├── admin-login.html
│           │   └── admin.html
│           │
│           └── application.properties
│
├── pom.xml
└── README.md

## 🔐 Security

JobPortal uses Spring Security to provide authentication and role-based authorization.

### Security Features

- User authentication using email and password
- BCrypt password hashing
- Session-based authentication
- `USER` and `ADMIN` roles
- Admin-only job creation
- Admin-only job deletion
- Protected application endpoints
- Admin-only candidate application details
- Secure logout
- Duplicate job application prevention

### Role-Based Access

```text
USER
 ├── Login
 ├── Browse Jobs
 ├── Search Jobs
 ├── View Job Details
 ├── Apply for Jobs
 └── View My Applications

ADMIN
 ├── Login
 ├── Add Jobs
 ├── Delete Jobs
 ├── Manage Job Listings
 └── View Candidate Applications

## 🗄️ Database

The application uses MySQL as the relational database.

### Database Name

```text
jobportal

### Main Tables

```text
users
jobs
applications

### Users Table

Stores user information such as:

- ID
- Name
- Email
- Password
- Role

### Jobs Table

Stores job information such as:

- ID
- Job Title
- Company
- Location
- Description
- Salary

### Applications Table

Stores:

- Application ID
- User ID
- Job ID
- Application Status

---

## 🔗 API Endpoints

### User APIs

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/users/register` | Register a new user |
| POST | `/api/users/login` | Login user |
| POST | `/api/users/logout` | Logout user |

### Job APIs

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/jobs` | Get all jobs |
| GET | `/api/jobs/search?keyword=java` | Search jobs |
| POST | `/api/jobs` | Add a new job |
| DELETE | `/api/jobs/{id}` | Delete a job |

### Application APIs

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/applications` | Apply for a job |
| GET | `/api/applications` | Get applications |
| GET | `/api/applications/details` | Get detailed candidate applications |

---

## 🚀 How to Run

### 1. Clone the Repository

```bash
git clone https://github.com/YOUR_USERNAME/jobportal.git

```bash
cd jobportal
```

### 2. Create MySQL Database

Open MySQL Workbench and create the database:

```sql
CREATE DATABASE jobportal;
```

### 3. Configure Database Connection

Open:

```text
src/main/resources/application.properties
```

Update the MySQL username and password according to your system.

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/jobportal
spring.datasource.username=root
spring.datasource.password=YOUR_MYSQL_PASSWORD

spring.jpa.hibernate.ddl-auto=update
```

Replace `YOUR_MYSQL_PASSWORD` with your MySQL password.

### 4. Run the Application

Using Maven:

```bash
mvn spring-boot:run
```

Or run `DemoApplication.java` directly from IntelliJ IDEA.

### 5. Open the Application

Open your browser and visit:

```text
http://localhost:8080
```

---

## 📸 Screenshots

Screenshots of the JobPortal application will be added here.

### 🏠 Home Page

_Add screenshot here_

### 🔐 Login Page

_Add screenshot here_

### 💼 Jobs Page

_Add screenshot here_

### 📄 Job Details

_Add screenshot here_

### 📋 My Applications

_Add screenshot here_

### 👑 Admin Dashboard

_Add screenshot here_

---

## 👨‍💻 Author

**Swapnil Kelgandre**

BCA Student

### Technologies & Interests

- Java
- Spring Boot
- Spring Security
- Web Development
- Backend Development
- MySQL
- Database Management

---

## ⭐ Project Highlights

- Full-stack Job Portal application
- REST API development
- Spring Security authentication
- BCrypt password hashing
- Role-based access control
- MySQL database integration
- Job search functionality
- Job application management
- Admin dashboard
- Responsive web interface

---

## 📄 License

This project was developed for educational and portfolio purposes.