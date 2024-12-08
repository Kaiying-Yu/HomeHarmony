package hh.homeharmony.model;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Chore extends BaseEntity implements IChore {
    private String choreName;
    private Integer points;
    private LocalDateTime createDate;
    private LocalDateTime dueDate;
    private ChoreStatus status;
    private User assignedUser;

    public Chore() {
        this.status = ChoreStatus.PENDING;
    }

    public Chore(String choreName, LocalDateTime createDate, LocalDateTime dueDate) {
        this.choreName = choreName;
        this.createDate = createDate;
        this.dueDate = dueDate;
        this.status = ChoreStatus.PENDING;
    }

    public void assignUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        this.assignedUser = user;
        this.status = ChoreStatus.IN_PROGRESS;
    }

    @Override
    public void markAsCompleted() {
        if (this.status == ChoreStatus.IN_PROGRESS) {
            this.status = ChoreStatus.COMPLETED;
        }
    }

    @Override
    public ChoreStatus getChoreStatus() {
        return this.status;
    }

    @Override
    public String toString() {
        return "Chore: " + choreName + 
               ", Assigned User: " + (assignedUser == null ? "None" : assignedUser.getUsername()) +
               ", Status: " + status + 
               ", Due Date: " + dueDate;
    }
}