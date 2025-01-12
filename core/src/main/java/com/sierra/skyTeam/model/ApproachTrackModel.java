package com.sierra.skyTeam.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.sierra.skyTeam.view.ApproachAirplaneView;
import com.sierra.skyTeam.view.Track;

public class ApproachTrackModel {
    Track approachTrack;
    ApproachAirplaneView[] airplaneViews;
    boolean [] isAirplaneRemoved;
    private int currentPosition;
    float[] yPositions = {94, 165, 236, 307, 378, 449, 521};
    float approachX = 78;
    float approachCurrentY;
    float offset = 60;

    public ApproachTrackModel(){
        airplaneViews = new ApproachAirplaneView[9];
        isAirplaneRemoved = new boolean[9];
        for (int i = 0; i < airplaneViews.length; i++) {
            airplaneViews[i] = new ApproachAirplaneView();
            isAirplaneRemoved[i] = false;
        }
        setAirplanes();
        currentPosition = 0;
        approachTrack = new Track(new Sprite(new Texture("approachTracker.png")), airplaneViews);
        approachCurrentY = yPositions[currentPosition];
        approachTrack.setTrackerPosition(approachX, approachCurrentY);
    }
    //will be used for throttle logic
    public void updateTrackBy1(){
        int newPosition = currentPosition + 1;
        if(newPosition >= 0 && newPosition < yPositions.length) {
            approachCurrentY = yPositions[newPosition];
            currentPosition = newPosition;
            approachTrack.updateIndex(newPosition, approachX, approachCurrentY);
        } else {
            System.out.println("Invalid index");
        }
    }

    public void updateTrackBy2(){
        int newPosition = currentPosition + 2;
        if(newPosition >= 0 && newPosition < yPositions.length) {
            approachCurrentY = yPositions[newPosition];
            currentPosition = newPosition;
            approachTrack.updateIndex(currentPosition, approachX, approachCurrentY);
        } else {
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
        int adjustedPos = trackPos - 1;

        if (adjustedPos >= yPositions.length) {
            adjustedPos = yPositions.length - 1;
            System.out.println("Value exceeds track length. Adjusted to removing airplanes on the last field");
        }

        float yPos = yPositions[adjustedPos] + offset;
        for(int i = 0; i < airplaneViews.length; i++){
            if(airplaneViews[i].getY() == yPos && !isAirplaneRemoved[i]){
                isAirplaneRemoved[i] = true;
                System.out.println("airplane removed");
                return;
            }
        }
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void draw(SpriteBatch batch) {
        approachTrack.draw(batch);
        for(int i = 0; i < airplaneViews.length; i++){
            if(!isAirplaneRemoved[i]){
                airplaneViews[i].draw(batch);
            }
        }
    }
}
