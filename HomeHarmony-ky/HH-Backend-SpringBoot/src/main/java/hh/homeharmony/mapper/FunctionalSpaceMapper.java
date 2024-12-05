package hh.homeharmony.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import hh.homeharmony.model.FunctionalSpace;

@Mapper
public interface FunctionalSpaceMapper {
  @Select("SELECT * FROM functional_spaces WHERE id = #{id}")
  FunctionalSpace findFunctionalSpaceById(Integer id);

  @Select("SELECT * FROM functional_spaces")
  List<FunctionalSpace> findAll();

  @Insert("INSERT INTO functional_spaces (name) VALUES (#{name})")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void insert(FunctionalSpace functionalSpace);

  @Update("UPDATE functional_spaces SET name = #{name} WHERE id = #{id}")
  void update(FunctionalSpace functionalSpace);

  @Delete("DELETE FROM functional_spaces WHERE id = #{id}")
  void delete(Integer id);
}
