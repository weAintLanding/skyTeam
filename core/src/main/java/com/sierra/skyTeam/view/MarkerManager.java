package com.sierra.skyTeam.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sierra.skyTeam.model.Engine;

/**
 * Verwaltet die Marker für das Spiel und aktualisiert ihre Positionen und Zustände basierend auf den Werten.
 * Diese Klasse verwaltet alle Marker und zeichnet sie auf dem Bildschirm.
 */
public class MarkerManager {
    private final Marker blueMarker;
    private final Marker orangeMarker;
    private final Marker redMarker;
    private final Engine engineModel;

    /**
     * Konstruktor für den MarkerManager, der Marker für Blau, Orange und Rot basierend auf dem angegebenen Werten initialisiert.
     *
     * @param engineModel das Engine-Modell, das die Marker-Werte enthält
     */
    public MarkerManager(Engine engineModel) {
        this.engineModel = engineModel;

        blueMarker = new Marker(new Sprite(new Texture("blueMarker.png")),engineModel.getBlueAeroMarker(), 538, 389, -38);
        orangeMarker = new Marker(new Sprite(new Texture("orangeMarker.png")), engineModel.getOrangeAeroMarker(), 681, 371, 24);
        redMarker = new Marker(new Sprite(new Texture("redMarker.png")), engineModel.getRedBrakeMarker(), 539, 259, -43);
    }

    /**
     * Aktualisiert die Marker basierend auf dem angegebenen Markernamen.
     *
     * @param markerName der Name des Markers (z. B. "blue", "orange", "red")
     */
    public void update(String markerName){
        switch(markerName.toLowerCase()){
            case "blue": //will change to their actual names like landing and flaps
                blueMarker.landingUpdate(engineModel.getBlueAeroMarker());
                break;
            case "orange":
                orangeMarker.flapsUpdate(engineModel.getOrangeAeroMarker());
                break;
            case "red":
                redMarker.brakesUpdate(engineModel.getRedBrakeMarker());
                break;
            default:
                break;
        }
    }

    /**
     * Zeichnet alle Marker mit dem angegebenen {@code SpriteBatch}.
     *
     * @param batch Der {@code SpriteBatch}, der zum Rendern verwendet wird.
     */
    public void draw(SpriteBatch batch) {
        blueMarker.draw(batch);
        orangeMarker.draw(batch);
        redMarker.draw(batch);
    }
}
