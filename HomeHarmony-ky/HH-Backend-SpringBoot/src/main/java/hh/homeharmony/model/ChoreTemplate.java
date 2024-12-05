package hh.homeharmony.model;

import java.time.Duration;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Template for chores that can be instantiated into actual tasks.
 * Includes attributes for managing default setup, including estimated time and points.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
// Without callSuper = true, the equals() and hashCode() methods would only consider the fields directly
// declared in FunctionalSpace, ignoring the inherited ID field, which could lead to incorrect equality comparisons.
public class ChoreTemplate extends BaseEntity {
  private String name;
  private String description;
  private Integer functionalSpaceId; // ID linking to a specific FunctionalSpace
  private boolean isGlobal; // Indicates if the template is applicable to all spaces
  private Integer estimatedTime;// Need TypeHandler if using Duration (Built-in methods for time manipulation)
  private int points; // Default points for completing the chore
}
