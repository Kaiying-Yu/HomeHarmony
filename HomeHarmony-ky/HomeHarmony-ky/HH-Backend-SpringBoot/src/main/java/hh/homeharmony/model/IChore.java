package hh.homeharmony.model;

/**
 * Interface defining core chore functionality.
 * Provides basic methods for managing chore status and assignment.
 */
public interface IChore {
    /**
     * Gets the current status of the chore.
     *
     * @return The current ChoreStatus
     */
    ChoreStatus getChoreStatus();

    /**
     * Assigns a user to this chore.
     * When a user is assigned, the chore's status should be updated to IN_PROGRESS.
     *
     * @param user The user to be assigned to this chore
     * @throws IllegalArgumentException if the user is null
     * @throws IllegalStateException if the chore is already completed
     */
    void assignUser(User user) throws IllegalArgumentException, IllegalStateException;

    /**
     * Marks the chore as completed if it's currently in progress.
     */
    void markAsCompleted();

} 