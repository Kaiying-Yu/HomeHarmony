package hh.homeharmony.service.templates;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import hh.homeharmony.mapper.ChoreMapper;
import hh.homeharmony.model.Chore;
import hh.homeharmony.model.FunctionalSpaceType;

/**
 * A template for generating default chores specific to a living room space.
 * This class extends DefaultChoreTemplate and provides
 * predefined chores tailored for living rooms.
 */
public class LivingRoomChoreTemplate extends DefaultChoreTemplate {

  /**
   * Constructs a new LivingRoomChoreTemplate.
   *
   * @param choreMapper the ChoreMapper used for mapping chore data
   */
  public LivingRoomChoreTemplate(ChoreMapper choreMapper) {
    super(choreMapper);
  }

  /**
   * Returns a list of default chores for a living room.
   *
   * @param spaceId the ID of the functional space (living room) for which chores are created
   * @return a list of default Chore objects for the living room
   */
  @Override
  public List<Chore> getDefaultChores(Integer spaceId) {
    List<Chore> chores = new ArrayList<>();

    chores.add(createChore("Vacuum Carpets and Rugs",
        "Vacuum the living room carpet and rugs thoroughly.",
        FunctionalSpaceType.LIVING_ROOM,
        Duration.ofMinutes(15),
        5,
        spaceId));

    chores.add(createChore("Dust Furniture and Electronics",
        "Dust all surfaces, including shelves, tables, and electronics.",
        FunctionalSpaceType.LIVING_ROOM,
        Duration.ofMinutes(10),
        4,
        spaceId));

    chores.add(createChore("Organize Pillows and Blankets",
        "Arrange pillows and fold blankets neatly.",
        FunctionalSpaceType.LIVING_ROOM,
        Duration.ofMinutes(5),
        2,
        spaceId));

    chores.add(createChore("Clean Windows",
        "Wipe down windows and clean the glass for a streak-free shine.",
        FunctionalSpaceType.LIVING_ROOM,
        Duration.ofMinutes(20),
        6,
        spaceId));

    return chores;
  }

  /**
   * Returns the functional space type associated with this template.
   *
   * @return the FunctionalSpaceType for this template, which is LIVING_ROOM
   */
  @Override
  public FunctionalSpaceType getFunctionalSpaceType() {
    return FunctionalSpaceType.LIVING_ROOM;
  }
}