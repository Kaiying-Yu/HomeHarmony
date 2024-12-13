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
 * Unit tests for the BathroomChoreTemplate class.
 */
public class BathroomChoreTemplateTest {

    private BathroomChoreTemplate bathroomChoreTemplate;

    private ChoreMapper choreMapper;

    /**
     * Sets up the test environment before each test.
     * Initializes the mocked ChoreMapper and the BathroomChoreTemplate instance under test.
     */
    @BeforeEach
    public void setUp() {
        // Use a mock ChoreMapper
        choreMapper = mock(ChoreMapper.class);
        bathroomChoreTemplate = new BathroomChoreTemplate(choreMapper);
    }

    /**
     * Tests the retrieval of default chores for a bathroom space.
     * Verifies the correct number of chores and their content.
     */
    @Test
    public void getDefaultChores_ShouldReturnExpectedChores() {
        // Arrange
        Integer spaceId = 1;

        // Act
        List<Chore> chores = bathroomChoreTemplate.getDefaultChores(spaceId);

        // Assert
        assertEquals(4, chores.size());

        // Validate specific chore properties
        Chore cleanToiletChore = chores.get(0);
        assertEquals("Clean Toilet", cleanToiletChore.getChoreName());
        assertEquals("Scrub and disinfect the toilet bowl, seat, and exterior.", cleanToiletChore.getChoreDescription());
        assertEquals(FunctionalSpaceType.BATHROOM, cleanToiletChore.getFunctionalSpaceType());
        assertEquals(7, cleanToiletChore.getPoints());

        Chore cleanSinkChore = chores.get(1);
        assertEquals("Clean Sink and Faucet", cleanSinkChore.getChoreName());
        assertEquals("Remove stains, scrub, and polish the sink and faucet.", cleanSinkChore.getChoreDescription());
    }

    /**
     * Tests the persistence of default chores for a bathroom space.
     * Verifies that all chores are saved through the ChoreMapper.
     */
    @Test
    public void createDefaultChores_ShouldPersistAllDefaultChores() {
        // Arrange
        Integer spaceId = 1;

        // Act
        bathroomChoreTemplate.createDefaultChores(spaceId);

        // Assert
        verify(choreMapper, times(4)).insert(any(Chore.class));

        // Validate specific interactions with the choreMapper
        verify(choreMapper).insert(argThat(chore ->
                chore.getChoreName().equals("Clean Toilet") &&
                        chore.getChoreDescription().equals("Scrub and disinfect the toilet bowl, seat, and exterior.")
        ));
        verify(choreMapper).insert(argThat(chore ->
                chore.getChoreName().equals("Clean Sink and Faucet") &&
                        chore.getFunctionalSpaceType() == FunctionalSpaceType.BATHROOM
        ));
    }

    /**
     * Tests the retrieval of the functional space type for the bathroom template.
     * Verifies that the correct FunctionalSpaceType is returned.
     */
    @Test
    public void getFunctionalSpaceType_ShouldReturnBathroom() {
        // Act
        FunctionalSpaceType type = bathroomChoreTemplate.getFunctionalSpaceType();

        // Assert
        assertEquals(FunctionalSpaceType.BATHROOM, type);
    }
}
