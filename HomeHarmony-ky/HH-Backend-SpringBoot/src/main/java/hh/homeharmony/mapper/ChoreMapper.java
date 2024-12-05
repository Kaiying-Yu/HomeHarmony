//package hh.homeharmony.mapper;
//
//import hh.homeharmony.model.Chore;
//import java.util.List;
//import org.apache.ibatis.annotations.Delete;
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Select;
//
//@Mapper
//public interface ChoreMapper {
//
//  @Select("select * from chores")
//  List<Chore> list();
//
//
//  @Delete("delete from chores where id = #{id}")
//  void deleteById(Integer id);
//}
