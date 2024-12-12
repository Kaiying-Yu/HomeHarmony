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
 * Unit tests for the BedroomChoreTemplate class.
 */
public class BedroomChoreTemplateTest {

    private BedroomChoreTemplate bedroomChoreTemplate;

    private ChoreMapper choreMapper;

    @BeforeEach
    public void setUp() {
        // Use a mock ChoreMapper
        choreMapper = mock(ChoreMapper.class);
        bedroomChoreTemplate = new BedroomChoreTemplate(choreMapper);
    }

    /**
     * Tests the retrieval of default chores for a bedroom space.
     * Verifies the correct number of chores and their content.
     */
    @Test
    public void getDefaultChores_ShouldReturnExpectedChores() {
        // Arrange
        Integer spaceId = 1;

        // Act
        List<Chore> chores = bedroomChoreTemplate.getDefaultChores(spaceId);

        // Assert
        assertEquals(4, chores.size());

        // Validate specific chore properties
        Chore makeBedChore = chores.get(0);
        assertEquals("Make the Bed", makeBedChore.getChoreName());
        assertEquals("Arrange the bedding neatly, including sheets, blankets, and pillows.", makeBedChore.getChoreDescription());
        assertEquals(FunctionalSpaceType.BEDROOM, makeBedChore.getFunctionalSpaceType());
        assertEquals(3, makeBedChore.getPoints());

        Chore organizeClosetChore = chores.get(1);
        assertEquals("Organize Closet", organizeClosetChore.getChoreName());
        assertEquals("Tidy up the closet by folding clothes and sorting items.", organizeClosetChore.getChoreDescription());
    }

    /**
     * Tests the persistence of default chores for a bedroom space.
     * Verifies that all chores are saved through the ChoreMapper.
     */
    @Test
    public void createDefaultChores_ShouldPersistAllDefaultChores() {
        // Arrange
        Integer spaceId = 1;

        // Act
        bedroomChoreTemplate.createDefaultChores(spaceId);

        // Assert
        verify(choreMapper, times(4)).insert(any(Chore.class));

        // Validate specific interactions with the choreMapper
        verify(choreMapper).insert(argThat(chore ->
                chore.getChoreName().equals("Make the Bed") &&
                        chore.getChoreDescription().equals("Arrange the bedding neatly, including sheets, blankets, and pillows.")
        ));
        verify(choreMapper).insert(argThat(chore ->
                chore.getChoreName().equals("Organize Closet") &&
                        chore.getFunctionalSpaceType() == FunctionalSpaceType.BEDROOM
        ));
    }

    /**
     * Tests the retrieval of the functional space type for the bedroom template.
     * Verifies that the correct FunctionalSpaceType is returned.
     */
    @Test
    public void getFunctionalSpaceType_ShouldReturnBedroom() {
        // Act
        FunctionalSpaceType type = bedroomChoreTemplate.getFunctionalSpaceType();

        // Assert
        assertEquals(FunctionalSpaceType.BEDROOM, type);
    }
}