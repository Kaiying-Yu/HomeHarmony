package hh;
// Abstract class that implements Chore and provides common attributes and functionalities
abstract class AbstractChore implements Chore {
  protected String createDate;
  protected String dueDate;
  protected String choreStatus;
  protected String assignedUser;

  public AbstractChore(String createDate, String dueDate) {
  this.createDate = createDate;
  this.dueDate = dueDate;
  this.choreStatus = "Not Assigned"; // Initial status
}

@Override
public void assignUser(String user) {
  this.assignedUser = user;
  this.choreStatus = "In Progress";
}

@Override
public void markAsDone() {
  if (this.choreStatus.equals("In Progress")) {
    this.choreStatus = "Done";
  }
}

@Override
public String getChoreStatus() {
  return this.choreStatus;
}
}