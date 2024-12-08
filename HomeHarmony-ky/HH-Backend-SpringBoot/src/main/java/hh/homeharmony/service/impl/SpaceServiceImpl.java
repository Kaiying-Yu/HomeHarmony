package hh.homeharmony.service.impl;

import hh.homeharmony.dto.SpaceRequest;
import hh.homeharmony.model.Space;
import hh.homeharmony.model.User;
import hh.homeharmony.service.SpaceService;
import hh.homeharmony.mapper.SpaceMapper;
import org.springframework.stereotype.Service;

/**
 * Implementation of SpaceService interface.
 * Manages Spaces and their associated operations.
 */
@Service
public class SpaceServiceImpl implements SpaceService {

  private final SpaceMapper spaceMapper;

  public SpaceServiceImpl(SpaceMapper spaceMapper) {
    this.spaceMapper = spaceMapper;
  }

  @Override
  public void createSpace(SpaceRequest request) {
    Space space = request.getSpace();
    User creator = request.getUser();

    if (space == null || creator == null) {
      throw new IllegalArgumentException("Space and Creator cannot be null");
    }

    spaceMapper.insertSpace(space); // Insert the Space
    spaceMapper.addUserToSpace(space.getId(), creator.getId()); // Associate creator with the Space
  }

  @Override
  public void joinSpace(Integer spaceId, SpaceRequest request) {
    User user = request.getUser();

    if (spaceId == null || user == null) {
      throw new IllegalArgumentException("Space ID and User cannot be null");
    }

    spaceMapper.addUserToSpace(spaceId, user.getId());
  }


  @Override
  public Space getSpaceById(Integer id) {
    if (id == null) {
      throw new IllegalArgumentException("Space ID cannot be null");
    }

    return spaceMapper.findSpaceById(id);
  }
}
