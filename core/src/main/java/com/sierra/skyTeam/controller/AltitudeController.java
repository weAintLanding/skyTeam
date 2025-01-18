package com.sierra.skyTeam.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sierra.skyTeam.model.AltitudeTrackModel;
import com.sierra.skyTeam.model.RerollTokenModel;

/**
 * Der AltitudeController verwaltet die Logik für die Höhenverfolgung und die Reroll-Token.
 * Es aktualisiert den Status der Höhenanzeige basierend auf der aktuellen Runde und
 * fügt bei bestimmten Runden neue Token hinzu.
 */

public class AltitudeController {
    private final AltitudeTrackModel trackManager;
    private final RerollTokenModel rerollToken;
    private int round;

    /**
     * Konstruktor für AltitudeController.
     */
    public AltitudeController() {
        this.trackManager = new AltitudeTrackModel();
        this.rerollToken = new RerollTokenModel();
        this.round = 1;
        drawTokens();
    }

    /**
     * Setzt die aktuelle Runde und aktualisiert die Position des Höhen-Trackers.
     *
     * @param round Die aktuelle Runde.
     */
    public void setRound(int round) {
        this.round = round;
        if (round == 1){
            trackManager.setStartingPosition();
        }else {
            int index = round - 1;
            trackManager.updateTrackerPosition(index);
        }
        addTokens();
    }

    /**
     * Wechselt zur nächsten Spielrunde, aktualisiert die Höhenposition
     * und fügt Reroll-Tokens hinzu, falls notwendig.
     */
    public void nextRound() {
        this.round = this.round + 1;
        if (round == 1){
            trackManager.setStartingPosition();
        }else {
            int index = round - 1;
            trackManager.updateTrackerPosition(index);
        }
        addTokens();
    }

    /**
     * Fügt Reroll-Token hinzu, wenn die aktuelle Runde 1 oder 5 ist.
     */
    public void addTokens(){
        if (round == 1 || round == 5){
            rerollToken.addToken();
        }
    }

    /**
     * Generiert der Reroll-Token an den Anfangspositionen.
     */
    public void drawTokens(){
        rerollToken.generateToken(920, 110);
        rerollToken.generateToken(920,395);
    }

    /**
     * Gibt die aktuelle Spielrunde zurück.
     *
     * @return Die aktuelle Runde.
     */
    public int getRound() {
        return this.round;
    }

    /**
     * Gibt das Modell der Reroll-Token zurück.
     *
     * @return Das {@link RerollTokenModel}.
     */
    public RerollTokenModel getRerollToken() {
        return rerollToken;
    }

    /**
     * Zeichnet den Höhen-Track und die Reroll-Token mit dem angegebenen {@code SpriteBatch}.
     *
     * @param batch Der SpriteBatch, der für das Zeichnen verwendet wird.
     */
    public void draw(SpriteBatch batch) {
        trackManager.draw(batch);
        rerollToken.draw(batch);
    }
}
