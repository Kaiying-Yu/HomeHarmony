package hh.homeharmony.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hh.homeharmony.mapper.SpaceMapper;
import hh.homeharmony.mapper.UserMapper;
import hh.homeharmony.model.Space;
import hh.homeharmony.model.User;
import hh.homeharmony.service.SpaceService;

/**
 * Implementation of the SpaceService interface.
 * Handles business logic for Space-related operations including creation,
 * updates, and user management within spaces.
 */
@Service
public class SpaceServiceImpl implements SpaceService {
  @Autowired
  private SpaceMapper spaceMapper; // MyBatis mapper for Space database operations

  @Autowired
  private UserMapper userMapper; // MyBatis mapper for User database operations

  /**
   * Retrieves a space by its ID, including all associated users.
   *
   * @param spaceId the ID of the space to retrieve
   * @return the space with its users, or null if not found
   */
  @Override
  public Space getSpaceById(Integer spaceId) {
    Space space = spaceMapper.findSpaceById(spaceId);
    if (space != null) {
        List<User> users = userMapper.findUsersBySpaceId(spaceId);
        space.setUsers(users);
    }
    return space;
  }

  /**
   * Creates a new space with an initial creator user.
   * The creator becomes the first member of the space.
   *
   * @param space the space to create, must include at least one user as creator
   * @return the created space with generated ID and initialized user list
   * @throws IllegalArgumentException if space has no users or creator not found
   */
  @Override
  public Space createSpace(Space space) throws IllegalArgumentException {
    // Validate that space has at least one user as creator
    if (space.getUsers() == null || space.getUsers().isEmpty()) {
        throw new IllegalArgumentException("Space must have a creator");
    }
    Integer creatorId = space.getUsers().get(0).getId();
    
    // Verify creator exists in the system
    User creator = userMapper.findUserById(creatorId);
    if (creator == null) {
        throw new IllegalArgumentException("Creator not found");
    }
    
    // Initialize empty user list for new space
    space.setUsers(new ArrayList<>());
    
    // Create space and establish creator relationship
    spaceMapper.insertSpace(space);
    space.addUser(creator);
    spaceMapper.updateSpace(space);
    userMapper.updateUserSpace(creator.getId(), space.getId());
    
    return spaceMapper.findSpaceById(space.getId());
  }

  /**
   * Updates an existing space's information.
   *
   * @param space the space with updated information
   */
  @Override
  public void updateSpace(Space space) {
    spaceMapper.updateSpace(space);
  }

  /**
   * Adds a user to an existing space.
   * Verifies both space and user exist before adding the relationship.
   *
   * @param spaceId the ID of the space
   * @param userId the ID of the user to add
   * @throws IllegalArgumentException if either space or user is not found
   */
  @Override
  public void addUserToSpace(Integer spaceId, Integer userId) throws IllegalArgumentException {
    // Verify space exists
    Space space = spaceMapper.findSpaceById(spaceId);
    if (space == null) {
        throw new IllegalArgumentException("Space not found");
    }
    
    // Verify user exists
    User user = userMapper.findUserById(userId);
    if (user == null) {
        throw new IllegalArgumentException("User not found");
    }
    
    // Add user to space and update relationships
    space.addUser(user);
    spaceMapper.updateSpace(space);
    userMapper.updateUserSpace(userId, spaceId);
  }

  /**
   * Removes a user from a space.
   * Verifies user belongs to the specified space before removal.
   *
   * @param spaceId the ID of the space
   * @param userId the ID of the user to remove
   * @throws IllegalArgumentException if space or user not found
   * @throws IllegalStateException if user doesn't belong to the specified space
   */
  @Override
  public void removeUserFromSpace(Integer spaceId, Integer userId) throws IllegalArgumentException, IllegalStateException {
    // Verify space and user exist
    Space space = spaceMapper.findSpaceById(spaceId);
    User user = userMapper.findUserById(userId);
    
    if (space == null) {
        throw new IllegalArgumentException("Space not found");
    }
    
    if (user == null) {
        throw new IllegalArgumentException("User not found");
    }

    // Verify user belongs to a space
    Integer userSpaceId = user.getSpaceId();
    if (userSpaceId == null) {
        throw new IllegalStateException("User is not a member of any space");
    }
    
    // Verify user belongs to the specified space
    if (!userSpaceId.equals(spaceId)) {
        throw new IllegalStateException("User belongs to different space: " + userSpaceId);
    }

    // Remove user from space
    userMapper.updateUserSpace(userId, null);
  }
}
