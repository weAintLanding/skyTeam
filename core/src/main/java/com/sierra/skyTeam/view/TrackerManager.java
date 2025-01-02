package com.sierra.skyTeam.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TrackerManager {
    Tracker approachTracker;
    Tracker altitudeTracker;
    float[] yPositions = {94, 165, 236, 307, 378, 449, 521};
    float approachX = 78;
    float altitudeX = 750;
    float approachCurrentY, altitudeCurrentY;

    public TrackerManager(){
        approachTracker = new Tracker(new Sprite(new Texture("approachTracker.png")));
        altitudeTracker = new Tracker(new Sprite(new Texture("altitudeTracker.png")));
        approachCurrentY = yPositions[0];
        altitudeCurrentY = yPositions[0];
        approachTracker.setTrackerPosition(approachX, approachCurrentY);
        altitudeTracker.setTrackerPosition(altitudeX, altitudeCurrentY);
    }

    public void updateTrackerPosition(String tracker, int newIndex){
        switch(tracker.toLowerCase()){
            case "approach":
                if(newIndex >= 0 && newIndex < yPositions.length) {
                        approachCurrentY = yPositions[newIndex];
                        approachTracker.updateIndex(newIndex, approachX, approachCurrentY);
                } else {
                    System.out.println("Invalid index");
                }
                break;
            case "altitude":
                if(newIndex >= 0 && newIndex < yPositions.length) {
                    altitudeCurrentY = yPositions[newIndex];
                    altitudeTracker.updateIndex(newIndex, altitudeX, altitudeCurrentY);
                } else {
                    System.out.println("Invalid index");
                }
                break;
            default:
                System.out.println("Invalid index");
        }
    }

    public void draw(SpriteBatch batch){
        approachTracker.draw(batch);
        altitudeTracker.draw(batch);
    }
}
