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
 * Unit tests for the GarageChoreTemplate class.
 */
public class GarageChoreTemplateTest {

    private GarageChoreTemplate garageChoreTemplate;

    private ChoreMapper choreMapper;

    /**
     * Sets up the test environment before each test.
     * Initializes the mocked ChoreMapper and the GarageChoreTemplate instance under test.
     */
    @BeforeEach
    public void setUp() {
        // Use a mock ChoreMapper
        choreMapper = mock(ChoreMapper.class);
        garageChoreTemplate = new GarageChoreTemplate(choreMapper);
    }

    /**
     * Tests the retrieval of default chores for a garage space.
     * Verifies the correct number of chores and their content.
     */
    @Test
    public void getDefaultChores_ShouldReturnExpectedChores() {
        // Arrange
        Integer spaceId = 1;

        // Act
        List<Chore> chores = garageChoreTemplate.getDefaultChores(spaceId);

        // Assert
        assertEquals(4, chores.size());

        // Validate specific chore properties
        Chore sweepFloorChore = chores.get(0);
        assertEquals("Sweep the Floor", sweepFloorChore.getChoreName());
        assertEquals("Sweep the garage floor to remove dust and debris.", sweepFloorChore.getChoreDescription());
        assertEquals(FunctionalSpaceType.GARAGE, sweepFloorChore.getFunctionalSpaceType());
        assertEquals(6, sweepFloorChore.getPoints());

        Chore sortToolsChore = chores.get(1);
        assertEquals("Sort Tools", sortToolsChore.getChoreName());
        assertEquals("Organize tools and place them in their designated storage areas.", sortToolsChore.getChoreDescription());
    }

    /**
     * Tests the persistence of default chores for a garage space.
     * Verifies that all chores are saved through the ChoreMapper.
     */
    @Test
    public void createDefaultChores_ShouldPersistAllDefaultChores() {
        // Arrange
        Integer spaceId = 1;

        // Act
        garageChoreTemplate.createDefaultChores(spaceId);

        // Assert
        verify(choreMapper, times(4)).insert(any(Chore.class));

        // Validate specific interactions with the choreMapper
        verify(choreMapper).insert(argThat(chore ->
                chore.getChoreName().equals("Sweep the Floor") &&
                        chore.getChoreDescription().equals("Sweep the garage floor to remove dust and debris.")
        ));
        verify(choreMapper).insert(argThat(chore ->
                chore.getChoreName().equals("Sort Tools") &&
                        chore.getFunctionalSpaceType() == FunctionalSpaceType.GARAGE
        ));
    }

    /**
     * Tests the retrieval of the functional space type for the garage template.
     * Verifies that the correct FunctionalSpaceType is returned.
     */
    @Test
    public void getFunctionalSpaceType_ShouldReturnGarage() {
        // Act
        FunctionalSpaceType type = garageChoreTemplate.getFunctionalSpaceType();

        // Assert
        assertEquals(FunctionalSpaceType.GARAGE, type);
    }
}
