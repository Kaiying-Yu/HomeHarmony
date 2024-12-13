package hh.homeharmony.service.templates;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import hh.homeharmony.mapper.ChoreMapper;
import hh.homeharmony.model.Chore;
import hh.homeharmony.model.FunctionalSpaceType;

/**
 * A template for generating default chores specific to a dining room space.
 * This class extends DefaultChoreTemplate and provides
 * predefined chores tailored for dining rooms.
 */
public class DiningRoomChoreTemplate extends DefaultChoreTemplate {

  /**
   * Constructs a new DiningRoomChoreTemplate.
   *
   * @param choreMapper the ChoreMapper used for mapping chore data
   */
  public DiningRoomChoreTemplate(ChoreMapper choreMapper) {
    super(choreMapper);
  }

  /**
   * Returns a list of default chores for a dining room.
   *
   * @param spaceId the ID of the functional space (dining room) for which chores are created
   * @return a list of default Chore objects for the dining room
   */
  @Override
  public List<Chore> getDefaultChores(Integer spaceId) {
    List<Chore> chores = new ArrayList<>();

    chores.add(createChore("Wipe Dining Table",
        "Wipe down the dining table and chairs to remove dust and stains.",
        FunctionalSpaceType.DINING_ROOM,
        Duration.ofMinutes(10),
        4,
        spaceId));

    chores.add(createChore("Vacuum/Mop Floor",
        "Clean the floor under and around the dining area.",
        FunctionalSpaceType.DINING_ROOM,
        Duration.ofMinutes(15),
        5,
        spaceId));

    chores.add(createChore("Arrange Chairs",
        "Align chairs neatly around the table.",
        FunctionalSpaceType.DINING_ROOM,
        Duration.ofMinutes(5),
        2,
        spaceId));

    chores.add(createChore("Polish Glassware",
        "Clean and polish glassware on display.",
        FunctionalSpaceType.DINING_ROOM,
        Duration.ofMinutes(15),
        6,
        spaceId));

    return chores;
  }

  /**
   * Returns the functional space type associated with this template.
   *
   * @return the FunctionalSpaceType for this template, which is DINING_ROOM
   */
  @Override
  public FunctionalSpaceType getFunctionalSpaceType() {
    return FunctionalSpaceType.DINING_ROOM;
  }
}