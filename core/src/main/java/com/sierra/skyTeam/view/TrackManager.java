package com.sierra.skyTeam.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class TrackManager {
    Track approachTrack;
    Track altitudeTrack;
    ApproachAirplaneView[] airplaneViews;
    boolean [] isAirplaneRemoved;
    float[] yPositions = {94, 165, 236, 307, 378, 449, 521};
    float approachX = 78;
    float altitudeX = 750;
    float approachCurrentY, altitudeCurrentY;
    float offset = 60;

    public TrackManager(){
        airplaneViews = new ApproachAirplaneView[9];
        isAirplaneRemoved = new boolean[9];
        for (int i = 0; i < airplaneViews.length; i++) {
            airplaneViews[i] = new ApproachAirplaneView();
            isAirplaneRemoved[i] = false;
        }
        setAirplanes();
        approachTrack = new Track(new Sprite(new Texture("approachTracker.png")), airplaneViews);
        altitudeTrack = new Track(new Sprite(new Texture("altitudeTracker.png")));
        approachCurrentY = yPositions[0];
        altitudeCurrentY = yPositions[0];
        approachTrack.setTrackerPosition(approachX, approachCurrentY);
        altitudeTrack.setTrackerPosition(altitudeX, altitudeCurrentY);
    }

    public void updateTrackerPosition(String tracker, int newIndex){
        switch(tracker.toLowerCase()){
            case "approach":
                if(newIndex >= 0 && newIndex < yPositions.length) {
                        approachCurrentY = yPositions[newIndex];
                        approachTrack.updateIndex(newIndex, approachX, approachCurrentY);
                } else {
                    System.out.println("Invalid index");
                }
                break;
            case "altitude":
                if(newIndex >= 0 && newIndex < yPositions.length) {
                    altitudeCurrentY = yPositions[newIndex];
                    altitudeTrack.updateIndex(newIndex, altitudeX, altitudeCurrentY);
                } else {
                    System.out.println("Invalid index");
                }
                break;
            default:
                System.out.println("Invalid index");
        }
    }

    public void setAirplanes() {
        airplaneViews[0].drawAirplaneAt(265, yPositions[2]+offset);
        airplaneViews[1].drawAirplaneAt(250, yPositions[3]+offset);
        airplaneViews[2].drawAirplaneAt(290, yPositions[3]+offset);
        airplaneViews[3].drawAirplaneAt(265, yPositions[4]+offset);
        airplaneViews[4].drawAirplaneAt(230, yPositions[5]+offset);
        airplaneViews[5].drawAirplaneAt(265, yPositions[5]+offset);
        airplaneViews[6].drawAirplaneAt(300, yPositions[5]+offset);
        airplaneViews[7].drawAirplaneAt(250, yPositions[6]+offset);
        airplaneViews[8].drawAirplaneAt(290, yPositions[6]+offset);
        for(ApproachAirplaneView airplaneView : airplaneViews){
            float randomRotation = MathUtils.random(0f, 360f);
            airplaneView.setRotation(randomRotation);
        }
    }

    //this method should not be run in draw/render or else all sprites at the position will be removed
    //or we add a boolean to remove only one
    public void removeAirplane(int trackPos) {
        float yPos = yPositions[trackPos - 1] + offset;
        for(int i = 0; i < airplaneViews.length; i++){
            if(airplaneViews[i].getY() == yPos && !isAirplaneRemoved[i]){
                isAirplaneRemoved[i] = true;
                System.out.println("airplane removed");
                return;
            }
        }
    }

    public void draw(SpriteBatch batch){
        approachTrack.draw(batch);
        altitudeTrack.draw(batch);
        for(int i = 0; i < airplaneViews.length; i++){
            if(!isAirplaneRemoved[i]){
                airplaneViews[i].draw(batch);
            }
        }
    }
}
