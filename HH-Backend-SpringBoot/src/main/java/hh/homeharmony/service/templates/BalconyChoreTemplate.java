package hh.homeharmony.service.templates;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import hh.homeharmony.mapper.ChoreMapper;
import hh.homeharmony.model.Chore;
import hh.homeharmony.model.FunctionalSpaceType;

/**
 * A template for generating default chores specific to a balcony space.
 * This class extends DefaultChoreTemplate and provides
 * predefined chores tailored for balconies.
 */
public class BalconyChoreTemplate extends DefaultChoreTemplate {

  /**
   * Constructs a new BalconyChoreTemplate.
   *
   * @param choreMapper the ChoreMapper used for mapping chore data
   */
  public BalconyChoreTemplate(ChoreMapper choreMapper) {
    super(choreMapper);
  }

  /**
   * Returns a list of default chores for a balcony.
   *
   * @param spaceId the ID of the functional space (balcony) for which chores are created
   * @return a list of default Chore objects for the balcony
   */
  @Override
  public List<Chore> getDefaultChores(Integer spaceId) {
    List<Chore> chores = new ArrayList<>();

    chores.add(createChore("Sweep the Floor",
        "Sweep dirt, leaves, or debris from the balcony floor.",
        FunctionalSpaceType.BALCONY,
        Duration.ofMinutes(10),
        4,
        spaceId));

    chores.add(createChore("Clean Railings",
        "Wipe down balcony railings to remove dust and stains.",
        FunctionalSpaceType.BALCONY,
        Duration.ofMinutes(10),
        4,
        spaceId));

    chores.add(createChore("Water Plants",
        "Water all the plants on the balcony.",
        FunctionalSpaceType.BALCONY,
        Duration.ofMinutes(5),
        3,
        spaceId));

    chores.add(createChore("Organize Furniture",
        "Arrange outdoor furniture neatly and clean the surfaces.",
        FunctionalSpaceType.BALCONY,
        Duration.ofMinutes(15),
        5,
        spaceId));

    return chores;
  }

  /**
   * Returns the functional space type associated with this template.
   *
   * @return the FunctionalSpaceType for this template, which is BALCONY
   */
  @Override
  public FunctionalSpaceType getFunctionalSpaceType() {
    return FunctionalSpaceType.BALCONY;
  }
}