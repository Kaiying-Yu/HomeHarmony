package hh.homeharmony.mapper;

import hh.homeharmony.model.Chore;
import hh.homeharmony.model.ChoreStatus;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Mapper interface for performing database operations on Chore objects.
 * Uses MyBatis annotations for SQL mappings.
 *
 * Provides CRUD operations for the Chore table.
 *
 * @author hh
 */
@Mapper
public interface ChoreMapper {

  /**
   * Inserts a new Chore into the database.
   *
   * @param chore The Chore object to insert.
   */
  @Insert("INSERT INTO chore (chore_name, description, functional_space_type, estimated_time, points, status, assigned_user_id, space_id) " +
      "VALUES (#{choreName}, #{description}, #{functionalSpaceType}, #{estimatedTime}, #{points}, #{status}, #{assignedUser.id}, #{spaceId})")
  void insert(Chore chore);

  /**
   * Finds a Chore by its ID.
   *
   * @param id The ID of the Chore.
   * @return The Chore with the specified ID.
   */
  @Select("SELECT * FROM chore WHERE id = #{id}")
  Chore findById(Integer id);

  /**
   * Finds all Chores by their functional space type.
   *
   * @param functionalSpaceType The functional space type (e.g., KITCHEN, BATHROOM).
   * @return A list of Chores matching the functional space type.
   */
  @Select("SELECT * FROM chore WHERE functional_space_type = #{functionalSpaceType}")
  List<Chore> findByFunctionalSpaceType(String functionalSpaceType);

  /**
   * Updates the status of a Chore.
   *
   * @param id     The ID of the Chore.
   * @param status The new status of the Chore.
   */
  @Update("UPDATE chore SET status = #{status} WHERE id = #{id}")
  void updateStatus(@Param("id") Long id, @Param("status") ChoreStatus status);

  /**
   * Deletes a Chore by its ID.
   *
   * @param id The ID of the Chore to delete.
   */
  @Delete("DELETE FROM chore WHERE id = #{id}")
  void deleteById(Integer id);
}
