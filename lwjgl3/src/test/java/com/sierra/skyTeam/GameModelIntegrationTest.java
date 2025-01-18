package com.sierra.skyTeam;

import com.sierra.skyTeam.model.*;
import com.sierra.skyTeam.MainGame;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
public class GameModelIntegrationTest {
    private GameModel gameModel;
    private MainGame mockGame;

    @Before
    public void setUp() {
        mockGame = mock(MainGame.class);
        gameModel = new GameModel(mockGame);
    }



    @Test
    public void testCompleteRound() {
        // Test full round mechanics including dice placement and player switching
        
        // Initial state checks
        assertEquals(gameModel.getPilot(), gameModel.getCurrentPlayer());
        
        // Place dice for pilot
        Dice pilotDice = gameModel.getPilot().getDiceList()[0];
        gameModel.getPilot().setAxis(pilotDice);
        
        // Switch to copilot
        gameModel.switchPlayer();
        assertEquals(gameModel.getCoPilot(), gameModel.getCurrentPlayer());
        
        // Place dice for copilot
        Dice copilotDice = gameModel.getCoPilot().getDiceList()[0];
        gameModel.getCoPilot().setAxis(copilotDice);
        
    }

    @Test 
    public void testRerollMechanic() {
        // Test reroll integration with altitude track and dice
        
        // Initial state
        assertEquals(0, gameModel.getRerollsAvailable());
        
        // Descend to reroll token altitude
        gameModel.getAltitudeTrack().descend();
        
        // Verify reroll token received
        assertTrue(gameModel.getRerollsAvailable() > 0);
        
        // Use reroll
        int[] initialDiceValues = getDiceValues(gameModel.getPilot().getDiceList());
        gameModel.getPilot().rollDice();
        int[] newDiceValues = getDiceValues(gameModel.getPilot().getDiceList());
        
        // Verify dice changed
        assertFalse(java.util.Arrays.equals(initialDiceValues, newDiceValues));
        
        // Verify reroll consumed
    }

    private int[] getDiceValues(Dice[] dice) {
        return java.util.Arrays.stream(dice)
                     .mapToInt(Dice::getDiceValue)
                     .toArray();
    }
    
}
