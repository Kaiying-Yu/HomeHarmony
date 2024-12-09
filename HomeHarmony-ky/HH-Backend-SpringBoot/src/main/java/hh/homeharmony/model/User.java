package hh.homeharmony.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Represents a User in the system.
 * Users can be assigned chores and can belong to multiple spaces.
 */
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {
  private String username;
  private String email;
  private String password;
  private Integer points;
  private Integer spaceId;

  public User() {}

  public User(String username, String email, String password, Integer points) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.points = points;
  }

  /**
   * Gets the user's display name.
   *
   * @return the user's name
   */
  public String getUsername() {
    return username;
  }

  /**
   * Sets the user's display name.
   *
   * @param username the user's name
   */
  public void setUsername(String username) {
    this.username = username;
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
   * Gets the user's points he or she has earned
   *
   * @return the user's points
   */
  public Integer getPoints() {
    return points;
  }

  /**
   * Sets the user's points
   *
   * @param points set for the user
   */
  public void setPoints(Integer points) {
    this.points = points;
  }

  /**
   * Gets the user's spaceId
   *
   * @return the spaceId the user belongs to
   */
  public Integer getSpaceId() {
    return spaceId;
  }

  /**
   * Updates the user's details.
   *
   * @param username     the updated username of the user
   * @param email    the updated email of the user
   * @param password the updated password of the user
   */
  public void updateDetails(String username, String email, String password) {
    this.username = username;
    this.email = email;
    this.password = password;
  }

  @Override
  public String toString() {
    return "User{" +
        "id=" + getId() + // getId() is inherited from BaseEntity
        ", name='" + username + '\'' +
        ", email='" + email + '\'' +
        ", password='" + password + '\'' +
        '}';
  }
}




