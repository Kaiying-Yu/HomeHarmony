package hh.homeharmony.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hh.homeharmony.model.User;
import hh.homeharmony.service.UserService;
import lombok.extern.slf4j.Slf4j;

/**
 * Controller responsible for handling authentication-related endpoints.
 * This includes user login and logout operations.
 */
@Slf4j  // Enables logging using Lombok
@RestController // Combines @Controller and @ResponseBody
@RequestMapping("/auth")  // Base path for all auth endpoints
public class AuthController {

    // @Autowired a Spring annotation for dependency injection.
    // Inject the UserService for handling user-related operations
    @Autowired
    private UserService userService;

    /**
     * Handles user login requests.
     * 
     * @param user The user credentials received in the request body
     * @return ResponseEntity containing either:
     *         - 200 OK with authenticated user details if login successful
     *         - 401 Unauthorized if credentials are invalid
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        // Log the login attempt for debugging/monitoring
        log.info("Login attempt for user: {}", user.getUsername());
        
        // Attempt to authenticate the user through UserService
        User authenticatedUser = userService.login(user);
        
        if (authenticatedUser != null) {
            // Login successful - return user details
            log.info("Login successful for user: {}", user.getUsername());
            return ResponseEntity.ok(authenticatedUser);
        } else {
            // Login failed - return error message
            log.warn("Login failed for user: {}", user.getUsername());
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    /**
     * Handles user logout requests.
     * Currently implements a simple logout mechanism that can be extended
     * for more complex logout logic (e.g., token invalidation).
     * 
     * @return ResponseEntity containing either:
     *         - 200 OK if logout successful
     *         - 500 Internal Server Error if logout fails
     */
    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        try {
            log.info("Processing logout request");
            // Logout successful- return success message
            // More complex logout logic can be added here
            return ResponseEntity.ok().body("Logged out successfully");
        } catch (Exception e) {
            // Log the error and return a failure response
            log.error("Logout failed", e);
            return ResponseEntity.internalServerError().body("Logout failed");
        }
    }
} 