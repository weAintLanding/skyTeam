package com.sierra.skyTeam.controller;

import com.sierra.skyTeam.MainGame;
import com.sierra.skyTeam.model.*;
import com.sierra.skyTeam.view.AxisView;
import com.sierra.skyTeam.view.FieldGenerator;
import com.sierra.skyTeam.view.MarkerManager;

public class EngineController {
    GameModel gameModel;
    MarkerManager markerManager;

    private final Airplane airplaneModel;

    private final Players pilot;
    private final Players copilot;

    private final FieldModel pilotField;
    private final FieldModel copilotField;
    private boolean pilotFieldSet;
    private boolean copilotFieldSet;
    private boolean engineChanged;

    private final ApproachTrackModel trackManager;

    public EngineController(GameModel gameModel, MarkerManager markerManager, ApproachTrackModel trackManager) {
        this.gameModel = gameModel;
        this.markerManager = markerManager;
        this.trackManager = trackManager;

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
        airplaneModel.getEngine().movePlane(pilot.getThrottle(), copilot.getThrottle(), trackManager);
    }

    public void draw() {
        if(pilotField.isOccupied() && !pilotFieldSet){
            System.out.println("This works.");
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
