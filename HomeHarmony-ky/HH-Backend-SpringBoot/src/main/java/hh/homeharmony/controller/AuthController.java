package hh.homeharmony.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hh.homeharmony.model.User;
import hh.homeharmony.service.UserService;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:7000")
@Slf4j
@RestController
@RequestMapping("/auth")  // Base path for all auth endpoints
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        log.info("Login attempt for user: {}", user.getUsername());
        User authenticatedUser = userService.login(user);
        
        if (authenticatedUser != null) {
            log.info("Login successful for user: {}", user.getUsername());
            return ResponseEntity.ok(authenticatedUser);
        } else {
            log.warn("Login failed for user: {}", user.getUsername());
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        try {
            log.info("Processing logout request");
            // Add any necessary logout logic here if needed
            return ResponseEntity.ok().body("Logged out successfully");
        } catch (Exception e) {
            log.error("Logout failed", e);
            return ResponseEntity.internalServerError().body("Logout failed");
        }
    }
} 