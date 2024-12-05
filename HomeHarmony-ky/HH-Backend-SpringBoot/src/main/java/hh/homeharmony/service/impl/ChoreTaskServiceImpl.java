package hh.homeharmony.service.impl;

import hh.homeharmony.mapper.ChoreTaskMapper;
import hh.homeharmony.model.ChoreTask;
import hh.homeharmony.service.ChoreTaskService;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChoreTaskServiceImpl implements ChoreTaskService {

  @Autowired
  private ChoreTaskMapper choreTaskMapper;

  @Override
  public ChoreTask getChoreTaskById(Integer id) {
    return choreTaskMapper.findById(id);
  }

  @Override
  public void createChoreTask(ChoreTask choreTask) {
    choreTaskMapper.insert(choreTask);
  }

  @Override
  public void updateChoreTask(ChoreTask choreTask) {
    choreTaskMapper.update(choreTask);
  }

  @Override
  public void deleteChoreTask(Integer id) {
    choreTaskMapper.delete(id);
  }

  @Override
  public void assignChoreTaskToUser(Integer choreTaskId, Integer userId) {
    // Implement assignment logic here
  }

  @Override
  public void changeDueDate(Integer choreTaskId, LocalDateTime newDueDate) {
    ChoreTask choreTask = getChoreTaskById(choreTaskId);
    choreTask.setDueDate(newDueDate);
    updateChoreTask(choreTask);
  }

  @Override
  public void updateChoreDescription(Integer choreTaskId, String newDescription) {
    ChoreTask choreTask = getChoreTaskById(choreTaskId);
    if (choreTask != null) {
      choreTask.setDescription(newDescription);
      updateChoreTask(choreTask); // Persist the updated chore task
    } else {
      throw new IllegalArgumentException("Chore Task with ID " + choreTaskId + " not found.");
    }
  }
}
