package hh.homeharmony.service;

import java.util.List;

import hh.homeharmony.model.FunctionalSpace;

/**
 * Interface for managing FunctionalSpaces which are specific areas within a larger space.
 */
public interface FunctionalSpaceService {
  FunctionalSpace getFunctionalSpaceById(Integer id);
  List<FunctionalSpace> getAllFunctionalSpaces();
  void createFunctionalSpace(FunctionalSpace functionalSpace);
  void updateFunctionalSpace(FunctionalSpace functionalSpace);
  void deleteFunctionalSpace(Integer id);
}
