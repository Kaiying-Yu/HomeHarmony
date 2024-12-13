package hh.homeharmony.service.templates;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import hh.homeharmony.mapper.ChoreMapper;
import hh.homeharmony.model.Chore;
import hh.homeharmony.model.FunctionalSpaceType;

/**
 * A template for generating default chores specific to a garage space.
 * This class extends DefaultChoreTemplate and provides
 * predefined chores tailored for garages.
 */
public class GarageChoreTemplate extends DefaultChoreTemplate {

  /**
   * Constructs a new GarageChoreTemplate.
   *
   * @param choreMapper the ChoreMapper used for mapping chore data
   */
  public GarageChoreTemplate(ChoreMapper choreMapper) {
    super(choreMapper);
  }

  /**
   * Returns a list of default chores for a garage.
   *
   * @param spaceId the ID of the functional space (garage) for which chores are created
   * @return a list of default Chore objects for the garage
   */
  @Override
  public List<Chore> getDefaultChores(Integer spaceId) {
    List<Chore> chores = new ArrayList<>();

    chores.add(createChore("Sweep the Floor",
        "Sweep the garage floor to remove dust and debris.",
        FunctionalSpaceType.GARAGE,
        Duration.ofMinutes(20),
        6,
        spaceId));

    chores.add(createChore("Sort Tools",
        "Organize tools and place them in their designated storage areas.",
        FunctionalSpaceType.GARAGE,
        Duration.ofMinutes(15),
        5,
        spaceId));

    chores.add(createChore("Remove Cobwebs",
        "Clear cobwebs from corners and ceilings.",
        FunctionalSpaceType.GARAGE,
        Duration.ofMinutes(10),
        4,
        spaceId));

    chores.add(createChore("Dispose of Recycling",
        "Take out recycling items and dispose of them appropriately.",
        FunctionalSpaceType.GARAGE,
        Duration.ofMinutes(10),
        4,
        spaceId));

    return chores;
  }

  /**
   * Returns the functional space type associated with this template.
   *
   * @return the FunctionalSpaceType for this template, which is GARAGE
   */
  @Override
  public FunctionalSpaceType getFunctionalSpaceType() {
    return FunctionalSpaceType.GARAGE;
  }
}