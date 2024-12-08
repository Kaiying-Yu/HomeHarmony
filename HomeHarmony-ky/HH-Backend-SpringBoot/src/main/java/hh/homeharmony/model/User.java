package hh.homeharmony.model;

import java.util.List;

/**
 * Represents a user in the system.
 * Contains user-specific details like name, email, and password.
 */
public class User extends BaseEntity {
  private String name; // The user's display name
  private String email; // The user's unique email address
  private String password; // The user's hashed password

  // Constructor
  public User() {}

  public User(String name, String email, String password) {
    this.name = name;
    this.email = email;
    this.password = password;
  }

  /**
   * Gets the user's display name.
   *
   * @return the user's name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the user's display name.
   *
   * @param name the user's name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the user's email address.
   *
   * @return the user's email
   */
  public String getEmail() {
    return email;
  }

  /**
   * Sets the user's email address.
   *
   * @param email the user's email
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Gets the user's hashed password.
   *
   * @return the user's password
   */
  public String getPassword() {
    return password;
  }

  /**
   * Sets the user's hashed password.
   *
   * @param password the user's password
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Updates the user's details.
   *
   * @param name     the updated name of the user
   * @param email    the updated email of the user
   * @param password the updated password of the user
   */
  public void updateDetails(String name, String email, String password) {
    this.name = name;
    this.email = email;
    this.password = password;
  }

  @Override
  public String toString() {
    return "User{" +
        "id=" + getId() + // getId() is inherited from BaseEntity
        ", name='" + name + '\'' +
        ", email='" + email + '\'' +
        ", password='" + password + '\'' +
        '}';
  }
}

