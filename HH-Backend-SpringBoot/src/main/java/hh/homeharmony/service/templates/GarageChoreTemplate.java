package hh.homeharmony.service.templates;

import hh.homeharmony.mapper.ChoreMapper;
import hh.homeharmony.model.Chore;
import hh.homeharmony.model.FunctionalSpaceType;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class GarageChoreTemplate extends DefaultChoreTemplate {

  public GarageChoreTemplate(ChoreMapper choreMapper) {
    super(choreMapper);
  }

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

  @Override
  public FunctionalSpaceType getFunctionalSpaceType() {
    return FunctionalSpaceType.GARAGE;
  }
}
