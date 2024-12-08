package hh.homeharmony.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the User class.
 * This class verifies the behavior of the User model's methods.
 */
@ExtendWith(MockitoExtension.class)
public class UserTest {

  private User user;

  @BeforeEach
  public void setUp() {
    user = new User("John Doe", "john.doe@example.com", "hashed_password");
  }

  @Test
  public void testDefaultConstructor() {
    User emptyUser = new User();
    assertNull(emptyUser.getName());
    assertNull(emptyUser.getEmail());
    assertNull(emptyUser.getPassword());
  }

  @Test
  public void testParameterizedConstructor() {
    assertEquals("John Doe", user.getName());
    assertEquals("john.doe@example.com", user.getEmail());
    assertEquals("hashed_password", user.getPassword());
  }

  @Test
  public void testSetName() {
    user.setName("Jane Doe");
    assertEquals("Jane Doe", user.getName());
  }

  @Test
  public void testSetEmail() {
    user.setEmail("jane.doe@example.com");
    assertEquals("jane.doe@example.com", user.getEmail());
  }

  @Test
  public void testSetPassword() {
    user.setPassword("new_password");
    assertEquals("new_password", user.getPassword());
  }

  @Test
  public void testUpdateDetails() {
    user.updateDetails("Jane Doe", "jane.doe@example.com", "new_password");
    assertEquals("Jane Doe", user.getName());
    assertEquals("jane.doe@example.com", user.getEmail());
    assertEquals("new_password", user.getPassword());
  }

  @Test
  public void testToString() {
    String expected = "User{id=null, name='John Doe', email='john.doe@example.com', password='hashed_password'}";
    assertEquals(expected, user.toString());
  }
}
