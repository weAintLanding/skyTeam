package com.sierra.skyTeam.model;

import java.util.ArrayList;
import java.util.Random;
import java.util.List;

public class Players {
    private final GameModel gameModel;
    private final Dice[] diceRolls;
    private Integer axisSlot = null;
    private Integer throttle = null;
    protected int radioSlots;
    protected Field[] radioPlayer;
    Random random = new Random();


    public Players(GameModel gameModel) {
        this.gameModel = gameModel;
        diceRolls = new Dice[4];
        this.rollDice();
    }

    public Airplane getAirplane(){
        return gameModel.getAirplane();
    }

    public void rollDice () {
        for(int i = 0; i < 4; i++){
            diceRolls[i] = new Dice();
        }
    }
    public Dice[] getDiceList() {
        return diceRolls;
    }
    public String getDiceRollsString() {
        List<Integer> diceArray = new ArrayList<>();
        for(Dice dice : diceRolls){
            if(dice != null){
                diceArray.add(dice.getDiceValue());
            }
        }
        return diceArray.toString();
    }

    public boolean isDiceThere (int diceValue){
        for(Dice dice : diceRolls){
            if(dice != null && dice.getDiceValue() == diceValue) return true;
        }
        return false;
    }

    public Dice getDice (int diceValue){
        for(Dice dice : diceRolls){
            if(dice.getDiceValue() == diceValue){
                return dice;
            }
        }
        return null;
    }
    public void removeDice (int diceValue){
        for (Dice dice : diceRolls){
            if (dice != null && dice.getDiceValue() == diceValue){
                dice = null;
                return;
            }
        }
    }
    public void reroll() {
        if (gameModel.getRerollsAvailable() > 0){
            for (int i = 0; i < diceRolls.length; i++) {
                if (diceRolls[i] != null) {
                    int newValue = random.nextInt(6)+1;
                    diceRolls[i].setDiceValue(newValue);
                }
            }
            gameModel.decreaseRerollsAvailable();
        } else {
            System.out.println("No rerolls available.");
        }
    }

    public boolean setAxis(Dice dice){
        int diceValue = dice.getDiceValue();
        if (axisSlot != null) {
            return false;
        }
        this.axisSlot = diceValue;
        removeDice(diceValue);
        return true;
    }
    public Integer getAxis() {
        return axisSlot;
    }
    public boolean isAxis(){
        return axisSlot != null;
    }

    public boolean setThrottle(Dice dice){
        int diceValue = dice.getDiceValue();
        if (throttle != null) {
            return false;
        }
        this.throttle = diceValue;
        removeDice(diceValue);
        return true;
    }
    public Integer getThrottle() {
        return throttle;
    }
    public boolean isThrottle(){
        return throttle != null;
    }

    public boolean setRadio(Dice dice){
        int diceValue = dice.getDiceValue();
        for(Field field: radioPlayer){
            if(!field.isOccupied()){
                field.placeDice(diceValue);
                removeDice(diceValue);
                return true;
            }
        }
        return false;
    }
    public void clearRadio(){
        for(Field field: radioPlayer){
            field.resetField();
        }
    }

    public boolean setCoffee (Dice dice){
        boolean coffeeAdded = this.getAirplane().getConcentration().addCoffee();
        this.removeDice(dice.getDiceValue());
        return coffeeAdded;
    }

    public void clearSlots(){
        this.axisSlot = null;
        this.throttle = null;
    }
}

