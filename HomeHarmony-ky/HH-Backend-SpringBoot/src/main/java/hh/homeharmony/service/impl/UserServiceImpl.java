package hh.homeharmony.service.impl;

import hh.homeharmony.mapper.UserMapper;
import hh.homeharmony.model.User;
import hh.homeharmony.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of the UserService interface.
 * Handles business logic for User-related operations.
 *
 * @author hh
 */
@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserMapper userMapper;

  @Override
  public User getUserById(Integer id) {
    return userMapper.findUserById(id);
  }

  @Override
  public void createUser(User user) {
    userMapper.insertUser(user);
  }

  @Override
  public void updateUser(User user) {
    userMapper.updateUser(user);
  }

  @Override
  public void deleteUser(Integer id) {
    userMapper.deleteUser(id);
  }

  @Override
  public User login(User user) {
    return userMapper.getByUsernameAndPassword(user);
  }

  @Override
  public void addPoints(Integer userId, Integer points) {
    User user = userMapper.findUserById(userId);
    if (user != null) {
        Integer currentPoints = user.getPoints() != null ? user.getPoints() : 0;
        user.setPoints(currentPoints + points);
        userMapper.updatePoints(user);
    }
  }
}