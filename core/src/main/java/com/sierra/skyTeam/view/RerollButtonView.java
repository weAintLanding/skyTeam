package com.sierra.skyTeam.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.sierra.skyTeam.controller.RerollController;

/**
 * Stellt die Ansicht für den Reroll-Button dar, der im Spiel verwendet wird, um Würfel neu zu rollen.
 * Diese Klasse verwaltet die Sichtbarkeit und das Aussehen des Buttons und stellt sicher, dass er basierend auf der Auswahl des Würfels aktualisiert wird.
 */
public class RerollButtonView {
    private final Sprite rerollButton;
    private boolean isVisible;
    private final RerollController rerollController;

    /**
     * Konstruktor für RerollButtonView.
     * Der Button wird initial auf eine Position gesetzt und auf unsichtbar gestellt.
     *
     * @param rerollController der Controller, der das Neu-Würfeln verwaltet
     */
    public RerollButtonView(RerollController rerollController) {
        this.rerollButton = new Sprite(new Texture("rerollButton.png"));
        rerollButton.setPosition(100, -40);
        rerollButton.setScale(0.38F);
        this.isVisible = false;
        this.rerollController = rerollController;
    }

    /**
     * Setzt die Sichtbarkeit des Reroll-Buttons.
     * Wenn der Button sichtbar ist, wird die Opacity je nach Auswahl des Würfels angepasst.
     *
     * @param isVisible true, wenn der Button sichtbar sein soll, andernfalls false
     */
    public void setVisibility(boolean isVisible) {
        this.isVisible = isVisible;
        if(isVisible) {
            if (rerollController.getSelectingDice()){
                rerollButton.setColor(1, 1,1,1F);
            } else {
                rerollButton.setColor(1, 1, 1, 0.7F);
            }
        } else {
            rerollButton.setColor(1, 1, 1, 0F);
        }
    }

    /**
     * Gibt die Bounds des Buttons zurück.
     *
     * @return die Bounds des Buttons
     */
    public Rectangle getBoundingRectangle() {
        return rerollButton.getBoundingRectangle();
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
        rerollButton.draw(batch);
    }
}
