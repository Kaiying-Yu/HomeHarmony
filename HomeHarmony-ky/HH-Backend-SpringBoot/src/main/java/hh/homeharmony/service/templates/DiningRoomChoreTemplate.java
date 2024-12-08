package hh.homeharmony.service.templates;

import hh.homeharmony.model.Chore;
import hh.homeharmony.mapper.ChoreMapper;
import hh.homeharmony.model.FunctionalSpaceType;
import java.util.ArrayList;
import java.time.Duration;
import java.util.List;

public class DiningRoomChoreTemplate extends DefaultChoreTemplate {

  public DiningRoomChoreTemplate(ChoreMapper choreMapper) {
    super(choreMapper);
  }

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

  @Override
  public FunctionalSpaceType getFunctionalSpaceType() {
    return FunctionalSpaceType.DINING_ROOM;
  }
}
