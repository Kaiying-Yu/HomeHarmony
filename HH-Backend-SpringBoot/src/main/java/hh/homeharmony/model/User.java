package hh.homeharmony.model;

/**
 * Represents a User in the system.
 * Users can be assigned chores and can belong to multiple spaces.
 */
public class User extends BaseEntity implements IUser {
  private String username;
  private String email;
  private String password;
  private Integer points;
  private Integer spaceId;

  /**
   * Default constructor.
   */
  public User() {}

  /**
   * Parameterized constructor.
   * Creates a user with the specified username, email, password and points.
   */
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
  @Override
  public String getUsername() {
    return username;
  }

  /**
   * Sets the user's display name.
   *
   * @param username the user's name
   */
  @Override
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * Gets the user's email address.
   *
   * @return the user's email
   */
  @Override
  public String getEmail() {
    return email;
  }

  /**
   * Sets the user's email address.
   *
   * @param email the user's email
   */
  @Override
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Gets the user's hashed password.
   *
   * @return the user's password
   */
  @Override
  public String getPassword() {
    return password;
  }

  /**
   * Sets the user's hashed password.
   *
   * @param password the user's password
   */
  @Override
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Gets the user's points he or she has earned
   *
   * @return the user's points
   */
  @Override
  public Integer getPoints() {
    return points;
  }

  /**
   * Sets the user's points
   *
   * @param points set for the user
   */
  @Override
  public void setPoints(Integer points) {
    this.points = points;
  }

  /**
   * Gets the user's spaceId
   *
   * @return the spaceId the user belongs to
   */
  @Override
  public Integer getSpaceId() {
    return spaceId;
  }

  /**
   * Sets the user's spaceId
   *
   * @param spaceId set for the user
   */
  @Override
  public void setSpaceId(Integer spaceId) {
    this.spaceId = spaceId;
  }

  @Override
  public String toString() {
    return "User{" +
        "id=" + getId() + // getId() is inherited from BaseEntity
        ", username='" + username + '\'' +
        ", email='" + email + '\'' +
        ", password='" + password + '\'' +
        ", points=" + points +
        ", spaceId=" + (spaceId != null ? spaceId : "Not in space") +
        '}';
  }
}




