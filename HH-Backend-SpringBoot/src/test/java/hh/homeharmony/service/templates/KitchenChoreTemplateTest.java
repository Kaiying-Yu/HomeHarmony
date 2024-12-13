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
 * Unit tests for the KitchenChoreTemplate class.
 */
public class KitchenChoreTemplateTest {

    private KitchenChoreTemplate kitchenChoreTemplate;

    private ChoreMapper choreMapper;

    /**
     * Sets up the test environment before each test.
     * Initializes the mocked ChoreMapper and the KitchenChoreTemplate instance under test.
     */
    @BeforeEach
    public void setUp() {
        // Use a mock ChoreMapper
        choreMapper = mock(ChoreMapper.class);
        kitchenChoreTemplate = new KitchenChoreTemplate(choreMapper);
    }

    /**
     * Tests the retrieval of default chores for a kitchen space.
     * Verifies the correct number of chores and their content.
     */
    @Test
    public void getDefaultChores_ShouldReturnExpectedChores() {
        // Arrange
        Integer spaceId = 1;

        // Act
        List<Chore> chores = kitchenChoreTemplate.getDefaultChores(spaceId);

        // Assert
        assertEquals(4, chores.size());

        // Validate specific chore properties
        Chore washDishesChore = chores.get(0);
        assertEquals("Wash Dishes", washDishesChore.getChoreName());
        assertEquals("Wash all dirty dishes, utensils, and cooking pots.", washDishesChore.getChoreDescription());
        assertEquals(FunctionalSpaceType.KITCHEN, washDishesChore.getFunctionalSpaceType());
        assertEquals(5, washDishesChore.getPoints());

        Chore cleanCountertopsChore = chores.get(1);
        assertEquals("Clean Countertops", cleanCountertopsChore.getChoreName());
        assertEquals("Wipe down all countertops and remove stains or spills.", cleanCountertopsChore.getChoreDescription());
    }

    /**
     * Tests the persistence of default chores for a kitchen space.
     * Verifies that all chores are saved through the ChoreMapper.
     */
    @Test
    public void createDefaultChores_ShouldPersistAllDefaultChores() {
        // Arrange
        Integer spaceId = 1;

        // Act
        kitchenChoreTemplate.createDefaultChores(spaceId);

        // Assert
        verify(choreMapper, times(4)).insert(any(Chore.class));

        // Validate specific interactions with the choreMapper
        verify(choreMapper).insert(argThat(chore ->
                chore.getChoreName().equals("Wash Dishes") &&
                        chore.getChoreDescription().equals("Wash all dirty dishes, utensils, and cooking pots.")
        ));
        verify(choreMapper).insert(argThat(chore ->
                chore.getChoreName().equals("Clean Countertops") &&
                        chore.getFunctionalSpaceType() == FunctionalSpaceType.KITCHEN
        ));
    }

    /**
     * Tests the retrieval of the functional space type for the kitchen template.
     * Verifies that the correct FunctionalSpaceType is returned.
     */
    @Test
    public void getFunctionalSpaceType_ShouldReturnKitchen() {
        // Act
        FunctionalSpaceType type = kitchenChoreTemplate.getFunctionalSpaceType();

        // Assert
        assertEquals(FunctionalSpaceType.KITCHEN, type);
    }
}