package hh.homeharmony.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Space class.
 * Tests the behavior of methods related to managing spaces and their users.
 */
public class SpaceTest {

  private Space space;
  private User creator;
  private User user1;
  private User user2;

  /**
   * Sets up a Space instance with a creator before each test.
   */
  @BeforeEach
  public void setUp() {
    creator = new User("Creator", "creator@example.com", "password123");
    user1 = new User("User1", "user1@example.com", "password123");
    user2 = new User("User2", "user2@example.com", "password123");

    space = new Space();
    space.setName("Test Space");
    space.setCreator(creator);
  }

  /**
   * Tests getting the name of the space.
   */
  @Test
  public void testGetName() {
    assertEquals("Test Space", space.getName());
  }

  /**
   * Tests setting the name of the space.
   */
  @Test
  public void testSetName() {
    space.setName("New Space Name");
    assertEquals("New Space Name", space.getName());
  }

  /**
   * Tests getting the creator of the space.
   */
  @Test
  public void testGetCreator() {
    assertEquals(creator, space.getCreator());
  }

  /**
   * Tests setting the creator of the space.
   */
  @Test
  public void testSetCreator() {
    User newCreator = new User("New Creator", "newcreator@example.com", "password123");
    space.setCreator(newCreator);
    assertEquals(newCreator, space.getCreator());
  }

  /**
   * Tests adding a user to the space.
   */
  @Test
  public void testAddUser() {
    space.addUser(user1);
    assertTrue(space.getUsers().contains(user1));
  }

  /**
   * Tests adding a null user to the space, expecting an IllegalArgumentException.
   */
  @Test
  public void testAddUserThrowsExceptionIfUserIsNull() {
    Exception exception = assertThrows(IllegalArgumentException.class, () -> space.addUser(null));
    assertEquals("User cannot be null.", exception.getMessage());
  }

  /**
   * Tests adding the same user twice to the space, expecting an IllegalStateException.
   */
  @Test
  public void testAddUserThrowsExceptionIfUserAlreadyExists() {
    space.addUser(user1);
    Exception exception = assertThrows(IllegalStateException.class, () -> space.addUser(user1));
    assertEquals("User is already in the space.", exception.getMessage());
  }

  /**
   * Tests removing a user from the space.
   */
  @Test
  public void testRemoveUser() {
    space.addUser(user1);
    space.removeUser(user1);
    assertFalse(space.getUsers().contains(user1));
  }

  /**
   * Tests removing a null user from the space, expecting an IllegalArgumentException.
   */
  @Test
  public void testRemoveUserThrowsExceptionIfUserIsNull() {
    Exception exception = assertThrows(IllegalArgumentException.class, () -> space.removeUser(null));
    assertEquals("User cannot be null.", exception.getMessage());
  }

  /**
   * Tests removing a user not in the space, expecting an IllegalStateException.
   */
  @Test
  public void testRemoveUserThrowsExceptionIfUserNotInSpace() {
    Exception exception = assertThrows(IllegalStateException.class, () -> space.removeUser(user1));
    assertEquals("User is not in the space.", exception.getMessage());
  }

  /**
   * Tests checking if a user is in the space.
   */
  @Test
  public void testHasUser() {
    space.addUser(user1);
    assertTrue(space.hasUser(user1));
    assertFalse(space.hasUser(user2));
  }

  /**
   * Tests retrieving the list of users in the space.
   */
  @Test
  public void testGetUsers() {
    space.addUser(user1);
    space.addUser(user2);
    List<User> users = space.getUsers();
    assertEquals(2, users.size());
    assertTrue(users.contains(user1));
    assertTrue(users.contains(user2));
  }

  /**
   * Tests the toString method of the Space class.
   */
  @Test
  public void testToString() {
    space.addUser(user1);
    String toString = space.toString();
    assertTrue(toString.contains("Test Space"));
    assertTrue(toString.contains("Creator"));
    assertTrue(toString.contains(user1.toString()));
  }
}
