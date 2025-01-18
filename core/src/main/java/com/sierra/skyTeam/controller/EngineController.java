package com.sierra.skyTeam.controller;

import com.sierra.skyTeam.MainGame;
import com.sierra.skyTeam.model.*;
import com.sierra.skyTeam.view.AxisView;
import com.sierra.skyTeam.view.FieldGenerator;
import com.sierra.skyTeam.view.MarkerManager;

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

    public void updateEngine(Players pilot, Players copilot){
        if(gameController.getAltitudeController().getRound() == 7){
            airplaneModel.getEngine().landPlane(pilot.getThrottle(), copilot.getThrottle(), game);
        } else {
            airplaneModel.getEngine().movePlane(pilot.getThrottle(), copilot.getThrottle(), trackManager, game);
        }
    }

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

    public void roundReset() {
        this.pilotFieldSet = false;
        this.copilotFieldSet = false;
        this.engineChanged = false;
    }


}
