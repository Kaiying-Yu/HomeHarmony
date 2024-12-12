package hh.homeharmony.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Space entity in the system.
 * Spaces are areas that can have various functional spaces and chores assigned to them,
 * and can be occupied by multiple members.
 */
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
    public Space(String name) throws IllegalArgumentException {
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
    @Override
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the space.
     *
     * @param name the name to set
     * @throws IllegalArgumentException if name is null or empty
     */
    @Override
    public void setName(String name) throws IllegalArgumentException {
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
    @Override
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

    /**
     * Adds a user to the space.
     * This method implements the ISpace interface method for adding users.
     * If the users list is null, it initializes a new ArrayList.
     * Users can only be added once to a space.
     * 
     * @param user The user to add to the space
     * @throws IllegalArgumentException if the user parameter is null
     * @throws IllegalStateException if the user is already a member of this space
     */
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

    /**
     * Removes a user from the space.
     * This method implements the ISpace interface method for removing users.
     * The user must be a current member of the space to be removed.
     * If the users list is null or the user is not found, throws an exception.
     *
     * @param user The user to remove from the space
     * @throws IllegalArgumentException if the user parameter is null
     * @throws IllegalStateException if the user is not a member of this space
     */
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