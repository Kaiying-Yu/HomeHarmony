package hh.homeharmony.service.impl;

import hh.homeharmony.model.ChoreStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hh.homeharmony.model.Chore;
import hh.homeharmony.service.ChoreService;
import hh.homeharmony.mapper.ChoreMapper;

import java.time.LocalDateTime;
import java.util.List;

import hh.homeharmony.mapper.UserMapper;

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

//    @Override
//    public void assignUserToChore(Chore chore, String strategyType, String userId) {
//        IAssignmentStrategy strategy;
//        switch (strategyType.toLowerCase()) {
//            case "lowestpoints":
//                strategy = lowestPointsStrategy;
//                break;
//            case "random":
//                strategy = randomAssignStrategy;
//                break;
//            case "manual":
//                User user = userMapper.selectById(Integer.parseInt(userId));
//                if (user == null) {
//                    throw new IllegalArgumentException("User not found");
//                }
//                manualAssignStrategy.setUser(user);
//                strategy = manualAssignStrategy;
//                break;
//            default:
//                throw new IllegalArgumentException("Invalid strategy type");
//        }
//
//        User assignedUser = strategy.assignUser(chore);
//        chore.assignUser(assignedUser);
//        choreMapper.update(chore);
//    }
}