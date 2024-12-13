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
 * Unit tests for the HomeOfficeChoreTemplate class.
 */
public class HomeOfficeChoreTemplateTest {

    private HomeOfficeChoreTemplate homeOfficeChoreTemplate;

    private ChoreMapper choreMapper;

    /**
     * Sets up the test environment before each test.
     * Initializes the mocked ChoreMapper and the HomeofficeChoreTemplate instance under test.
     */
    @BeforeEach
    public void setUp() {
        // Use a mock ChoreMapper
        choreMapper = mock(ChoreMapper.class);
        homeOfficeChoreTemplate = new HomeOfficeChoreTemplate(choreMapper);
    }

    /**
     * Tests the retrieval of default chores for a home office space.
     * Verifies the correct number of chores and their content.
     */
    @Test
    public void getDefaultChores_ShouldReturnExpectedChores() {
        // Arrange
        Integer spaceId = 1;

        // Act
        List<Chore> chores = homeOfficeChoreTemplate.getDefaultChores(spaceId);

        // Assert
        assertEquals(4, chores.size());

        // Validate specific chore properties
        Chore organizeDeskChore = chores.get(0);
        assertEquals("Organize Desk", organizeDeskChore.getChoreName());
        assertEquals("Clear clutter, arrange stationery, and wipe the desk surface.", organizeDeskChore.getChoreDescription());
        assertEquals(FunctionalSpaceType.HOME_OFFICE, organizeDeskChore.getFunctionalSpaceType());
        assertEquals(4, organizeDeskChore.getPoints());

        Chore dustElectronicsChore = chores.get(1);
        assertEquals("Dust Electronics", dustElectronicsChore.getChoreName());
        assertEquals("Dust and clean monitors, keyboards, and other electronics.", dustElectronicsChore.getChoreDescription());
    }

    /**
     * Tests the persistence of default chores for a home office space.
     * Verifies that all chores are saved through the ChoreMapper.
     */
    @Test
    public void createDefaultChores_ShouldPersistAllDefaultChores() {
        // Arrange
        Integer spaceId = 1;

        // Act
        homeOfficeChoreTemplate.createDefaultChores(spaceId);

        // Assert
        verify(choreMapper, times(4)).insert(any(Chore.class));

        // Validate specific interactions with the choreMapper
        verify(choreMapper).insert(argThat(chore ->
                chore.getChoreName().equals("Organize Desk") &&
                        chore.getChoreDescription().equals("Clear clutter, arrange stationery, and wipe the desk surface.")
        ));
        verify(choreMapper).insert(argThat(chore ->
                chore.getChoreName().equals("Dust Electronics") &&
                        chore.getFunctionalSpaceType() == FunctionalSpaceType.HOME_OFFICE
        ));
    }

    /**
     * Tests the retrieval of the functional space type for the home office template.
     * Verifies that the correct FunctionalSpaceType is returned.
     */
    @Test
    public void getFunctionalSpaceType_ShouldReturnHomeOffice() {
        // Act
        FunctionalSpaceType type = homeOfficeChoreTemplate.getFunctionalSpaceType();

        // Assert
        assertEquals(FunctionalSpaceType.HOME_OFFICE, type);
    }
}