package hh;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
// Example usage
public class Main {
  public static void main(String[] args) {
    HouseholdChore chore = new HouseholdChore("Clean Kitchen", "2024-12-01", "2024-12-03");
    System.out.println(chore);

    // Assign user to chore
    chore.assignUser("Alice");
    System.out.println(chore);

    // Mark chore as done
    chore.markAsDone();
    System.out.println(chore);
  }
}