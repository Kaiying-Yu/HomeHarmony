package hh.homeharmony.service.impl;

import hh.homeharmony.model.FunctionalSpaceType;
import hh.homeharmony.service.CreateChoreService;
import hh.homeharmony.service.templates.DefaultChoreTemplate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 * Implementation of the CreateChoreService interface.
 * Handles the creation of default chores based on functional space types.
 */
@Service
public class CreateChoreServiceImpl implements CreateChoreService {

  private final Map<FunctionalSpaceType, DefaultChoreTemplate> templateMap;

  /**
   * Constructor for CreateChoreServiceImpl.
   * Initializes the map of functional space types to their respective chore templates.
   *
   * @param templates A list of all available DefaultChoreTemplate beans.
   */
  public CreateChoreServiceImpl(List<DefaultChoreTemplate> templates) {
    this.templateMap = new HashMap<>();
    for (DefaultChoreTemplate template : templates) {
      this.templateMap.put(template.getFunctionalSpaceType(), template);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void createDefaultChores(Integer spaceId, FunctionalSpaceType type) {
    DefaultChoreTemplate template = templateMap.get(type);

    if (template == null) {
      throw new IllegalArgumentException("No template found for FunctionalSpaceType: " + type);
    }

    template.createDefaultChores(spaceId);
  }
}
