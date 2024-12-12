package hh.homeharmony.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hh.homeharmony.model.User;
import hh.homeharmony.service.UserService;

/**
 * REST Controller for managing User-related operations in the HomeHarmony application.
 * Provides endpoints for creating, reading, updating, and managing users.
 * All endpoints are mapped under the "/user" base path.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    // Inject UserService for handling business logic
    @Autowired
    private UserService userService;

    /**
     * Gets a user by their ID.
     * Returns a response containing user details and their associated space information.
     *
     * @param id The unique identifier of the user
     * @return ResponseEntity containing either:
     *         - 200 OK with user details and success status
     *         - 500 Internal Server Error with error message if retrieval fails
     * @throws IllegalArgumentException if the user is not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getUserById(@PathVariable Integer id) {
        try {
            // Attempt to retrieve user from service
            User user = userService.getUserById(id);

            // Create success response with user data
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("data", user);

            // Include space ID if user belongs to a space
            if (user.getSpaceId() != null) {
                response.put("spaceId", user.getSpaceId());
            }
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    /**
     * Creates a new user in the system.
     *
     * @param user The user object containing the details of the user to be created
     * @return ResponseEntity containing either:
     *         - 200 OK with the created user
     *         - 400 Bad Request if user data is invalid
     */
    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        // Create new user using service layer
        userService.createUser(user);
        return ResponseEntity.ok(user);
    }

    /**
     * Updates an existing user's information.
     *
     * @param id The unique identifier of the user to update
     * @param user The updated user data
     * @return ResponseEntity containing either:
     *         - 200 OK with the updated user if successful
     *         - 404 Not Found if no user exists with the given ID
     * @throws IllegalArgumentException if the user is not found or if the user data is invalid
     */
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User user) {
    try {
        User existingUser = userService.getUserById(id);
        user.setId(id); // Ensure we're updating the correct user
        userService.updateUser(user);
        return ResponseEntity.ok(user);
    } catch (IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}

    /**
     * Deletes a user from the system by their ID.
     *
     * @param id The unique identifier of the user to delete
     * @return ResponseEntity containing either:
     *         - 200 OK with no content if deletion is successful
     *         - 404 Not Found if no user exists with the given ID
     * @throws IllegalArgumentException if the user is not found
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        try {
            User existingUser = userService.getUserById(id);
            userService.deleteUser(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
