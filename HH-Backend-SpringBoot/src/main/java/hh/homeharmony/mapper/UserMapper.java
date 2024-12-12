package hh.homeharmony.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import hh.homeharmony.model.User;

/**
 * Mapper interface for User entity.
 * Provides SQL operations for managing users in the database.
 * Uses MyBatis annotations for SQL mapping.
 * Works in conjunction with UserService for business logic handling.
 */
@Mapper
public interface UserMapper {
  /**
   * Retrieves a user by their ID.
   * Maps space_id to spaceId property using column alias.
   *
   * @param id The unique identifier of the user
   * @return The user with the specified ID, or null if not found
   */
  @Select("SELECT id, username, email, password, space_id as spaceId, points FROM users WHERE id = #{id}")
  User findUserById(@Param("id") Integer id);

  /**
   * Creates a new user in the database.
   * Uses generated keys to populate the user's ID after insertion.
   *
   * @param user The user object to insert, containing username, email, and password
   */
  @Insert("INSERT INTO users (username, email, password, space_id) VALUES (#{username}, #{email}, #{password}, #{occupiedSpace.id})")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void insertUser(User user);

  /**
   * Updates an existing user's information.
   * Updates all fields including username, email, password, and space association.
   *
   * @param user The user object containing updated values
   */
  @Update("UPDATE users SET username = #{name}, email = #{email}, password = #{password}, space_id = #{occupiedSpace.id} WHERE id = #{id}")
  void updateUser(User user);

  /**
   * Deletes a user from the database.
   * Should ensure user is removed from any spaces before deletion.
   *
   * @param id The unique identifier of the user to delete
   */
  @Delete("DELETE FROM users WHERE id = #{id}")
  void deleteUser(Integer id);

  /**
   * Authenticates a user by username and password.
   * Used during login process.
   *
   * @param user The user object containing username and password to check
   * @return The authenticated user if credentials match, null otherwise
   */
  @Select("select * from users where username=#{username} and password=#{password}")
  User getByUsernameAndPassword(User user);

  /**
   * Updates a user's space association.
   * Used when adding or removing users from spaces.
   *
   * @param userId The ID of the user to update
   * @param spaceId The ID of the space to associate with user (can be null for removal)
   */
  @Update("UPDATE users SET space_id = #{spaceId} WHERE id = #{userId}")
  void updateUserSpace(@Param("userId") Integer userId, @Param("spaceId") Integer spaceId);

  /**
   * Finds all users associated with a specific space.
   * Used to get space members list.
   *
   * @param spaceId The ID of the space whose users to retrieve
   * @return List of users belonging to the specified space
   */
  @Select("SELECT id, username, points FROM users WHERE space_id = #{spaceId}")
  List<User> findUsersBySpaceId(Integer spaceId);

  /**
   * Updates a user's points.
   * Used when completing chores or other point-earning activities.
   *
   * @param user The user object containing the updated points value
   */
  @Update("UPDATE users SET points = #{points} WHERE id = #{id}")
  void updatePoints(User user);
}

