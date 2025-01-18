package com.sierra.skyTeam.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Diese Klasse stellt die Ansicht für der "End Turn"-Button dar.
 * Sie zeigt die "End Turn"-Sprite an und bietet die Möglichkeit, die Sichtbarkeit dieses Sprites zu steuern.
 */
public class EndTurnView {
    private final Sprite endTurn;
    private boolean isVisible;

    /**
     * Konstruktor für die Klasse EndTurnView.
     * Initialisiert die Button-Sprite und setzt die Anfangsposition und Skalierung.
     */
    public EndTurnView(float x, float y) {
        this.endTurn = new Sprite(new Texture("endTurn.png"));
        endTurn.setPosition(x, y);
        endTurn.setScale(0.38F);
        this.isVisible = false;
    }

    /**
     * Setzt die Sichtbarkeit des Buttons.
     *
     * @param isVisible Ein Boolean, der die Sichtbarkeit festlegt (true = sichtbar, false = unsichtbar)
     */
    public void setVisibility(boolean isVisible) {
        this.isVisible = isVisible;
    }

    /**
     * Gibt das Bounding-Rectangle des Buttons zurück.
     *
     * @return Das Begrenzungsrechteck des Sprites
     */
    public Rectangle getBoundingRectangle() {
        return endTurn.getBoundingRectangle();
    }

    /**
     * Gibt zurück, ob der Button sichtbar ist.
     *
     * @return true, wenn der Button sichtbar ist, andernfalls false
     */
    public boolean isVisible() {
        return isVisible;
    }

    /**
     * Zeichnet den Button, wenn es sichtbar ist, mit dem angegebenen {@code SpriteBatch}.
     *
     * @param batch Der {@code SpriteBatch}, der zum Rendern verwendet wird.
     */
    public void draw(SpriteBatch batch) {
        endTurn.draw(batch);
    }
}
