package hh.homeharmony.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import hh.homeharmony.model.Space;
import hh.homeharmony.model.User;

/**
 * Mapper interface for Space entity.
 * Provides SQL operations for managing spaces in the database.
 * Uses MyBatis annotations for SQL mapping.
 * Works in conjunction with SpaceService for business logic handling.
 */
@Mapper
public interface SpaceMapper {
    /**
     * Retrieves a space by its ID.
     * Used by SpaceService to get space details and associated users.
     *
     * @param id The unique identifier of the space
     * @return The space with the specified ID, or null if not found
     */
    @Select("SELECT * FROM spaces WHERE id = #{id}")
    Space findSpaceById(Integer id);

    /**
     * Creates a new space in the database.
     * Uses generated keys to populate the space's ID after insertion.
     *
     * @param space The space object to insert, containing the space name
     */
    @Insert("INSERT INTO spaces (name) VALUES (#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertSpace(Space space);

    /**
     * Updates an existing space's information.
     * Currently only updates the space name.
     *
     * @param space The space object containing updated values
     */
    @Update("UPDATE spaces SET name = #{name} WHERE id = #{id}")
    void updateSpace(Space space);

    /**
     * Finds all users associated with a specific space.
     * Used by SpaceService to populate space members list.
     *
     * @param spaceId The ID of the space whose users to retrieve
     * @return List of users belonging to the specified space
     */
    @Select("SELECT * FROM users WHERE space_id = #{spaceId}")
    List<User> findUsersBySpaceId(Integer spaceId);
}