package hh.homeharmony.model;

/**
 * Enum defining possible statuses for a ChoreTask.
 */
public enum ChoreStatus {
  PENDING("Pending"),     // Task is pending execution
  IN_PROGRESS("In Progress"), // Task is currently being worked on
  COMPLETED("Completed"), // Task has been completed
  OVERDUE("Overdue");     // Task was not completed in the allotted time

  private final String status;

  ChoreStatus(String status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return this.status;
  }
}