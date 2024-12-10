package hh.homeharmony.controller;

import java.util.HashMap;
import java.util.List;
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

import hh.homeharmony.model.Chore;
import hh.homeharmony.service.ChoreService;
import hh.homeharmony.service.UserService;

/**
 * REST Controller for managing chore-related operations.
 * Provides endpoints for CRUD operations on chores and chore assignments.
 * All endpoints are mapped under the "/chores" base path.
 */
@RestController
@RequestMapping("/chores")
public class ChoreController {
    // Service layer dependencies
    // Inject the ChoreService for handling chore-related operations
    @Autowired
    private ChoreService choreService;

    // Inject the UserService for handling user-related operations
    @Autowired
    private UserService userService;

    /**
     * Creates a new chore.
     *
     * @param chore The chore object to be created
     * @return ResponseEntity containing the created chore if successful
     */
    // The ResponseEntity needs to contain the created chore because the frontend needs to display it.
    @PostMapping
    public ResponseEntity<Chore> createChore(@RequestBody Chore chore) {
        return ResponseEntity.ok(choreService.createChore(chore));
    }

    /**
     * Updates an existing chore.
     *
     * @param id The ID of the chore to update
     * @param chore The updated chore data
     * @return ResponseEntity containing the updated chore
     */
    @PutMapping("/{id}")
    public ResponseEntity<Chore> updateChore(@PathVariable Integer id, @RequestBody Chore chore) {
        chore.setId(id);  // Ensure the chore has the correct ID
        return ResponseEntity.ok(choreService.updateChore(chore));
    }

    /**
     * Deletes a chore by its ID.
     *
     * @param id The ID of the chore to delete
     * @return ResponseEntity with no content if deletion is successful
     */
    // The ResponseEntity have no content because the frontend doesn't need to display deleted chore.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChore(@PathVariable Integer id) {
        choreService.deleteChore(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Retrieves all chores in the system.
     * Returns a map containing status and data/error message.
     *
     * @return ResponseEntity containing a map with all chores or error details
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllChores() {
        try {
            List<Chore> chores = choreService.getAllChores();
            // Create success response
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("data", chores);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Create error response
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    /**
     * Assigns a user to a specific chore.
     *
     * @param choreId The ID of the chore to be assigned
     * @param userId The ID of the user to assign to the chore
     * @return ResponseEntity containing success/error status and message
     */
    @PutMapping("/{choreId}/assign/{userId}")
    public ResponseEntity<Map<String, Object>> assignUserToChore(
            @PathVariable Integer choreId,
            @PathVariable Integer userId) {
        try {
            choreService.assignUserToChore(choreId, userId);
            // Create success response
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "User assigned successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Create error response
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    /**
     * Marks a chore as completed and awards points to the assigned user.
     *
     * @param choreId The ID of the chore to mark as completed
     * @return ResponseEntity containing success/error status and message
     */
    @PutMapping("/{choreId}/complete")
    public ResponseEntity<Map<String, Object>> completeChore(@PathVariable Integer choreId) {
        try {
            Chore chore = choreService.completeChore(choreId);
            // Award points to assigned user if exists
            if (chore.getAssignedUser() != null) {
                userService.addPoints(chore.getAssignedUser().getId(), chore.getPoints());
            }
            
            // Create success response
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Chore completed successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Create error response
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    /**
     * Retrieves all chores assigned to a specific user.
     *
     * @param userId The ID of the user whose chores to retrieve
     * @return ResponseEntity containing a map with the user's chores or error details
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<Map<String, Object>> getChoresByUserId(@PathVariable Integer userId) {
        try {
            List<Chore> chores = choreService.getChoresByUserId(userId);
            // Create success response
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("data", chores);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Create error response
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}

