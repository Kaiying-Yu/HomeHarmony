package hh;
// Concrete class that extends AbstractChore
class HouseholdChore extends AbstractChore {
  private String choreName;

  public HouseholdChore(String choreName, String createDate, String dueDate) {
    super(createDate, dueDate);
    this.choreName = choreName;
  }

  public String getChoreName() {
    return choreName;
  }

  public String getAssignedUser() {
    return assignedUser;
  }

  @Override
  public String toString() {
    return "Chore: " + choreName + ", Assigned User: " + (assignedUser == null ? "None" : assignedUser) +
        ", Status: " + choreStatus + ", Due Date: " + dueDate;
  }
}

