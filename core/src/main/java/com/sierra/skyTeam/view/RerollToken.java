package com.sierra.skyTeam.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class RerollToken {
    Sprite rerollTokenSprite;
    boolean onBoard;
    boolean used;

    public RerollToken () {
        rerollTokenSprite = new Sprite(new Texture("rerollToken.png"));
        rerollTokenSprite.setScale(0.3F);
        this.onBoard = false;
        this.used = false;
    }

    public void setPosition(float x, float y){
        rerollTokenSprite.setPosition(x, y);
    }

    public boolean isOnBoard() {
        return onBoard;
    }

    public void setOnBoard(boolean onBoard) {
        this.onBoard = onBoard;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed() {
        this.used = true;
    }

    public void draw(SpriteBatch batch) {
        if(!used) {
            rerollTokenSprite.draw(batch);
        }
    }
}
