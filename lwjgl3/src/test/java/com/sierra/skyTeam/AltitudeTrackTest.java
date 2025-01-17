package com.sierra.skyTeam;

import com.sierra.skyTeam.model.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AltitudeTrackTest {
    private GameModel mockGameModel;
    private AltitudeTrack altitudeTrack;
    
    @Before
    public void setUp() {
        mockGameModel = mock(GameModel.class);
        altitudeTrack = new AltitudeTrack(mockGameModel);
    }

    @Test
    public void testInitialAltitude() {
        // Test initial altitude is 6000 at index -1
        altitudeTrack.descend();
        assertEquals(6000, altitudeTrack.getCurrentAltitude());
    }

    @Test 
    public void testDescendAltitude() {
        // Test descending changes altitude correctly
        altitudeTrack.descend();
        altitudeTrack.descend();
        assertEquals(5000, altitudeTrack.getCurrentAltitude());
        verify(mockGameModel).setRerollsAvailable();
    }

    @Test
    public void testDescendToBottom() {
        // Test descending to ground level
        for(int i = 0; i < 7; i++) {
            altitudeTrack.descend();
        }
        assertEquals(0, altitudeTrack.getCurrentAltitude());
    }

    @Test
    public void testRerollTokens() {
        // Test reroll tokens are given at correct altitudes
        altitudeTrack.descend(); // At 6000ft
        verify(mockGameModel).setRerollsAvailable();
        assertEquals(1, altitudeTrack.getRerollTokens());
        
        altitudeTrack.descend(); // At 5000ft
        assertEquals(0, altitudeTrack.getRerollTokens());
    }
}