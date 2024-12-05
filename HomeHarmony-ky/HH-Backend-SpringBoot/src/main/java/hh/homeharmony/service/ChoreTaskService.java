package hh.homeharmony.service;

import hh.homeharmony.model.ChoreTask;
import java.time.LocalDateTime;

public interface ChoreTaskService {
  ChoreTask getChoreTaskById(Integer id);
  void createChoreTask(ChoreTask choreTask);
  void updateChoreTask(ChoreTask choreTask);
  void deleteChoreTask(Integer id);
  void assignChoreTaskToUser(Integer choreTaskId, Integer userId);
  void changeDueDate(Integer choreTaskId, LocalDateTime newDueDate);
  void updateChoreDescription(Integer choreTaskId, String newDescription);
}
