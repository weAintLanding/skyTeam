package com.sierra.skyTeam.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.sierra.skyTeam.view.ApproachAirplaneView;
import com.sierra.skyTeam.view.Track;

/**
 * Die Klasse {@code ApproachTrackModel} enthält Informationen für die Darstellung und Logik der ApproachTrack.
 * Sie enthält Informationen über die Position des Trackers, Flugzeuge und die Interaktion mit der ApproachTrack.
 */
public class ApproachTrackModel {
    Track approachTrack;
    ApproachAirplaneView[] airplaneViews;
    boolean [] isAirplaneRemoved;
    private int currentPosition;
    float[] yPositions = {94, 165, 236, 307, 378, 449, 521};
    float approachX = 78;
    float approachCurrentY;
    float offset = 60;

    /**
     * Konstruktor für die Klasse {@code ApproachTrackModel}.
     * Initialisiert den ApproachTrack-Tracker, die Positionen und die Flugzeugansichten.
     */

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

    /**
     * Aktualisiert den Tracker um eine Position nach vorne.
     * Diese Methode wird für Logiken wie Throttle{@link Engine} verwendet.
     */
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

    /**
     * Aktualisiert den Tracker um zwei Positionen nach vorne.
     * Diese Methode wird ebenfalls für Logiken wie Throttle{@link Engine} verwendet.
     */
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

    /**
     * Setzt die Flugzeuge an ihre Startpositionen auf der ApproachTrack.
     */
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

    /**
     * Entfernt ein Flugzeug von einer bestimmten Position der ApproachTrack.
     *
     * @param trackPos Die Position der ApproachTrack.
     * @param gameModel Das GameModel, das die Logik der Annäherungsspur verwaltet.
     */
    /*this method should not be run in draw/render or else all sprites at the position will be removed
    or we add a boolean to remove only one*/
    public void removeAirplane(int trackPos, GameModel gameModel) {
        int adjustedPos = trackPos - 1;

        if (adjustedPos >= yPositions.length) {
            return;
        }

        float yPos = yPositions[adjustedPos] + offset;
        for(int i = 0; i < airplaneViews.length; i++){
            if(airplaneViews[i].getY() == yPos && !isAirplaneRemoved[i]){
                isAirplaneRemoved[i] = true;
                gameModel.getApproachTrack().removeAirplane(trackPos-1);
                return;
            }
        }
    }

    /**
     * Gibt die aktuelle Position des Trackers zurück.
     *
     * @return Die aktuelle Position des Trackers.
     */
    public int getCurrentPosition() {
        return currentPosition;
    }

    /**
     * Zeichnet den ApproachTrack-Tracker und die Flugzeuge auf den Bildschirm.
     *
     * @param batch Der SpriteBatch, der für das Rendering verwendet wird.
     */
    public void draw(SpriteBatch batch) {
        approachTrack.draw(batch);
        for(int i = 0; i < airplaneViews.length; i++){
            if(!isAirplaneRemoved[i]){
                airplaneViews[i].draw(batch);
            }
        }
    }
}
