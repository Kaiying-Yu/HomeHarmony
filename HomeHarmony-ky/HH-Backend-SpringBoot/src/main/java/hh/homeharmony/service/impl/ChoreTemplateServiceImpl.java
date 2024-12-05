package hh.homeharmony.service.impl;

import hh.homeharmony.mapper.ChoreTemplateMapper;
import hh.homeharmony.model.ChoreTemplate;
import hh.homeharmony.service.ChoreTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service implementation for ChoreTemplate management, providing CRUD operations and additional logic.
 * This class encapsulates all data access and transformation logic for chore templates, isolating business logic from data access.
 */
@Service
public class ChoreTemplateServiceImpl implements ChoreTemplateService {

  @Autowired
  private ChoreTemplateMapper choreTemplateMapper;

  @Override
  public ChoreTemplate getChoreTemplateById(Integer id) {
    return choreTemplateMapper.findById(id); // Retrieve ChoreTemplate by ID, handling not found scenarios within controller or here.
  }

  @Override
  public void createChoreTemplate(ChoreTemplate choreTemplate) {
    choreTemplateMapper.insert(choreTemplate); // Insert new ChoreTemplate
  }

  @Override
  public void updateChoreTemplate(ChoreTemplate choreTemplate) {
    ChoreTemplate existingTemplate = choreTemplateMapper.findById(choreTemplate.getId());
    if (existingTemplate != null) {
      choreTemplateMapper.update(choreTemplate); // Update existing ChoreTemplate
    } else {
      // Handle the case where the chore template does not exist, possibly throwing an exception or logging.
      throw new IllegalArgumentException("ChoreTemplate with ID " + choreTemplate.getId() + " does not exist.");
    }
  }

  @Override
  public void deleteChoreTemplate(Integer id) {
    ChoreTemplate existingTemplate = choreTemplateMapper.findById(id);
    if (existingTemplate != null) {
      choreTemplateMapper.delete(id); // Delete existing ChoreTemplate
    } else {
      // Handle the case where the chore template does not exist, possibly throwing an exception or logging.
      throw new IllegalArgumentException("ChoreTemplate with ID " + id + " does not exist.");
    }
  }
}
