package hh.homeharmony.model;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Chore class.
 * Tests all functionality including constructors, getters/setters, and core methods.
 * Uses two chore instances with different states to ensure comprehensive testing.
 */
public class ChoreTest {
    private Chore chore1;  // PENDING status chore
    private Chore chore2;  // IN_PROGRESS status chore
    private User testUser;

    /**
     * Sets up test fixtures before each test method.
     * Creates two chores with different states and a test user.
     */
    @BeforeEach
    public void setUp() {
        // First chore with PENDING status
        chore1 = new Chore("Clean Kitchen",
            "Wipe all surfaces and mop the floor.",
            FunctionalSpaceType.KITCHEN,
            Duration.ofMinutes(30),
            10,
            ChoreStatus.PENDING);
            
        // Second chore with IN_PROGRESS status
        chore2 = new Chore("Clean Bathroom",
            "Clean toilet and shower.",
            FunctionalSpaceType.BATHROOM,
            Duration.ofMinutes(45),
            15,
            ChoreStatus.IN_PROGRESS);
        
        testUser = new User();
        testUser.setId(1);
        testUser.setUsername("testUser");
    }

    /**
     * Tests the default constructor.
     * Verifies that a new chore is created with PENDING status.
     */
    @Test
    public void testDefaultConstructor() {
        Chore defaultChore = new Chore();
        assertEquals(ChoreStatus.PENDING, defaultChore.getChoreStatus());
    }

    /**
     * Tests the parameterized constructor with valid inputs.
     * Verifies all fields are properly initialized.
     */
    @Test
    public void testParameterizedConstructor() {
        assertEquals("Clean Kitchen", chore1.getChoreName());
        assertEquals("Wipe all surfaces and mop the floor.", chore1.getChoreDescription());
        assertEquals(FunctionalSpaceType.KITCHEN, chore1.getFunctionalSpaceType());
        assertEquals(Duration.ofMinutes(30), chore1.getEstimatedTime());
        assertEquals(10, chore1.getPoints());
        assertEquals(ChoreStatus.PENDING, chore1.getChoreStatus());
        assertNull(chore1.getAssignedUser());
    }

    /**
     * Tests constructor validation for null or empty chore name.
     */
    @Test
    public void testConstructorValidation() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Chore(null, "Description", FunctionalSpaceType.KITCHEN, 
                Duration.ofMinutes(30), 10, ChoreStatus.PENDING);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Chore("", "Description", FunctionalSpaceType.KITCHEN, 
                Duration.ofMinutes(30), 10, ChoreStatus.PENDING);
        });
    }

    /**
     * Tests the assignUser method with various scenarios.
     */
    @Test
    public void testAssignUser() {
        // Arrange
        chore1.setStatus(ChoreStatus.PENDING);

        // Test assigning a valid user to a PENDING chore
        chore1.assignUser(testUser);
        assertEquals(testUser, chore1.getAssignedUser());
        assertEquals(ChoreStatus.IN_PROGRESS, chore1.getChoreStatus());

        // Test assigning a null user
        IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class, () -> {
            chore2.assignUser(null);
        });
        assertEquals("User cannot be null", exception1.getMessage());

        // Test assigning a user to a COMPLETED chore
        chore2.setStatus(ChoreStatus.COMPLETED);
        IllegalStateException exception2 = assertThrows(IllegalStateException.class, () -> {
            chore2.assignUser(testUser);
        });
        assertEquals("Cannot assign user to completed chore", exception2.getMessage());
    }

    /**
     * Tests the markAsCompleted method in different states.
     */
    @Test
    public void testMarkAsCompleted() {
        // Test completing IN_PROGRESS chore
        chore2.markAsCompleted();
        assertEquals(ChoreStatus.COMPLETED, chore2.getChoreStatus());

        // Test completing PENDING chore (should not change status)
        chore1.markAsCompleted();
        assertEquals(ChoreStatus.PENDING, chore1.getChoreStatus());
    }

    /**
     * Tests space ID getter and setter.
     */
    @Test
    public void testSpaceId() {
        chore1.setSpaceId(123);
        assertEquals(123, chore1.getSpaceId());
    }

    /**
     * Tests the toString method for different chore states.
     */
    @Test
    public void testToString() {
        chore1.setId(1);
        String expected1 = "Chore{" +
                "id=1" +
                ", choreName='Clean Kitchen'" +
                ", functionalSpaceType=KITCHEN" +
                ", estimatedTime=PT30M" +
                ", points=10" +
                ", status=Pending" +
                ", assignedUser=Unassigned" +
                '}';
        assertEquals(expected1, chore1.toString());

        // Test toString with assigned user
        chore2.setId(2);
        chore2.assignUser(testUser);
        String expected2 = "Chore{" +
                "id=2" +
                ", choreName='Clean Bathroom'" +
                ", functionalSpaceType=BATHROOM" +
                ", estimatedTime=PT45M" +
                ", points=15" +
                ", status=In Progress" +
                ", assignedUser=testUser" +
                '}';
        assertEquals(expected2, chore2.toString());
    }

    /**
     * Tests equals and hashCode methods.
     */
    @Test
    public void testEqualsAndHashCode() {
        Chore copy1 = new Chore("Clean Kitchen",
            "Wipe all surfaces and mop the floor.",
            FunctionalSpaceType.KITCHEN,
            Duration.ofMinutes(30),
            10,
            ChoreStatus.PENDING);
        copy1.setId(1);
        chore1.setId(1);

        assertTrue(chore1.equals(copy1));
        assertEquals(chore1.hashCode(), copy1.hashCode());
        assertFalse(chore1.equals(chore2));
    }
}
