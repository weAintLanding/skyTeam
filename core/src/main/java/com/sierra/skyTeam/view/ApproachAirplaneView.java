package com.sierra.skyTeam.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ApproachAirplaneView {
    Sprite airplaneSprite;

    public ApproachAirplaneView(){
        airplaneSprite = new Sprite(new Texture("airplane.png"));
        airplaneSprite.setScale(0.37F);
    }

    public void drawAirplaneAt(float x, float y){
        airplaneSprite.setPosition(x, y);
    }

    public void setRotation(float rotation) {
        airplaneSprite.setRotation(rotation);
    }

    public float getY() {
        return airplaneSprite.getY();
    }

    public void draw(SpriteBatch batch) {
        airplaneSprite.draw(batch);
    }
}
