package com.sierra.skyTeam;

import com.sierra.skyTeam.model.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class ConcentrationTest {
    private Concentration concentration;
    private Dice mockDice;

    @Before
    public void setUp() {
        concentration = new Concentration();
        mockDice = mock(Dice.class);
    }

    @Test
    public void testGetCoffeeAvailable() {
        // Test initial coffee available is 0
        assertEquals(0, concentration.getCoffeeAvailable());
    }

    @Test
    public void testCheckCoffeeAvailableWhenNoCoffee() {
        // Test checkCoffeeAvailable returns false when no coffee is available
        assertFalse(concentration.checkCoffeeAvailable());
    }

    @Test
    public void testCheckCoffeeAvailableWhenCoffeeAvailable() {
        // Test checkCoffeeAvailable returns true when coffee is available
        concentration.addCoffee();
        assertTrue(concentration.checkCoffeeAvailable());
    }

    @Test
    public void testAddCoffeeSuccess() {
        // Test adding coffee when below maximum limit
        assertTrue(concentration.addCoffee());
        assertEquals(1, concentration.getCoffeeAvailable());
    }

    @Test
    public void testAddCoffeeMaxLimit() {
        // Test adding coffee up to maximum limit
        concentration.addCoffee();
        concentration.addCoffee();
        concentration.addCoffee();
        assertFalse(concentration.addCoffee());
        assertEquals(3, concentration.getCoffeeAvailable());
    }

    @Test
    public void testUseCoffeeSuccess() {
        // Test using coffee successfully modifies dice value
        concentration.addCoffee();
        when(mockDice.getDiceValue()).thenReturn(3);
        boolean result = concentration.useCoffee(mockDice, 1);
        assertTrue(result);
        assertEquals(0, concentration.getCoffeeAvailable());
        verify(mockDice).setDiceValue(4);
    }

    @Test
    public void testUseCoffeeModifiedValueOutOfBoundsLow() {
        // Test using coffee with modified dice value below 1
        concentration.addCoffee();
        when(mockDice.getDiceValue()).thenReturn(1);
        boolean result = concentration.useCoffee(mockDice, -1);
        assertFalse(result);
        assertEquals(1, concentration.getCoffeeAvailable());
        verify(mockDice, never()).setDiceValue(anyInt());
    }

    @Test
    public void testUseCoffeeModifiedValueOutOfBoundsHigh() {
        // Test using coffee with modified dice value above 6
        concentration.addCoffee();
        when(mockDice.getDiceValue()).thenReturn(6);
        boolean result = concentration.useCoffee(mockDice, 1);
        assertFalse(result);
        assertEquals(1, concentration.getCoffeeAvailable());
        verify(mockDice, never()).setDiceValue(anyInt());
    }
    
}
