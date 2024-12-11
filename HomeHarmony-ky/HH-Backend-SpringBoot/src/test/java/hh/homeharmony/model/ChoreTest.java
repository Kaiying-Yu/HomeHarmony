package hh.homeharmony.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Duration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Chore class.
 * Validates the behavior of methods for managing chore properties and state.
 */
public class ChoreTest {

  private Chore chore;

  /**
   * Sets up a Chore instance with default properties before each test.
   */
  @BeforeEach
  public void setUp() {
    chore = new Chore("Clean Kitchen",
        "Wipe all surfaces and mop the floor.",
        FunctionalSpaceType.KITCHEN,
        Duration.ofMinutes(30),
        10,
        ChoreStatus.PENDING);
  }

  /**
   * Tests the parameterized constructor.
   */
  @Test
  public void testParameterizedConstructor() {
    assertEquals("Clean Kitchen", chore.getChoreName());
    assertEquals("Wipe all surfaces and mop the floor.", chore.getChoreDescription());
    assertEquals(FunctionalSpaceType.KITCHEN, chore.getFunctionalSpaceType());
    assertEquals(Duration.ofMinutes(30), chore.getEstimatedTime());
    assertEquals(10, chore.getPoints());
    assertEquals(ChoreStatus.PENDING, chore.getChoreStatus());
    assertNull(chore.getAssignedUser());
  }

  /**
   * Tests getting and setting the chore name.
   */
  @Test
  public void testSetAndGetChoreName() {
    chore.setChoreName("Clean Bathroom");
    assertEquals("Clean Bathroom", chore.getChoreName());
  }

  /**
   * Tests getting and setting the chore description.
   */
  @Test
  public void testSetAndGetChoreDescription() {
    chore.setChoreDescription("Scrub the tiles and clean the sink.");
    assertEquals("Scrub the tiles and clean the sink.", chore.getChoreDescription());
  }

  /**
   * Tests getting and setting the functional space type.
   */
  @Test
  public void testSetAndGetFunctionalSpaceType() {
    chore.setFunctionalSpaceType(FunctionalSpaceType.BATHROOM);
    assertEquals(FunctionalSpaceType.BATHROOM, chore.getFunctionalSpaceType());
  }

  /**
   * Tests getting and setting the estimated time for the chore.
   */
  @Test
  public void testSetAndGetEstimatedTime() {
    chore.setEstimatedTime(Duration.ofMinutes(45));
    assertEquals(Duration.ofMinutes(45), chore.getEstimatedTime());
  }

  /**
   * Tests getting and setting the reward points for the chore.
   */
  @Test
  public void testSetAndGetPoints() {
    chore.setPoints(15);
    assertEquals(15, chore.getPoints());
  }

  /**
   * Tests getting and setting the status of the chore.
   */
  @Test
  public void testSetAndGetStatus() {
    chore.setStatus(ChoreStatus.IN_PROGRESS);
    assertEquals(ChoreStatus.IN_PROGRESS, chore.getChoreStatus());
  }

  /**
   * Tests marking the chore as completed.
   */
  @Test
  public void testMarkAsCompleted() {
    chore.setStatus(ChoreStatus.IN_PROGRESS);
    chore.markAsCompleted();
    assertEquals(ChoreStatus.COMPLETED, chore.getChoreStatus());
  }

  /**
   * Tests marking the chore as completed when not in progress.
   * Expects an IllegalStateException.
   */
  @Test
  public void testMarkAsCompletedThrowsException() {
    chore.setStatus(ChoreStatus.PENDING);
    Exception exception = assertThrows(IllegalStateException.class, chore::markAsCompleted);
    assertEquals("Chore must be 'In Progress' to mark as completed.", exception.getMessage());
  }

  /**
   * Tests getting the assigned user (should be null initially).
   */
  @Test
  public void testGetAssignedUserInitiallyNull() {
    assertNull(chore.getAssignedUser());
  }

  /**
   * Tests the equals and hashCode methods for Chore objects.
   */
  @Test
  public void testEqualsAndHashCode() {
    Chore anotherChore = new Chore("Clean Kitchen",
        "Wipe all surfaces and mop the floor.",
        FunctionalSpaceType.KITCHEN,
        Duration.ofMinutes(30),
        10,
        ChoreStatus.PENDING);
    anotherChore.setId(1);
    chore.setId(1);

    assertEquals(chore, anotherChore);
    assertEquals(chore.hashCode(), anotherChore.hashCode());
  }

  /**
   * Tests the toString method of the Chore class.
   * Verifies the string representation of the Chore instance.
   */
  @Test
  public void testToString() {
    chore.setId(1); // Setting the ID to include it in the output
    String expected = "Chore{" +
        "id=1" +
        ", choreName='Clean Kitchen'" +
        ", functionalSpaceType=KITCHEN" +
        ", estimatedTime=30 minutes" +
        ", points=10" +
        ", status=Pending" +
        ", assignedUser=Unassigned" +
        '}';
    assertEquals(expected, chore.toString());
  }
}
