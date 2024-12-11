package hh.homeharmony.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hh.homeharmony.mapper.UserMapper;
import hh.homeharmony.model.User;
import hh.homeharmony.service.UserService;

/**
 * Implementation of the UserService interface.
 * Provides business logic for user management operations including
 * CRUD operations, authentication, and points management.
 */
@Service
public class UserServiceImpl implements UserService {

    // Mapper for database operations related to users
    @Autowired
    private UserMapper userMapper;

    /**
     * Retrieves a user by their ID.
     *
     * @param id the ID of the user to retrieve
     * @return the found user
     * @throws IllegalArgumentException if ID is null or invalid
     */
    @Override
    public User getUserById(Integer id) throws IllegalArgumentException {
        if (id == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        User user = userMapper.findUserById(id);
        if (user == null) {
            throw new IllegalArgumentException("User not found with ID: " + id);
        }
        return user;
    }

    /**
     * Creates a new user in the system.
     *
     * @param user the user to create
     * @throws IllegalArgumentException if user is null or has invalid fields
     */
    @Override
    public void createUser(User user) throws IllegalArgumentException {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("Username is required");
        }
        userMapper.insertUser(user);
    }

    /**
     * Updates an existing user's information.
     *
     * @param user the user with updated information
     * @throws IllegalArgumentException if user is null, not found, or has invalid data
     */
    @Override
    public void updateUser(User user) throws IllegalArgumentException {
        if (user == null || user.getId() == null) {
            throw new IllegalArgumentException("User and ID cannot be null");
        }
        // Verify user exists
        if (userMapper.findUserById(user.getId()) == null) {
            throw new IllegalArgumentException("User not found with ID: " + user.getId());
        }
        userMapper.updateUser(user);
    }

    /**
     * Deletes a user from the system.
     *
     * @param id the ID of the user to delete
     * @throws IllegalArgumentException if ID is null or user not found
     */
    @Override
    public void deleteUser(Integer id) throws IllegalArgumentException {
        if (id == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        // Verify user exists before deletion
        if (userMapper.findUserById(id) == null) {
            throw new IllegalArgumentException("User not found with ID: " + id);
        }
        userMapper.deleteUser(id);
    }

    /**
     * Authenticates a user and creates a session.
     *
     * @param user the user credentials for login
     * @return the authenticated user with session information
     * @throws IllegalArgumentException if credentials are invalid or user not found
     */
    @Override
    public User login(User user) throws IllegalArgumentException {
        if (user == null || user.getUsername() == null || user.getPassword() == null) {
            throw new IllegalArgumentException("Username and password are required");
        }
        User authenticatedUser = userMapper.getByUsernameAndPassword(user);
        if (authenticatedUser == null) {
            throw new IllegalArgumentException("Invalid username or password");
        }
        return authenticatedUser;
    }

    /**
     * Adds points to a user's total score.
     *
     * @param userId the ID of the user to receive points
     * @param points the number of points to add
     * @throws IllegalArgumentException if user not found or points invalid
     */
    @Override
    public void addPoints(Integer userId, Integer points) throws IllegalArgumentException {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        if (points == null || points < 0) {
            throw new IllegalArgumentException("Points must be a positive number");
        }

        User user = userMapper.findUserById(userId);
        if (user == null) {
            throw new IllegalArgumentException("User not found with ID: " + userId);
        }

        // Calculate and update points
        Integer currentPoints = user.getPoints() != null ? user.getPoints() : 0;
        user.setPoints(currentPoints + points);
        userMapper.updatePoints(user);
    }
}