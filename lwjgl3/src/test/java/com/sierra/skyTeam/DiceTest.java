package com.sierra.skyTeam;
import com.sierra.skyTeam.model.Dice;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DiceTest {

    private Dice dice;

    @Before
    public void setUp() {
        dice = new Dice(); // Initialize with a starting value
    }


     //to ensure the dice is initialized with a valid value between 1 and 6.

    @Test
    public void testDiceInitialization() {
        int initialValue = dice.getDiceValue();
        assertTrue("Dice value should be between 1 and 6 upon initialization", initialValue >= 1 && initialValue <= 6);
    }

    // to ensure the reroll method generates a valid dice value.
    @Test
    public void testRerollGeneratesValidValue() {
        dice.reroll();
        int newValue = dice.getDiceValue();
        assertTrue("Dice value after reroll should be between 1 and 6", newValue >= 1 && newValue <= 6);
    }


     //to ensure the setDiceValue method correctly updates the dice value.

    @Test
    public void testSetDiceValue() {
        dice.setDiceValue(4);
        assertEquals("Dice value should be set to 4", 4, dice.getDiceValue());

        dice.setDiceValue(1);
        assertEquals("Dice value should be set to 1", 1, dice.getDiceValue());

        dice.setDiceValue(6);
        assertEquals("Dice value should be set to 6", 6, dice.getDiceValue());
    }


     //to ensure the isPlaced flag behaves as expected.

    @Test
    public void testIsPlacedFlag() {
        assertFalse("Dice should not be placed initially", dice.isPlaced());

        dice.setPlaced(true);
        assertTrue("Dice should be placed after calling setPlaced(true)", dice.isPlaced());

        dice.setPlaced(false);
        assertFalse("Dice should not be placed after calling setPlaced(false)", dice.isPlaced());
    }


     //Test to ensure reroll actually changes the dice value in most cases.

    @Test
    public void testRerollChangesValue() {
        int initialValue = dice.getDiceValue();
        dice.reroll();

        // Since reroll is random, there's a small chance the value won't change.
        // This test ensures it changes most of the time.
        int newValue = dice.getDiceValue();
        assertNotEquals("Dice value should change after reroll (most of the time)", initialValue, newValue);
    }
}
