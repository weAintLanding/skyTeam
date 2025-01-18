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

        when(mockAirplane.getEngine()).thenReturn(mockEngine);

        brakes = new Brakes(mockAirplane);
    }

    @Test
    public void testBrakeFieldActivation_ValidDiceValue() {
        int validDiceValue = 2;

        boolean result = brakes.setBrakeFieldsTrue(0, validDiceValue);

        assertTrue(result);
        assertEquals(1, brakes.getActivatedBrakeFields());
        verify(mockEngine).setRedBrakeMarker(1);
    }

    @Test
    public void testBrakeFieldActivation_InvalidDiceValue() {
        int invalidDiceValue = 5;

        boolean result = brakes.setBrakeFieldsTrue(0, invalidDiceValue);

        assertFalse(result);
        assertEquals(0, brakes.getActivatedBrakeFields());
        verify(mockEngine, never()).setRedBrakeMarker(anyInt());
    }

    @Test
    public void testBrakeFieldActivation_InvalidIndex() {
        boolean result = brakes.setBrakeFieldsTrue(-1, 2);

        assertFalse(result);
        assertEquals(0, brakes.getActivatedBrakeFields());
    }

    @Test
    public void testBrakeFieldActivation_WithoutPreviousField() {
        int validDiceValue = 4;

        boolean result = brakes.setBrakeFieldsTrue(1, validDiceValue); // Trying to activate second field without first field

        assertFalse(result);
        assertEquals(0, brakes.getActivatedBrakeFields());
        verify(mockEngine, never()).setRedBrakeMarker(anyInt());
    }

    @Test
    public void testBrakeFieldActivation_Sequence() {
        int firstDiceValue = 2;
        int secondDiceValue = 4;
        int thirdDiceValue = 6;

        boolean firstResult = brakes.setBrakeFieldsTrue(0, firstDiceValue);
        boolean secondResult = brakes.setBrakeFieldsTrue(1, secondDiceValue);
        boolean thirdResult = brakes.setBrakeFieldsTrue(2, thirdDiceValue);

        assertTrue(firstResult);
        assertTrue(secondResult);
        assertTrue(thirdResult);
        assertEquals(3, brakes.getActivatedBrakeFields());

        
    }

    @Test
    public void testBrakeFieldDisplay() {
        brakes.setBrakeFieldsTrue(0, 2);
        brakes.setBrakeFieldsTrue(1, 4);
        brakes.displayBrakeFields();

    }

    @Test
    public void testBrakeFieldClear() {
        brakes.setBrakeFieldsTrue(0, 2);
        brakes.setBrakeFieldsTrue(1, 4);

        brakes.clearField();

        assertEquals(2, brakes.getActivatedBrakeFields());
    }

    @Test
    public void testBrakeFieldActivation_DuplicateActivation() {
        int validDiceValue = 2;

        brakes.setBrakeFieldsTrue(0, validDiceValue);
        boolean duplicateResult = brakes.setBrakeFieldsTrue(0, validDiceValue);

        assertFalse(duplicateResult); // Second activation should fail
        assertEquals(1, brakes.getActivatedBrakeFields());
    }
}
