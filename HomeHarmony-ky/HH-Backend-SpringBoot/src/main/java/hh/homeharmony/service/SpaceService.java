package hh.homeharmony.service;

import hh.homeharmony.model.Space;

/**
 * Service interface for managing Spaces.
 * Defines all business operations that can be performed on a Space.
 *
 * @author hh
 */
public interface SpaceService {
  Space getSpaceById(Integer id); // Fetches a Space by its ID
  Space createSpace(Space space); // Creates a new Space
  void updateSpace(Space space); // Updates an existing Space
  void addUserToSpace(Integer spaceId, Integer userId);
  void removeUserFromSpace(Integer spaceId, Integer userId);
}
