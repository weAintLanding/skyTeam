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

public class EngineTest {

    private Engine engine;
    private Airplane mockAirplane;
    private ApproachTrackModel mockTrackManager;
    private MainGame mockMainGame;
    private GameModel mockGameModel;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize mocks
        mockAirplane = mock(Airplane.class);
        mockTrackManager = mock(ApproachTrackModel.class);
        mockMainGame = mock(MainGame.class);
        mockGameModel = mock(GameModel.class);

        // Initialize Engine with mocked Airplane
        engine = new Engine(mockAirplane);

        // Set up mockAirplane behavior
        when(mockAirplane.getGame()).thenReturn(mockGameModel);
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
    public void testInitialPlaneLandedStatus() {
        // Ensure plane is not landed initially
        assertFalse(engine.isPlaneLanded());
    }

    @Test
    public void testMovePlaneNoMove() {
        // Test plane does not move if engine sum is less than or equal to blue aero marker
        int pilotValue = 2;
        int copilotValue = 2; // engineSum = 4 <= blueAeroMarker (4)

        // Assume initial approach position is 0
        when(mockAirplane.getApproachPosition()).thenReturn(0);

        // Call movePlane
        engine.movePlane(pilotValue, copilotValue, mockTrackManager, mockMainGame);

        // Verify no movement
        verify(mockTrackManager, never()).updateTrackBy1();
        verify(mockTrackManager, never()).updateTrackBy2();
        verify(mockAirplane, never()).setApproachPosition(anyInt());
        verify(mockMainGame, never()).setScreen(any(CrashScreen.class));
    }

    @Test
    public void testMovePlaneMoveOnePosition_Successful() {
        // Test plane moves 1 position if engine sum is greater than blue aero marker but <= orange aero marker
        int pilotValue = 3;
        int copilotValue = 2; // engineSum = 5 > blueAeroMarker (4) and <= orangeAeroMarker (8)

        when(mockAirplane.getApproachPosition()).thenReturn(3);
        when(mockGameModel.checkCrashMove(1)).thenReturn(false);

        // Call movePlane
        engine.movePlane(pilotValue, copilotValue, mockTrackManager, mockMainGame);

        // Verify track update and position change
        verify(mockTrackManager).updateTrackBy1();
        verify(mockAirplane).setApproachPosition(4);
        verify(mockMainGame, never()).setScreen(any(CrashScreen.class));
    }



    @Test
    public void testMovePlaneMoveTwoPositions_Successful() {
        // Test plane moves 2 positions if engine sum is greater than orange aero marker
        int pilotValue = 5;
        int copilotValue = 4; // engineSum = 9 > orangeAeroMarker (8)

        when(mockAirplane.getApproachPosition()).thenReturn(2);
        when(mockGameModel.checkCrashMove(2)).thenReturn(false);

        // Call movePlane
        engine.movePlane(pilotValue, copilotValue, mockTrackManager, mockMainGame);

        // Verify track update and position change
        verify(mockTrackManager).updateTrackBy2();
        verify(mockAirplane).setApproachPosition(4);
        verify(mockMainGame, never()).setScreen(any(CrashScreen.class));
    }

 
    

    @Test
    public void testLandPlaneSuccessfulLanding() {
        // Test successful landing when engine sum is within redBrakeMarker
        int pilotValue = 0;
        int copilotValue = 1; // engineSum = 1 <= redBrakeMarker (1)

        // Call landPlane
        engine.landPlane(pilotValue, copilotValue, mockMainGame);

        // Verify plane is landed and crash screen is not set
        assertTrue(engine.isPlaneLanded());
        verify(mockMainGame, never()).setScreen(any(CrashScreen.class));
    }
}

