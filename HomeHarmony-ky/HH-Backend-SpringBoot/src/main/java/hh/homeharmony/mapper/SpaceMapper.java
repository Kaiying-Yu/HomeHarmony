package hh.homeharmony.mapper;

import hh.homeharmony.model.Space;
import hh.homeharmony.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Mapper interface for performing database operations on Space objects.
 * Uses MyBatis annotations for SQL mappings.
 */
@Mapper
public interface SpaceMapper {

  /**
   * Inserts a new Space into the database.
   *
   * @param space The Space object to insert.
   */
  @Insert("INSERT INTO space (name, description, created_at) " +
      "VALUES (#{name}, #{description}, NOW())")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void insertSpace(Space space);

  /**
   * Associates a User with a Space in the join table.
   *
   * @param spaceId The ID of the Space.
   * @param userId  The ID of the User.
   */
  @Insert("INSERT INTO space_user (space_id, user_id) VALUES (#{spaceId}, #{userId})")
  void addUserToSpace(@Param("spaceId") Integer spaceId, @Param("userId") Integer userId);

  /**
   * Finds a Space by its ID.
   *
   * @param id The ID of the Space.
   * @return The Space object with the given ID.
   */
  @Select("SELECT * FROM space WHERE id = #{id}")
  Space findSpaceById(@Param("id") Integer id);

  /**
   * Finds all Users in a Space.
   *
   * @param spaceId The ID of the Space.
   * @return A list of Users in the Space.
   */
  @Select("SELECT u.* FROM user u " +
      "INNER JOIN space_user su ON u.id = su.user_id " +
      "WHERE su.space_id = #{spaceId}")
  List<User> findUsersBySpaceId(@Param("spaceId") Integer spaceId);

  /**
   * Deletes a User from a Space.
   *
   * @param spaceId The ID of the Space.
   * @param userId  The ID of the User.
   */
  @Delete("DELETE FROM space_user WHERE space_id = #{spaceId} AND user_id = #{userId}")
  void removeUserFromSpace(@Param("spaceId") Integer spaceId, @Param("userId") Integer userId);

  /**
   * Deletes a Space by its ID.
   *
   * @param id The ID of the Space to delete.
   */
  @Delete("DELETE FROM space WHERE id = #{id}")
  void deleteSpaceById(@Param("id") Integer id);
}
