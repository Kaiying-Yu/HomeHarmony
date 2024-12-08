package hh.homeharmony.service.templates;

import hh.homeharmony.model.Chore;
import hh.homeharmony.mapper.ChoreMapper;
import hh.homeharmony.model.FunctionalSpaceType;
import java.util.ArrayList;
import java.time.Duration;
import java.util.List;

public class HomeOfficeChoreTemplate extends DefaultChoreTemplate {

  public HomeOfficeChoreTemplate(ChoreMapper choreMapper) {
    super(choreMapper);
  }

  @Override
  public List<Chore> getDefaultChores(Integer spaceId) {
    List<Chore> chores = new ArrayList<>();

    chores.add(createChore("Organize Desk",
        "Clear clutter, arrange stationery, and wipe the desk surface.",
        FunctionalSpaceType.HOME_OFFICE,
        Duration.ofMinutes(10),
        4,
        spaceId));

    chores.add(createChore("Dust Electronics",
        "Dust and clean monitors, keyboards, and other electronics.",
        FunctionalSpaceType.HOME_OFFICE,
        Duration.ofMinutes(10),
        4,
        spaceId));

    chores.add(createChore("Empty Trash Bin",
        "Remove trash from the bin and replace the liner.",
        FunctionalSpaceType.HOME_OFFICE,
        Duration.ofMinutes(5),
        3,
        spaceId));

    chores.add(createChore("Organize Cables",
        "Sort and organize cables to prevent tangling.",
        FunctionalSpaceType.HOME_OFFICE,
        Duration.ofMinutes(15),
        5,
        spaceId));

    return chores;
  }

  @Override
  public FunctionalSpaceType getFunctionalSpaceType() {
    return FunctionalSpaceType.HOME_OFFICE;
  }
}
