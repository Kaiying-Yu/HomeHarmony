package hh.homeharmony.model;

/**
 * Abstract base class for all entities with a common identifier.
 * This class provides a common structure for ID management across all entities.
 */
public abstract class BaseEntity {
  protected Integer id;

  /**
   * Gets the universal identifier for the entity.
   *
   * @return the entity's ID
   */
  public Integer getId() {
    return id;
  }

  /**
   * Sets the universal identifier for the entity.
   *
   * @param id the entity's ID
   */
  public void setId(Integer id) {
    this.id = id;
  }
}

// The unique identifier for each record in the database.
// It's assigned either manually or automatically (e.g., via database auto-increment or UUID generation).