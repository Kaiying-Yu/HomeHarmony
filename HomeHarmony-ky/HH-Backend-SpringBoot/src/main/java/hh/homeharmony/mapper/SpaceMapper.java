package hh.homeharmony.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import hh.homeharmony.model.FunctionalSpace;
import hh.homeharmony.model.Space;
import hh.homeharmony.model.User;

/**
 * Mapper for the Space entity.
 * Provides SQL statements for CRUD operations on Spaces within the database.
 * Utilizes MyBatis annotations to define SQL queries and mappings.
 *
 * @author hh
 */
@Mapper
public interface SpaceMapper {
  @Select("SELECT * FROM spaces WHERE id = #{id}")
  Space findSpaceById(Integer id); // Retrieves a Space by its ID

  @Insert("INSERT INTO spaces (name) VALUES (#{name})")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void insertSpace(Space space); // Inserts a new Space into the database

  @Update("UPDATE spaces SET name = #{name} WHERE id = #{id}")
  void updateSpace(Space space); // Updates an existing Space

  @Delete("DELETE FROM spaces WHERE id = #{id}")
  void deleteSpace(Integer id); // Deletes a Space by ID

  @Select("SELECT * FROM users WHERE space_id = #{spaceId}")
  List<User> findUsersBySpaceId(Integer spaceId);

  @Select("SELECT * FROM functional_spaces WHERE space_id = #{spaceId}")
  List<FunctionalSpace> findFunctionalSpacesBySpaceId(Integer spaceId);
}