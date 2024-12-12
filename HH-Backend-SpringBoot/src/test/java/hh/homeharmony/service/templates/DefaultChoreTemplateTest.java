package hh.homeharmony.service.templates;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import hh.homeharmony.mapper.ChoreMapper;
import hh.homeharmony.model.Chore;
import hh.homeharmony.model.ChoreStatus;
import hh.homeharmony.model.FunctionalSpaceType;

/**
 * Unit tests for the DefaultChoreTemplate class using a mock ChoreMapper.
 */
public class DefaultChoreTemplateTest {

    private BalconyChoreTemplate balconyTemplate;

    private ChoreMapper choreMapper;

    @BeforeEach
    public void setUp() {
        // Use a mock ChoreMapper
        choreMapper = mock(ChoreMapper.class);
        balconyTemplate = new BalconyChoreTemplate(choreMapper);
    }

    /**
     * Tests the creation of a single chore using the createChore method.
     * Verifies that the created chore has the expected properties.
     */
    @Test
    public void createChore_ShouldReturnChoreWithCorrectProperties() {
        // Arrange
        Integer spaceId = 1;

        // Act
        Chore chore = balconyTemplate.createChore(
                "Test Chore",
                "Test Description",
                FunctionalSpaceType.BALCONY,
                Duration.ofMinutes(15),
                5,
                spaceId
        );

        // Assert
        assertEquals("Test Chore", chore.getChoreName());
        assertEquals("Test Description", chore.getChoreDescription());
        assertEquals(FunctionalSpaceType.BALCONY, chore.getFunctionalSpaceType());
        assertEquals(Duration.ofMinutes(15), chore.getEstimatedTime());
        assertEquals(5, chore.getPoints());
        assertEquals(ChoreStatus.PENDING, chore.getChoreStatus());
    }

    /**
     * Tests the retrieval of default chores using getDefaultChores.
     * Verifies that the list of chores matches expectations.
     */
    @Test
    public void getDefaultChores_ShouldReturnExpectedChores() {
        // Arrange
        Integer spaceId = 1;

        // Act
        List<Chore> chores = balconyTemplate.getDefaultChores(spaceId);

        // Assert
        assertEquals(4, chores.size());
        assertTrue(chores.stream().anyMatch(chore -> chore.getChoreName().equals("Sweep the Floor")));
        assertTrue(chores.stream().anyMatch(chore -> chore.getChoreName().equals("Clean Railings")));
    }

    /**
     * Tests the persistence of default chores using createDefaultChores.
     * Verifies that the chores are correctly saved through the mock mapper.
     */
    @Test
    public void createDefaultChores_ShouldPersistAllDefaultChores() {
        // Arrange
        Integer spaceId = 1;

        // Act
        balconyTemplate.createDefaultChores(spaceId);

        // Assert
        verify(choreMapper, times(4)).insert(any(Chore.class));

        // Ensure specific chore properties are persisted correctly
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
     * Tests the retrieval of the functional space type.
     * Verifies the correct type is returned.
     */
    @Test
    public void getFunctionalSpaceType_ShouldReturnBalcony() {
        // Act
        FunctionalSpaceType type = balconyTemplate.getFunctionalSpaceType();

        // Assert
        assertEquals(FunctionalSpaceType.BALCONY, type);
    }
}
