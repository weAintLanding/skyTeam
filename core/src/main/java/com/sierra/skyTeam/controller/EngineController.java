package com.sierra.skyTeam.controller;

import com.sierra.skyTeam.MainGame;
import com.sierra.skyTeam.model.*;
import com.sierra.skyTeam.view.AxisView;
import com.sierra.skyTeam.view.FieldGenerator;
import com.sierra.skyTeam.view.MarkerManager;

/**
 * Der EngineController steuert die Motorleistung des Flugzeugs und koordiniert
 * die Interaktionen zwischen den Spielern, den Feldern und dem Anflug-Track.
 */
public class EngineController {
    MainGame game;
    GameModel gameModel;
    GameController gameController;

    private final Airplane airplaneModel;

    private final Players pilot;
    private final Players copilot;

    private final FieldModel pilotField;
    private final FieldModel copilotField;
    private boolean pilotFieldSet;
    private boolean copilotFieldSet;
    private boolean engineChanged;

    private final ApproachTrackModel trackManager;

    /**
     * Konstruktor: Initialisiert die Engine-Controller mit den entsprechenden Modellen und Feldern.
     *
     * @param gameModel     Das GameModel, das alle notwendigen Daten enthält.
     * @param trackManager  Das Anflug-Track-Modell.
     * @param game          Die MainGame-Instanz.
     * @param gameController Die Controller des Spiels.
     */
    public EngineController(GameModel gameModel, ApproachTrackModel trackManager, MainGame game, GameController gameController) {
        this.game = game;
        this.gameModel = gameModel;
        this.trackManager = trackManager;
        this.gameController = gameController;

        this.airplaneModel = gameModel.getAirplane();

        this.pilot = gameModel.getPilot();
        this.copilot = gameModel.getCoPilot();

        this.pilotField = FieldGenerator.getPilotThrottleFieldView().getFieldModel();
        this.copilotField = FieldGenerator.getCopilotThrottleFieldView().getFieldModel();

        this.pilotFieldSet = false;
        this.copilotFieldSet = false;
        this.engineChanged = false;
    }

    /**
     * Aktualisiert die Motorleistung des Flugzeugs basierend auf den Werten von Pilot und Copilot.
     *
     * @param pilot   Der Pilot-Instanz.
     * @param copilot Der Copilot-Instanz.
     */
    public void updateEngine(Players pilot, Players copilot){
        if(gameController.getAltitudeController().getRound() == 7){
            airplaneModel.getEngine().landPlane(pilot.getThrottle(), copilot.getThrottle(), game);
        } else {
            airplaneModel.getEngine().movePlane(pilot.getThrottle(), copilot.getThrottle(), trackManager, game);
        }
    }

    /**
     * Setzt den Status der Felder und der Motorleistung für die nächste Runde zurück.
     */
    public void roundReset() {
        this.pilotFieldSet = false;
        this.copilotFieldSet = false;
        this.engineChanged = false;
    }

    /**
     * Überprüft die Belegung der Steuerungsfelder und aktualisiert die Motorleistung,
     * falls alle erforderlichen Felder belegt sind.
     */
    public void draw() {
        if(pilotField.isOccupied() && !pilotFieldSet){
            pilot.setThrottle(pilotField.getPlacedDice());
            pilotFieldSet = true;
        }
        if(copilotField.isOccupied() && !copilotFieldSet){
            copilot.setThrottle(copilotField.getPlacedDice());
            copilotFieldSet = true;
        }
        if(pilotFieldSet && copilotFieldSet && !engineChanged){
            this.updateEngine(pilot,copilot);
            engineChanged = true;
        }

    }
}
