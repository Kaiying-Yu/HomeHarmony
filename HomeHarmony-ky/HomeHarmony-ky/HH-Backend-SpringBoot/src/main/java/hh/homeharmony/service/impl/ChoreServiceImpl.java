package hh.homeharmony.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hh.homeharmony.mapper.ChoreMapper;
import hh.homeharmony.mapper.UserMapper;
import hh.homeharmony.model.Chore;
import hh.homeharmony.model.ChoreStatus;
import hh.homeharmony.model.User;
import hh.homeharmony.service.ChoreService;

/**
 * Implementation of the ChoreService interface that provides business logic for chore management.
 * This service handles all chore-related operations including creation, updates, deletion,
 * assignment, and status changes.
 */
@Service
public class ChoreServiceImpl implements ChoreService {

    // Mapper for database operations related to chores
    @Autowired
    private ChoreMapper choreMapper;

    // Mapper for database operations related to users
    @Autowired
    private UserMapper userMapper;

    /**
     * Retrieves all chores associated with a specific space.
     *
     * @param spaceId the ID of the space to get chores from
     * @return List of chores in the specified space
     */
    @Override
    public List<Chore> getAllChores(Integer spaceId) {
        return choreMapper.selectAllBySpaceId(spaceId);
    }

    /**
     * Creates a new chore in the system.
     * Sets the initial status to PENDING and validates required fields.
     *
     * @param chore the chore object to create
     * @return the created chore with generated ID
     * @throws IllegalArgumentException if space ID is missing
     */
    @Override
    public Chore createChore(Chore chore) throws IllegalArgumentException {
        if (chore.getSpaceId() == null) {
            throw new IllegalArgumentException("Space ID is required");
        }
        chore.setStatus(ChoreStatus.PENDING);
        choreMapper.insert(chore);
        return chore;
    }

    /**
     * Updates an existing chore's information.
     * Verifies the chore exists before updating.
     *
     * @param chore the chore with updated information
     * @return the updated chore
     * @throws RuntimeException if the chore doesn't exist
     */
    @Override
    public Chore updateChore(Chore chore) {
        Chore existingChore = choreMapper.selectById(chore.getId());
        if (existingChore == null) {
            throw new RuntimeException("Chore not found");
        }
        choreMapper.update(chore);
        return chore;
    }

    /**
     * Deletes a chore from the system.
     *
     * @param id the ID of the chore to delete
     */
    @Override
    public void deleteChore(Integer id) {
        choreMapper.deleteById(id);
    }

    /**
     * Assigns a user to a specific chore.
     * Verifies both chore and user exist before assignment.
     * Updates the chore's status to IN_PROGRESS upon assignment.
     *
     * @param choreId the ID of the chore to assign
     * @param userId the ID of the user to assign
     * @throws IllegalArgumentException if either chore or user is not found
     */
    @Override
    public void assignUserToChore(Integer choreId, Integer userId) throws IllegalArgumentException {
        // Verify chore exists
        Chore chore = choreMapper.selectById(choreId);
        if (chore == null) {
            throw new IllegalArgumentException("Chore not found");
        }

        // Verify user exists
        User user = userMapper.findUserById(userId);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        chore.assignUser(user);
        choreMapper.update(chore);
    }

    /**
     * Marks a chore as completed.
     * Only chores in IN_PROGRESS state can be completed.
     *
     * @param choreId the ID of the chore to complete
     * @return the completed chore
     * @throws IllegalArgumentException if chore is not found
     */
    @Override
    public Chore completeChore(Integer choreId) throws IllegalArgumentException {
        Chore chore = choreMapper.selectById(choreId);
        if (chore == null) {
            throw new IllegalArgumentException("Chore not found");
        }
        chore.markAsCompleted();
        choreMapper.update(chore);
        return chore;
    }

    /**
     * Retrieves all chores assigned to a specific user.
     *
     * @param userId the ID of the user
     * @return List of chores assigned to the specified user
     */
    @Override
    public List<Chore> getChoresByUserId(Integer userId) {
        return choreMapper.findChoresByUserId(userId);
    }
}