package com.sierra.skyTeam;

import com.sierra.skyTeam.model.Brakes;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.sierra.skyTeam.model.Airplane;
import com.sierra.skyTeam.model.Engine;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BrakesTest {

    private Brakes brakes;

    @Mock
    private Airplane mockAirplane;

    @Mock
    private Engine mockEngine;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Mock the Airplane and its Engine
        when(mockAirplane.getEngine()).thenReturn(mockEngine);

        // Create the Brakes object with the mocked Airplane
        brakes = new Brakes(mockAirplane);
    }

    @Test
    public void testBrakeFieldActivation_ValidDiceValue() {
        // Arrange
        int validDiceValue = 2;

        // Act
        boolean result = brakes.setBrakeFieldsTrue(0, validDiceValue);

        // Assert
        assertTrue(result);
        assertEquals(1, brakes.getActivatedBrakeFields());
        verify(mockEngine).setRedBrakeMarker(1);
    }

    @Test
    public void testBrakeFieldActivation_InvalidDiceValue() {
        // Arrange
        int invalidDiceValue = 5;

        // Act
        boolean result = brakes.setBrakeFieldsTrue(0, invalidDiceValue);

        // Assert
        assertFalse(result);
        assertEquals(0, brakes.getActivatedBrakeFields());
        verify(mockEngine, never()).setRedBrakeMarker(anyInt());
    }

    @Test
    public void testBrakeFieldActivation_InvalidIndex() {
        // Act
        boolean result = brakes.setBrakeFieldsTrue(-1, 2);

        // Assert
        assertFalse(result);
        assertEquals(0, brakes.getActivatedBrakeFields());
    }

    @Test
    public void testBrakeFieldActivation_WithoutPreviousField() {
        // Arrange
        int validDiceValue = 4;

        // Act
        boolean result = brakes.setBrakeFieldsTrue(1, validDiceValue); // Trying to activate second field without first field

        // Assert
        assertFalse(result);
        assertEquals(0, brakes.getActivatedBrakeFields());
        verify(mockEngine, never()).setRedBrakeMarker(anyInt());
    }

    @Test
    public void testBrakeFieldActivation_Sequence() {
        // Arrange
        int firstDiceValue = 2;
        int secondDiceValue = 4;
        int thirdDiceValue = 6;

        // Act
        boolean firstResult = brakes.setBrakeFieldsTrue(0, firstDiceValue);
        boolean secondResult = brakes.setBrakeFieldsTrue(1, secondDiceValue);
        boolean thirdResult = brakes.setBrakeFieldsTrue(2, thirdDiceValue);

        // Assert
        assertTrue(firstResult);
        assertTrue(secondResult);
        assertTrue(thirdResult);
        assertEquals(3, brakes.getActivatedBrakeFields());

        
    }

    @Test
    public void testBrakeFieldDisplay() {
        // Act
        brakes.setBrakeFieldsTrue(0, 2);
        brakes.setBrakeFieldsTrue(1, 4);
        brakes.displayBrakeFields();

        // No assertion for display method, just ensure no exceptions are thrown
    }

    @Test
    public void testBrakeFieldClear() {
        // Arrange
        brakes.setBrakeFieldsTrue(0, 2);
        brakes.setBrakeFieldsTrue(1, 4);

        // Act
        brakes.clearField();

        // Assert
        assertEquals(2, brakes.getActivatedBrakeFields());
    }

    @Test
    public void testBrakeFieldActivation_DuplicateActivation() {
        // Arrange
        int validDiceValue = 2;

        // Act
        brakes.setBrakeFieldsTrue(0, validDiceValue);
        boolean duplicateResult = brakes.setBrakeFieldsTrue(0, validDiceValue);

        // Assert
        assertFalse(duplicateResult); // Second activation should fail
        assertEquals(1, brakes.getActivatedBrakeFields());
    }
}
