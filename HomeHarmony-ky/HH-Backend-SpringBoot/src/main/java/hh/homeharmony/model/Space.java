package hh.homeharmony.model;

import java.util.ArrayList;
import java.util.List;

import lombok.EqualsAndHashCode;

/**
 * Represents a Space entity in the system.
 * Spaces are areas that can have various functional spaces and chores assigned to them,
 * and can be occupied by multiple members.
 */
@EqualsAndHashCode(callSuper = true)
public class Space extends BaseEntity implements ISpace {
    private String name;
    private List<User> users; // List of Users associated with the Space

    /**
     * Default constructor.
     * Initializes a new Space with an empty list of users.
     */
    public Space() {
        this.users = new ArrayList<>();
    }

    /**
     * Parameterized constructor.
     * Creates a new Space with the specified name and an empty list of users.
     *
     * @param name the name of the space
     * @throws IllegalArgumentException if name is null or empty
     */
    public Space(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Space name cannot be null or empty");
        }
        this.name = name;
        this.users = new ArrayList<>();
    }

    /**
     * Gets the name of the space.
     *
     * @return the space name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the space.
     *
     * @param name the name to set
     * @throws IllegalArgumentException if name is null or empty
     */
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Space name cannot be null or empty");
        }
        this.name = name;
    }

    /**
     * Gets the list of users in this space.
     *
     * @return list of users, may be empty but never null
     */
    public List<User> getUsers() {
        if (users == null) {
            users = new ArrayList<>();
        }
        return users;
    }

    /**
     * Sets the list of users in this space.
     *
     * @param users the list of users to set
     */
    public void setUsers(List<User> users) {
        this.users = users != null ? users : new ArrayList<>();
    }

    // Implementation of ISpace methods remains the same
    @Override
    public void addUser(User user) throws IllegalArgumentException, IllegalStateException {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (users == null) {
            users = new ArrayList<>();
        }
        if (!users.contains(user)) {
            users.add(user);
        } else {
            throw new IllegalStateException("User is already a member of this space.");
        }
    }

    @Override
    public void removeUser(User user) throws IllegalArgumentException, IllegalStateException {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (users != null && users.contains(user)) {
            users.remove(user);
        } else {
            throw new IllegalStateException("User is not a member of this space.");
        }
    }

    /**
     * Returns a string representation of the space.
     *
     * @return formatted string containing space details
     */
    @Override
    public String toString() {
        return "Space{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", users=" + (users != null ? users.size() : 0) + " members" +
                '}';
    }
}