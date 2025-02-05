package com.sierra.skyTeam.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Diese Klasse stellt die Ansicht für der "End Round"-Button dar.
 * Sie zeigt die "End Round"-Sprite an und bietet die Möglichkeit, die Sichtbarkeit dieses Sprites zu steuern.
 */
public class EndRoundView {
    private final Sprite endRound;
    private boolean isVisible;

    /**
     * Konstruktor für die Klasse EndRoundView.
     * Initialisiert die Button-Sprite und setzt die Anfangsposition und Skalierung.
     */
    public EndRoundView() {
        this.endRound = new Sprite(new Texture("endRound.png"));
        endRound.setPosition(736, -40);
        endRound.setScale(0.38F);
        this.isVisible = true;
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
        return endRound.getBoundingRectangle();
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
        if(isVisible) {
            endRound.draw(batch);
        }
    }
}
