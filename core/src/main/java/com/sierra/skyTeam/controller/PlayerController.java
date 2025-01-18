package com.sierra.skyTeam.controller;


import com.sierra.skyTeam.model.*;

import java.util.List;

/**
 * Der PlayerController verwaltet die Spiellogik der Spieler, insbesondere der Pilot- und Co-Pilot-Charaktere.
 * Er stellt Funktionen zur Verfügung, um auf die Würfel der Spieler zuzugreifen.
 */
public class PlayerController {

    private GameModel gameModel;

    /**
     * Konstruktor: Initialisiert den PlayerController mit dem gegebenen Spielmodell.
     *
     * @param gameModel Das Modell des Spiels, das die Spieler und deren Daten enthält.
     */
    public PlayerController(GameModel gameModel){
        this.gameModel = gameModel;
    }

    /**
     * Gibt die Würfel des Piloten zurück.
     *
     * @return Ein Array von Würfeln des Piloten.
     */
    public Dice[] getPilotDice(){
        return gameModel.getPilot().getDiceList();
    }

    /**
     * Gibt die Würfel des Co-Piloten zurück.
     *
     * @return Ein Array von Würfeln des Co-Piloten.
     */
    public Dice[] getCoPilotDice(){
        return gameModel.getCoPilot().getDiceList();
    }
}
