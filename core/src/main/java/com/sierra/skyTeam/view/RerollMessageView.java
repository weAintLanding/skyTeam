package com.sierra.skyTeam.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RerollMessageView {
    Sprite rerollMessage;
    boolean isVisible;
    boolean isPilot;
    public RerollMessageView() {
        this.rerollMessage = new Sprite(new Texture("rerollAvailable.png"));
        rerollMessage.setScale(0.37F);
        this.isVisible = false;
    }

    public void setPosition(float x, float y) {
        this.rerollMessage.setPosition(x, y);
    }

    public void setVisibility(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void draw(SpriteBatch batch) {
        rerollMessage.draw(batch);
    }
}
