package com.sierra.skyTeam;

import com.sierra.skyTeam.model.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AirplaneTest {

    private Airplane airplane;
    private GameModel mockGameModel;
    private AxisModel mockAxisModel;
    private Engine mockEngine;
    private Brakes mockBrakes;
    private LandingGear mockLandingGear;
    private Flaps mockFlaps;
    private Concentration mockConcentration;

    @Before
    public void setUp() {
        // Initialize Airplane instance
        airplane = new Airplane();

        mockGameModel = mock(GameModel.class);
        mockAxisModel = mock(AxisModel.class);
        mockEngine = mock(Engine.class);
        mockBrakes = mock(Brakes.class);
        mockLandingGear = mock(LandingGear.class);
        mockFlaps = mock(Flaps.class);
        mockConcentration = mock(Concentration.class);

    }

    @Test
    public void testInitialAltitude() {
        // Checks that initial altitude is set correctly
        assertEquals(6000, airplane.getAltitude());
    }

    @Test
    public void testInitialApproachPosition() {
        // Checks that initial approach position is set correctly
        assertEquals(0, airplane.getApproachPosition());
    }

    @Test
    public void testSetApproachPosition() {
        // Updates the approach position and verify
        airplane.setApproachPosition(3);
        assertEquals(3, airplane.getApproachPosition());
    }

    @Test
    public void testSetGame() {
        // Sets the game model and verify
        airplane.setGame(mockGameModel);
        assertEquals(mockGameModel, airplane.getGame());
    }

    @Test
    public void testGetAxis() {
        // Checks that the axis model is not null
        assertNotNull(airplane.getAxis());
    }

    @Test
    public void testGetEngine() {
        // Checks that the engine object is not null
        assertNotNull(airplane.getEngine());
    }

    @Test
    public void testGetBrakes() {
        // Checks that the brakes object is not null
        assertNotNull(airplane.getBrakes());
    }

    @Test
    public void testGetLandingGear() {
        // Checks that the landing gear object is not null
        assertNotNull(airplane.getLandingGear());
    }

    @Test
    public void testGetFlaps() {
        // Checks that the flaps object is not null
        assertNotNull(airplane.getFlaps());
    }

    @Test
    public void testGetConcentration() {
        // Checks that the concentration object is not null
        assertNotNull(airplane.getConcentration());
    }
}
