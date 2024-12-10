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

@RestController
@RequestMapping("/space")
public class SpaceController {

  // @Autowired a Spring annotation for dependency injection.
  // Inject the SpaceService for handling space-related operations
  @Autowired
  private SpaceService spaceService;

  @GetMapping("/{id}")
  public ResponseEntity<Space> getSpaceById(@PathVariable Integer id) {
    Space space = spaceService.getSpaceById(id);
    if (space != null) {
      return ResponseEntity.ok(space);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping
  public ResponseEntity<Space> createSpace(@RequestBody Space space) {
    try {
        Space createdSpace = spaceService.createSpace(space);
        return ResponseEntity.ok(createdSpace);
    } catch (IllegalArgumentException e) {
        return ResponseEntity.badRequest().build();
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<Space> updateSpace(@PathVariable Integer id, @RequestBody Space space) {
    Space existingSpace = spaceService.getSpaceById(id);
    if (existingSpace != null) {
      space.setId(id);
      spaceService.updateSpace(space);
      return ResponseEntity.ok(space);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping("/{spaceId}/users/{userId}")
  public ResponseEntity<?> addUserToSpace(@PathVariable Integer spaceId, @PathVariable Integer userId) {
    try {
        spaceService.addUserToSpace(spaceId, userId);
        return ResponseEntity.ok().build();
    } catch (IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    } catch (Exception e) {
        return ResponseEntity.internalServerError().body("Failed to add user to space");
    }
  }

  @DeleteMapping("/{spaceId}/users/{userId}")
  public ResponseEntity<?> removeUserFromSpace(@PathVariable Integer spaceId, @PathVariable Integer userId) {
    try {
        spaceService.removeUserFromSpace(spaceId, userId);
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Successfully left the space");
        return ResponseEntity.ok(response);
    } catch (IllegalArgumentException | IllegalStateException e) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("status", "error");
        errorResponse.put("message", e.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }
  }
}
