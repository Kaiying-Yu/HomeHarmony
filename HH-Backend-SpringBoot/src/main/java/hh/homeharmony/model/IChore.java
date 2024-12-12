package hh.homeharmony.model;

import java.time.Duration;

/**
 * Interface defining core chore functionality.
 * Provides basic methods for managing chore status and assignment.
 */
public interface IChore {

    /**
     * Gets the name of the chore.
     *
     * @return the name of the chore as a String.
     */
    String getChoreName();

    /**
     * Sets the name of the chore.
     *
     * @param choreName the name to set for the chore.
     * @throws IllegalArgumentException if the chore name is null or empty.
     */
    void setChoreName(String choreName);

    /**
     * Gets the description of the chore.
     *
     * @return the description of the chore as a String.
     */
    String getChoreDescription();

    /**
     * Sets the description of the chore.
     *
     * @param description the description to set for the chore.
     */
    void setChoreDescription(String description);

    /**
     * Gets the functional space type where this chore should be performed.
     *
     * @return the FunctionalSpaceType of the chore.
     */
    FunctionalSpaceType getFunctionalSpaceType();

    /**
     * Sets the functional space type for this chore.
     *
     * @param functionalSpaceType the FunctionalSpaceType to set for the chore.
     */
    void setFunctionalSpaceType(FunctionalSpaceType functionalSpaceType);

    /**
     * Gets the estimated time to complete the chore.
     *
     * @return the estimated time as a Duration.
     */
    Duration getEstimatedTime();

    /**
     * Sets the estimated time to complete the chore.
     *
     * @param estimatedTime the Duration to set for the chore.
     */
    void setEstimatedTime(Duration estimatedTime);

    /**
     * Gets the points awarded for completing the chore.
     *
     * @return the points as an Integer.
     */
    Integer getPoints();

    /**
     * Sets the points value for the chore.
     *
     * @param points the Integer points to set.
     */
    void setPoints(Integer points);

    /**
     * Gets the current status of the chore.
     *
     * @return the current ChoreStatus of the chore.
     */
    ChoreStatus getChoreStatus();

    /**
     * Sets the status of the chore.
     *
     * @param status the ChoreStatus to set.
     */
    void setStatus(ChoreStatus status);

    /**
     * Gets the ID of the space this chore belongs to.
     *
     * @return the space ID as an Integer.
     */
    Integer getSpaceId();

    /**
     * Sets the ID of the space this chore belongs to.
     *
     * @param spaceId the Integer space ID to set.
     */
    void setSpaceId(Integer spaceId);

    /**
     * Gets the user assigned to this chore.
     *
     * @return the User assigned to the chore, or null if unassigned.
     */
    User getAssignedUser();

    /**
     * Assigns a user to the chore.
     *
     * @param user the User to assign to the chore.
     * @throws IllegalArgumentException if the user is null.
     * @throws IllegalStateException if the chore is already completed.
     */
    void assignUser(User user) throws IllegalArgumentException, IllegalStateException;

    /**
     * Marks the chore as completed if it is currently in progress.
     *
     * Changes the status to COMPLETED only if the current status is
     * IN_PROGRESS. If the chore is not in progress, the status remains unchanged.
     */
    void markAsCompleted();
}