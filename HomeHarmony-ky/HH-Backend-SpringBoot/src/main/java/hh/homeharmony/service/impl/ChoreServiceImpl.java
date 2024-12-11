package hh.homeharmony.service.impl;

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
    public List<Chore> getAllChores(Integer spaceId) {
        return choreMapper.selectAllBySpaceId(spaceId);
    }

    @Override
    public Chore createChore(Chore chore) {
        if (chore.getSpaceId() == null) {
            throw new IllegalArgumentException("Space ID is required");
        }
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

        chore.assignUser(user);
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