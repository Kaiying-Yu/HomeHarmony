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
 * Unit tests for the DiningRoomChoreTemplate class.
 */
public class DiningRoomChoreTemplateTest {

    private DiningRoomChoreTemplate diningRoomChoreTemplate;

    private ChoreMapper choreMapper;

    @BeforeEach
    public void setUp() {
        // Use a mock ChoreMapper
        choreMapper = mock(ChoreMapper.class);
        diningRoomChoreTemplate = new DiningRoomChoreTemplate(choreMapper);
    }

    /**
     * Tests the retrieval of default chores for a dining room space.
     * Verifies the correct number of chores and their content.
     */
    @Test
    public void getDefaultChores_ShouldReturnExpectedChores() {
        // Arrange
        Integer spaceId = 1;

        // Act
        List<Chore> chores = diningRoomChoreTemplate.getDefaultChores(spaceId);

        // Assert
        assertEquals(4, chores.size());

        // Validate specific chore properties
        Chore wipeTableChore = chores.get(0);
        assertEquals("Wipe Dining Table", wipeTableChore.getChoreName());
        assertEquals("Wipe down the dining table and chairs to remove dust and stains.", wipeTableChore.getChoreDescription());
        assertEquals(FunctionalSpaceType.DINING_ROOM, wipeTableChore.getFunctionalSpaceType());
        assertEquals(4, wipeTableChore.getPoints());

        Chore vacuumFloorChore = chores.get(1);
        assertEquals("Vacuum/Mop Floor", vacuumFloorChore.getChoreName());
        assertEquals("Clean the floor under and around the dining area.", vacuumFloorChore.getChoreDescription());
    }

    /**
     * Tests the persistence of default chores for a dining room space.
     * Verifies that all chores are saved through the ChoreMapper.
     */
    @Test
    public void createDefaultChores_ShouldPersistAllDefaultChores() {
        // Arrange
        Integer spaceId = 1;

        // Act
        diningRoomChoreTemplate.createDefaultChores(spaceId);

        // Assert
        verify(choreMapper, times(4)).insert(any(Chore.class));

        // Validate specific interactions with the choreMapper
        verify(choreMapper).insert(argThat(chore ->
                chore.getChoreName().equals("Wipe Dining Table") &&
                        chore.getChoreDescription().equals("Wipe down the dining table and chairs to remove dust and stains.")
        ));
        verify(choreMapper).insert(argThat(chore ->
                chore.getChoreName().equals("Vacuum/Mop Floor") &&
                        chore.getFunctionalSpaceType() == FunctionalSpaceType.DINING_ROOM
        ));
    }

    /**
     * Tests the retrieval of the functional space type for the dining room template.
     * Verifies that the correct FunctionalSpaceType is returned.
     */
    @Test
    public void getFunctionalSpaceType_ShouldReturnDiningRoom() {
        // Act
        FunctionalSpaceType type = diningRoomChoreTemplate.getFunctionalSpaceType();

        // Assert
        assertEquals(FunctionalSpaceType.DINING_ROOM, type);
    }
}
