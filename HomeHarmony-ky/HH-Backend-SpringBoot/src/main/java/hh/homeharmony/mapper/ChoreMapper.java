package hh.homeharmony.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import hh.homeharmony.model.Chore;
import hh.homeharmony.model.ChoreStatus;
import hh.homeharmony.model.FunctionalSpaceType;

/**
 * Mapper interface for Chore entity.
 * Provides SQL operations for managing chores in the database.
 * Uses MyBatis annotations for SQL mapping.
 */
@Mapper
public interface ChoreMapper {

    /**
     * Result mapping configuration for Chore entity.
     * Maps database columns to Chore object properties.
     * Includes complex mapping for enum types and user association.
     */
    @Results(id = "choreMap", value = {
        @Result(property = "id", column = "id"),
        @Result(property = "choreName", column = "chore_name"),
        @Result(property = "points", column = "points"),
        @Result(property = "createDate", column = "create_date"),
        @Result(property = "dueDate", column = "due_date"),
        @Result(property = "status", column = "status", javaType = ChoreStatus.class),
        @Result(property = "functionalSpaceType", column = "functional_space_type",
                javaType = FunctionalSpaceType.class),
        @Result(property = "assignedUser", column = "assigned_user_id",
                one = @One(select = "hh.homeharmony.mapper.UserMapper.findUserById")),
        @Result(property = "spaceId", column = "space_id")
    })
    @Select("SELECT * FROM chores")
    List<Chore> selectAll();

    /**
     * Retrieves a chore by its ID.
     * Uses the choreMap result mapping.
     *
     * @param id The unique identifier of the chore
     * @return The chore with the specified ID, or null if not found
     */
    @ResultMap("choreMap")
    @Select("SELECT * FROM chores WHERE id = #{id}")
    Chore selectById(Integer id);

    /**
     * Inserts a new chore into the database.
     * Uses generated keys to populate the chore's ID.
     *
     * @param chore The chore object to insert
     */
    @Insert("INSERT INTO chores (chore_name, points, due_date, status, functional_space_type, space_id) " +
           "VALUES (#{choreName}, #{points}, #{dueDate}, #{status}, #{functionalSpaceType}, #{spaceId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Chore chore);

    /**
     * Updates an existing chore in the database.
     * Updates all fields including the assigned user.
     *
     * @param chore The chore object containing updated values
     */
    @Update("UPDATE chores SET chore_name = #{choreName}, points = #{points}, " +
            "due_date = #{dueDate}, status = #{status}, " +
            "functional_space_type = #{functionalSpaceType}, " +
            "assigned_user_id = #{assignedUser.id} " +
            "WHERE id = #{id}")
    void update(Chore chore);

    /**
     * Deletes a chore by its ID.
     *
     * @param id The unique identifier of the chore to delete
     */
    @Delete("DELETE FROM chores WHERE id = #{id}")
    void deleteById(Integer id);

    /**
     * Finds all chores assigned to a specific user.
     *
     * @param userId The ID of the user whose chores to retrieve
     * @return List of chores assigned to the specified user
     */
    @Select("SELECT * FROM chores WHERE assigned_user_id = #{userId}")
    List<Chore> findChoresByUserId(@Param("userId") Integer userId);

    /**
     * Retrieves all chores for a specific space.
     *
     * @param spaceId The ID of the space whose chores to retrieve
     * @return List of chores belonging to the specified space
     */
    @Select("SELECT * FROM chores WHERE space_id = #{spaceId}")
    List<Chore> selectAllBySpaceId(@Param("spaceId") Integer spaceId);
}
