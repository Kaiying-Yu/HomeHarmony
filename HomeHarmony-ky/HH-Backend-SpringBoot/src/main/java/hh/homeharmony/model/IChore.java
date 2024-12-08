package hh.homeharmony.model;

/**
 * Interface defining core chore functionality.
 * Provides basic methods for managing chore status and assignment.
 */
public interface IChore {
    /**
     * Marks the chore as done if it's currently in progress.
     */
    void markAsCompleted();

    /**
     * Gets the current status of the chore.
     * @return The current ChoreStatus
     */
    ChoreStatus getChoreStatus();

    // /**
    //  * Sets the assigned user for the chore.
    //  * @param userId The ID of the user to be assigned
    //  */
    // void setAssignedUser(Integer userId);

    // /**
    //  * Sets the status of the chore.
    //  * @param status The new status of the chore
    //  */
    // void setStatus(ChoreStatus status);
} 