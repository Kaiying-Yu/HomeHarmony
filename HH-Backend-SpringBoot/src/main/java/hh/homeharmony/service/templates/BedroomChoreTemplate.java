package hh.homeharmony.service.templates;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import hh.homeharmony.mapper.ChoreMapper;
import hh.homeharmony.model.Chore;
import hh.homeharmony.model.FunctionalSpaceType;

/**
 * A template for generating default chores specific to a bedroom space.
 * This class extends DefaultChoreTemplate and provides
 * predefined chores tailored for bedrooms.
 */
public class BedroomChoreTemplate extends DefaultChoreTemplate {

  /**
   * Constructs a new BedroomChoreTemplate.
   *
   * @param choreMapper the ChoreMapper used for mapping chore data
   */
  public BedroomChoreTemplate(ChoreMapper choreMapper) {
    super(choreMapper);
  }

  /**
   * Returns a list of default chores for a bedroom.
   *
   * @param spaceId the ID of the functional space (bedroom) for which chores are created
   * @return a list of default Chore objects for the bedroom
   */
  @Override
  public List<Chore> getDefaultChores(Integer spaceId) {
    List<Chore> chores = new ArrayList<>();

    chores.add(createChore("Make the Bed",
        "Arrange the bedding neatly, including sheets, blankets, and pillows.",
        FunctionalSpaceType.BEDROOM,
        Duration.ofMinutes(5),
        3,
        spaceId));

    chores.add(createChore("Organize Closet",
        "Tidy up the closet by folding clothes and sorting items.",
        FunctionalSpaceType.BEDROOM,
        Duration.ofMinutes(15),
        6,
        spaceId));

    chores.add(createChore("Vacuum the Floor",
        "Vacuum the bedroom floor or mop if it's not carpeted.",
        FunctionalSpaceType.BEDROOM,
        Duration.ofMinutes(10),
        4,
        spaceId));

    chores.add(createChore("Dust Surfaces",
        "Dust nightstands, dressers, and other surfaces.",
        FunctionalSpaceType.BEDROOM,
        Duration.ofMinutes(10),
        4,
        spaceId));

    return chores;
  }

  /**
   * Returns the functional space type associated with this template.
   *
   * @return the FunctionalSpaceType for this template, which is BEDROOM
   */
  @Override
  public FunctionalSpaceType getFunctionalSpaceType() {
    return FunctionalSpaceType.BEDROOM;
  }
}