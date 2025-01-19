package com.sierra.skyTeam.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sierra.skyTeam.view.Track;

/**
 * Die Klasse {@code AltitudeTrackModel} enthält Informationen für die visuelle Darstellung des AltitudeTrack-Trackers.
 * Sie enthält die Position und das Rendering des Trackers auf dem Bildschirm.
 */
public class AltitudeTrackModel {
    Track altitudeTrack;
    float[] yPositions = {94, 165, 236, 307, 378, 449, 521};
    float altitudeX = 750;
    float altitudeCurrentY;

    /**
     * Konstruktor für die Klasse {@code AltitudeTrackModel}.
     * Initialisiert den Tracker mit einer entsprechenden Textur.
     */
    public AltitudeTrackModel(){
        altitudeTrack = new Track(new Sprite(new Texture("altitudeTracker.png")));
        altitudeTrack.setTrackerPosition(altitudeX, altitudeCurrentY);
    }

    /**
     * Setzt die Startposition der Tracker auf die erste Höhenstufe.
     */
    public void setStartingPosition() {
        altitudeTrack.updateIndex(0, altitudeX, yPositions[0]);
    }

    /**
     * Aktualisiert die Position der Tracker basierend auf einem neuen Index.
     *
     * @param newIndex Der neue Index, der die gewünschte Höhenstufe darstellt.
     *                 Muss im Bereich von 0 bis zur Länge der y-Positionen liegen.
     */
    public void updateTrackerPosition(int newIndex){
        if(newIndex >= 0 && newIndex < yPositions.length) {
            altitudeCurrentY = yPositions[newIndex];
            altitudeTrack.updateIndex(newIndex, altitudeX, altitudeCurrentY);
        } else {
            System.out.println("Invalid index");
        }
    }

    /**
     * Zeichnet den Tracker auf den Bildschirm.
     *
     * @param batch Der SpriteBatch, der für das Rendering verwendet wird.
     */
    public void draw(SpriteBatch batch){
        altitudeTrack.draw(batch);
    }
}
