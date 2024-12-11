package hh.homeharmony.model;

/**
 * Interface defining core user functionality.
 * A user represents an individual account in the system that can join spaces and perform chores.
 * This interface provides basic methods for managing user attributes and state.
 */
public interface IUser {
    /**
     * Gets the username of the user.
     *
     * @return the username
     */
    String getUsername();

    /**
     * Sets the username of the user.
     *
     * @param username the username to set
     */
    void setUsername(String username);

    /**
     * Gets the email address of the user.
     *
     * @return the email address
     */
    String getEmail();

    /**
     * Sets the email address of the user.
     *
     * @param email the email address to set
     */
    void setEmail(String email);

    /**
     * Gets the password of the user.
     *
     * @return the password
     */
    String getPassword();

    /**
     * Sets the password of the user.
     *
     * @param password the password to set
     */
    void setPassword(String password);

    /**
     * Gets the points accumulated by the user.
     *
     * @return the points, or null if no points assigned
     */
    Integer getPoints();

    /**
     * Sets the points for the user.
     *
     * @param points the points to set
     */
    void setPoints(Integer points);

    /**
     * Gets the ID of the space this user belongs to.
     *
     * @return the space ID, or null if not in a space
     */
    Integer getSpaceId();

    /**
     * Sets the space ID for the user.
     *
     * @param spaceId the space ID to set
     */
    void setSpaceId(Integer spaceId);
}
