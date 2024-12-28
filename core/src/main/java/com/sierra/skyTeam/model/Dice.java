package com.sierra.skyTeam.model;

import java.util.Random;

public class Dice {
    private int diceValue;
    Random random = new Random();

    public Dice() {
        random = new Random();
        this.diceValue = random.nextInt(6)+1;
    }

    public int getDiceValue() {
        return diceValue;
    }
    public void reroll() {
        this.diceValue = random.nextInt(6) + 1;
    }

    void setDiceValue(int newDiceValue){
        this.diceValue = newDiceValue;
    }
}
