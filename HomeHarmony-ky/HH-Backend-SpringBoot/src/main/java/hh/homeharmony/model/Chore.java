package hh.homeharmony.model;

import java.time.Duration;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Chore extends BaseEntity implements IChore {
    private String choreName;
    private String description; // Description of the chore
    private FunctionalSpaceType functionalSpaceType;// Functional space type
    private Duration estimatedTime;// Estimated time to complete the chore
    private Integer points;
    private LocalDateTime dueDate;
    private ChoreStatus status;
    private User assignedUser;
    private Integer spaceId;

    public Chore() {
        this.status = ChoreStatus.PENDING;
    }

    public Chore(String choreName, LocalDateTime dueDate) {
        this.choreName = choreName;
        this.dueDate = dueDate;
        this.status = ChoreStatus.PENDING;
    }

    public Chore(String choreName, String description, FunctionalSpaceType functionalSpaceType, Duration estimatedTime, Integer points, ChoreStatus status) {
        this.choreName = choreName;
        this.description = description;
        this.functionalSpaceType = functionalSpaceType;
        this.estimatedTime = estimatedTime;
        this.points = points;
        this.status = status;
    }

    public String getChoreDescription() {
        return description;
    }


    public void setChoreDescription(String description) {
        this.description = description;
    }


    public FunctionalSpaceType getFunctionalSpaceType() {
        return functionalSpaceType;
    }


    public void setFunctionalSpaceType(FunctionalSpaceType functionalSpaceType) {
        this.functionalSpaceType = functionalSpaceType;
    }


    public Duration getEstimatedTime() {
        return estimatedTime;
    }


    public void setEstimatedTime(Duration estimatedTime) {
        this.estimatedTime = estimatedTime;
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

    public Integer getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(Integer spaceId) {
        this.spaceId = spaceId;
    }
}