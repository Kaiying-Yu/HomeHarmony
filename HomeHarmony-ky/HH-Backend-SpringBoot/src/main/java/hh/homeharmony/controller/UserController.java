package hh.homeharmony.controller;

import hh.homeharmony.model.User;
import hh.homeharmony.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:7000")
@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping("/{id}")
  public ResponseEntity<User> getUserById(@PathVariable Integer id) {
    User user = userService.getUserById(id);
    if (user != null) {
      return ResponseEntity.ok(user);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping("/")
  public ResponseEntity<User> createUser(@RequestBody User user) {
    userService.createUser(user);
    return ResponseEntity.ok(user); // Consider returning ResponseEntity.created() with a location header
  }

  @PutMapping("/{id}")
  public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User user) {
    User existingUser = userService.getUserById(id);
    if (existingUser != null) {
      user.setId(id);
      userService.updateUser(user);
      return ResponseEntity.ok(user);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
    User existingUser = userService.getUserById(id);
    if (existingUser != null) {
      userService.deleteUser(id);
      return ResponseEntity.ok().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
