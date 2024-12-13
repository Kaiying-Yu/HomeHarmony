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
 * Unit tests for the BalconyChoreTemplate class.
 */
public class BalconyChoreTemplateTest {

    private BalconyChoreTemplate balconyTemplate;

    private ChoreMapper choreMapper;

    /**
     * Sets up the test environment before each test.
     * Initializes the mocked ChoreMapper and the BalconyChoreTemplate instance under test.
     */
    @BeforeEach
    public void setUp() {
        // Use a mock ChoreMapper
        choreMapper = mock(ChoreMapper.class);
        balconyTemplate = new BalconyChoreTemplate(choreMapper);
    }

    /**
     * Tests the retrieval of default chores for a balcony space.
     * Verifies the correct number of chores and their content.
     */
    @Test
    public void getDefaultChores_ShouldReturnExpectedChores() {
        // Arrange
        Integer spaceId = 1;

        // Act
        List<Chore> chores = balconyTemplate.getDefaultChores(spaceId);

        // Assert
        assertEquals(4, chores.size());

        // Validate specific chore properties
        Chore sweepFloorChore = chores.get(0);
        assertEquals("Sweep the Floor", sweepFloorChore.getChoreName());
        assertEquals("Sweep dirt, leaves, or debris from the balcony floor.", sweepFloorChore.getChoreDescription());
        assertEquals(FunctionalSpaceType.BALCONY, sweepFloorChore.getFunctionalSpaceType());
        assertEquals(4, sweepFloorChore.getPoints());

        Chore cleanRailingsChore = chores.get(1);
        assertEquals("Clean Railings", cleanRailingsChore.getChoreName());
    }

    /**
     * Tests the persistence of default chores for a balcony space.
     * Verifies that all chores are saved through the ChoreMapper.
     */
    @Test
    public void createDefaultChores_ShouldPersistAllDefaultChores() {
        // Arrange
        Integer spaceId = 1;

        // Act
        balconyTemplate.createDefaultChores(spaceId);

        // Assert
        verify(choreMapper, times(4)).insert(any(Chore.class));

        // Validate specific interactions with the choreMapper
        verify(choreMapper).insert(argThat(chore ->
                chore.getChoreName().equals("Sweep the Floor") &&
                        chore.getChoreDescription().equals("Sweep dirt, leaves, or debris from the balcony floor.")
        ));
        verify(choreMapper).insert(argThat(chore ->
                chore.getChoreName().equals("Water Plants") &&
                        chore.getFunctionalSpaceType() == FunctionalSpaceType.BALCONY
        ));
    }

    /**
     * Tests the retrieval of the functional space type for the balcony template.
     * Verifies that the correct FunctionalSpaceType is returned.
     */
    @Test
    public void getFunctionalSpaceType_ShouldReturnBalcony() {
        // Act
        FunctionalSpaceType type = balconyTemplate.getFunctionalSpaceType();

        // Assert
        assertEquals(FunctionalSpaceType.BALCONY, type);
    }
}
