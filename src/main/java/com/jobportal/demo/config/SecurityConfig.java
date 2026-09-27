package com.jobportal.demo.config;

import com.jobportal.demo.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(
            CustomUserDetailsService customUserDetailsService) {

        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider(
                        customUserDetailsService
                );

        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration)
            throws Exception {

        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http)
            throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .authenticationProvider(authenticationProvider())

                .authorizeHttpRequests(auth -> auth

                        // =========================
                        // PUBLIC PAGES
                        // =========================

                        .requestMatchers(
                                "/",
                                "/login",
                                "/register",
                                "/jobs",
                                "/job-details",
                                "/admin-login",
                                "/css/**",
                                "/js/**",
                                "/images/**"
                        ).permitAll()


                        // =========================
                        // PUBLIC USER APIs
                        // =========================

                        .requestMatchers(
                                "/api/users/register",
                                "/api/users/login"
                        ).permitAll()


                        // =========================
                        // JOB APIs
                        // =========================

                        // Anyone can view/search jobs
                        .requestMatchers(
                                org.springframework.http.HttpMethod.GET,
                                "/api/jobs/**"
                        ).permitAll()

                        // Only ADMIN can add jobs
                        .requestMatchers(
                                org.springframework.http.HttpMethod.POST,
                                "/api/jobs/**"
                        ).hasRole("ADMIN")

                        // Only ADMIN can delete jobs
                        .requestMatchers(
                                org.springframework.http.HttpMethod.DELETE,
                                "/api/jobs/**"
                        ).hasRole("ADMIN")


                        // =========================
                        // APPLICATION APIs
                        // =========================

                        // Admin can see detailed applications
                        .requestMatchers(
                                "/api/applications/details"
                        ).hasRole("ADMIN")

                        // Logged-in users can access applications
                        .requestMatchers(
                                "/api/applications/**"
                        ).authenticated()


                        // =========================
                        // EVERYTHING ELSE
                        // =========================

                        .anyRequest().permitAll()
                );

        return http.build();
    }
}