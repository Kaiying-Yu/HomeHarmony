package hh.homeharmony.service.templates;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import hh.homeharmony.mapper.ChoreMapper;
import hh.homeharmony.model.Chore;
import hh.homeharmony.model.FunctionalSpaceType;

/**
 * A template for generating default chores specific to a bathroom space.
 * This class extends DefaultChoreTemplate and provides
 * predefined chores tailored for bathrooms.
 */
public class BathroomChoreTemplate extends DefaultChoreTemplate {

  /**
   * Constructs a new BathroomChoreTemplate.
   *
   * @param choreMapper the ChoreMapper used for mapping chore data
   */
  public BathroomChoreTemplate(ChoreMapper choreMapper) {
    super(choreMapper);
  }

  /**
   * Returns a list of default chores for a bathroom.
   *
   * @param spaceId the ID of the functional space (bathroom) for which chores are created
   * @return a list of default Chore objects for the bathroom
   */
  @Override
  public List<Chore> getDefaultChores(Integer spaceId) {
    List<Chore> chores = new ArrayList<>();

    chores.add(createChore("Clean Toilet",
        "Scrub and disinfect the toilet bowl, seat, and exterior.",
        FunctionalSpaceType.BATHROOM,
        Duration.ofMinutes(20),
        7,
        spaceId));

    chores.add(createChore("Clean Sink and Faucet",
        "Remove stains, scrub, and polish the sink and faucet.",
        FunctionalSpaceType.BATHROOM,
        Duration.ofMinutes(10),
        4,
        spaceId));

    chores.add(createChore("Mop Bathroom Floor",
        "Sweep and mop the floor to remove dirt and water stains.",
        FunctionalSpaceType.BATHROOM,
        Duration.ofMinutes(15),
        5,
        spaceId));

    chores.add(createChore("Clean Shower Area",
        "Scrub tiles and glass in the shower or bathtub.",
        FunctionalSpaceType.BATHROOM,
        Duration.ofMinutes(20),
        6,
        spaceId));

    return chores;
  }

  /**
   * Returns the functional space type associated with this template.
   *
   * @return the FunctionalSpaceType for this template, which is BATHROOM
   */
  @Override
  public FunctionalSpaceType getFunctionalSpaceType() {
    return FunctionalSpaceType.BATHROOM;
  }
}
