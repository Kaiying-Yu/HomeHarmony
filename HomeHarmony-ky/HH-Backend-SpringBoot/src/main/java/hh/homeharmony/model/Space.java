package hh.homeharmony.model;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Space entity in the system.
 * Spaces are areas that can have various functional spaces and chores assigned to them,
 * and can be occupied by multiple members.
 */
public class Space extends BaseEntity implements ISpace {
  private String name; // Name of the space
  private User creator; // The user who created the space
  private List<User> users = new ArrayList<>(); // List of users in the space

  /**
   * Gets the name of the space.
   *
   * @return the name of the space
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * Sets the name of the space.
   *
   * @param name the name of the space
   */
  @Override
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the list of users in the space.
   *
   * @return the list of users
   */
  @Override
  public List<User> getUsers() {
    return users;
  }

  /**
   * Adds a user to the space.
   *
   * @param user The user to add
   * @throws IllegalArgumentException if the user is null
   * @throws IllegalStateException if the user is already in the space
   */
  @Override
  public void addUser(User user) throws IllegalArgumentException, IllegalStateException {
    if (user == null) {
      throw new IllegalArgumentException("User cannot be null.");
    }
    if (users.contains(user)) {
      throw new IllegalStateException("User is already in the space.");
    }
    users.add(user);
  }

  /**
   * Removes a user from the space.
   *
   * @param user The user to remove
   * @throws IllegalArgumentException if the user is null
   * @throws IllegalStateException if the user is not in the space
   */
  @Override
  public void removeUser(User user) throws IllegalArgumentException, IllegalStateException {
    if (user == null) {
      throw new IllegalArgumentException("User cannot be null.");
    }
    if (!users.contains(user)) {
      throw new IllegalStateException("User is not in the space.");
    }
    users.remove(user);
  }

  /**
   * Checks if a user is part of the space.
   *
   * @param user the user to check
   * @return true if the user is in the space, false otherwise
   */
  @Override
  public boolean hasUser(User user) {
    return users.contains(user);
  }

  /**
   * Gets the creator of the space.
   *
   * @return the user who created the space
   */
  public User getCreator() {
    return creator;
  }

  /**
   * Sets the creator of the space.
   *
   * @param creator the user who created the space
   */
  public void setCreator(User creator) {
    this.creator = creator;
  }

  @Override
  public String toString() {
    return "Space{" +
        "id=" + getId() + // Inherited from BaseEntity
        ", name='" + name + '\'' +
        ", creator=" + (creator != null ? creator.getName() : "null") +
        ", users=" + users +
        '}';
  }
}