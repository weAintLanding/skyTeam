package com.sierrra.skyTeam.View;

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
    private int diceValue;
    private int [] allowedValues;

    public Field (float x, float y, boolean hasSwitch){
        this.bounds = new Rectangle(x, y, fieldSize, fieldSize);
        this.hasSwitch = hasSwitch;
        this.switchOn = false;

        if(hasSwitch){
            switchImg = new Sprite(new Texture("switch.png"));
            switchImg.setScale(0.6F);
            switchImg.setPosition(x+14, y-45);
        }
    }

    //field that only allows specific values
    public Field (float x, float y, boolean hasSwitch, int[] allowedValues){
        this.bounds = new Rectangle(x, y, fieldSize, fieldSize);
        this.hasSwitch = hasSwitch;
        this.switchOn = false;
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
        if(hasSwitch && !switchOn){
            this.switchOn = true;
            int offset = 24;
            switchImg.setX(switchImg.getX() - offset);
        }
    }

    public Field getClickedField(float touchX, float touchY) {
        if (bounds.contains(touchX, touchY)) {
            return this;
        }
        return null;
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
