package hh.homeharmony.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import hh.homeharmony.model.Space;
import hh.homeharmony.service.SpaceService;

/**
 * Unit tests for the SpaceController class.
 * Tests all endpoints related to space management including retrieval, creation, updating, and user management.
 * Uses Mockito to mock SpaceService dependency.
 */
public class SpaceControllerTest {

    @Mock
    private SpaceService spaceService;

    @InjectMocks
    private SpaceController spaceController;

    private Space testSpace;

    /**
     * Sets up test fixtures before each test method.
     * Initializes mock objects and creates a test space.
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        
        // Setup test space data
        testSpace = new Space("Test Space");
        testSpace.setId(1);
    }

    /**
     * Tests retrieval of a space by valid ID.
     * Verifies that the correct space is returned with a 200 OK response.
     */
    @Test
    public void getSpaceById_ValidId_ShouldReturnSpace() {
        // Arrange
        when(spaceService.getSpaceById(1)).thenReturn(testSpace);

        // Act
        ResponseEntity<Space> response = spaceController.getSpaceById(1);

        // Assert
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals(testSpace, response.getBody());
        verify(spaceService).getSpaceById(1);
    }

    /**
     * Tests retrieval of a space by non-existent ID.
     * Verifies that a 404 Not Found response is returned.
     */
    @Test
    public void getSpaceById_NonExistentId_ShouldReturnNotFound() {
        // Arrange
        when(spaceService.getSpaceById(999)).thenReturn(null);

        // Act
        ResponseEntity<Space> response = spaceController.getSpaceById(999);

        // Assert
        assertEquals(404, response.getStatusCode().value());
    }

    /**
     * Tests creation of a new space with valid data.
     * Verifies that the created space is returned with a 200 OK response.
     */
    @Test
    public void createSpace_ValidData_ShouldReturnCreatedSpace() {
        // Arrange
        when(spaceService.createSpace(any(Space.class))).thenReturn(testSpace);

        // Act
        ResponseEntity<Space> response = spaceController.createSpace(testSpace);

        // Assert
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals(testSpace, response.getBody());
        verify(spaceService).createSpace(testSpace);
    }

    /**
     * Tests creation of a new space with invalid data.
     * Verifies that a 400 Bad Request response is returned.
     */
    @Test
    public void createSpace_InvalidData_ShouldReturnBadRequest() {
        // Arrange
        when(spaceService.createSpace(any(Space.class))).thenThrow(new IllegalArgumentException("Invalid space data"));

        // Act
        ResponseEntity<Space> response = spaceController.createSpace(testSpace);

        // Assert
        assertEquals(400, response.getStatusCode().value());
    }

    /**
     * Tests updating an existing space with valid data.
     * Verifies that the updated space is returned with a 200 OK response.
     */
    @Test
    public void updateSpace_ValidData_ShouldReturnUpdatedSpace() {
        // Arrange
        when(spaceService.getSpaceById(1)).thenReturn(testSpace);

        // Act
        ResponseEntity<Space> response = spaceController.updateSpace(1, testSpace);

        // Assert
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals(testSpace, response.getBody());
        verify(spaceService).updateSpace(testSpace);
    }

    /**
     * Tests updating a non-existent space.
     * Verifies that a 404 Not Found response is returned.
     */
    @Test
    public void updateSpace_NonExistentId_ShouldReturnNotFound() {
        // Arrange
        when(spaceService.getSpaceById(999)).thenReturn(null);

        // Act
        ResponseEntity<Space> response = spaceController.updateSpace(999, testSpace);

        // Assert
        assertEquals(404, response.getStatusCode().value());
    }

    /**
     * Tests adding a user to a space with valid IDs.
     * Verifies that a 200 OK response is returned.
     */
    @Test
    public void addUserToSpace_ValidIds_ShouldReturnOk() {
        // Act
        ResponseEntity<?> response = spaceController.addUserToSpace(1, 1);

        // Assert
        assertEquals(200, response.getStatusCode().value());
        verify(spaceService).addUserToSpace(1, 1);
    }

    /**
     * Tests adding a user to a space with invalid IDs.
     * Verifies that a 400 Bad Request response is returned.
     */
    @Test
    public void addUserToSpace_InvalidIds_ShouldReturnBadRequest() {
        // Arrange
        doThrow(new IllegalArgumentException("Invalid space ID")).when(spaceService).addUserToSpace(999, 1);

        // Act
        ResponseEntity<?> response = spaceController.addUserToSpace(999, 1);

        // Assert
        assertEquals(400, response.getStatusCode().value());
    }

    /**
     * Tests removing a user from a space with valid IDs.
     * Verifies that a 200 OK response is returned.
     */
    @Test
    public void removeUserFromSpace_ValidIds_ShouldReturnOk() {
        // Act
        ResponseEntity<?> response = spaceController.removeUserFromSpace(1, 1);

        // Assert
        assertEquals(200, response.getStatusCode().value());
        verify(spaceService).removeUserFromSpace(1, 1);
    }

    /**
     * Tests removing a user from a space with invalid IDs.
     * Verifies that a 400 Bad Request response is returned.
     */
    @Test
    public void removeUserFromSpace_InvalidIds_ShouldReturnBadRequest() {
        // Arrange
        doThrow(new IllegalArgumentException("Invalid user ID")).when(spaceService).removeUserFromSpace(1, 1);

        // Act
        ResponseEntity<?> response = spaceController.removeUserFromSpace(1, 1);

        // Assert
        assertEquals(400, response.getStatusCode().value());
    }
} 