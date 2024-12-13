package hh.homeharmony.service.templates;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import hh.homeharmony.mapper.ChoreMapper;
import hh.homeharmony.model.Chore;
import hh.homeharmony.model.FunctionalSpaceType;

/**
 * A template for generating default chores specific to a kitchen space.
 * This class extends DefaultChoreTemplate and provides
 * predefined chores tailored for kitchens.
 */
public class KitchenChoreTemplate extends DefaultChoreTemplate {

  /**
   * Constructs a new KitchenChoreTemplate.
   *
   * @param choreMapper the ChoreMapper used for mapping chore data
   */
  public KitchenChoreTemplate(ChoreMapper choreMapper) {
    super(choreMapper);
  }

  /**
   * Returns a list of default chores for a kitchen.
   *
   * @param spaceId the ID of the functional space (kitchen) for which chores are created
   * @return a list of default Chore objects for the kitchen
   */
  @Override
  public List<Chore> getDefaultChores(Integer spaceId) {
    List<Chore> chores = new ArrayList<>();

    chores.add(createChore("Wash Dishes",
        "Wash all dirty dishes, utensils, and cooking pots.",
        FunctionalSpaceType.KITCHEN,
        Duration.ofMinutes(15),
        5,
        spaceId));

    chores.add(createChore("Clean Countertops",
        "Wipe down all countertops and remove stains or spills.",
        FunctionalSpaceType.KITCHEN,
        Duration.ofMinutes(10),
        4,
        spaceId));

    chores.add(createChore("Empty Trash",
        "Take out the trash and replace the garbage bag.",
        FunctionalSpaceType.KITCHEN,
        Duration.ofMinutes(5),
        3,
        spaceId));

    chores.add(createChore("Clean Refrigerator",
        "Remove expired food, clean shelves, and organize items in the fridge.",
        FunctionalSpaceType.KITCHEN,
        Duration.ofMinutes(20),
        6,
        spaceId));

    return chores;
  }

  /**
   * Returns the functional space type associated with this template.
   *
   * @return the FunctionalSpaceType for this template, which is KITCHEN
   */
  @Override
  public FunctionalSpaceType getFunctionalSpaceType() {
    return FunctionalSpaceType.KITCHEN;
  }
}