package hh.homeharmony.service;

import hh.homeharmony.model.FunctionalSpaceType;

/**
 * Interface for services that handle the creation of default chores.
 */
/* OOD Idea: Dependency Inversion-Details depend on abstractions */
/* OOD Idea: Abstraction-This interface defines the contract for any class that implements it,
without specifying how the operations are performed.*/
public interface TemplateService {

  /**
   * Creates default chores for a specific space based on its functional type.
   *
   * @param spaceId The ID of the space for which chores need to be created.
   * @param type    The functional space type (e.g., KITCHEN, BATHROOM).
   */
  void createDefaultChores(Integer spaceId, FunctionalSpaceType type);
}