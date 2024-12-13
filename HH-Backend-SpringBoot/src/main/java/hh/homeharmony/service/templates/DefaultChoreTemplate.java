package hh.homeharmony.service.templates;

import java.time.Duration;
import java.util.List;

import hh.homeharmony.mapper.ChoreMapper;
import hh.homeharmony.model.Chore;
import hh.homeharmony.model.ChoreStatus;
import hh.homeharmony.model.FunctionalSpaceType;

/**
 * An abstract template class for creating and managing default chores
 * for specific functional spaces. Subclasses should define the specific
 * default chores and associated functional space type.
 */
public abstract class DefaultChoreTemplate {

  /**
   * The ChoreMapper used to persist chore data.
   */
  protected final ChoreMapper choreMapper;

  /**
   * Constructs a new DefaultChoreTemplate.
   *
   * @param choreMapper the ChoreMapper used for mapping chore data
   */
  protected DefaultChoreTemplate(ChoreMapper choreMapper) {
    this.choreMapper = choreMapper;
  }

  /**
   * Creates a new Chore object with the given parameters.
   *
   * @param name the name of the chore
   * @param description a description of the chore
   * @param spaceType the functional space type associated with the chore
   * @param estimatedTime the estimated time required to complete the chore
   * @param points the points assigned to the chore based on difficulty
   * @param spaceId the ID of the space associated with this chore
   * @return a new Chore object with the specified details
   */
  protected Chore createChore(String name, String description, FunctionalSpaceType spaceType,
      Duration estimatedTime, Integer points, Integer spaceId) {
    Chore chore = new Chore();
    chore.setChoreName(name);
    chore.setChoreDescription(description);
    chore.setFunctionalSpaceType(spaceType);
    chore.setEstimatedTime(estimatedTime);
    chore.setPoints(points);
    chore.setStatus(ChoreStatus.PENDING); // Default status
    return chore;
  }

  /**
   * Creates and persists the default chores for the specified space.
   *
   * @param spaceId the ID of the functional space for which default chores are created
   */
  public void createDefaultChores(Integer spaceId) {
    List<Chore> chores = getDefaultChores(spaceId);
    for (Chore chore : chores) {
      choreMapper.insert(chore);
    }
  }

  /**
   * Returns a list of default chores for the given space ID.
   * Subclasses must implement this method to define specific chore details.
   *
   * @param spaceId the ID of the functional space for which default chores are retrieved
   * @return a list of default chores for the specified space
   */
  public abstract List<Chore> getDefaultChores(Integer spaceId);

  /**
   * Returns the FunctionalSpaceType associated with this template.
   * Subclasses must implement this method to specify the functional space type.
   *
   * @return the FunctionalSpaceType for this template
   */
  public abstract FunctionalSpaceType getFunctionalSpaceType();
}