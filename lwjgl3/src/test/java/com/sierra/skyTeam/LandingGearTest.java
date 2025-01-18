package com.sierra.skyTeam;

import com.sierra.skyTeam.model.*;
import com.sierra.skyTeam.screens.CrashScreen;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedConstruction;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
    
    public class LandingGearTest {
    
        private LandingGear landingGear;
        private Airplane mockAirplane;
        private Engine mockEngine;
    
        @Before
        public void setUp() {
            mockAirplane = mock(Airplane.class);
            mockEngine = mock(Engine.class);
    
            when(mockAirplane.getEngine()).thenReturn(mockEngine);
    
            landingGear = new LandingGear(mockAirplane);
        }
    
        @Test
        public void testConstructor_InitialState() {
            assertEquals(0, landingGear.getActivatedLandingGearFields());
            verifyNoInteractions(mockEngine);
        }
    
        @Test
        public void testSetLandingGearFieldsTrue_ValidDiceValue() {
            int index = 0;
            int validDice = 1;
    
            boolean result = landingGear.setLandingGearFieldsTrue(index, validDice);
    
            assertTrue(result);
            assertEquals(1, landingGear.getActivatedLandingGearFields());
    
            // Should increment the engine's blue marker
            verify(mockEngine).setBlueAeroMarker(anyInt());
        }
    
        @Test
        public void testSetLandingGearFieldsTrue_InvalidDiceValue() {
            int index = 0;
            int invalidDice = 3;
    
            boolean result = landingGear.setLandingGearFieldsTrue(index, invalidDice);
    
            assertFalse(result);
            assertEquals(0, landingGear.getActivatedLandingGearFields());
            verify(mockEngine, never()).setBlueAeroMarker(anyInt());
        }
    
        @Test
        public void testSetLandingGearFieldsTrue_AlreadyActivated() {
            int index = 0;
            landingGear.setLandingGearFieldsTrue(index, 1);
            assertEquals(1, landingGear.getActivatedLandingGearFields());
    
            boolean result = landingGear.setLandingGearFieldsTrue(index, 1);
    
            assertFalse(result); 
            assertEquals(1, landingGear.getActivatedLandingGearFields());
            verify(mockEngine).setBlueAeroMarker(anyInt());
        }
    
        @Test
        public void testGetActivatedLandingGearFields() {
            landingGear.setLandingGearFieldsTrue(0, 1);
            landingGear.setLandingGearFieldsTrue(1, 3);
    
            assertEquals(2, landingGear.getActivatedLandingGearFields());
        }
  
        @Test
        public void testDisplayFlapFields_NoException() {
            try {
                landingGear.displayFlapFields();
            } catch (Exception e) {
                fail("displayFlapFields() threw an exception: " + e.getMessage());
            }
        }
    }


