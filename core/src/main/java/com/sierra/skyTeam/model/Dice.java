package com.sierra.skyTeam.model;

import java.util.Random;

public class Dice {
    private int diceValue;
    private final Random random;
    private boolean isPlaced;

    public Dice() {
        this.random = new Random();
        this.diceValue = random.nextInt(6) + 1;
        this.isPlaced = false;
    }

    public int getDiceValue() {
        return diceValue;
    }

    public void reroll() {
        this.diceValue = random.nextInt(6) + 1;
    }

    public void setDiceValue(int newDiceValue) {
        this.diceValue = newDiceValue;
    }

    public boolean isPlaced() {
        return isPlaced;
    }
    public void setPlaced(boolean placed) {
        isPlaced = placed;
    }
}
