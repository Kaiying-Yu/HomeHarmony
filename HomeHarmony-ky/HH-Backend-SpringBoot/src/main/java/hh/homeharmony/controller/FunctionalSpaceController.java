package hh.homeharmony.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hh.homeharmony.model.FunctionalSpace;
import hh.homeharmony.service.FunctionalSpaceService;

@CrossOrigin(origins = "http://localhost:7000")
@RestController
@RequestMapping("/functionalSpace")
public class FunctionalSpaceController {

    @Autowired
    private FunctionalSpaceService functionalSpaceService;

    @GetMapping
    public ResponseEntity<List<FunctionalSpace>> getAllFunctionalSpaces() {
        List<FunctionalSpace> spaces = functionalSpaceService.getAllFunctionalSpaces();
        return ResponseEntity.ok(spaces);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FunctionalSpace> getFunctionalSpaceById(@PathVariable Integer id) {
        FunctionalSpace space = functionalSpaceService.getFunctionalSpaceById(id);
        if (space != null) {
            return ResponseEntity.ok(space);
        }
        return ResponseEntity.notFound().build();
    }
} 