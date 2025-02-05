package com.sierra.skyTeam;

import com.sierra.skyTeam.model.Airplane;
import com.sierra.skyTeam.model.AxisModel;
import com.sierra.skyTeam.model.GameModel;
import com.sierra.skyTeam.model.Players;
import com.sierra.skyTeam.screens.CrashScreen;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class AxisModelTest {

    private AxisModel axisModel;

    @Mock
    private Airplane mockAirplane;

    @Mock
    private Players mockPilot;

    @Mock
    private Players mockCopilot;

    @Mock
    private MainGame mockGame;

    @Mock
    private GameModel mockGameModel;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        axisModel = new AxisModel(mockAirplane);

        // Mock the GameModel returned by mockAirplane.getGame()
        when(mockAirplane.getGame()).thenReturn(mockGameModel);
    }

    @Test
    public void testChangeAxisWithoutCrash() {
        // Arrange
        when(mockPilot.getAxis()).thenReturn(1);
        when(mockCopilot.getAxis()).thenReturn(-1);
        when(mockGameModel.checkCrashAxis()).thenReturn(false); // No crash condition

        // Act
        axisModel.changeAxis(mockPilot, mockCopilot, mockGame);

        // Assert
        verify(mockGame, never()).setScreen(any(CrashScreen.class)); // Ensure setScreen is NOT called
    }



    @Test
    public void testGetAxisValue() {
        // Act
        int axisValue = axisModel.getAxisValue();

        // Assert
        assertEquals(0, axisValue);
    }
}
