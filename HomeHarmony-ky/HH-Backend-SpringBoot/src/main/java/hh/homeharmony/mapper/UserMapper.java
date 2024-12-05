package hh.homeharmony.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import hh.homeharmony.model.User;

/**
 * Mapper for the User entity.
 * Provides SQL statements for CRUD operations on Users within the database.
 * Utilizes MyBatis annotations to define SQL queries and mappings.
 *
 * @author hh
 */
@Mapper
public interface UserMapper {
  @Select("SELECT * FROM users WHERE id = #{id}")
  User findUserById(@Param("id") Integer id);

  @Insert("INSERT INTO users (username, email, password, space_id) VALUES (#{username}, #{email}, #{password}, #{occupiedSpace.id})")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void insertUser(User user);

  @Update("UPDATE users SET username = #{name}, email = #{email}, password = #{password}, space_id = #{occupiedSpace.id} WHERE id = #{id}")
  void updateUser(User user);

  @Delete("DELETE FROM users WHERE id = #{id}")
  void deleteUser(Integer id);

  @Select("select * from users where username=#{username} and password=#{password}")
  User getByUsernameAndPassword(User user);

  @Update("UPDATE users SET space_id = #{spaceId} WHERE id = #{userId}")
  void updateUserSpace(@Param("userId") Integer userId, @Param("spaceId") Integer spaceId);
}

