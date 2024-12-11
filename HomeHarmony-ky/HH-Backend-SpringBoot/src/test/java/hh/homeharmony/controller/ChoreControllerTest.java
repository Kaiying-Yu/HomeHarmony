package hh.homeharmony.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import hh.homeharmony.model.Chore;
import hh.homeharmony.service.ChoreService;
import hh.homeharmony.service.UserService;

/**
 * Unit tests for the ChoreController class.
 * Tests all REST endpoints for managing chore-related operations.
 * Uses Mockito to mock ChoreService and UserService dependencies.
 */
public class ChoreControllerTest {

    @Mock
    private ChoreService choreService;

    @Mock
    private UserService userService;

    @InjectMocks
    private ChoreController choreController;

    private Chore testChore;

    /**
     * Sets up test fixtures before each test method.
     * Initializes mock objects and creates a test chore.
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        
        // Setup test chore data
        testChore = new Chore();
        testChore.setId(1);
        testChore.setChoreName("Test Chore");
        testChore.setSpaceId(1);
        testChore.setPoints(10);
    }

    /**
     * Tests the creation of a new chore.
     * Verifies that the response contains the created chore.
     */
    @Test
    public void createChore_ValidChore_ShouldReturnCreatedChore() {
        when(choreService.createChore(testChore)).thenReturn(testChore);

        ResponseEntity<Chore> response = choreController.createChore(testChore);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(testChore, response.getBody());
        verify(choreService).createChore(testChore);
    }

    /**
     * Tests the update of an existing chore.
     * Verifies that the response contains the updated chore.
     */
    @Test
    public void updateChore_ValidIdAndChore_ShouldReturnUpdatedChore() {
        when(choreService.updateChore(testChore)).thenReturn(testChore);

        ResponseEntity<Chore> response = choreController.updateChore(1, testChore);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(testChore, response.getBody());
        verify(choreService).updateChore(testChore);
    }

    /**
     * Tests the deletion of a chore.
     * Verifies that the response indicates successful deletion.
     */
    @Test
    public void deleteChore_ValidId_ShouldReturnNoContent() {
        ResponseEntity<Void> response = choreController.deleteChore(1);

        assertEquals(200, response.getStatusCodeValue());
        verify(choreService).deleteChore(1);
    }

    /**
     * Tests retrieval of all chores for a specific space.
     * Verifies that the response contains the list of chores.
     */
    @Test
    public void getAllChores_ValidSpaceId_ShouldReturnChores() {
        List<Chore> chores = List.of(testChore);
        when(choreService.getAllChores(1)).thenReturn(chores);

        ResponseEntity<Map<String, Object>> response = choreController.getAllChores(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("success", response.getBody().get("status"));
        assertEquals(chores, response.getBody().get("data"));
        verify(choreService).getAllChores(1);
    }

    /**
     * Tests assigning a user to a chore.
     * Verifies that the response indicates successful assignment.
     */
    @Test
    public void assignUserToChore_ValidChoreIdAndUserId_ShouldReturnSuccess() {
        Map<String, Object> expectedResponse = new HashMap<>();
        expectedResponse.put("status", "success");
        expectedResponse.put("message", "User assigned successfully");

        ResponseEntity<Map<String, Object>> response = choreController.assignUserToChore(1, 1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(expectedResponse, response.getBody());
        verify(choreService).assignUserToChore(1, 1);
    }

    /**
     * Tests marking a chore as completed.
     * Verifies that the response indicates successful completion.
     */
    @Test
    public void completeChore_ValidChoreId_ShouldReturnSuccess() {
        Map<String, Object> expectedResponse = new HashMap<>();
        expectedResponse.put("status", "success");
        expectedResponse.put("message", "Chore completed successfully");

        when(choreService.completeChore(1)).thenReturn(testChore);

        ResponseEntity<Map<String, Object>> response = choreController.completeChore(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(expectedResponse, response.getBody());
        verify(choreService).completeChore(1);
    }

    /**
     * Tests retrieval of chores assigned to a specific user.
     * Verifies that the response contains the list of user's chores.
     */
    @Test
    public void getChoresByUserId_ValidUserId_ShouldReturnChores() {
        List<Chore> chores = List.of(testChore);
        when(choreService.getChoresByUserId(1)).thenReturn(chores);

        ResponseEntity<Map<String, Object>> response = choreController.getChoresByUserId(1);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("success", response.getBody().get("status"));
        assertEquals(chores, response.getBody().get("data"));
        verify(choreService).getChoresByUserId(1);
    }
} 