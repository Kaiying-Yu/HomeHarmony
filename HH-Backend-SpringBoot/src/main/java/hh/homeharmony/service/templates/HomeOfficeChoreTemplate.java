package hh.homeharmony.service.templates;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import hh.homeharmony.mapper.ChoreMapper;
import hh.homeharmony.model.Chore;
import hh.homeharmony.model.FunctionalSpaceType;

/**
 * A template for generating default chores specific to a home office space.
 * This class extends DefaultChoreTemplate and provides
 * predefined chores tailored for home offices.
 */
public class HomeOfficeChoreTemplate extends DefaultChoreTemplate {

  /**
   * Constructs a new HomeOfficeChoreTemplate.
   *
   * @param choreMapper the ChoreMapper used for mapping chore data
   */
  public HomeOfficeChoreTemplate(ChoreMapper choreMapper) {
    super(choreMapper);
  }

  /**
   * Returns a list of default chores for a home office.
   *
   * @param spaceId the ID of the functional space (home office) for which chores are created
   * @return a list of default Chore objects for the home office
   */
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

  /**
   * Returns the functional space type associated with this template.
   *
   * @return the FunctionalSpaceType for this template, which is HOME_OFFICE
   */
  @Override
  public FunctionalSpaceType getFunctionalSpaceType() {
    return FunctionalSpaceType.HOME_OFFICE;
  }
}