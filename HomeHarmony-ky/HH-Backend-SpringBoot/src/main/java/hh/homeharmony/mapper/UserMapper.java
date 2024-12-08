package hh.homeharmony.mapper;

import hh.homeharmony.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Mapper interface for performing database operations on User objects.
 * Uses MyBatis annotations for SQL mappings.
 *
 * Provides CRUD operations for the User table.
 *
 * @author hh
 */
@Mapper
public interface UserMapper {

  /**
   * Inserts a new User into the database.
   *
   * @param user The User object to insert.
   */
  @Insert("INSERT INTO user (name, email, password) VALUES (#{name}, #{email}, #{password})")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void insert(User user);

  /**
   * Finds a User by their ID.
   *
   * @param id The ID of the User.
   * @return The User with the specified ID.
   */
  @Select("SELECT * FROM user WHERE id = #{id}")
  User findById(Integer id);

  /**
   * Finds a User by their email.
   *
   * @param email The email of the User.
   * @return The User with the specified email.
   */
  @Select("SELECT * FROM user WHERE email = #{email}")
  User findByEmail(String email);

  /**
   * Finds all Users in the database.
   *
   * @return A list of all Users.
   */
  @Select("SELECT * FROM user")
  List<User> findAll();

  /**
   * Updates the details of an existing User.
   *
   * @param user The User object with updated details.
   */
  @Update("UPDATE user SET name = #{name}, email = #{email}, password = #{password} WHERE id = #{id}")
  void update(User user);

  /**
   * Deletes a User by their ID.
   *
   * @param id The ID of the User to delete.
   */
  @Delete("DELETE FROM user WHERE id = #{id}")
  void deleteById(Integer id);

  @Select("SELECT * FROM user WHERE email = #{email} AND password = #{password}")
  User getByUsernameAndPassword(User user);

  User findUserById(Integer id);
}
