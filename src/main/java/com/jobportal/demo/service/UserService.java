package com.jobportal.demo.service;

import com.jobportal.demo.entity.User;
import com.jobportal.demo.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(User user) {

        // Every newly registered account is a normal USER
        user.setRole("USER");

        // Encrypt password before saving
        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        return userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> loginUser(String email, String password) {

        Optional<User> userOptional =
                userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            return Optional.empty();
        }

        User user = userOptional.get();

        /*
         * Supports both:
         * 1. New BCrypt passwords
         * 2. Existing old plaintext passwords
         *
         * If an old plaintext password is used successfully,
         * it will automatically be converted to BCrypt.
         */

        boolean passwordMatches;

        if (user.getPassword().startsWith("$2a$")
                || user.getPassword().startsWith("$2b$")
                || user.getPassword().startsWith("$2y$")) {

            passwordMatches =
                    passwordEncoder.matches(
                            password,
                            user.getPassword()
                    );

        } else {

            // Old plaintext password
            passwordMatches =
                    user.getPassword().equals(password);

            if (passwordMatches) {

                // Convert old password to BCrypt
                user.setPassword(
                        passwordEncoder.encode(password)
                );

                userRepository.save(user);
            }
        }

        if (passwordMatches) {
            return Optional.of(user);
        }

        return Optional.empty();
    }
}