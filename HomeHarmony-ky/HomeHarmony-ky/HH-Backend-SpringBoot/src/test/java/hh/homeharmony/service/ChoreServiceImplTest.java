package hh.homeharmony.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import hh.homeharmony.service.impl.ChoreServiceImpl;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import hh.homeharmony.mapper.ChoreMapper;
import hh.homeharmony.mapper.UserMapper;
import hh.homeharmony.model.Chore;
import hh.homeharmony.model.ChoreStatus;
import hh.homeharmony.model.User;

/**
 * Unit tests for the ChoreServiceImpl class.
 * Tests all service operations including chore creation, updates, deletion, and status changes.
 * Uses Mockito to mock ChoreMapper and UserMapper dependencies.
 */
public class ChoreServiceImplTest {

    @Mock
    private ChoreMapper choreMapper;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private ChoreServiceImpl choreService;

    private Chore testChore;
    private User testUser;

    /**
     * Sets up test fixtures before each test method.
     * Initializes mock objects and creates test data.
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        
        // Setup test data
        testChore = new Chore();
        testChore.setId(1);
        testChore.setSpaceId(1);
        testChore.setChoreName("Test Chore");
        testChore.setStatus(ChoreStatus.PENDING);

        testUser = new User();
        testUser.setId(1);
        testUser.setUsername("testUser");
    }

    /**
     * Tests chore creation with valid data.
     * Verifies that the chore is created with PENDING status and correct space ID.
     */
    @Test
    public void createChore_WithValidData_ShouldSucceed() {
        // Arrange
        Chore choreToCreate = new Chore();
        choreToCreate.setSpaceId(1);

        // Act
        Chore result = choreService.createChore(choreToCreate);

        // Assert
        assertEquals(ChoreStatus.PENDING, result.getChoreStatus());
        verify(choreMapper).insert(choreToCreate);
    }

    /**
     * Tests chore creation without space ID.
     * Verifies that an IllegalArgumentException is thrown when space ID is missing.
     */
    @Test
    public void createChore_WithoutSpaceId_ShouldThrowException() {
        // Arrange
        Chore choreWithoutSpaceId = new Chore();

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            choreService.createChore(choreWithoutSpaceId);
        });
    }

    /**
     * Tests user assignment to chore with valid IDs.
     * Verifies that the chore status changes to IN_PROGRESS and user is properly assigned.
     */
    @Test
    public void assignUserToChore_WithValidIds_ShouldSucceed() {
        // Arrange
        when(choreMapper.selectById(1)).thenReturn(testChore);
        when(userMapper.findUserById(1)).thenReturn(testUser);

        // Act
        choreService.assignUserToChore(1, 1);

        // Assert
        verify(choreMapper).update(any(Chore.class));
        assertEquals(ChoreStatus.IN_PROGRESS, testChore.getChoreStatus());
        assertEquals(testUser, testChore.getAssignedUser());
    }

    /**
     * Tests chore completion with valid chore ID.
     * Verifies that the chore status changes to COMPLETED.
     */
    @Test
    public void completeChore_WithValidId_ShouldSucceed() {
        // Arrange
        testChore.setStatus(ChoreStatus.IN_PROGRESS);
        when(choreMapper.selectById(1)).thenReturn(testChore);

        // Act
        Chore result = choreService.completeChore(1);

        // Assert
        assertEquals(ChoreStatus.COMPLETED, result.getChoreStatus());
        verify(choreMapper).update(testChore);
    }

    /**
     * Tests retrieval of all chores for a space.
     * Verifies that the correct list of chores is returned.
     */
    @Test
    public void getAllChores_ShouldReturnChoreList() {
        // Arrange
        List<Chore> expectedChores = Arrays.asList(testChore);
        when(choreMapper.selectAllBySpaceId(1)).thenReturn(expectedChores);

        // Act
        List<Chore> result = choreService.getAllChores(1);

        // Assert
        assertEquals(expectedChores, result);
        verify(choreMapper).selectAllBySpaceId(1);
    }
}
