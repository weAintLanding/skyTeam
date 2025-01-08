package com.sierra.skyTeam.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.sierra.skyTeam.model.FieldModel;

public class FieldView {
    private final Rectangle bounds;
    private Sprite switchImg;
    private final FieldModel fieldModel;
    private boolean switchMoved;

    public FieldView(float x, float y, FieldModel fieldModel){
        int fieldSize = 45;
        this.bounds = new Rectangle(x, y, fieldSize, fieldSize);
        this.fieldModel = fieldModel;

        if(fieldModel.hasSwitch()){
            switchImg = new Sprite(new Texture("switch.png"));
            switchImg.setScale(0.6F);
            switchImg.setPosition(x+14, y-45);
        }
    }

    public void switchRenderer(SpriteBatch batch) {
        if(fieldModel.hasSwitch()){
            switchImg.draw(batch);
        }
        updateSwitch();
    }

    public void updateSwitch() {
        if (fieldModel.getIsSwitchOn() && !switchMoved) {
            switchImg.setX(switchImg.getX() - 24);
            switchMoved = true;
        } else if (!fieldModel.getIsSwitchOn() && switchMoved) {
            switchImg.setX(switchImg.getX() + 24);
            switchMoved = false;
        }
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public FieldModel getFieldModel() {
        return fieldModel;
    }

    public void renderer(com.badlogic.gdx.graphics.glutils.ShapeRenderer shapeRenderer) {
        shapeRenderer.rect(bounds.x, bounds.y, bounds.width, bounds.height);
    }
}
