package com.sierra.skyTeam.model;

import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.Random;

public class Dice {
    private int diceValue;
    private final Random random;
    private boolean isPlaced;
    private Sprite diceSprite;
    private boolean selectedForReroll;

    public Dice() {
        this.random = new Random();
        this.diceValue = random.nextInt(6) + 1;
        this.isPlaced = false;
        this.selectedForReroll = false;
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

    public void setDiceSprite(Sprite diceSprite){
        this.diceSprite = diceSprite;
    }

    public Sprite getDiceSprite() {
        return diceSprite;
    }

    public boolean isPlaced() {
        return isPlaced;
    }

    public void setPlaced(boolean placed) {
        isPlaced = placed;
    }

    public boolean isSelectedForReroll() {
        return selectedForReroll;
    }

    public void setSelectedForReroll(boolean selectedForReroll) {
        this.selectedForReroll = selectedForReroll;
    }
}
