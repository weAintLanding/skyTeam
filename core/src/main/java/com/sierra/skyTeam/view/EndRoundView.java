package com.sierra.skyTeam.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class EndRoundView {
    private final Sprite endRound;
    private boolean isVisible;

    public EndRoundView() {
        this.endRound = new Sprite(new Texture("endRound.png"));
        endRound.setPosition(736, -40);
        endRound.setScale(0.38F);
        this.isVisible = true;
    }

    public void setVisibility(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public Rectangle getBoundingRectangle() {
        return endRound.getBoundingRectangle();
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void draw(SpriteBatch batch) {
        if(isVisible) {
            endRound.draw(batch);
        }
    }
}
