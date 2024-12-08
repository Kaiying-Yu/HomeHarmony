package hh.homeharmony.model;

import java.time.Duration;
import java.util.Objects;

/**
 * Represents a chore assigned to a user within a space.
 */
public class Chore extends BaseEntity implements IChore {
  private String choreName; // Name of the chore
  private String description; // Description of the chore
  private FunctionalSpaceType functionalSpaceType; // Functional space type
  private Duration estimatedTime; // Estimated time to complete the chore
  private Integer points; // Reward points for completing the chore
  private ChoreStatus status; // Status: Pending, In Progress, Completed, Overdue
  private User assignedUser; // User assigned to this chore


  // Constructors
  public Chore() {}

  public Chore(String choreName, String description, FunctionalSpaceType functionalSpaceType, Duration estimatedTime, Integer points, ChoreStatus status) {
    this.choreName = choreName;
    this.description = description;
    this.functionalSpaceType = functionalSpaceType;
    this.estimatedTime = estimatedTime;
    this.points = points;
    this.status = status;
  }

  @Override
  public String getChoreName() {
    return choreName;
  }

  @Override
  public void setChoreName(String choreName) {
    this.choreName = choreName;
  }

  @Override
  public String getChoreDescription() {
    return description;
  }

  @Override
  public void setChoreDescription(String description) {
    this.description = description;
  }

  @Override
  public FunctionalSpaceType getFunctionalSpaceType() {
    return functionalSpaceType;
  }

  @Override
  public void setFunctionalSpaceType(FunctionalSpaceType functionalSpaceType) {
    this.functionalSpaceType = functionalSpaceType;
  }

  @Override
  public Duration getEstimatedTime() {
    return estimatedTime;
  }

  @Override
  public void setEstimatedTime(Duration estimatedTime) {
    this.estimatedTime = estimatedTime;
  }

  @Override
  public Integer getPoints() {
    return points;
  }

  @Override
  public void setPoints(Integer points) {
    this.points = points;
  }

  @Override
  public ChoreStatus getStatus() {
    return status;
  }

  @Override
  public void setStatus(ChoreStatus status) {
    this.status = status;
  }

  @Override
  public User getAssignedUser() {
    return assignedUser;
  }

  @Override
  public void markAsCompleted() {
    if (status != ChoreStatus.IN_PROGRESS) {
      throw new IllegalStateException("Chore must be 'In Progress' to mark as completed.");
    }
    this.status = ChoreStatus.COMPLETED;
  }

  @Override
  public String toString() {
    return "Chore{" +
        "id=" + getId() +
        ", choreName='" + choreName + '\'' +
        ", functionalSpaceType=" + functionalSpaceType +
        ", estimatedTime=" + estimatedTime.toMinutes() + " minutes" +
        ", points=" + points +
        ", status=" + status +
        ", assignedUser=" + (assignedUser != null ? assignedUser.getName() : "Unassigned") +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Chore)) return false;
    Chore chore = (Chore) o;
    return Objects.equals(getId(), chore.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId());
  }
}