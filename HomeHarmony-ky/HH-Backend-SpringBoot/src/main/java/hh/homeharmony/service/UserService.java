package hh.homeharmony.service;

import hh.homeharmony.model.User;

/**
 * Service interface for managing Users.
 * Defines all business operations that can be performed on a User.
 *
 * @author hh
 */
public interface UserService {
  User getUserById(Integer id);
  void createUser(User user);
  void updateUser(User user);
  void deleteUser(Integer id);

  User login(User user);

  void addPoints(Integer userId, Integer points);
}

