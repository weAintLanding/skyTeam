package com.sierra.skyTeam.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Stellt die Ansicht eines Kaffees für Konzentration dar, der auf dem Spielfeld angezeigt wird.
 * Der Kaffee wird als Sprite gerendert und kann an verschiedenen Positionen angezeigt werden.
 */
public class CoffeeView {
    Sprite coffee;
    Texture coffeeTexture;
    private boolean isAvailable;

    /**
     * Erstellt eine neue Instanz der CoffeeView und platziert den Kaffee an den angegebenen Koordinaten.
     *
     * @param x die x-Koordinate der Position des Kaffees
     * @param y die y-Koordinate der Position des Kaffees
     */
    public CoffeeView(float x, float y) {
        coffeeTexture = new Texture("coffee.png");
        coffee = new Sprite(coffeeTexture);
        coffee.setPosition(x, y);
        coffee.setScale(0.37F);
        this.isAvailable = false;
    }

    /**
     * Gibt die Sprite des Kaffees zurück.
     *
     * @return der Sprite des Kaffees
     */
    public Sprite getSprite(){
        return coffee;
    }

    /**
     * Zeichnet der Kaffee mit dem angegebenen {@code SpriteBatch}.
     *
     * @param batch Der {@code SpriteBatch}, der zum Rendern des Sprites verwendet wird.
     */
    public void render(SpriteBatch batch) {
        coffee.draw(batch);
    }

    /**
     * Gibt zurück, ob der Kaffee verfügbar ist.
     *
     * @return true, wenn der Kaffee verfügbar ist, andernfalls false
     */
    public boolean isAvailable() {
        return this.isAvailable;
    }

    /**
     * Setzt den Verfügbarkeitsstatus des Kaffees.
     *
     * @param available true, wenn der Kaffee verfügbar ist, andernfalls false
     */
    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }
}
