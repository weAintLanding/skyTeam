package com.sierra.skyTeam.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Field {
    private Rectangle bounds;
    private int fieldSize = 45;
    Sprite switchImg;
    boolean hasSwitch;
    boolean switchOn;
    boolean isOccupied;
    private int diceValue;
    private int [] allowedValues;
    Dice dice = new Dice();

    public Field (float x, float y){
        this.bounds = new Rectangle(x, y, fieldSize, fieldSize);
        this.switchOn = false;
        this.isOccupied = false;
    }

    //field that only allows specific values
    public Field (float x, float y, boolean hasSwitch, int[] allowedValues){
        this.bounds = new Rectangle(x, y, fieldSize, fieldSize);
        this.hasSwitch = hasSwitch;
        this.switchOn = false;
        this.isOccupied = false;
        this.allowedValues = allowedValues;

        if(hasSwitch){
            switchImg = new Sprite(new Texture("switch.png"));
            switchImg.setScale(0.6F);
            switchImg.setPosition(x+14, y-45);
        }
    }

    public boolean isDiceAllowed (int diceValue) {
        if(allowedValues == null){
            return false;
        }
        for(int allowedValue : allowedValues){
            if(diceValue == allowedValue){
                this.diceValue = diceValue;
                toggleSwitch();
                return true;
            }
        }
        return false;
    }

    public void toggleSwitch() {
        if(hasSwitch && !switchOn && !isOccupied){
            this.switchOn = true;
            int offset = 24;
            switchImg.setX(switchImg.getX() - offset);
        }
    }

//    public Field getClickedField(float touchX, float touchY) {
//        if (bounds.contains(touchX, touchY)) {
//            return this;
//        }
//        return null;
//    }

    public void placeDiceOnField (Sprite selectedDice) {
        float centerX = bounds.x + (bounds.width - selectedDice.getWidth()) / 2;
        float centerY = bounds.y + (bounds.height - selectedDice.getHeight()) / 2;
        selectedDice.setPosition(centerX, centerY);
        this.isOccupied = true;
    }

    public Rectangle getBounds() {
        return bounds;
    }
    public void switchRenderer(SpriteBatch batch) {
        if(hasSwitch && switchImg != null){
            switchImg.draw(batch);
        }
    }
    public void renderer(com.badlogic.gdx.graphics.glutils.ShapeRenderer shapeRenderer) {
        shapeRenderer.rect(bounds.x, bounds.y, bounds.width, bounds.height);
    }
}
