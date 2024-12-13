package hh.homeharmony.dto;

import hh.homeharmony.model.Space;
import hh.homeharmony.model.User;

/**
 * Data Transfer Object (DTO) for managing Space requests.
 * Encapsulates data required for creating or joining a Space.
 */
public class SpaceRequest {

  /**
   * The Space object associated with this request.
   */
  private Space space;

  /**
   * The User object associated with this request.
   */
  private User user;

  /**
   * Constructor for creating a SpaceRequest.
   *
   * @param space The Space object.
   * @param user  The User object.
   */
  public SpaceRequest(Space space, User user) {
    this.space = space;
    this.user = user;
  }

  /**
   * Gets the Space object associated with this request.
   *
   * @return The Space object.
   */
  public Space getSpace() {
    return space;
  }

  /**
   * Sets the Space object for this request.
   *
   * @param space The Space object to set.
   */
  public void setSpace(Space space) {
    this.space = space;
  }

  /**
   * Gets the User object associated with this request.
   *
   * @return The User object.
   */
  public User getUser() {
    return user;
  }

  /**
   * Sets the User object for this request.
   *
   * @param user The User object to set.
   */
  public void setUser(User user) {
    this.user = user;
  }

  /**
   * Returns a string representation of the SpaceRequest object.
   *
   * @return A string representation of the object.
   */
  @Override
  public String toString() {
    return "SpaceRequest{" +
        "space=" + space +
        ", user=" + user +
        '}';
  }
}
