package hh.homeharmony.model;

/**
 * Enum representing the predefined types of functional spaces in a home.
 * Used to categorize different areas within a space for chore assignment and organization.
 * Referenced by Chore entity and ChoreTemplates for functional-space-specific task management.
 */
/* OOD Idea: Reusability-The enum can be reused throughout the codebase wherever these functional space types are needed,*/
public enum FunctionalSpaceType {
  KITCHEN,
  BATHROOM,
  LIVING_ROOM,
  BEDROOM,
  GARAGE,
  HOME_OFFICE,
  DINING_ROOM,
  BALCONY
}