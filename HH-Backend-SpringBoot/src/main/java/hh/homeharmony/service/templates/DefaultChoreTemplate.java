package hh.homeharmony.service.templates;

import java.time.Duration;
import java.util.List;

import hh.homeharmony.mapper.ChoreMapper;
import hh.homeharmony.model.Chore;
import hh.homeharmony.model.ChoreStatus;
import hh.homeharmony.model.FunctionalSpaceType;

public abstract class DefaultChoreTemplate {

  protected final ChoreMapper choreMapper;

  protected DefaultChoreTemplate(ChoreMapper choreMapper) {
    this.choreMapper = choreMapper;
  }

  protected Chore createChore(String name, String description, FunctionalSpaceType spaceType,
      Duration estimatedTime, Integer points, Integer spaceId) {
    Chore chore = new Chore();
    chore.setChoreName(name);
    chore.setChoreDescription(description);
    chore.setFunctionalSpaceType(spaceType);
    chore.setEstimatedTime(estimatedTime);
    chore.setPoints(points);
    chore.setStatus(ChoreStatus.PENDING); // Default status
//    chore.setAssignedUser(null); // No assigned user by default
//    chore.setSpaceId(spaceId); // Space ID associated with this chore
    return chore;
  }

  /**
   * Create and persist the default chores for the given space.
   */
  public void createDefaultChores(Integer spaceId) {
    List<Chore> chores = getDefaultChores(spaceId);
    for (Chore chore : chores) {
      choreMapper.insert(chore);
    }
  }

  /**
   * Returns a list of default chores for the given spaceId.
   * Implementations define the chore details (names, frequencies, etc.).
   */
  public abstract List<Chore> getDefaultChores(Integer spaceId);

  /**
   * Returns the FunctionalSpaceType associated with this template.
   */
  public abstract FunctionalSpaceType getFunctionalSpaceType();
}
