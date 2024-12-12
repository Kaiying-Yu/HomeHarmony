package hh.homeharmony.service;

import java.util.List;

import hh.homeharmony.model.Chore;

/**
 * Service interface for managing Chores.
 * Defines all business operations that can be performed on Chores within a space.
 * Handles chore creation, assignment, completion, and retrieval operations.
 */
public interface ChoreService {
    /**
     * Retrieves all chores for a specific space.
     *
     * @param spaceId the ID of the space to get chores from
     * @return list of chores in the space, empty list if none found
     */
    List<Chore> getAllChores(Integer spaceId);

    /**
     * Creates a new chore in the system.
     *
     * @param chore the chore to create
     * @return the created chore with generated ID
     * @throws IllegalArgumentException if chore is null or has invalid fields
     */
    Chore createChore(Chore chore) throws IllegalArgumentException;

    /**
     * Updates an existing chore's information.
     *
     * @param chore the chore with updated information
     * @return the updated chore
     */
    Chore updateChore(Chore chore);

    /**
     * Deletes a chore from the system.
     *
     * @param id the ID of the chore to delete
     */
    void deleteChore(Integer id);

    /**
     * Assigns a user to a specific chore.
     * Updates the chore's status to IN_PROGRESS when assigned.
     *
     * @param choreId the ID of the chore to assign
     * @param userId the ID of the user to assign to the chore
     * @throws IllegalArgumentException if either ID is invalid or entities not found
     */
    void assignUserToChore(Integer choreId, Integer userId) throws IllegalArgumentException;

    /**
     * Marks a chore as completed.
     * Only chores in IN_PROGRESS state can be completed.
     *
     * @param choreId the ID of the chore to complete
     * @return the completed chore
     * @throws IllegalArgumentException if chore not found
     */
    Chore completeChore(Integer choreId) throws IllegalArgumentException;

    /**
     * Retrieves all chores assigned to a specific user.
     *
     * @param userId the ID of the user
     * @return list of chores assigned to the user, empty list if none found
     */
    List<Chore> getChoresByUserId(Integer userId);
}
