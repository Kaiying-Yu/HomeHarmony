package hh.homeharmony.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hh.homeharmony.mapper.ChoreMapper;
import hh.homeharmony.mapper.UserMapper;
import hh.homeharmony.model.Chore;
import hh.homeharmony.model.ChoreStatus;
import hh.homeharmony.model.User;
import hh.homeharmony.service.ChoreService;

@Service
public class ChoreServiceImpl implements ChoreService {


    @Autowired
    private ChoreMapper choreMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Chore> getAllChores() {
        return choreMapper.selectAll();
    }

    @Override
    public Chore createChore(Chore chore) {
        chore.setCreateDate(LocalDateTime.now());
        chore.setStatus(ChoreStatus.PENDING);
        choreMapper.insert(chore);
        return chore;
    }

    @Override
    public Chore updateChore(Chore chore) {
        Chore existingChore = choreMapper.selectById(chore.getId());
        if (existingChore == null) {
            throw new RuntimeException("Chore not found");
        }
        choreMapper.update(chore);
        return chore;
    }

    @Override
    public void deleteChore(Integer id) {
        choreMapper.deleteById(id);
    }

    @Override
    public void assignUserToChore(Integer choreId, Integer userId) {
        Chore chore = choreMapper.selectById(choreId);
        if (chore == null) {
            throw new IllegalArgumentException("Chore not found");
        }

        User user = userMapper.findUserById(userId);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        chore.setAssignedUser(user);
        chore.setStatus(ChoreStatus.IN_PROGRESS);
        choreMapper.update(chore);
    }

    @Override
    public Chore completeChore(Integer choreId) {
        Chore chore = choreMapper.selectById(choreId);
        if (chore == null) {
            throw new IllegalArgumentException("Chore not found");
        }
        chore.markAsCompleted();
        choreMapper.update(chore);
        return chore;
    }

    @Override
    public List<Chore> getChoresByUserId(Integer userId) {
        return choreMapper.findChoresByUserId(userId);
    }
}