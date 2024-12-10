package hh.homeharmony.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Represents a Space entity in the system.
 * Spaces are areas that can have various functional spaces and chores assigned to them,
 * and can be occupied by multiple members.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Space extends BaseEntity implements ISpace {
  private String name;
  private List<User> users; // List of Users associated with the Space

  // Method to add a user to the Space
  @Override
  public void addUser(User user)
          throws IllegalArgumentException, IllegalStateException {
    if (user == null) {
      throw new IllegalArgumentException("User cannot be null");
    }
    if (users == null) {
      users = new ArrayList<>();
    }
    if (!users.contains(user)) {
      users.add(user);
    } else {
      throw new IllegalStateException("User is already a member of this space.");
    }
  }

  @Override
  public void removeUser(User user)
          throws IllegalArgumentException, IllegalStateException {
    if (user == null) {
      throw new IllegalArgumentException("User cannot be null");
    }
    if (users != null && users.contains(user)) {
      users.remove(user);
    } else {
      throw new IllegalStateException("User is not a member of this space.");
    }
  }
}