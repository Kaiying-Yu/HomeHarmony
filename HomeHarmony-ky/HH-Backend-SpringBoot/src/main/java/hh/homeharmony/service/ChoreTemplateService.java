package hh.homeharmony.service;

import hh.homeharmony.model.ChoreTemplate;

/**
 * Interface for managing ChoreTemplates which are blueprints for creating actual chores.
 */
public interface ChoreTemplateService {
  ChoreTemplate getChoreTemplateById(Integer id);
  void createChoreTemplate(ChoreTemplate choreTemplate);
  void updateChoreTemplate(ChoreTemplate choreTemplate);
  void deleteChoreTemplate(Integer id);
}
