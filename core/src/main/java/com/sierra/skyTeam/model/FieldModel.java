package com.sierra.skyTeam.model;

import com.sierra.skyTeam.view.FieldView;

public class FieldModel {
    private Dice placedDice;
    private int diceValue;
    private boolean isOccupied;
    private boolean pilotOnly;
    private boolean bothPilots;
    private boolean hasSwitch;
    private boolean switchOn;
    private int[] allowedValues;
    private FieldModel previousField;

    public FieldModel(boolean pilotOnly, boolean bothPilots, boolean hasSwitch, int[] allowedValues) {
        this.pilotOnly = pilotOnly;
        this.bothPilots = bothPilots;
        this.hasSwitch = hasSwitch;
        this.allowedValues = allowedValues;
        this.switchOn = false;
        this.isOccupied = false;
    }

    public boolean canPlaceDice (boolean isPilot) {
        if (pilotOnly && isPilot) return false;
        if (!pilotOnly && !isPilot) return false;
        return !bothPilots;
    }

    public boolean isDiceAllowed (int diceValue,boolean isPilot) {
        if(allowedValues == null){
            System.out.println("Dice Not Allowed.");
            return false;
        }
        if (canPlaceDice(isPilot)) {
            System.out.println("Player not allowed to place dice in this field.");
            return false;
        }

        if (previousField != null && !previousField.switchOn) {
            System.out.println("Previous field must be activated first.");
            return false;
        }

        for(int allowedValue : allowedValues){
            if(diceValue == allowedValue){
                this.diceValue = diceValue;
                System.out.println("Dice Allowed.");
                toggleSwitch();
                return true;
            }
        }
        return false;
    }

    public boolean placeDice(Dice dice, boolean isPilot, FieldView fieldView) {
        if (canPlaceDice(isPilot)) {
            return false;
        }
        this.placedDice = dice;
        this.isOccupied = true;

        float centerX = fieldView.getBounds().x + (fieldView.getBounds().width - placedDice.getDiceSprite().getWidth()) / 2;
        float centerY = fieldView.getBounds().y + (fieldView.getBounds().height - placedDice.getDiceSprite().getHeight()) / 2;
        placedDice.getDiceSprite().setPosition(centerX, centerY);

        toggleSwitch();
        return true;
    }

    public void removeDice() {
        this.placedDice = null;
        this.isOccupied = false;
    }

    public Dice getPlacedDice() {
        return placedDice;
    }

    public boolean hasDice() {
        return placedDice != null;
    }

    public void toggleSwitch() {
        if (hasSwitch && !switchOn) {
            switchOn = true;
        }
    }

    public boolean hasSwitch() {
        return hasSwitch;
    }

    public boolean getIsSwitchOn() {
        return switchOn;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setPreviousField(FieldModel previousField) {
        this.previousField = previousField;
    }
}
