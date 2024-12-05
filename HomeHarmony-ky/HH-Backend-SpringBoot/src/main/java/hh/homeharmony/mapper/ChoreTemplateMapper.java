package hh.homeharmony.mapper;

import hh.homeharmony.model.ChoreTemplate;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ChoreTemplateMapper {
  @Select("SELECT * FROM chore_templates WHERE id = #{id}")
  ChoreTemplate findById(Integer id);

  @Insert("INSERT INTO chore_templates (name, description, functional_space_id, is_global, estimated_time, points) VALUES (#{name}, #{description}, #{functionalSpaceId}, #{isGlobal}, #{estimatedTime}, #{points})")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void insert(ChoreTemplate choreTemplate);

  @Update("UPDATE chore_templates SET name = #{name}, description = #{description}, functional_space_id = #{functionalSpaceId}, is_global = #{isGlobal}, estimated_time = #{estimatedTime}, points = #{points} WHERE id = #{id}")
  void update(ChoreTemplate choreTemplate);

  @Delete("DELETE FROM chore_templates WHERE id = #{id}")
  void delete(Integer id);
}
