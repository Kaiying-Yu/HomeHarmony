package hh.homeharmony.service.templates;

import hh.homeharmony.mapper.ChoreMapper;
import hh.homeharmony.model.Chore;
import hh.homeharmony.model.FunctionalSpaceType;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class KitchenChoreTemplate extends DefaultChoreTemplate {

  public KitchenChoreTemplate(ChoreMapper choreMapper) {
    super(choreMapper);
  }

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

  @Override
  public FunctionalSpaceType getFunctionalSpaceType() {
    return FunctionalSpaceType.KITCHEN;
  }
}