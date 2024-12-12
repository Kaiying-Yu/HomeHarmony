package hh.homeharmony.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hh.homeharmony.model.Space;
import hh.homeharmony.service.SpaceService;

/**
 * REST Controller for managing Space-related operations in the HomeHarmony application.
 * Provides endpoints for creating, reading, updating, and managing spaces and their members.
 * All endpoints are mapped under the "/space" base path.
 */
@RestController
@RequestMapping("/space")
public class SpaceController {

  // @Autowired a Spring annotation for dependency injection.
  // Inject the SpaceService for handling space-related operations
  @Autowired
  private SpaceService spaceService;

  /**
   * Gets a space by its ID.
   *
   * @param id The unique identifier of the space
   * @return ResponseEntity containing either:
   *         - 200 OK with the space details if found
   *         - 404 Not Found if no space exists with the given ID
   */
  @GetMapping("/{id}")
  public ResponseEntity<Space> getSpaceById(@PathVariable Integer id) {
      Space space = spaceService.getSpaceById(id);
      if (space != null) {
          return ResponseEntity.ok(space);
      } else {
          return ResponseEntity.notFound().build();
      }
  }

  /**
   * Creates a new space.
   *
   * @param space The space object containing the details of the space to be created
   * @return ResponseEntity containing either:
   *         - 200 OK with the created space if successful
   *         - 400 Bad Request if the space data is invalid
   */
  @PostMapping
  public ResponseEntity<Space> createSpace(@RequestBody Space space) {
      try {
          // Attempt to create the space using the service layer
          Space createdSpace = spaceService.createSpace(space);
          return ResponseEntity.ok(createdSpace);
      } catch (IllegalArgumentException e) {
          // Return 400 Bad Request if space creation fails due to invalid data
          return ResponseEntity.badRequest().build();
      }
  }

  /**
   * Updates an existing space.
   *
   * @param id The unique identifier of the space to update
   * @param space The updated space data
   * @return ResponseEntity containing either:
   *         - 200 OK with the updated space if successful
   *         - 404 Not Found if no space exists with the given ID
   */
  @PutMapping("/{id}")
  public ResponseEntity<Space> updateSpace(@PathVariable Integer id, @RequestBody Space space) {
      // Check if the space exists
      Space existingSpace = spaceService.getSpaceById(id);
      if (existingSpace != null) {
          // Set the ID to ensure we're updating the correct space
          space.setId(id);
          spaceService.updateSpace(space);
          return ResponseEntity.ok(space);
      } else {
          return ResponseEntity.notFound().build();
      }
  }

  /**
   * Adds a user to an existing space.
   *
   * @param spaceId The ID of the space to add the user to
   * @param userId The ID of the user to be added
   * @return ResponseEntity containing either:
   *         - 200 OK if the user was successfully added
   *         - 400 Bad Request if the request is invalid
   *         - 500 Internal Server Error if an unexpected error occurs
   */
  @PostMapping("/{spaceId}/users/{userId}")
  public ResponseEntity<?> addUserToSpace(@PathVariable Integer spaceId, @PathVariable Integer userId) {
      try {
          // Attempt to add the user to the space
          spaceService.addUserToSpace(spaceId, userId);
          return ResponseEntity.ok().build();
      } catch (IllegalArgumentException e) {
          // Handle invalid input parameters
          return ResponseEntity.badRequest().body(e.getMessage());
      } catch (Exception e) {
          // Handle unexpected errors
          return ResponseEntity.internalServerError().body("Failed to add user to space");
      }
  }

  /**
   * Removes a user from a space.
   *
   * @param spaceId The ID of the space to remove the user from
   * @param userId The ID of the user to be removed
   * @return ResponseEntity containing either:
   *         - 200 OK with success message if the user was successfully removed
   *         - 400 Bad Request with error message if the request is invalid
   */
  @DeleteMapping("/{spaceId}/users/{userId}")
  public ResponseEntity<?> removeUserFromSpace(@PathVariable Integer spaceId, @PathVariable Integer userId) {
      try {
          // Attempt to remove the user from the space
          spaceService.removeUserFromSpace(spaceId, userId);

          // Create success response
          Map<String, Object> response = new HashMap<>();
          response.put("status", "success");
          response.put("message", "Successfully left the space");
          return ResponseEntity.ok(response);
      } catch (IllegalArgumentException | IllegalStateException e) {
          // Handle validation errors and invalid state transitions
          Map<String, Object> errorResponse = new HashMap<>();
          errorResponse.put("status", "error");
          errorResponse.put("message", e.getMessage());
          return ResponseEntity.badRequest().body(errorResponse);
      }
  }
}
