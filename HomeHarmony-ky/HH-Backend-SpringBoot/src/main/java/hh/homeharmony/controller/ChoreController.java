package hh.homeharmony.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hh.homeharmony.model.Chore;
import hh.homeharmony.model.User;
import hh.homeharmony.service.ChoreService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping("/chores")
@CrossOrigin(origins = "http://localhost:7000")
public class ChoreController {
    @Autowired
    private ChoreService choreService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllChores() {
        try {
            List<Chore> chores = choreService.getAllChores();
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("data", chores);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PostMapping
    public ResponseEntity<Chore> createChore(@RequestBody Chore chore) {
        return ResponseEntity.ok(choreService.createChore(chore));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Chore> updateChore(@PathVariable Integer id, @RequestBody Chore chore) {
        chore.setId(id);
        return ResponseEntity.ok(choreService.updateChore(chore));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChore(@PathVariable Integer id) {
        choreService.deleteChore(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{choreId}/assign/{userId}")
    public ResponseEntity<Map<String, Object>> assignUserToChore(
            @PathVariable Integer choreId,
            @PathVariable Integer userId) {
        try {
            choreService.assignUserToChore(choreId, userId);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "User assigned successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PutMapping("/{choreId}/complete")
    public ResponseEntity<Map<String, Object>> completeChore(@PathVariable Integer choreId) {
        try {
            choreService.completeChore(choreId);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Chore marked as completed");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}

// Chore Request DTO
@Data
@AllArgsConstructor
@NoArgsConstructor
class ChoreRequest {
    private String choreName;
    private LocalDateTime createDate;
    private LocalDateTime dueDate;
    private String strategyType;
    private User user;

    // Getters and Setters
}
