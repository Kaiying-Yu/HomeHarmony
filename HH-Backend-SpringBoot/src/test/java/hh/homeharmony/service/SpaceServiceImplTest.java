package hh.homeharmony.service;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import hh.homeharmony.mapper.SpaceMapper;
import hh.homeharmony.mapper.UserMapper;
import hh.homeharmony.model.Space;
import hh.homeharmony.model.User;
import hh.homeharmony.service.impl.SpaceServiceImpl;

/**
 * Unit tests for the SpaceServiceImpl class.
 * Tests all service operations including space creation, updates, and user management.
 * Uses Mockito to mock SpaceMapper and UserMapper dependencies.
 */
public class SpaceServiceImplTest {

    @Mock
    private SpaceMapper spaceMapper;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private SpaceServiceImpl spaceService;

    private Space testSpace;
    private User testUser;
    private List<User> testUsers;

    /**
     * Sets up test fixtures before each test method.
     * Initializes mock objects and creates test data.
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        
        // Setup test user
        testUser = new User();
        testUser.setId(1);
        testUser.setUsername("testUser");
        
        // Setup test space
        testSpace = new Space("Test Space");
        testSpace.setId(1);
        testUsers = new ArrayList<>();
        testUsers.add(testUser);
        testSpace.setUsers(testUsers);
    }

    /**
     * Tests space retrieval with valid ID.
     * Verifies that space and its users are correctly retrieved.
     */
    @Test
    public void getSpaceById_WithValidId_ShouldReturnSpace() {
        // Arrange
        when(spaceMapper.findSpaceById(1)).thenReturn(testSpace);
        when(userMapper.findUsersBySpaceId(1)).thenReturn(testUsers);

        // Act
        Space result = spaceService.getSpaceById(1);

        // Assert
        assertNotNull(result);
        assertEquals(testSpace.getId(), result.getId());
        assertEquals(testUsers, result.getUsers());
        verify(spaceMapper).findSpaceById(1);
        verify(userMapper).findUsersBySpaceId(1);
    }

    /**
     * Tests space creation with valid data.
     * Verifies space creation and creator assignment.
     */
    @Test
    public void createSpace_WithValidData_ShouldSucceed() {
        // Arrange
        when(userMapper.findUserById(1)).thenReturn(testUser);
        when(spaceMapper.findSpaceById(1)).thenReturn(testSpace);

        // Act
        Space result = spaceService.createSpace(testSpace);

        // Assert
        assertNotNull(result);
        verify(spaceMapper).insertSpace(testSpace);
        verify(spaceMapper).updateSpace(testSpace);
        verify(userMapper).updateUserSpace(testUser.getId(), testSpace.getId());
    }

    /**
     * Tests space creation without creator.
     * Verifies that appropriate exception is thrown.
     */
    @Test
    public void createSpace_WithoutCreator_ShouldThrowException() {
        // Arrange
        Space spaceWithoutCreator = new Space("Test Space");
        spaceWithoutCreator.setUsers(new ArrayList<>());

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            spaceService.createSpace(spaceWithoutCreator);
        });
        assertNotNull(exception);
    }

    /**
     * Tests adding user to space with valid IDs.
     * Verifies user is correctly added to space.
     */
    @Test
    public void addUserToSpace_WithValidIds_ShouldSucceed() {
        // Arrange
        when(spaceMapper.findSpaceById(1)).thenReturn(testSpace);
        when(userMapper.findUserById(2)).thenReturn(new User());

        // Act
        spaceService.addUserToSpace(1, 2);

        // Assert
        verify(spaceMapper).updateSpace(testSpace);
        verify(userMapper).updateUserSpace(2, 1);
    }

    /**
     * Tests adding user to non-existent space.
     * Verifies that appropriate exception is thrown.
     */
    @Test
    public void addUserToSpace_WithInvalidSpaceId_ShouldThrowException() {
        // Arrange
        when(spaceMapper.findSpaceById(999)).thenReturn(null);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
            spaceService.addUserToSpace(999, 1)
        );
        assertNotNull(exception);
    }

    /**
     * Tests removing user from space with valid data.
     * Verifies user is correctly removed from space.
     */
    @Test
    public void removeUserFromSpace_WithValidData_ShouldSucceed() {
        // Arrange
        testUser.setSpaceId(1);
        when(spaceMapper.findSpaceById(1)).thenReturn(testSpace);
        when(userMapper.findUserById(1)).thenReturn(testUser);

        // Act
        spaceService.removeUserFromSpace(1, 1);

        // Assert
        verify(userMapper).updateUserSpace(1, null);
    }

    /**
     * Tests removing user from wrong space.
     * Verifies that appropriate exception is thrown.
     */
    @Test
    public void removeUserFromSpace_UserInDifferentSpace_ShouldThrowException() {
        // Arrange
        testUser.setSpaceId(2);
        when(spaceMapper.findSpaceById(1)).thenReturn(testSpace);
        when(userMapper.findUserById(1)).thenReturn(testUser);

        // Act & Assert
        IllegalStateException exception = assertThrows(IllegalStateException.class, () ->
            spaceService.removeUserFromSpace(1, 1)
        );
        assertNotNull(exception);
    }

    /**
     * Tests space update with valid data.
     * Verifies space information is correctly updated.
     */
    @Test
    public void updateSpace_WithValidData_ShouldSucceed() {
        // Act
        spaceService.updateSpace(testSpace);

        // Assert
        verify(spaceMapper).updateSpace(testSpace);
    }
}
