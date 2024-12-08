package hh.homeharmony.service;

import hh.homeharmony.model.Space;
import hh.homeharmony.dto.SpaceRequest;

/**
 * Service interface for managing Spaces.
 * Defines basic operations for creating, joining, and fetching Spaces.
 */
public interface SpaceService {

  /**
   * Creates a new Space based on the SpaceRequest.
   *
   * @param request The SpaceRequest containing Space and User details.
   */
  void createSpace(SpaceRequest request);

  /**
   * Adds a User to an existing Space based on the SpaceRequest.
   *
   * @param spaceId The ID of the Space to join.
   * @param request The SpaceRequest containing User details.
   */
  void joinSpace(Integer spaceId, SpaceRequest request);

  /**
   * Fetches a Space by its ID.
   *
   * @param id The ID of the Space.
   * @return The Space with the given ID.
   */
  Space getSpaceById(Integer id);
}

