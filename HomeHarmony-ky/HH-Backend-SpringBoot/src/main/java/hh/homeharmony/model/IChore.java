package hh.homeharmony.model;

import java.time.Duration;

/**
 * Interface defining the operations for a chore.
 */
public interface IChore {

  /**
   * Gets the name of the chore.
   *
   * @return the name of the chore
   */
  String getChoreName();

  /**
   * Sets the name of the chore.
   *
   * @param choreName the name of the chore
   */
  void setChoreName(String choreName);

  /**
   * Gets the description of the chore.
   *
   * @return the description of the chore
   */
  String getChoreDescription();

  /**
   * Sets the description of the chore.
   *
   * @param choreDescription the description of the chore
   */
  void setChoreDescription(String choreDescription);

  /**
   * Gets the functional space type associated with the chore.
   *
   * @return the functional space type
   */
  FunctionalSpaceType getFunctionalSpaceType();

  /**
   * Sets the functional space type for the chore.
   *
   * @param functionalSpaceType the functional space type
   */
  void setFunctionalSpaceType(FunctionalSpaceType functionalSpaceType);

  /**
   * Gets the estimated time to complete the chore.
   *
   * @return the estimated time as a Duration
   */
  Duration getEstimatedTime();

  /**
   * Sets the estimated time to complete the chore.
   *
   * @param estimatedTime the time as a Duration
   */
  void setEstimatedTime(Duration estimatedTime);

  /**
   * Gets the points for completing the chore.
   *
   * @return the points
   */
  Integer getPoints();

  /**
   * Sets the points for completing the chore.
   *
   * @param points the points
   */
  void setPoints(Integer points);

  /**
   * Gets the status of the chore.
   *
   * @return the chore's status
   */
  ChoreStatus getStatus();

  /**
   * Sets the status of the chore.
   *
   * @param status the chore's status
   */
  void setStatus(ChoreStatus status);

  /**
   * Gets the user assigned to the chore.
   *
   * @return the assigned user
   */
  User getAssignedUser();

  /**
   * Marks the chore as completed.
   */
  void markAsCompleted();
}
