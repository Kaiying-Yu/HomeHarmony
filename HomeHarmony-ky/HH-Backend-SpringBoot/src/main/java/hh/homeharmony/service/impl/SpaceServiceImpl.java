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
 * Handles business logic for Space-related operations.
 *
 * @author hh
 */
@Service
public class SpaceServiceImpl implements SpaceService {
  @Autowired
  private SpaceMapper spaceMapper; // MyBatis mapper for Space

  @Autowired
  private UserMapper userMapper; // MyBatis mapper for User

  @Override
  public Space getSpaceById(Integer spaceId) {
    Space space = spaceMapper.findSpaceById(spaceId);
    if (space != null) {
        List<User> users = userMapper.findUsersBySpaceId(spaceId);
        space.setUsers(users);
    }
    return space;
  }

  @Override
  public Space createSpace(Space space) {
    if (space.getUsers() == null || space.getUsers().isEmpty()) {
        throw new IllegalArgumentException("Space must have a creator");
    }
    Integer creatorId = space.getUsers().get(0).getId();
    
    User creator = userMapper.findUserById(creatorId);
    if (creator == null) {
        throw new IllegalArgumentException("Creator not found");
    }
    
    space.setUsers(new ArrayList<>());
    
    spaceMapper.insertSpace(space);
    space.addUser(creator);
    spaceMapper.updateSpace(space);
    userMapper.updateUserSpace(creator.getId(), space.getId());
    
    return spaceMapper.findSpaceById(space.getId());
  }

  @Override
  public void updateSpace(Space space) {
    spaceMapper.updateSpace(space); // Update existing Space details
  }

  @Override
  public void addUserToSpace(Integer spaceId, Integer userId) {
    Space space = spaceMapper.findSpaceById(spaceId);
    if (space == null) {
        throw new IllegalArgumentException("Space not found");
    }
    
    User user = userMapper.findUserById(userId);
    if (user == null) {
        throw new IllegalArgumentException("User not found");
    }
    
    space.addUser(user);
    spaceMapper.updateSpace(space);
    userMapper.updateUserSpace(userId, spaceId);
  }

  @Override
  public void removeUserFromSpace(Integer spaceId, Integer userId) {
    Space space = spaceMapper.findSpaceById(spaceId);
    User user = userMapper.findUserById(userId);
    
    if (space == null) {
        throw new IllegalArgumentException("Space not found");
    }
    
    if (user == null) {
        throw new IllegalArgumentException("User not found");
    }

    Integer userSpaceId = user.getSpaceId();
    if (userSpaceId == null) {
        throw new IllegalStateException("User is not a member of any space");
    }
    
    if (!userSpaceId.equals(spaceId)) {
        throw new IllegalStateException("User belongs to different space: " + userSpaceId);
    }
    
    userMapper.updateUserSpace(userId, null);
  }
}
