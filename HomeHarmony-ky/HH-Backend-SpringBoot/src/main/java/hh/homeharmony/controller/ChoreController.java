//package hh.homeharmony.controller;
//
//import hh.homeharmony.model.Chore;
//import hh.homeharmony.model.Result;
//import hh.homeharmony.service.ChoreService;
//import java.util.List;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//
//@CrossOrigin(origins = "http://localhost:7000")
//@Slf4j
//@RestController
//public class ChoreController {
//
//  @Autowired
//  private ChoreService choreService;
//
//  @GetMapping("/chores")
//  public Result list(){
//    log.info("Read all data of chores");
//
//    List<Chore> chorelist = choreService.list();
//
//    return Result.success(chorelist);
//  }
//
//  @DeleteMapping("/chores/{id}")
//  public Result delete(@PathVariable Integer id){
//    log.info("Delete chore by Id:{}", id);
//    choreService.delete(id);
//    return Result.success();
//  }
//}
