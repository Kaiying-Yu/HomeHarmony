package hh.homeharmony.model;

import java.time.Duration;

/**
 * Represents a chore entity in the system.
 * A chore is a task that needs to be completed within a space, with associated properties
 * such as name, description, estimated time, and points.
 * Implements IChore interface for core chore functionality.
 */
public class Chore extends BaseEntity implements IChore {
    private String choreName;                    // Name of the chore task
    private String description;                  // Detailed description of what needs to be done
    private FunctionalSpaceType functionalSpaceType; // Type of room/area where the chore is performed
    private Duration estimatedTime;              // Expected time to complete the chore
    private Integer points;                      // Points awarded for completing the chore
    private ChoreStatus status;                  // Current state of the chore (PENDING, IN_PROGRESS, etc.)
    private User assignedUser;                   // User assigned to complete the chore
    private Integer spaceId;                     // ID of the space this chore belongs to

    /**
     * Default constructor.
     * Initializes a new chore with PENDING status.
     */
    public Chore() {
        this.status = ChoreStatus.PENDING;
    }

    /**
     * Creates a new chore with required fields.
     *
     * @param choreName The name of the chore
     * @param description The detailed description of the chore
     * @param functionalSpaceType The type of space this chore belongs to
     * @param estimatedTime The estimated time to complete the chore
     * @param points The points awarded for completing the chore
     * @param status The initial status of the chore
     * @throws IllegalArgumentException if choreName is null or empty
     */
    public Chore(String choreName, String description, FunctionalSpaceType functionalSpaceType,
        Duration estimatedTime, Integer points, ChoreStatus status) {
        if (choreName == null || choreName.trim().isEmpty()) {
            throw new IllegalArgumentException("Chore name cannot be null or empty");
        }
        this.choreName = choreName;
        this.description = description;
        this.functionalSpaceType = functionalSpaceType;
        this.estimatedTime = estimatedTime;
        this.points = points;
        this.status = status != null ? status : ChoreStatus.PENDING;
        this.assignedUser = null;
    }

    /**
     * Gets the name of the chore.
     *
     * @return the chore name
     */
    public String getChoreName() {
        return choreName;
    }

    /**
     * Sets the name of the chore.
     *
     * @param choreName the name to set
     */
    public void setChoreName(String choreName) {
        this.choreName = choreName;
    }

    /**
     * Gets the description of the chore.
     *
     * @return the chore description
     */
    public String getChoreDescription() {
        return description;
    }

    /**
     * Sets the description of the chore.
     *
     * @param description the description to set
     */
    public void setChoreDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the functional space type where this chore should be performed.
     *
     * @return the functional space type
     */
    public FunctionalSpaceType getFunctionalSpaceType() {
        return functionalSpaceType;
    }

    /**
     * Sets the functional space type for this chore.
     *
     * @param functionalSpaceType the space type to set
     */
    public void setFunctionalSpaceType(FunctionalSpaceType functionalSpaceType) {
        this.functionalSpaceType = functionalSpaceType;
    }

    /**
     * Gets the estimated time to complete the chore.
     *
     * @return the estimated duration
     */
    public Duration getEstimatedTime() {
        return estimatedTime;
    }

    /**
     * Sets the estimated time to complete the chore.
     *
     * @param estimatedTime the duration to set
     */
    public void setEstimatedTime(Duration estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    /**
     * Gets the points awarded for completing this chore.
     *
     * @return the points value
     */
    public Integer getPoints() {
        return points;
    }

    /**
     * Sets the points value for this chore.
     *
     * @param points the points to set
     */
    public void setPoints(Integer points) {
        this.points = points;
    }

    /**
     * Gets the current status of the chore.
     * Implementation of IChore interface method.
     *
     * @return the current ChoreStatus
     */
    @Override
    public ChoreStatus getChoreStatus() {
        return this.status;
    }

    /**
     * Sets the status of the chore.
     *
     * @param status the status to set
     */
    public void setStatus(ChoreStatus status) {
        this.status = status;
    }

    /**
     * Gets the ID of the space this chore belongs to.
     *
     * @return the space ID
     */
    public Integer getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(Integer spaceId) {
        this.spaceId = spaceId;
    }

    /**
     * Gets the user assigned to this chore.
     *
     * @return the assigned user, or null if unassigned
     */
    public User getAssignedUser() {
        return assignedUser;
    }

    /**
     * Assigns a user to this chore.
     * Implementation of IChore interface method.
     *
     * @param user the user to assign
     * @throws IllegalArgumentException if user is null
     * @throws IllegalStateException if the chore is already completed
     */
    @Override
    public void assignUser(User user) throws IllegalArgumentException, IllegalStateException {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (this.status == ChoreStatus.COMPLETED) {
            throw new IllegalStateException("Cannot assign user to completed chore");
        }
        this.assignedUser = user;
        this.status = ChoreStatus.IN_PROGRESS;
    }

    /**
     * Marks the chore as completed if it's currently in progress.
     * Implementation of IChore interface method.
     * Only changes status if current status is IN_PROGRESS.
     */
    @Override
    public void markAsCompleted() {
        if (this.status == ChoreStatus.IN_PROGRESS) {
            this.status = ChoreStatus.COMPLETED;
        }
    }

    /**
     * Returns a string representation of the chore.
     * Includes all relevant chore information for display purposes.
     *
     * @return formatted string containing chore details
     */
    @Override
    public String toString() {
        return "Chore{" +
                "id=" + getId() +
                ", choreName='" + choreName + "'" +
                ", functionalSpaceType=" + functionalSpaceType +
                ", estimatedTime=" + estimatedTime +
                ", points=" + points +
                ", status=" + status +
                ", assignedUser=" + (assignedUser != null ? assignedUser.getUsername() : "Unassigned") +
                '}';
    }

}