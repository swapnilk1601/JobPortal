package com.jobportal.demo.controller;

import com.jobportal.demo.dto.UserResponse;
import com.jobportal.demo.entity.User;
import com.jobportal.demo.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    private final SecurityContextRepository securityContextRepository =
            new HttpSessionSecurityContextRepository();

    public UserController(
            UserService userService,
            AuthenticationManager authenticationManager) {

        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    // =========================
    // REGISTER
    // =========================

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(
            @RequestBody User user) {

        User savedUser =
                userService.registerUser(user);

        return ResponseEntity.ok(
                convertToResponse(savedUser)
        );
    }

    // =========================
    // LOGIN
    // =========================

    @PostMapping("/login")
    public ResponseEntity<Object> login(
            @RequestBody User user,
            HttpServletRequest request,
            HttpServletResponse response) {

        try {

            Optional<User> existingUser =
                    userService.loginUser(
                            user.getEmail(),
                            user.getPassword()
                    );

            if (existingUser.isEmpty()) {

                return ResponseEntity
                        .status(401)
                        .body("Invalid email or password");
            }

            Authentication authentication =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    user.getEmail(),
                                    user.getPassword()
                            )
                    );

            SecurityContext context =
                    SecurityContextHolder.createEmptyContext();

            context.setAuthentication(authentication);

            SecurityContextHolder.setContext(context);

            securityContextRepository.saveContext(
                    context,
                    request,
                    response
            );

            User loggedInUser =
                    existingUser.get();

            return ResponseEntity.ok(
                    convertToResponse(loggedInUser)
            );

        } catch (Exception e) {

            return ResponseEntity
                    .status(401)
                    .body("Invalid email or password");
        }
    }

    // =========================
    // LOGOUT
    // =========================

    @PostMapping("/logout")
    public ResponseEntity<String> logout(
            HttpServletRequest request) {

        HttpSession session =
                request.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        SecurityContextHolder.clearContext();

        return ResponseEntity.ok(
                "Logged out successfully"
        );
    }

    // =========================
    // CONVERT USER → RESPONSE
    // =========================

    private UserResponse convertToResponse(User user) {

        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );
    }
}