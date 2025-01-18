package com.sierra.skyTeam.view;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Stellt eine Tracker-Sprite, die bewegt, um den Fortschritt anzuzeigen.
 * Die Klasse ermöglicht das Setzen und Aktualisieren der Position des Trackers.
 */
public class Track {
    private final Sprite trackerSprite;
    ApproachAirplaneView[] airplaneView;
    int index;
    float scale = 0.37F;

    /**
     * Konstruktor für die Track-Klasse.
     *
     * @param trackerSprite die Sprite, die den Tracker darstellt
     */
    public Track(Sprite trackerSprite){
        this.trackerSprite = trackerSprite;
        this.index = 0;
        this.trackerSprite.setScale(scale);
    }

    /**
     * Konstruktor für die Track-Klasse mit einer Liste von Flugzeugen-Sprites.
     *
     * @param trackerSprite die Sprite, die den Tracker darstellt
     * @param airplaneView eine Liste von Flugzeugen-Sprites, die im Track verfolgt werden
     */
    public Track(Sprite trackerSprite, ApproachAirplaneView[] airplaneView){
        this.trackerSprite = trackerSprite;
        this.airplaneView = airplaneView;
        this.index = 0;
        this.trackerSprite.setScale(scale);
    }

    /**
     * Setzt die Position des Trackers.
     *
     * @param x die x-Koordinate der neuen Position
     * @param y die y-Koordinate der neuen Position
     */
    public void setTrackerPosition(float x, float y){
        if(index == 0) {
            trackerSprite.setPosition(x, y);
        }
    }

    /**
     * Aktualisiert den Index des Trackers und setzt die Position des Trackers basierend auf dem neuen Index.
     *
     * @param newIndex der neue Index
     * @param x die neue x-Koordinate der Position
     * @param y die neue y-Koordinate der Position
     */
    public void updateIndex(int newIndex, float x, float y){
        this.index = newIndex;
        trackerSprite.setPosition(x, y);
    }

    /**
     * Zeichnet den Tracker mit dem angegebenen {@code SpriteBatch}.
     *
     * @param batch Der {@code SpriteBatch}, der zum Rendern verwendet wird.
     */
    public void draw (SpriteBatch batch){
        trackerSprite.draw(batch);
    }
}
