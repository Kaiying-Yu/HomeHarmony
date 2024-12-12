package hh.homeharmony.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import hh.homeharmony.mapper.UserMapper;
import hh.homeharmony.model.User;
import hh.homeharmony.service.impl.UserServiceImpl;

/**
 * Unit tests for the UserServiceImpl class.
 * Tests all service operations including user creation, updates, deletion, authentication, and points management.
 * Uses Mockito to mock UserMapper dependency.
 */
public class UserServiceImplTest {

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

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
        testUser.setPoints(0);
    }

    /**
     * Tests user creation with valid data.
     * Verifies that the user is created successfully.
     */
    @Test
    public void createUser_WithValidData_ShouldSucceed() {
        // Act
        userService.createUser(testUser);

        // Assert
        verify(userMapper).insertUser(testUser);
    }

    /**
     * Tests user creation with null user.
     * Verifies that an IllegalArgumentException is thrown.
     */
    @Test
    public void createUser_NullUser_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            userService.createUser(null);
        });
    }

    /**
     * Tests user creation with empty username.
     * Verifies that an IllegalArgumentException is thrown.
     */
    @Test
    public void createUser_EmptyUsername_ShouldThrowException() {
        testUser.setUsername("");
        assertThrows(IllegalArgumentException.class, () -> {
            userService.createUser(testUser);
        });
    }

    /**
     * Tests retrieval of a user by ID.
     * Verifies that the correct user is returned.
     */
    @Test
    public void getUserById_ValidId_ShouldReturnUser() {
        when(userMapper.findUserById(1)).thenReturn(testUser);

        User result = userService.getUserById(1);

        assertEquals(testUser, result);
        verify(userMapper).findUserById(1);
    }

    /**
     * Tests retrieval of a user by invalid ID.
     * Verifies that an IllegalArgumentException is thrown.
     */
    @Test
    public void getUserById_InvalidId_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            userService.getUserById(null);
        });
    }

    /**
     * Tests retrieval of a user by non-existent ID.
     * Verifies that an IllegalArgumentException is thrown.
     */
    @Test
    public void getUserById_NonExistentId_ShouldThrowException() {
        when(userMapper.findUserById(999)).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> {
            userService.getUserById(999);
        });
    }

    /**
     * Tests updating an existing user's information.
     * Verifies that the user is updated successfully.
     */
    @Test
    public void updateUser_ValidUser_ShouldSucceed() {
        when(userMapper.findUserById(1)).thenReturn(testUser);

        userService.updateUser(testUser);

        verify(userMapper).updateUser(testUser);
    }

    /**
     * Tests updating a user that does not exist.
     * Verifies that an IllegalArgumentException is thrown.
     */
    @Test
    public void updateUser_NonExistentUser_ShouldThrowException() {
        when(userMapper.findUserById(1)).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> {
            userService.updateUser(testUser);
        });
    }

    /**
     * Tests deleting a user by ID.
     * Verifies that the user is deleted successfully.
     */
    @Test
    public void deleteUser_ValidId_ShouldSucceed() {
        when(userMapper.findUserById(1)).thenReturn(testUser);

        userService.deleteUser(1);

        verify(userMapper).deleteUser(1);
    }

    /**
     * Tests deleting a user with a null ID.
     * Verifies that an IllegalArgumentException is thrown.
     */
    @Test
    public void deleteUser_NullId_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            userService.deleteUser(null);
        });
    }

    /**
     * Tests deleting a non-existent user.
     * Verifies that an IllegalArgumentException is thrown.
     */
    @Test
    public void deleteUser_NonExistentId_ShouldThrowException() {
        when(userMapper.findUserById(999)).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> {
            userService.deleteUser(999);
        });
    }

    /**
     * Tests user login with valid credentials.
     * Verifies that the authenticated user is returned.
     */
    @Test
    public void login_ValidCredentials_ShouldReturnUser() {
        when(userMapper.getByUsernameAndPassword(testUser)).thenReturn(testUser);

        User result = userService.login(testUser);

        assertEquals(testUser, result);
        verify(userMapper).getByUsernameAndPassword(testUser);
    }

    /**
     * Tests user login with invalid credentials.
     * Verifies that an IllegalArgumentException is thrown.
     */
    @Test
    public void login_InvalidCredentials_ShouldThrowException() {
        when(userMapper.getByUsernameAndPassword(testUser)).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> {
            userService.login(testUser);
        });
    }

    /**
     * Tests adding points to a user's total score.
     * Verifies that the points are added correctly.
     */
    @Test
    public void addPoints_ValidUser_ShouldSucceed() {
        when(userMapper.findUserById(1)).thenReturn(testUser);

        userService.addPoints(1, 10);

        assertEquals(10, testUser.getPoints());
        verify(userMapper).updatePoints(testUser);
    }

    /**
     * Tests adding points to a non-existent user.
     * Verifies that an IllegalArgumentException is thrown.
     */
    @Test
    public void addPoints_NonExistentUser_ShouldThrowException() {
        when(userMapper.findUserById(999)).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> {
            userService.addPoints(999, 10);
        });
    }

    /**
     * Tests adding negative points to a user.
     * Verifies that an IllegalArgumentException is thrown.
     */
    @Test
    public void addPoints_NegativePoints_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            userService.addPoints(1, -5);
        });
    }
}
