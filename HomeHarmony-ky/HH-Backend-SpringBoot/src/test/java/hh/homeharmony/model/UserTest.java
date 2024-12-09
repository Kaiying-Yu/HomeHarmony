package hh.homeharmony.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the User class.
 * Verifies the behavior of the User model's methods.
 */
public class UserTest {

  private User user;

  /**
   * Sets up a User instance before each test.
   */
  @BeforeEach
  public void setUp() {
    user = new User("John Doe", "john.doe@example.com", "hashed_password");
  }

  /**
   * Tests the default constructor of the User class.
   */
  @Test
  public void testDefaultConstructor() {
    User emptyUser = new User();
    assertNull(emptyUser.getName());
    assertNull(emptyUser.getEmail());
    assertNull(emptyUser.getPassword());
  }

  /**
   * Tests the parameterized constructor of the User class.
   */
  @Test
  public void testParameterizedConstructor() {
    assertEquals("John Doe", user.getName());
    assertEquals("john.doe@example.com", user.getEmail());
    assertEquals("hashed_password", user.getPassword());
  }

  /**
   * Tests setting the name of the User.
   */
  @Test
  public void testSetName() {
    user.setName("Jane Doe");
    assertEquals("Jane Doe", user.getName());
  }

  /**
   * Tests setting the email of the User.
   */
  @Test
  public void testSetEmail() {
    user.setEmail("jane.doe@example.com");
    assertEquals("jane.doe@example.com", user.getEmail());
  }

  /**
   * Tests setting the password of the User.
   */
  @Test
  public void testSetPassword() {
    user.setPassword("new_password");
    assertEquals("new_password", user.getPassword());
  }

  /**
   * Tests updating user details using the updateDetails method.
   */
  @Test
  public void testUpdateDetails() {
    user.updateDetails("Jane Doe", "jane.doe@example.com", "new_password");
    assertEquals("Jane Doe", user.getName());
    assertEquals("jane.doe@example.com", user.getEmail());
    assertEquals("new_password", user.getPassword());
  }

  /**
   * Tests the toString method of the User class.
   */
  @Test
  public void testToString() {
    String expected = "User{id=null, name='John Doe', email='john.doe@example.com', password='hashed_password'}";
    assertEquals(expected, user.toString());
  }
}
