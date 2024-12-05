package hh.homeharmony.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hh.homeharmony.mapper.FunctionalSpaceMapper;
import hh.homeharmony.model.FunctionalSpace;
import hh.homeharmony.service.FunctionalSpaceService;

/**
 * Service implementation for FunctionalSpace management, handling data operations and business logic.
 */
@Service
public class FunctionalSpaceServiceImpl implements FunctionalSpaceService {

  @Autowired
  private FunctionalSpaceMapper functionalSpaceMapper;

  @Override
  public FunctionalSpace getFunctionalSpaceById(Integer id) {
    return functionalSpaceMapper.findFunctionalSpaceById(id);
  }

  @Override
  public void createFunctionalSpace(FunctionalSpace functionalSpace) {
    functionalSpaceMapper.insert(functionalSpace); // Insert new FunctionalSpace
  }

  @Override
  public void updateFunctionalSpace(FunctionalSpace functionalSpace) {
    functionalSpaceMapper.update(functionalSpace); // Update existing FunctionalSpace
  }

  @Override
  public void deleteFunctionalSpace(Integer id) {
    functionalSpaceMapper.delete(id); // Delete FunctionalSpace by ID
  }

  @Override
  public List<FunctionalSpace> getAllFunctionalSpaces() {
    return functionalSpaceMapper.findAll();
  }
}
