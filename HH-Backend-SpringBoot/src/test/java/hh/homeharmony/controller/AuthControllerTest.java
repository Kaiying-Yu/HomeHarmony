package hh.homeharmony.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import hh.homeharmony.model.User;
import hh.homeharmony.service.UserService;

/**
 * Unit tests for the AuthController class.
 * Tests authentication-related endpoints including user login and logout operations.
 * Uses Mockito to mock UserService dependency.
 */
public class AuthControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private AuthController authController;

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
        testUser.setUsername("testUser");
        testUser.setPassword("password123");
    }

    /**
     * Tests the login functionality with valid credentials.
     * Verifies that the response contains the authenticated user details.
     */
    @Test
    public void login_ValidCredentials_ShouldReturnUser() {
        // Arrange
        when(userService.login(testUser)).thenReturn(testUser);

        // Act
        ResponseEntity<?> response = authController.login(testUser);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(testUser, response.getBody());
        verify(userService).login(testUser);
    }

    /**
     * Tests the login functionality with invalid credentials.
     * Verifies that the response contains a 401 Unauthorized status.
     */
    @Test
    public void login_InvalidCredentials_ShouldReturnUnauthorized() {
        // Arrange
        when(userService.login(any(User.class))).thenThrow(new IllegalArgumentException("Invalid username or password"));

        // Act
        ResponseEntity<?> response = authController.login(testUser);

        // Assert
        assertEquals(401, response.getStatusCodeValue());
        assertEquals("Invalid credentials", response.getBody());
        verify(userService).login(testUser);
    }

    /**
     * Tests the logout functionality.
     * Verifies that the response contains a success message.
     */
    @Test
    public void logout_ShouldReturnSuccessMessage() {
        // Act
        ResponseEntity<?> response = authController.logout();

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Logged out successfully", response.getBody());
    }

} 