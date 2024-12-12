package hh.homeharmony.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the User class.
 * Tests all functionality including constructors, getters/setters using two different user instances.
 * Ensures proper handling of edge cases and exceptions.
 */
public class UserTest {
    private User user1;
    private User user2;

    /**
     * Sets up test fixtures before each test method.
     * Creates two users with different states for testing.
     */
    @BeforeEach
    public void setUp() {
        user1 = new User("User1", "user1@example.com", "password123", 0);
        user1.setId(1);
        
        user2 = new User("User2", "user2@example.com", "password456", 100);
        user2.setId(2);
        user2.setSpaceId(1);
    }

    /**
     * Tests the default constructor.
     * Verifies that new users are created with null fields.
     */
    @Test
    public void testDefaultConstructor() {
        User defaultUser1 = new User();
        User defaultUser2 = new User();
        
        assertNull(defaultUser1.getUsername());
        assertNull(defaultUser1.getEmail());
        assertNull(defaultUser1.getPassword());
        assertNull(defaultUser1.getPoints());
        assertNull(defaultUser1.getSpaceId());
        
        assertNull(defaultUser2.getUsername());
        assertNull(defaultUser2.getEmail());
        assertNull(defaultUser2.getPassword());
        assertNull(defaultUser2.getPoints());
        assertNull(defaultUser2.getSpaceId());
    }

    /**
     * Tests the parameterized constructor.
     * Verifies all fields are properly initialized for both users.
     */
    @Test
    public void testParameterizedConstructor() {
        assertEquals("User1", user1.getUsername());
        assertEquals("user1@example.com", user1.getEmail());
        assertEquals("password123", user1.getPassword());
        assertEquals(0, user1.getPoints());
        assertNull(user1.getSpaceId());

        assertEquals("User2", user2.getUsername());
        assertEquals("user2@example.com", user2.getEmail());
        assertEquals("password456", user2.getPassword());
        assertEquals(100, user2.getPoints());
        assertEquals(1, user2.getSpaceId());
    }

    /**
     * Tests username getter and setter for both users.
     */
    @Test
    public void testUsernameGetterSetter() {
        user1.setUsername("NewUser1");
        user2.setUsername("NewUser2");
        
        assertEquals("NewUser1", user1.getUsername());
        assertEquals("NewUser2", user2.getUsername());
        
        user1.setUsername(null);
        user2.setUsername(null);
        
        assertNull(user1.getUsername());
        assertNull(user2.getUsername());
    }

    /**
     * Tests email getter and setter for both users.
     */
    @Test
    public void testEmailGetterSetter() {
        user1.setEmail("new1@example.com");
        user2.setEmail("new2@example.com");
        
        assertEquals("new1@example.com", user1.getEmail());
        assertEquals("new2@example.com", user2.getEmail());
        
        user1.setEmail(null);
        user2.setEmail(null);
        
        assertNull(user1.getEmail());
        assertNull(user2.getEmail());
    }

    /**
     * Tests password getter and setter for both users.
     */
    @Test
    public void testPasswordGetterSetter() {
        user1.setPassword("newpass1");
        user2.setPassword("newpass2");
        
        assertEquals("newpass1", user1.getPassword());
        assertEquals("newpass2", user2.getPassword());
        
        user1.setPassword(null);
        user2.setPassword(null);
        
        assertNull(user1.getPassword());
        assertNull(user2.getPassword());
    }

    /**
     * Tests points getter and setter for both users.
     */
    @Test
    public void testPointsGetterSetter() {
        user1.setPoints(50);
        user2.setPoints(150);
        
        assertEquals(50, user1.getPoints());
        assertEquals(150, user2.getPoints());
        
        user1.setPoints(null);
        user2.setPoints(null);
        
        assertNull(user1.getPoints());
        assertNull(user2.getPoints());
    }

    /**
     * Tests spaceId getter and setter for both users.
     */
    @Test
    public void testSpaceIdGetterSetter() {
        user1.setSpaceId(123);
        user2.setSpaceId(456);
        
        assertEquals(123, user1.getSpaceId());
        assertEquals(456, user2.getSpaceId());
        
        user1.setSpaceId(null);
        user2.setSpaceId(null);
        
        assertNull(user1.getSpaceId());
        assertNull(user2.getSpaceId());
    }

    /**
     * Tests equals and hashCode methods with both users.
     */
    @Test
    public void testEqualsAndHashCode() {
        User copyUser1 = new User("User1", "user1@example.com", "password123", 0);
        User copyUser2 = new User("User2", "user2@example.com", "password456", 100);
        
        copyUser1.setId(1);
        copyUser2.setId(2);
        copyUser2.setSpaceId(1);
        
        assertTrue(user1.equals(copyUser1));
        assertTrue(user2.equals(copyUser2));
        assertEquals(user1.hashCode(), copyUser1.hashCode());
        assertEquals(user2.hashCode(), copyUser2.hashCode());
        
        assertFalse(user1.equals(user2));
        assertFalse(copyUser1.equals(copyUser2));
    }

    /**
     * Tests the toString method with different user states.
     */
    @Test
    public void testToString() {
        String user1String = user1.toString();
        String user2String = user2.toString();
        
        assertTrue(user1String.contains("User1"));
        assertTrue(user1String.contains("user1@example.com"));
        assertTrue(user1String.contains("points=0"));
        assertTrue(user1String.contains("spaceId"));
        
        assertTrue(user2String.contains("User2"));
        assertTrue(user2String.contains("user2@example.com"));
        assertTrue(user2String.contains("points=100"));
        assertTrue(user2String.contains("spaceId=1"));
    }
}
