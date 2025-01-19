package com.sierra.skyTeam.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sierra.skyTeam.controller.RoundController;
import com.sierra.skyTeam.view.EndRoundView;

/**
 * Die Klasse {@code EndRound} enthält die Logik für die Taste, die eine Runde im Spiel beendet.
 * Sie überprüft, ob der Benutzer das Ende der Runde angeklickt hat und steuert
 * die Sichtbarkeit der entsprechenden Ansicht.
 */
public class EndRound {
    private final EndRoundView endRound;
    private final RoundController roundController;

    /**
     * Konstruktor für die {@code EndRound}-Klasse.
     * Initialisiert die Ansicht für das Ende der Runde und den Controller für die Runde.
     *
     * @param roundController Der Controller für die aktuelle Runde.
     */
    public EndRound(RoundController roundController) {
        this.roundController = roundController;
        endRound = new EndRoundView();
        endRound.setVisibility(false);
    }

    /**
     * Überprüft, ob sich der Mauszeiger über der EndRound-Taste befindet.
     *
     * @param touchX Die X-Position des Touch-Ereignisses.
     * @param touchY Die Y-Position des Touch-Ereignisses.
     * @return {@code true}, wenn der Mauszeiger über dem End-Runde-Bereich schwebt, sonst {@code false}.
     */
    public boolean isHovered(float touchX, float touchY) {
        return (endRound.getBoundingRectangle().contains(touchX, touchY) && endRound.isVisible());
    }

    /**
     * Behandelt einen Mausklick auf die EndRound-Taste.
     * Wenn der Bereich für die EndRound-Taste angeklickt wird, endet die Runde.
     *
     * @param touchX Die X-Position des Touch-Ereignisses.
     * @param touchY Die Y-Position des Touch-Ereignisses.
     */
    public void handleClick(float touchX, float touchY) {
        if(endRound.getBoundingRectangle().contains(touchX, touchY) && Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && endRound.isVisible()) {
            roundController.endRound();
        }
    }

    /**
     * Gibt die Ansicht für die EndRound-Taste zurück.
     *
     * @return Die {@code EndRoundView}-Instanz.
     */
    public EndRoundView getEndRoundView() {
        return endRound;
    }
    /**
     * Zeichnet die EndRound-Taste.
     *
     * @param batch Der {@code SpriteBatch}, der zum Zeichnen der Ansicht verwendet wird.
     */
    public void draw(SpriteBatch batch) {
        endRound.draw(batch);
    }
}
