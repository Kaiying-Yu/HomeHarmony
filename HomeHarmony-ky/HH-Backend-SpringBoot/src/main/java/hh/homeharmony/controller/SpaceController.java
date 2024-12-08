package hh.homeharmony.controller;

import hh.homeharmony.dto.SpaceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hh.homeharmony.model.Space;
import hh.homeharmony.service.SpaceService;

@CrossOrigin(origins = "http://localhost:7000")
@RestController
@RequestMapping("/space")
public class SpaceController {

  private final SpaceService spaceService;

  @Autowired
  public SpaceController(SpaceService spaceService) {
    this.spaceService = spaceService;
  }

  @PostMapping("/create")
  public ResponseEntity<Void> createSpace(@RequestBody SpaceRequest request) {
    spaceService.createSpace(request);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PostMapping("/{spaceId}/join")
  public ResponseEntity<Void> joinSpace(@PathVariable Integer spaceId,
      @RequestBody SpaceRequest request) {
    spaceService.joinSpace(spaceId, request);
    return ResponseEntity.ok().build();
  }
}

//  @PutMapping("/{id}")
//  public ResponseEntity<Space> updateSpace(@PathVariable Integer id, @RequestBody Space space) {
//    Space existingSpace = spaceService.getSpaceById(id);
//    if (existingSpace != null) {
//      space.setId(id);
//      spaceService.updateSpace(space);
//      return ResponseEntity.ok(space);
//    } else {
//      return ResponseEntity.notFound().build();
//    }
//  }
//
//  @DeleteMapping("/{id}")
//  public ResponseEntity<Void> deleteSpace(@PathVariable Integer id) {
//    Space existingSpace = spaceService.getSpaceById(id);
//    if (existingSpace != null) {
//      spaceService.deleteSpace(id);
//      return ResponseEntity.ok().build();
//    } else {
//      return ResponseEntity.notFound().build();
//    }
//  }
//}
