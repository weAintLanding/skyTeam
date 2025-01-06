package com.sierra.skyTeam.view;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Track {
    private final Sprite trackerSprite;
    ApproachAirplaneView[] airplaneView;
    int index;
    float scale = 0.37F;

    public Track(Sprite trackerSprite){
        this.trackerSprite = trackerSprite;
        this.index = 0;
        this.trackerSprite.setScale(scale);
    }

    public Track(Sprite trackerSprite, ApproachAirplaneView[] airplaneView){
        this.trackerSprite = trackerSprite;
        this.airplaneView = airplaneView;
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
