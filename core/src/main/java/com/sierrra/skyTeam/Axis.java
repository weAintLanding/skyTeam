package com.sierrra.skyTeam;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Axis {
    private int axisValue;
    private Sprite axis;

    public Axis() {
        this.axisValue = 0;
        Texture texture = new Texture("Axis.png");
        axis = new Sprite(texture);
        axis.setPosition(525, 410);
        axis.setScale(0.6F);
        axis.setOriginCenter();
    }

    public void calcAxis(int pilotAxisValue, int copilotAxisValue){
        // idk some thing for fadhil
    }

    public void setAxisValue(int axisValue) {
        this.axisValue = axisValue;
        rotateAxis();
    }

    public void rotateAxis() {
        float animationSpeed = 2f;
        float deltaTime = Gdx.graphics.getDeltaTime();
        float targetAngle;
        if (Math.abs(axisValue) == 3){
            targetAngle = -axisValue * 25F;
        } else targetAngle = -axisValue * 27F;
        float currentAngle = axis.getRotation();
        float angleDifference = targetAngle - currentAngle;
        if(Math.abs(angleDifference) > 1F){
            axis.setRotation(currentAngle+angleDifference*animationSpeed*deltaTime);
        }else {
            axis.setRotation(targetAngle);
        }
    }

    public void render(SpriteBatch batch) {
        axis.draw(batch);
    }
}
