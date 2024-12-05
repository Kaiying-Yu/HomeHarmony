package hh.homeharmony.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

/**
 * Represents a specific area within a Space, such as a kitchen or living room.
 * Inherits a common identifier and can hold a list of ChoreTemplates.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FunctionalSpace extends BaseEntity {
  private String name;
  private List<ChoreTemplate> choreTemplates; // Default chore templates for this space
}
