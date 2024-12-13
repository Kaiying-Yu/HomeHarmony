package hh.homeharmony.service;

import hh.homeharmony.model.User;

/**
 * Service interface for managing Users.
 * Defines all business operations that can be performed on Users within the system.
 * Handles user creation, updates, deletion, authentication, and point management.
 */
/* OOD Idea: Dependency Inversion-Details depend on abstractions */
/* OOD Idea: Abstraction-This interface defines the contract for any class that implements it,
without specifying how the operations are performed.*/
public interface UserService {
    /**
     * Retrieves a user by their unique identifier.
     *
     * @param id the ID of the user to retrieve
     * @return the found user, or null if not found
     * @throws IllegalArgumentException if id is null or invalid
     */
    /* OOD Idea: check inputs */
    User getUserById(Integer id) throws IllegalArgumentException;

    /**
     * Creates a new user in the system.
     *
     * @param user the user to create
     * @throws IllegalArgumentException if user data is invalid
     */
    /* OOD Idea: check inputs */
    void createUser(User user) throws IllegalArgumentException;

    /**
     * Updates an existing user's information.
     *
     * @param user the user with updated information
     * @throws IllegalArgumentException if user not found or data invalid
     */
    /* OOD Idea: check inputs */
    void updateUser(User user) throws IllegalArgumentException;

    /**
     * Deletes a user from the system.
     *
     * @param id the ID of the user to delete
     * @throws IllegalArgumentException if id is null or user not found
     */
    /* OOD Idea: check inputs */
    void deleteUser(Integer id) throws IllegalArgumentException;

    /**
     * Authenticates a user and creates a session.
     *
     * @param user the user credentials for login
     * @return the authenticated user with session information
     * @throws IllegalArgumentException if credentials are invalid
     */
    /* OOD Idea: check inputs */
    User login(User user) throws IllegalArgumentException;

    /**
     * Adds points to a user's total score.
     * Used for rewarding completed chores and other achievements.
     *
     * @param userId the ID of the user to receive points
     * @param points the number of points to add
     * @throws IllegalArgumentException if user not found or points invalid
     */
    /* OOD Idea: check inputs */
    void addPoints(Integer userId, Integer points) throws IllegalArgumentException;
}