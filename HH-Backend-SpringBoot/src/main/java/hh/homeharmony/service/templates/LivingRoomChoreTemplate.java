package hh.homeharmony.service.templates;

import hh.homeharmony.mapper.ChoreMapper;
import hh.homeharmony.model.Chore;
import hh.homeharmony.model.FunctionalSpaceType;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class LivingRoomChoreTemplate extends DefaultChoreTemplate {

  public LivingRoomChoreTemplate(ChoreMapper choreMapper) {
    super(choreMapper);
  }

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

  @Override
  public FunctionalSpaceType getFunctionalSpaceType() {
    return FunctionalSpaceType.LIVING_ROOM;
  }
}
