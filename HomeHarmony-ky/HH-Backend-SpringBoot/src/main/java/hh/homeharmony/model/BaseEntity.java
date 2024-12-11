package hh.homeharmony.model;

/**
 * Abstract base class for all entities with a common identifier.
 * This class provides a common structure for ID management across all entities.
 * All domain entities in the system should extend this class to inherit ID functionality.
 */
public abstract class BaseEntity {
    /** Universal identifier for all entities */
    protected Integer id;

    /**
     * Default constructor.
     * Creates a new instance with null ID.
     */
    public BaseEntity() {
    }

    /**
     * Constructor with ID.
     * Creates a new instance with the specified ID.
     *
     * @param id the unique identifier for this entity
     */
    public BaseEntity(Integer id) {
        this.id = id;
    }

    /**
     * Gets the entity's unique identifier.
     *
     * @return the ID of this entity, or null if not yet persisted
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the entity's unique identifier.
     * This should typically only be called by the persistence layer.
     *
     * @param id the new ID for this entity
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Checks if this entity equals another object.
     * Entities are considered equal if they have the same non-null ID.
     *
     * @param o the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseEntity)) return false;
        BaseEntity that = (BaseEntity) o;
        return id != null && id.equals(that.getId());
    }

    /**
     * Generates a hash code for this entity.
     * The hash code is based on the ID if it's not null.
     *
     * @return the hash code value for this entity
     */
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    /**
     * Returns a string representation of this base entity.
     * Includes the entity's class name and ID.
     *
     * @return a string representation of this entity
     */
    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                '}';
    }
}
