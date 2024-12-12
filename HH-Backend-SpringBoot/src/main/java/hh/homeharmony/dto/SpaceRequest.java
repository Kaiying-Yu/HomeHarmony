package hh.homeharmony.dto;

import hh.homeharmony.model.Space;
import hh.homeharmony.model.User;

/**
 * Data Transfer Object (DTO) for managing Space requests.
 * Encapsulates data required for creating or joining a Space.
 */
public class SpaceRequest {

  private Space space;
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

  public Space getSpace() {
    return space;
  }

  public void setSpace(Space space) {
    this.space = space;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public String toString() {
    return "SpaceRequest{" +
        "space=" + space +
        ", user=" + user +
        '}';
  }
}
