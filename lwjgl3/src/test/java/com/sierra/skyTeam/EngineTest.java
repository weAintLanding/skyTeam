package com.sierra.skyTeam;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;


import com.sierra.skyTeam.model.*;
import com.sierra.skyTeam.view.*;


public class EngineTest {
    private Engine engine;
    private Airplane mockAirplane;
    private MainGame mockMainGame;
    private GameModel mockGame;
    

    @Before
    public void setUp() {
        mockAirplane = mock(Airplane.class);
        engine = new Engine(mockAirplane);
        mockGame = mock(GameModel.class);
        mockMainGame = mock(MainGame.class);
        mockAirplane.setGame(mockGame);        
    }

    @Test
    public void testInitialBlueAeroMarker() {
        // Test initial blue aero marker value
        assertEquals(4, engine.getBlueAeroMarker());
    }

    @Test
    public void testSetBlueAeroMarker() {
        // Test setting blue aero marker
        engine.setBlueAeroMarker(5);
        assertEquals(5, engine.getBlueAeroMarker());
    }

    @Test
    public void testInitialOrangeAeroMarker() {
        // Test initial orange aero marker value
        assertEquals(8, engine.getOrangeAeroMarker());
    }

    @Test
    public void testSetOrangeAeroMarker() {
        // Test setting orange aero marker
        engine.setOrangeAeroMarker(9);
        assertEquals(9, engine.getOrangeAeroMarker());
    }

    @Test
    public void testInitialRedBrakeMarker() {
        // Test initial red brake marker value
        assertEquals(1, engine.getRedBrakeMarker());
    }

    @Test
    public void testSetRedBrakeMarker() {
        // Test setting red brake marker
        engine.setRedBrakeMarker(2);
        assertEquals(2, engine.getRedBrakeMarker());
    }

    @Test
    public void testMovePlaneNoMove() {
        // Test plane does not move if engine sum is less than or equal to blue aero marker
        engine.movePlane(3, 1, mock(ApproachTrackModel.class), mock(MainGame.class));
        assertEquals(0, mockAirplane.getApproachPosition());
    }

    @Test
    public void testMovePlaneOneMove() {
        // Test plane moves 1 position if engine sum is less than or equal to orange aero marker
        engine.movePlane(5, 3, mock(ApproachTrackModel.class), mock(MainGame.class));
        assertEquals(1, mockAirplane.getApproachPosition());
    }

    @Test
    public void testMovePlaneTwoMove() {
        // Test plane moves 2 positions if engine sum is greater than orange aero marker
        engine.movePlane(9, 3, mock(ApproachTrackModel.class), mock(MainGame.class));
        assertEquals(2, mockAirplane.getApproachPosition());
    }

    @Test
    public void testMovePlaneCrash() {
        // Test plane crashes if engine sum is greater than orange aero marker and crash move is true
        MainGame mockGame = mock(MainGame.class);
        engine.movePlane(9, 3, mock(ApproachTrackModel.class), mockGame);
        assertEquals(0, mockAirplane.getApproachPosition());
    }

    @Test
    public void testMovePlaneCrashEnd() {
        // Test plane crashes if engine sum is greater than orange aero marker and approach position is at end
        MainGame mockGame = mock(MainGame.class);
        engine.movePlane(9, 3, mock(ApproachTrackModel.class), mockGame);
        assertEquals(0, mockAirplane.getApproachPosition());
    }

}
