package com.sierra.skyTeam.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sierra.skyTeam.view.Track;

public class AltitudeTrackModel {
    Track altitudeTrack;
    float[] yPositions = {94, 165, 236, 307, 378, 449, 521};
    float altitudeX = 750;
    float altitudeCurrentY;

    public AltitudeTrackModel(){
        altitudeTrack = new Track(new Sprite(new Texture("altitudeTracker.png")));
        altitudeTrack.setTrackerPosition(altitudeX, altitudeCurrentY);
    }

    public void setStartingPosition() {
        altitudeTrack.updateIndex(0, altitudeX, yPositions[0]);
    }

    public void updateTrackerPosition(int newIndex){
        if(newIndex >= 0 && newIndex < yPositions.length) {
            altitudeCurrentY = yPositions[newIndex];
            altitudeTrack.updateIndex(newIndex, altitudeX, altitudeCurrentY);
        } else {
            System.out.println("Invalid index");
        }
    }
    public void draw(SpriteBatch batch){
        altitudeTrack.draw(batch);
    }
}
