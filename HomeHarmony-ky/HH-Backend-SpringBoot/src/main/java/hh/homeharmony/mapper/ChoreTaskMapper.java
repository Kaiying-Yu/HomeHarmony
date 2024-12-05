package hh.homeharmony.mapper;

import hh.homeharmony.model.ChoreTask;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ChoreTaskMapper {
  @Select("SELECT * FROM chore_tasks WHERE id = #{id}")
  ChoreTask findById(Integer id);

  @Insert("INSERT INTO chore_tasks (template_id, room_id, due_date, customized_time, customized_points, status, recurrence_pattern_id) " +
      "VALUES (#{templateId}, #{roomId}, #{dueDate}, #{customizedTime}, #{customizedPoints}, #{status.name()}, #{recurrencePattern.id})")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void insert(ChoreTask choreTask);

  @Update("UPDATE chore_tasks SET template_id = #{templateId}, room_id = #{roomId}, due_date = #{dueDate}, customized_time = #{customizedTime}, customized_points = #{customizedPoints}, status = #{status.name()}, recurrence_pattern_id = #{recurrencePattern.id} WHERE id = #{id}")
  void update(ChoreTask choreTask);

  @Delete("DELETE FROM chore_tasks WHERE id = #{id}")
  void delete(Integer id);
}
