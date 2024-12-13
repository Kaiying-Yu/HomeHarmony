package hh.homeharmony.model;

/**
 * Enum representing the possible states of a chore in the system.
 * Used to track the lifecycle of chores from creation to completion.
 * Referenced by Chore entity and ChoreService for status management.
 */
/* OOD Idea: Reusability-The enum can be reused throughout the codebase wherever these chore status are needed,*/
public enum ChoreStatus {
  // Initial state when chore is created but not yet started
  PENDING("Pending"),

  // State when chore has been assigned and is being worked on
  IN_PROGRESS("In Progress"),

  // Final state when chore has been successfully finished
  COMPLETED("Completed"),

  // State when chore wasn't completed in the allotted time
  OVERDUE("Overdue");

  /** Display string for the status */
  private final String status;

  /**
   * Constructor for ChoreStatus enum.
   * Creates a new status with a display string.
   *
   * @param status The human-readable string representation of the status
   */
  ChoreStatus(String status) {
    this.status = status;
  }

  /**
   * Returns the human-readable representation of the status.
   * Used for display purposes in UI and logging.
   *
   * @return String representation of the status
   */
  @Override
  public String toString() {
    return this.status;
  }
}