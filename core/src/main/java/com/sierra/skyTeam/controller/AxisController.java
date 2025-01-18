package com.sierra.skyTeam.controller;

import com.sierra.skyTeam.MainGame;
import com.sierra.skyTeam.model.FieldModel;
import com.sierra.skyTeam.model.GameModel;
import com.sierra.skyTeam.model.Players;
import com.sierra.skyTeam.model.Airplane;
import com.sierra.skyTeam.view.AxisView;

import com.sierra.skyTeam.view.FieldGenerator;
import com.sierra.skyTeam.view.FieldView;

import java.util.List;

public class AxisController {
    private final MainGame game;

    private final AxisView axisView;
    private final Airplane airplaneModel;

    private final Players pilot;
    private final Players copilot;

    private final FieldModel pilotField;
    private final FieldModel copilotField;
    private boolean pilotFieldSet;
    private boolean copilotFieldSet;
    private boolean axisChanged;

    public AxisController(GameModel gameModel, MainGame game) {
        this.game = game;

        this.airplaneModel = gameModel.getAirplane();
        this.axisView = new AxisView(airplaneModel.getAxis().getAxisValue());

        this.pilot = gameModel.getPilot();
        this.copilot = gameModel.getCoPilot();

        this.pilotField = FieldGenerator.getPilotAxisFieldView().getFieldModel();
        this.copilotField = FieldGenerator.getCopilotAxisFieldView().getFieldModel();

        this.pilotFieldSet = false;
        this.copilotFieldSet = false;
        this.axisChanged = false;
    }

    public AxisView getAxisView(){
        return axisView;
    }


    public void updateAxis(Players pilot, Players copilot){
        airplaneModel.getAxis().changeAxis(pilot,copilot,game);
    }

    public void draw() {
        if(pilotField.isOccupied() && !pilotFieldSet){
            pilot.setAxis(pilotField.getPlacedDice());
            pilotFieldSet = true;
        }
        if(copilotField.isOccupied() && !copilotFieldSet){
            copilot.setAxis(copilotField.getPlacedDice());
            copilotFieldSet = true;
        }
        if(pilotFieldSet && copilotFieldSet && !axisChanged){
            this.updateAxis(pilot,copilot);
            axisChanged = true;
        }
        axisView.setAxisValue(airplaneModel.getAxis().getAxisValue());
    }

    public void roundReset() {
        this.copilotFieldSet = false;
        this.pilotFieldSet = false;
        this.axisChanged = false;
    }

}
