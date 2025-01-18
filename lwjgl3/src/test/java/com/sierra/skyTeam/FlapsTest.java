package com.sierra.skyTeam;

import com.sierra.skyTeam.model.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class FlapsTest {

    private Flaps flaps;
    private Airplane mockAirplane;
    private Engine mockEngine;

    @Before
    public void setUp() {
        // Mock the Airplane and Engine
        mockAirplane = mock(Airplane.class);
        mockEngine = mock(Engine.class);

        // Configure mockAirplane to return mockEngine
        when(mockAirplane.getEngine()).thenReturn(mockEngine);
        
        // Instantiate Flaps with the mock airplane
        flaps = new Flaps(mockAirplane);

        // By default, Engine's marker is 8 
        when(mockEngine.getOrangeAeroMarker()).thenReturn(8);
    }

    @Test
    public void testSetFlapFieldsTrue_Valid() {
        int index = 0;    // expects dice values 1 or 2 in flapConstraints
        int diceValue = 1; 

        flaps.setFlapFieldsTrue(index, diceValue);


        // Check that flap field is now switched on
        assertEquals(1, flaps.getActivatedFlapFields());
        // Verify the engine's AeroMarker was incremented
        verify(mockEngine).setOrangeAeroMarker(9); 
    }

    @Test
    public void testSetFlapFieldsTrue_InvalidDiceValue() {
        int index = 0;  
        int invalidDiceValue = 3; // flapConstraints[0] = {1,2}, so 3 is invalid

        flaps.setFlapFieldsTrue(index, invalidDiceValue);

        // Should not increment activation count
        assertEquals(0, flaps.getActivatedFlapFields());
        // Should not set orangeAeroMarker
        verify(mockEngine, never()).setOrangeAeroMarker(anyInt());
    }

    @Test
    public void testSetFlapFieldsTrue_AlreadyActivated() {
        int index = 0;
        int diceValue = 1; 
        flaps.setFlapFieldsTrue(index, diceValue);

        flaps.setFlapFieldsTrue(index, diceValue);

        assertEquals(1, flaps.getActivatedFlapFields());
        verify(mockEngine).setOrangeAeroMarker(9); // only once
    }

    @Test
    public void testSetFlapFieldsTrue_NextField_RequiresPreviousActivated() {
        int index0 = 0;
        int index1 = 1; 
        int diceValue0 = 2; 
        int diceValue1 = 3; 

        flaps.setFlapFieldsTrue(index0, diceValue0);
        assertEquals(1, flaps.getActivatedFlapFields());

        flaps.setFlapFieldsTrue(index1, diceValue1);

        assertEquals(2, flaps.getActivatedFlapFields());
        verify(mockEngine, times(2)).setOrangeAeroMarker(anyInt());
    }



    @Test
    public void testDisplayFlapFields_NoExceptionThrown() {
        // Just ensure no runtime errors
        try {
            flaps.displayFlapFields();
        } catch (Exception e) {
            fail("displayFlapFields() threw an exception: " + e.getMessage());
        }
    }
}