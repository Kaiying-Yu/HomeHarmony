package hh.homeharmony.model;

public interface ISpace {
    /**
     * Adds a user to the space.
     * @param user The user to add
     * @throws IllegalArgumentException if the user is null
     * @throws IllegalStateException if the user is already in the space
     */
    void addUser(User user) throws IllegalArgumentException, IllegalStateException;

    /**
     * Removes a user from the space.
     * @param user The user to remove
     * @throws IllegalArgumentException if the user is null
     * @throws IllegalStateException if the user is not in the space
     */
    void removeUser(User user) throws IllegalArgumentException, IllegalStateException;
} 