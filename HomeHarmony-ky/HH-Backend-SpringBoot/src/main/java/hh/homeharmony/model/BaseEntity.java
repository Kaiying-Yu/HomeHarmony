package hh.homeharmony.model;

import lombok.Data;

/**
 * Abstract base class for all entities with a common identifier.
 * This class provides a common structure for ID management across all entities.
 */
@Data
public abstract class BaseEntity {
  protected Integer id; // Universal identifier for all entities
}
