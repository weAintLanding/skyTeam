package com.sierra.skyTeam.model;

public class Field {
    private final String fieldType;
    private Players owner;
    private int diceValue;
    private boolean occupied;
    private boolean switchLight;

    public Field(String fieldType) {
        this.fieldType = fieldType;
        this.occupied = false;
        this.diceValue = -1;
        this.switchLight = false;
    }
    public void setOwner(Players player) {

        this.owner = player;

    }
    public boolean placeDice(int diceValue) {
        if (occupied) {
            return false;
        }
        this.diceValue = diceValue;
        this.occupied = true;
        return true;
    }

    public void resetField() {
        this.diceValue = -1;
        this.occupied = false;
        //System.out.println(fieldType + " field has been reset.");
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void displayState() {
        System.out.println("fieldView Type: " + fieldType);
        System.out.println("Owner: " + owner);
        System.out.println("Occupied: " + occupied);
        if (occupied) {
            System.out.println("Dice Value: " + diceValue);
        } else {
            System.out.println("No dice placed yet.");
        }
        if (switchLight) {
            System.out.println("Turned On.");
        }else{
            System.out.println("Not Turned On.");
        }
    }
    public boolean isSwitchedOn(){
        return switchLight;
    }

    public void setSwitchOn(){
        this.switchLight = true;
    }

    public String getFieldType() {
        return fieldType;
    }

    public Players getOwner() {
        return owner;
    }

    public int getDiceValue() {
        return diceValue;
    }
}
