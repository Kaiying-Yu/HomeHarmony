package hh.homeharmony.service.templates;

import hh.homeharmony.mapper.ChoreMapper;
import hh.homeharmony.model.Chore;
import hh.homeharmony.model.FunctionalSpaceType;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class BedroomChoreTemplate extends DefaultChoreTemplate {

  public BedroomChoreTemplate(ChoreMapper choreMapper) {
    super(choreMapper);
  }

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

  @Override
  public FunctionalSpaceType getFunctionalSpaceType() {
    return FunctionalSpaceType.BEDROOM;
  }
}
