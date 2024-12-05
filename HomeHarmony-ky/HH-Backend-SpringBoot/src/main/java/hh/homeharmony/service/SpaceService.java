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
  void deleteSpace(Integer id); // Deletes a Space by its ID
  void addUserToSpace(Integer spaceId, Integer userId);
}
