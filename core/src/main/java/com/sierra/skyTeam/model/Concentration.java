package com.sierra.skyTeam.model;

public class Concentration {
    private int coffeeAvailable = 0;

    public int getCoffeeAvailable() {
        return coffeeAvailable;
    }

    public boolean checkCoffeeAvailable() {
        return coffeeAvailable > 0;
    }

    public boolean addCoffee() {
        int maxCoffee = 3;
        if(coffeeAvailable < maxCoffee) {
            coffeeAvailable++;
            return true;
        } else {
            return false;
        }
    }

    public boolean useCoffee(Dice dice, int coffeeAmount) {
        int diceValue = dice.getDiceValue();
        int modifiedDiceValue = diceValue + coffeeAmount;

        if(modifiedDiceValue >= 1 && modifiedDiceValue <= 6) {
            coffeeAvailable -= Math.abs(coffeeAmount);
            dice.setDiceValue(modifiedDiceValue);
            return true;
        } else {
            return false;
        }
    }
}
