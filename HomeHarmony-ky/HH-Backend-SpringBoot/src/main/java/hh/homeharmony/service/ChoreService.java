package hh.homeharmony.service;

import java.util.List;

import hh.homeharmony.model.Chore;

public interface ChoreService {
    List<Chore> getAllChores();
    Chore createChore(Chore chore);
    Chore updateChore(Chore chore);
    void deleteChore(Integer id);
    void assignUserToChore(Integer choreId, Integer userId);
}
