package hh.homeharmony.model;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Space class.
 * Tests all functionality including constructors, getters/setters, and user management.
 * Ensures proper handling of edge cases and exceptions.
 */
public class SpaceTest {
    private Space space;
    private User user1;
    private User user2;

    /**
     * Sets up test fixtures before each test method.
     * Creates a space and two test users with different states.
     */
    @BeforeEach
    public void setUp() {
        user1 = new User("User1", "user1@example.com", "password123", 0);
        user1.setId(1);
        user2 = new User("User2", "user2@example.com", "password123",0);
        user2.setId(2);
        
        space = new Space("Test Space");
    }

    /**
     * Tests the default constructor.
     * Verifies that a new space is created with an empty user list.
     */
    @Test
    public void testDefaultConstructor() {
        Space defaultSpace = new Space();
        assertNotNull(defaultSpace.getUsers());
        assertTrue(defaultSpace.getUsers().isEmpty());
    }

    /**
     * Tests the parameterized constructor with valid and invalid inputs.
     */
    @Test
    public void testParameterizedConstructor() {
        assertEquals("Test Space", space.getName());
        assertNotNull(space.getUsers());
        assertTrue(space.getUsers().isEmpty());

        // Test null name
        assertThrows(IllegalArgumentException.class, () -> new Space(null));
        
        // Test empty name
        assertThrows(IllegalArgumentException.class, () -> new Space("  "));
    }

    /**
     * Tests name getter and setter with valid and invalid values.
     */
    @Test
    public void testNameGetterSetter() {
        space.setName("New Space Name");
        assertEquals("New Space Name", space.getName());

        // Test null name
        assertThrows(IllegalArgumentException.class, () -> space.setName(null));
        
        // Test empty name
        assertThrows(IllegalArgumentException.class, () -> space.setName(""));
    }

    /**
     * Tests user list getter and setter.
     */
    @Test
    public void testUsersGetterSetter() {
        List<User> newUsers = new ArrayList<>();
        newUsers.add(user1);
        newUsers.add(user2);
        
        space.setUsers(newUsers);
        assertEquals(2, space.getUsers().size());
        assertTrue(space.getUsers().contains(user1));
        assertTrue(space.getUsers().contains(user2));

        // Test setting null list (should create empty list)
        space.setUsers(null);
        assertNotNull(space.getUsers());
        assertTrue(space.getUsers().isEmpty());
    }

    /**
     * Tests adding users to the space.
     */
    @Test
    public void testAddUser() {
        space.addUser(user1);
        assertTrue(space.getUsers().contains(user1));
        assertEquals(1, space.getUsers().size());

        // Add second user
        space.addUser(user2);
        assertTrue(space.getUsers().contains(user2));
        assertEquals(2, space.getUsers().size());

        // Test adding null user
        assertThrows(IllegalArgumentException.class, () -> space.addUser(null));

        // Test adding duplicate user
        assertThrows(IllegalStateException.class, () -> space.addUser(user1));
    }

    /**
     * Tests removing users from the space.
     */
    @Test
    public void testRemoveUser() {
        // Setup
        space.addUser(user1);
        space.addUser(user2);
        
        // Test removal
        space.removeUser(user1);
        assertFalse(space.getUsers().contains(user1));
        assertTrue(space.getUsers().contains(user2));
        assertEquals(1, space.getUsers().size());

        // Test removing null user
        assertThrows(IllegalArgumentException.class, () -> space.removeUser(null));

        // Test removing non-existent user
        User user3 = new User("User3", "user3@example.com", "password123",0);
        assertThrows(IllegalStateException.class, () -> space.removeUser(user3));
    }

    /**
     * Tests the toString method with different space states.
     */
    @Test
    public void testToString() {
        space.setId(1);
        
        // Empty space
        String emptySpaceString = space.toString();
        assertTrue(emptySpaceString.contains("Test Space"));
        assertTrue(emptySpaceString.contains("0 members"));

        // Space with users
        space.addUser(user1);
        space.addUser(user2);
        String fullSpaceString = space.toString();
        assertTrue(fullSpaceString.contains("Test Space"));
        assertTrue(fullSpaceString.contains("2 members"));
    }
}
