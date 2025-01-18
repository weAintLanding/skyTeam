package com.sierra.skyTeam.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Stellt die Ansicht eines Flugzeugs auf dem Approach-Track dar.
 * Die Flugzeug-Sprite wird als Sprite gerendert und kann an verschiedenen Positionen und mit unterschiedlicher Rotation angezeigt werden.
 */
public class ApproachAirplaneView {
    Sprite airplaneSprite;

    /**
     * Konstruktor für ApproachAirplaneView.
     */
    public ApproachAirplaneView(){
        airplaneSprite = new Sprite(new Texture("airplane.png"));
        airplaneSprite.setScale(0.37F);
    }

    /**
     * Zeichnet das Flugzeug an der angegebenen Position.
     *
     * @param x die x-Koordinate der Position
     * @param y die y-Koordinate der Position
     */
    public void drawAirplaneAt(float x, float y){
        airplaneSprite.setPosition(x, y);
    }

    /**
     * Setzt die Rotation des Flugzeugs.
     *
     * @param rotation der Rotationswinkel in Grad
     */
    public void setRotation(float rotation) {
        airplaneSprite.setRotation(rotation);
    }

    /**
     * Gibt die y-Koordinate des Flugzeugs zurück.
     *
     * @return die y-Koordinate des Flugzeugs
     */
    public float getY() {
        return airplaneSprite.getY();
    }

    /**
     * Zeichnet das Flugzeug mit dem angegebenen {@code SpriteBatch}.
     *
     * @param batch Der {@code SpriteBatch}, der zum Rendern des Sprites verwendet wird.
     */
    public void draw(SpriteBatch batch) {
        airplaneSprite.draw(batch);
    }
}
