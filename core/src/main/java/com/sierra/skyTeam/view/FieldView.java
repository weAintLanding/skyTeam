package com.sierra.skyTeam.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.sierra.skyTeam.model.Dice;

public class FieldView {
    private Rectangle bounds;
    private int fieldSize = 45;
    Sprite switchImg;
    boolean hasSwitch;
    boolean switchOn;
    boolean isOccupied;
    private int diceValue;
    private int [] allowedValues;
    Dice dice = new Dice(2);
    private boolean pilotOnly;
    private boolean bothPilots;
    private FieldView previousField;

    public FieldView(float x, float y, boolean pilotOnly, boolean bothPilots){
        this.bounds = new Rectangle(x, y, fieldSize, fieldSize);
        this.hasSwitch = false;
        this.isOccupied = false;
        this.pilotOnly = pilotOnly;
        this.bothPilots = bothPilots;
    }

    //field that only allows specific values
    public FieldView(float x, float y, boolean hasSwitch, boolean pilotOnly, int[] allowedValues){
        this.bounds = new Rectangle(x, y, fieldSize, fieldSize);
        this.hasSwitch = hasSwitch;
        this.switchOn = false;
        this.isOccupied = false;
        this.allowedValues = allowedValues;
        this.pilotOnly = pilotOnly;

        if(hasSwitch){
            switchImg = new Sprite(new Texture("switch.png"));
            switchImg.setScale(0.6F);
            switchImg.setPosition(x+14, y-45);
        }
    }

    public void setPreviousField(FieldView previousField) {
        this.previousField = previousField;
    }

    public boolean canPlaceDice(boolean isPilot) {
        if (pilotOnly && isPilot) {
            return false;
        }
        if (!pilotOnly && !isPilot) {
            return false;
        }
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

    public void toggleSwitch() {
        if (hasSwitch && !switchOn && !isOccupied) {
            System.out.println("toggle switch");
            this.switchOn = true;
            int offset = 24;
            switchImg.setX(switchImg.getX() - offset);
        }
    }

//    public fieldView getClickedField(float touchX, float touchY) {
//        if (bounds.contains(touchX, touchY)) {
//            return this;
//        }
//        return null;
//    }

    public boolean placeDiceOnField (Sprite selectedDice, boolean isPilot) {
        if (canPlaceDice(isPilot)) {
            System.out.println("Player not allowed to place dice on this field.");
            return false;
        }
        float centerX = bounds.x + (bounds.width - selectedDice.getWidth()) / 2;
        float centerY = bounds.y + (bounds.height - selectedDice.getHeight()) / 2;
        selectedDice.setPosition(centerX, centerY);
        this.isOccupied = true;
        return true;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void switchRenderer(SpriteBatch batch) {
        if(hasSwitch && switchImg != null){
            switchImg.draw(batch);
        }
    }

    public boolean hasSwitch() {
        return hasSwitch;
    }

    public void renderer(com.badlogic.gdx.graphics.glutils.ShapeRenderer shapeRenderer) {
        shapeRenderer.rect(bounds.x, bounds.y, bounds.width, bounds.height);
    }

    public boolean isSwitchOn() {
        return switchOn;
    }
}
