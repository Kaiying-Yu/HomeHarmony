package hh.homeharmony.service.templates;

import hh.homeharmony.mapper.ChoreMapper;
import hh.homeharmony.model.Chore;
import hh.homeharmony.model.FunctionalSpaceType;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class BalconyChoreTemplate extends DefaultChoreTemplate {

  public BalconyChoreTemplate(ChoreMapper choreMapper) {
    super(choreMapper);
  }

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

  @Override
  public FunctionalSpaceType getFunctionalSpaceType() {
    return FunctionalSpaceType.BALCONY;
  }
}
