package com.sierra.skyTeam;
import com.sierra.skyTeam.model.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ApproachTrackTest {

    private ApproachTrack approachTrack;

    @Before
    public void setUp() {
        approachTrack = new ApproachTrack();
    }

    @Test
    public void testGetLength() {
        // Test that the length of the approach track is 7
        assertEquals(7, approachTrack.getLength());
    }

    @Test
    public void testGetAirplanesAtValidIndex() {
        // Test getting airplane count at valid indices
        assertEquals(0, approachTrack.getAirplanesAt(0));
        assertEquals(0, approachTrack.getAirplanesAt(1));
        assertEquals(1, approachTrack.getAirplanesAt(2));
        assertEquals(2, approachTrack.getAirplanesAt(3));
        assertEquals(1, approachTrack.getAirplanesAt(4));
        assertEquals(3, approachTrack.getAirplanesAt(5));
        assertEquals(2, approachTrack.getAirplanesAt(6));
    }

    @Test
    public void testGetAirplanesAtInvalidIndex() {
        // Test getting airplane count at invalid indices
        assertEquals(-1, approachTrack.getAirplanesAt(-1));
        assertEquals(-1, approachTrack.getAirplanesAt(7));
    }

    @Test
    public void testRemoveAirplaneSuccess() {
        // Test successfully removing an airplane from a position with airplanes
        assertTrue(approachTrack.removeAirplane(2));
        assertEquals(0, approachTrack.getAirplanesAt(2));
    }

    @Test
    public void testRemoveAirplaneFailure() {
        // Test removing an airplane from a position with no airplanes
        assertFalse(approachTrack.removeAirplane(0));
    }

    @Test
    public void testRemoveAirplaneInvalidIndex() {
        // Test removing an airplane from invalid indices
        assertFalse(approachTrack.removeAirplane(-1));
        assertFalse(approachTrack.removeAirplane(7));
    }

    @Test
    public void testHasAirplanesAt() {
        // Test checking if there are airplanes at specific positions
        assertFalse(approachTrack.hasAirplanesAt(0));
        assertFalse(approachTrack.hasAirplanesAt(1));
        assertTrue(approachTrack.hasAirplanesAt(2));
        assertTrue(approachTrack.hasAirplanesAt(3));
        assertTrue(approachTrack.hasAirplanesAt(4));
        assertTrue(approachTrack.hasAirplanesAt(5));
        assertTrue(approachTrack.hasAirplanesAt(6));
    }

    @Test
    public void testHasAirplanesAtInvalidIndex() {
        // Test checking airplanes at invalid indices
        assertFalse(approachTrack.hasAirplanesAt(-1));
        assertFalse(approachTrack.hasAirplanesAt(7));
    }
    
}
