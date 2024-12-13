package hh.homeharmony.service.templates;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import hh.homeharmony.mapper.ChoreMapper;
import hh.homeharmony.model.Chore;
import hh.homeharmony.model.FunctionalSpaceType;

/**
 * Unit tests for the LivingRoomChoreTemplate class.
 */
public class LivingRoomChoreTemplateTest {

    private LivingRoomChoreTemplate livingRoomChoreTemplate;

    private ChoreMapper choreMapper;

    /**
     * Sets up the test environment before each test.
     * Initializes the mocked ChoreMapper and the LivingRoomChoreTemplate instance under test.
     */
    @BeforeEach
    public void setUp() {
        // Use a mock ChoreMapper
        choreMapper = mock(ChoreMapper.class);
        livingRoomChoreTemplate = new LivingRoomChoreTemplate(choreMapper);
    }

    /**
     * Tests the retrieval of default chores for a living room space.
     * Verifies the correct number of chores and their content.
     */
    @Test
    public void getDefaultChores_ShouldReturnExpectedChores() {
        // Arrange
        Integer spaceId = 1;

        // Act
        List<Chore> chores = livingRoomChoreTemplate.getDefaultChores(spaceId);

        // Assert
        assertEquals(4, chores.size());

        // Validate specific chore properties
        Chore vacuumCarpetsChore = chores.get(0);
        assertEquals("Vacuum Carpets and Rugs", vacuumCarpetsChore.getChoreName());
        assertEquals("Vacuum the living room carpet and rugs thoroughly.", vacuumCarpetsChore.getChoreDescription());
        assertEquals(FunctionalSpaceType.LIVING_ROOM, vacuumCarpetsChore.getFunctionalSpaceType());
        assertEquals(5, vacuumCarpetsChore.getPoints());

        Chore dustFurnitureChore = chores.get(1);
        assertEquals("Dust Furniture and Electronics", dustFurnitureChore.getChoreName());
        assertEquals("Dust all surfaces, including shelves, tables, and electronics.", dustFurnitureChore.getChoreDescription());
    }

    /**
     * Tests the persistence of default chores for a living room space.
     * Verifies that all chores are saved through the ChoreMapper.
     */
    @Test
    public void createDefaultChores_ShouldPersistAllDefaultChores() {
        // Arrange
        Integer spaceId = 1;

        // Act
        livingRoomChoreTemplate.createDefaultChores(spaceId);

        // Assert
        verify(choreMapper, times(4)).insert(any(Chore.class));

        // Validate specific interactions with the choreMapper
        verify(choreMapper).insert(argThat(chore ->
                chore.getChoreName().equals("Vacuum Carpets and Rugs") &&
                        chore.getChoreDescription().equals("Vacuum the living room carpet and rugs thoroughly.")
        ));
        verify(choreMapper).insert(argThat(chore ->
                chore.getChoreName().equals("Dust Furniture and Electronics") &&
                        chore.getFunctionalSpaceType() == FunctionalSpaceType.LIVING_ROOM
        ));
    }

    /**
     * Tests the retrieval of the functional space type for the living room template.
     * Verifies that the correct FunctionalSpaceType is returned.
     */
    @Test
    public void getFunctionalSpaceType_ShouldReturnLivingRoom() {
        // Act
        FunctionalSpaceType type = livingRoomChoreTemplate.getFunctionalSpaceType();

        // Assert
        assertEquals(FunctionalSpaceType.LIVING_ROOM, type);
    }
}