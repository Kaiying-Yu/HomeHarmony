package hh.homeharmony.model;

import java.util.List;

/**
 * This interface defining core space functionality.
 * A space represents a shared living environment where users can collaborate on chores.
 * This interface provides basic methods for managing user membership within a space.
 */
/* OOD Idea: Dependency Inversion-Details depend on abstractions */
/* OOD Idea: Open for extension, closed for modification */
/* OOD Idea: Abstraction-This interface defines the contract for any class that implements it,
without specifying how the operations are performed.*/
public interface ISpace {
    /**
     * Gets the name of the space.
     *
     * @return the name of the space
     */
    String getName();

    /**
     * Sets the name of the space.
     *
     * @param name the name of the space
     */
    void setName(String name);

    /**
     * Gets the list of users in the space.
     *
     * @return the list of users
     */
    List<User> getUsers();

    /**
     * Adds a user to the space.
     *
     * @param user The user to add
     * @throws IllegalArgumentException if the user is null
     * @throws IllegalStateException if the user is already in the space
     */
    /* OOD Idea: check inputs */
    void addUser(User user) throws IllegalArgumentException, IllegalStateException;

    /**
     * Removes a user from the space.
     *
     * @param user The user to remove
     * @throws IllegalArgumentException if the user is null
     * @throws IllegalStateException if the user is not in the space
     */
    /* OOD Idea: check inputs */
    void removeUser(User user) throws IllegalArgumentException, IllegalStateException;
} 