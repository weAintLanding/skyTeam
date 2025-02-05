package com.sierra.skyTeam.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Stellt eine Ansicht für eine Nachricht dar, die dem nächsten Spieler anzeigt, dass das Reroll verfügbar ist.
 */
public class RerollMessageView {
    Sprite rerollMessage;
    boolean isVisible;

    /**
     * Konstruktor für RerollMessageView.
     * Der Sprite wird skaliert und die Nachricht ist anfänglich unsichtbar.
     */
    public RerollMessageView() {
        this.rerollMessage = new Sprite(new Texture("rerollAvailable.png"));
        rerollMessage.setScale(0.37F);
        this.isVisible = false;
    }

    /**
     * Setzt die Position der Nachricht auf dem Bildschirm.
     *
     * @param x die x-Koordinate der neuen Position
     * @param y die y-Koordinate der neuen Position
     */
    public void setPosition(float x, float y) {
        this.rerollMessage.setPosition(x, y);
    }

    /**
     * Setzt die Sichtbarkeit der Nachricht.
     *
     * @param isVisible true, wenn die Nachricht sichtbar sein soll, andernfalls false
     */
    public void setVisibility(boolean isVisible) {
        this.isVisible = isVisible;
    }

    /**
     * Gibt zurück, ob der Button sichtbar ist oder nicht.
     *
     * @return true, wenn der Button sichtbar ist, andernfalls false
     */
    public boolean isVisible() {
        return isVisible;
    }

    /**
     * Zeichnet den Button mit dem angegebenen {@code SpriteBatch}.
     *
     * @param batch Der {@code SpriteBatch}, der zum Rendern verwendet wird.
     */
    public void draw(SpriteBatch batch) {
        rerollMessage.draw(batch);
    }
}
