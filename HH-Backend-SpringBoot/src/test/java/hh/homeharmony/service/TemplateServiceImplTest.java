package hh.homeharmony.service;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.anyInt;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import hh.homeharmony.model.FunctionalSpaceType;
import hh.homeharmony.service.impl.TemplateServiceImpl;
import hh.homeharmony.service.templates.DefaultChoreTemplate;

/**
 * Test class for TemplateServiceImpl.
 * Verifies functionality for managing default chore creation based on functional space types.
 */
class TemplateServiceImplTest {

    @Mock
    private DefaultChoreTemplate kitchenTemplate;

    @Mock
    private DefaultChoreTemplate livingRoomTemplate;

    private TemplateServiceImpl templateServiceImpl;

    private final FunctionalSpaceType kitchenType = FunctionalSpaceType.KITCHEN;
    private final FunctionalSpaceType livingRoomType = FunctionalSpaceType.LIVING_ROOM;

    /**
     * Sets up the test environment before each test.
     * Initializes the mocked templates and the TemplateServiceImpl instance under test.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Mock the behavior of the templates
        when(kitchenTemplate.getFunctionalSpaceType()).thenReturn(kitchenType);
        when(livingRoomTemplate.getFunctionalSpaceType()).thenReturn(livingRoomType);

        // Initialize the TemplateServiceImpl with mocked templates
        templateServiceImpl = new TemplateServiceImpl(List.of(kitchenTemplate, livingRoomTemplate));
    }

    /**
     * Tests that the correct template's createDefaultChores method is called for a valid space type.
     */
    @Test
    void testCreateDefaultChores_withValidType_callsCorrectTemplate() {
        Integer spaceId = 1;

        // Call the method under test
        templateServiceImpl.createDefaultChores(spaceId, kitchenType);

        // Verify the correct template method was called
        verify(kitchenTemplate, times(1)).createDefaultChores(spaceId);
        verify(livingRoomTemplate, never()).createDefaultChores(anyInt());
    }

    /**
     * Tests that an exception is thrown when no template exists for a given space type.
     */
    @Test
    void testCreateDefaultChores_withInvalidType_throwsException() {
        Integer spaceId = 1;
        FunctionalSpaceType invalidType = FunctionalSpaceType.GARAGE; // Assume no template exists for this type

        // Verify that the method throws an exception for an invalid type
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            templateServiceImpl.createDefaultChores(spaceId, invalidType);
        });

        assertEquals("No template found for FunctionalSpaceType: " + invalidType, exception.getMessage());
    }

    /**
     * Tests that the template map is initialized correctly and mappings are honored.
     */
    @Test
    void testCreateDefaultChores_templateMapInitialization() {
        // Verify that the template map contains the correct mappings
        templateServiceImpl.createDefaultChores(1, kitchenType);
        verify(kitchenTemplate, times(1)).createDefaultChores(1);

        templateServiceImpl.createDefaultChores(2, livingRoomType);
        verify(livingRoomTemplate, times(1)).createDefaultChores(2);
    }

    /**
     * Tests that the correct template's createDefaultChores method is called for a valid space type.
     */
    @Test
    void testDefaultChoreTemplate_createDefaultChores() {
        Integer spaceId = 1;

        // Mock the createDefaultChores behavior
        doNothing().when(kitchenTemplate).createDefaultChores(spaceId);

        // Call the method under test
        templateServiceImpl.createDefaultChores(spaceId, kitchenType);

        // Verify that createDefaultChores was called on the correct template
        verify(kitchenTemplate, times(1)).createDefaultChores(spaceId);

        // Ensure no interactions with unrelated templates
        verify(livingRoomTemplate, never()).createDefaultChores(anyInt());
    }
}
