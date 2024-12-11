package hh.homeharmony.service;

import hh.homeharmony.model.Space;

/**
 * Service interface for managing Spaces.
 * Defines all business operations that can be performed on Spaces within the system.
 * Handles space creation, user management, and space retrieval operations.
 */
public interface SpaceService {
    
    /**
     * Retrieves a space by its unique identifier.
     * Includes all associated users in the returned space object.
     *
     * @param spaceId the ID of the space to retrieve
     * @return the space with the specified ID, or null if not found
     */
    Space getSpaceById(Integer spaceId);

    /**
     * Creates a new space in the system.
     * Validates space data before creation.
     *
     * @param space the space object to create
     * @return the created space with generated ID
     * @throws IllegalArgumentException if space data is invalid
     */
    Space createSpace(Space space) throws IllegalArgumentException;

    /**
     * Updates an existing space's information.
     *
     * @param space the space with updated information
     */
    void updateSpace(Space space);

    /**
     * Adds a user to an existing space.
     * Updates both space and user associations.
     *
     * @param spaceId the ID of the space
     * @param userId the ID of the user to add
     * @throws IllegalArgumentException if either space or user is not found
     */
    void addUserToSpace(Integer spaceId, Integer userId) throws IllegalArgumentException;

    /**
     * Removes a user from a space.
     * Updates both space and user associations.
     *
     * @param spaceId the ID of the space
     * @param userId the ID of the user to remove
     * @throws IllegalArgumentException if either space or user is not found
     * @throws IllegalStateException if user is not in the space
     */
    void removeUserFromSpace(Integer spaceId, Integer userId) throws IllegalArgumentException, IllegalStateException;

}
