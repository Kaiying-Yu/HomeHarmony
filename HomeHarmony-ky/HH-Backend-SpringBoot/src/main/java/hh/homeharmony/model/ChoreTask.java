package hh.homeharmony.model;

import java.time.Duration;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Represents a specific chore instance created from a template.
 * Allows customization of the default settings and setting up recurrences.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ChoreTask extends BaseEntity {
  private Integer choreTemplateId; // Reference to the ChoreTemplate
  private Integer roomId; // Reference to the FunctionalSpace
  private LocalDateTime dueDate; // When the chore is due
  private Integer customizedTime; // Customized time in minutes
  private int customizedPoints; // Customized points
  private String description; // Description of this chore
  private ChoreStatus status; // Current status of the chore, using the ChoreStatus enum
}
