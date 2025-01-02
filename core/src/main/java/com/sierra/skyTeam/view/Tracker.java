package com.sierra.skyTeam.view;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Tracker {
    private final Sprite trackerSprite;
    int index;
    float scale = 0.37F;

    public Tracker (Sprite trackerSprite){
        this.trackerSprite = trackerSprite;
        this.index = 0;
        this.trackerSprite.setScale(scale);
    }

    public void setTrackerPosition(float x, float y){
        if(index == 0) {
            trackerSprite.setPosition(x, y);
        }
    }

    public void updateIndex(int newIndex, float x, float y){
        this.index = newIndex;
        trackerSprite.setPosition(x, y);
    }

    public void draw (SpriteBatch batch){
        trackerSprite.draw(batch);
    }
}
