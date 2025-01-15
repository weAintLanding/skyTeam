package com.sierra.skyTeam.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class RerollButtonView {
    private final Sprite rerollButton;
    private boolean isVisible;

    public RerollButtonView() {
        this.rerollButton = new Sprite(new Texture("rerollButton.png"));
        rerollButton.setPosition(100, -40);
        rerollButton.setScale(0.38F);
        this.isVisible = false;
    }

    public void setVisibility(boolean isVisible) {
        this.isVisible = isVisible;
        if(isVisible) {
            rerollButton.setColor(1, 1,1,1);
        } else {
            rerollButton.setColor(1, 1, 1, 0.7F);
        }
    }

    public Rectangle getBoundingRectangle() {
        return rerollButton.getBoundingRectangle();
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void draw(SpriteBatch batch) {
        rerollButton.draw(batch);
    }
}
