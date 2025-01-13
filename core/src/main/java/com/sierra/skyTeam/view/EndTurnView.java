package com.sierra.skyTeam.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class EndTurnView {
    private final Sprite endTurn;
    private boolean isVisible;

    public EndTurnView(float x, float y) {
        this.endTurn = new Sprite(new Texture("endTurn.png"));
        endTurn.setPosition(x, y);
        endTurn.setScale(0.38F);
        this.isVisible = true;
    }

    public void setVisibility(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public Rectangle getBoundingRectangle() {
        return endTurn.getBoundingRectangle();
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void draw(SpriteBatch batch) {
        endTurn.draw(batch);
    }
}
