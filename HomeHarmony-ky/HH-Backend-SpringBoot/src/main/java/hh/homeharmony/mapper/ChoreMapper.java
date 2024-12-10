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

@Mapper
public interface ChoreMapper {

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

    @ResultMap("choreMap")
    @Select("SELECT * FROM chores WHERE id = #{id}")
    Chore selectById(Integer id);

    @Insert("INSERT INTO chores (chore_name, points, due_date, create_date, status, functional_space_type, space_id) " +
           "VALUES (#{choreName}, #{points}, #{dueDate}, #{createDate}, #{status}, #{functionalSpaceType}, #{spaceId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Chore chore);

    @Update("UPDATE chores SET chore_name = #{choreName}, points = #{points}, " +
            "due_date = #{dueDate}, status = #{status}, " +
            "functional_space_type = #{functionalSpaceType}, " +
            "assigned_user_id = #{assignedUser.id} " +
            "WHERE id = #{id}")
    void update(Chore chore);

    @Delete("DELETE FROM chores WHERE id = #{id}")
    void deleteById(Integer id);

    @Select("SELECT * FROM chores WHERE assigned_user_id = #{userId}")
    List<Chore> findChoresByUserId(@Param("userId") Integer userId);

    @Select("SELECT * FROM chores WHERE space_id = #{spaceId}")
    List<Chore> selectAllBySpaceId(@Param("spaceId") Integer spaceId);
}
