package com.sierra.skyTeam.view;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Stellt ein Marker-Objekt mit einem Sprite, einem Wert und Koordinaten dar.
 * Diese Klasse verwaltet die Position, Rotation und Aktualisierungslogik des Markers für Landing-Gear, Flaps und Bremsen.
 */
public class Marker {
    private int value;
    Sprite markerSprite;
    float x, y, rotation;

    /**
     * Erstellt ein Marker-Objekt mit dem angegebenen Sprite, Initialwert, Position und Rotation.
     *
     * @param markerSprite die Sprite, die den Marker darstellt
     * @param initialValue der Anfangswert des Markers
     * @param x die x-Koordinate des Markers
     * @param y die y-Koordinate des Markers
     * @param rotation die Rotation des Markers in Grad
     */
    public Marker (Sprite markerSprite, int initialValue, float x, float y, float rotation){
        this.markerSprite = markerSprite;
        this.value = initialValue;
        this.x = x;
        this.y = y;
        this.rotation = rotation;
        this.markerSprite.setPosition(x, y);
        this.markerSprite.setRotation(rotation);
        this.markerSprite.setScale(0.7F);
    }

    /**
     * Aktualisiert die Position und Rotation des Markers basierend auf dem aktiven Landing-Gear-Switches.
     *
     * @param value der neue Wert, der den Blau-Marker darstellt
     */
    public void landingUpdate (int value){
        switch(value){
            case 5:
                markerSprite.setPosition(x + 33, y - 19);
                markerSprite.setRotation(rotation + 12);
                break;
            case 6:
                markerSprite.setPosition(x + 69, y - 30);
                markerSprite.setRotation(rotation + 30);
                break;
            case 7:
                markerSprite.setPosition(x + 108, y - 30);
                markerSprite.setRotation(rotation + 45);
                break;
            default:
                break;
        }
    }

    /**
     * Aktualisiert die Position und Rotation des Markers basierend auf dem aktiven Flap-Switches.
     *
     * @param value der neue Wert, der den Orange-Marker darstellt
     */
    public void flapsUpdate (int value){
        switch(value){
            case 9:
                markerSprite.setPosition(x + 33, y + 21);
                markerSprite.setRotation(rotation + 18);
                break;
            case 10:
                markerSprite.setPosition(x + 58, y + 51);
                markerSprite.setRotation(rotation + 33);
                break;
            case 11:
                markerSprite.setPosition(x + 74, y + 85);
                markerSprite.setRotation(rotation + 45);
                break;
            case 12:
                markerSprite.setPosition(x + 80, y + 123);
                markerSprite.setRotation(rotation + 64);
                break;
            default:
                break;
        }
    }

    /**
     * Aktualisiert die Position und Rotation des Markers basierend auf dem aktiven Bremsen-Switches.
     *
     * @param value der neue Wert, der den Rote-Marker darstellt
     */
    public void brakesUpdate(int value){
        switch(value){
            case 2:
                markerSprite.setPosition(x + 33, y - 20);
                markerSprite.setRotation(rotation + 18);
                break;
            case 4:
                markerSprite.setPosition(x + 108, y - 30);
                markerSprite.setRotation(rotation + 50);
                break;
            case 6:
                markerSprite.setPosition(x + 176, y + 4);
                markerSprite.setRotation(rotation + 85);
                break;
            default:
                break;
        }
    }

    /**
     * Zeichnet die Marker-Sprite mit dem angegebenen {@code SpriteBatch}.
     *
     * @param batch Der {@code SpriteBatch}, der zum Rendern verwendet wird.
     */
    public void draw(SpriteBatch batch){
        markerSprite.draw(batch);
    }

    public void setValue(int value) {
        this.value = value;
    }
}
