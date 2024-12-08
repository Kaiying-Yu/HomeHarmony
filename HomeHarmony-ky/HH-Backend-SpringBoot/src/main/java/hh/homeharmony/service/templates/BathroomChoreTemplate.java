package hh.homeharmony.service.templates;

import hh.homeharmony.model.Chore;
import hh.homeharmony.mapper.ChoreMapper;
import hh.homeharmony.model.FunctionalSpaceType;
import java.util.ArrayList;
import java.time.Duration;
import java.util.List;

public class BathroomChoreTemplate extends DefaultChoreTemplate {

  public BathroomChoreTemplate(ChoreMapper choreMapper) {
    super(choreMapper);
  }

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

  @Override
  public FunctionalSpaceType getFunctionalSpaceType() {
    return FunctionalSpaceType.BATHROOM;
  }
}
