package com.sierra.skyTeam.model;

/**
 * Die Klasse {@code CoPilot} repräsentiert der Pilot im Spiel, der von der {@code Players}-Klasse erbt.
 * Der Pilot hat ein spezielles Attribut für die Funkfelder, das für den Funkverkehr genutzt wird.
 */
public class Pilot extends Players {

    /**
     * Konstruktor für die {@code Pilot}-Klasse.
     *
     * @param gameModel Das Spielmodell, das mit diesem Piloten verbunden ist.
     */
    public Pilot(GameModel gameModel) {
        super(gameModel);
        this.radioSlots = 1;
        this.radioPlayer = new Field[radioSlots];
        for (int i = 0; i < radioSlots; i++) {
            radioPlayer[i] = new Field("Radio Pilot");
        }
    }
}
