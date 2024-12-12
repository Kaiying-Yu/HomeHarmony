package hh.homeharmony.controller;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import hh.homeharmony.model.User;
import hh.homeharmony.service.UserService;

/**
 * Unit tests for the UserController class.
 * Tests all user-related endpoints including retrieval, creation, updating, and deletion of users.
 * Uses Mockito to mock UserService dependency.
 */
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private User testUser;

    /**
     * Sets up test fixtures before each test method.
     * Initializes mock objects and creates a test user.
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        
        // Setup test user data
        testUser = new User();
        testUser.setId(1);
        testUser.setUsername("testUser");
        testUser.setPassword("password123");
        testUser.setSpaceId(1);
    }

    /**
     * Tests the retrieval of a user by ID.
     * Verifies that the response contains the correct user details.
     */
    @Test
    public void getUserById_ValidId_ShouldReturnUser() {
        // Arrange
        when(userService.getUserById(1)).thenReturn(testUser);

        // Act
        ResponseEntity<Map<String, Object>> response = userController.getUserById(1);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("success", response.getBody().get("status"));
        assertEquals(testUser, response.getBody().get("data"));
        assertEquals(1, response.getBody().get("spaceId"));
        verify(userService).getUserById(1);
    }

    /**
     * Tests the retrieval of a user by ID when the user does not exist.
     * Verifies that the response contains an error message.
     */
    @Test
    public void getUserById_NonExistentId_ShouldReturnError() {
        // Arrange
        when(userService.getUserById(999)).thenThrow(new IllegalArgumentException("User not found"));

        // Act
        ResponseEntity<Map<String, Object>> response = userController.getUserById(999);

        // Assert
        assertEquals(500, response.getStatusCodeValue());
        assertEquals("error", response.getBody().get("status"));
        assertEquals("User not found", response.getBody().get("message"));
        verify(userService).getUserById(999);
    }

    /**
     * Tests the creation of a new user.
     * Verifies that the response contains the created user.
     */
    @Test
    public void createUser_ValidUser_ShouldReturnUser() {
        // Act
        ResponseEntity<User> response = userController.createUser(testUser);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(testUser, response.getBody());
        verify(userService).createUser(testUser);
    }

    /**
     * Tests the updating of an existing user.
     * Verifies that the response contains the updated user.
     */
    @Test
    public void updateUser_ValidId_ShouldReturnUpdatedUser() {
        // Arrange
        when(userService.getUserById(1)).thenReturn(testUser);

        // Act
        ResponseEntity<User> response = userController.updateUser(1, testUser);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(testUser, response.getBody());
        verify(userService).updateUser(testUser);
    }

    /**
     * Tests updating a user that does not exist.
     * Verifies that a 404 Not Found response is returned.
     */
    @Test
    public void updateUser_NonExistentId_ShouldReturnNotFound() {
        // Arrange
        when(userService.getUserById(1)).thenThrow(new IllegalArgumentException("User not found"));

        // Act
        ResponseEntity<User> response = userController.updateUser(1, testUser);

        // Assert
        assertEquals(404, response.getStatusCodeValue());
        verify(userService).getUserById(1);
    }

    /**
     * Tests the deletion of a user by ID.
     * Verifies that a 200 OK response is returned when deletion is successful.
     */
    @Test
    public void deleteUser_ValidId_ShouldReturnOk() {
        // Arrange
        when(userService.getUserById(1)).thenReturn(testUser);

        // Act
        ResponseEntity<Void> response = userController.deleteUser(1);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        verify(userService).deleteUser(1);
    }

    /**
     * Tests the deletion of a user that does not exist.
     * Verifies that a 404 Not Found response is returned.
     */
    @Test
    public void deleteUser_NonExistentId_ShouldReturnNotFound() {
        // Arrange
        when(userService.getUserById(999)).thenThrow(new IllegalArgumentException("User not found"));

        // Act
        ResponseEntity<Void> response = userController.deleteUser(999);

        // Assert
        assertEquals(404, response.getStatusCodeValue());
        verify(userService).getUserById(999);
    }
} 