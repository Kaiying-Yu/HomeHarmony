package hh.homeharmony.service;

import hh.homeharmony.model.FunctionalSpaceType;

/**
 * Interface for services that handle the creation of default chores.
 */
public interface TemplateService {

  /**
   * Creates default chores for a specific space based on its functional type.
   *
   * @param spaceId The ID of the space for which chores need to be created.
   * @param type    The functional space type (e.g., KITCHEN, BATHROOM).
   */
  void createDefaultChores(Integer spaceId, FunctionalSpaceType type);
}