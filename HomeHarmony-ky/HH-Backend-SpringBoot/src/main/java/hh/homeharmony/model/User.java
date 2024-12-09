package hh.homeharmony.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Represents a User in the system.
 * Users can be assigned chores and can belong to multiple spaces.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {
  private String username;
  private String email;
  private String password;
  private Integer points;
  private Integer spaceId;
  //private List<Integer> assignedChores; // Chores assigned to the user

  public Integer getPoints() {
    return points;
  }

  public void setPoints(Integer points) {
    this.points = points;
  }
}
