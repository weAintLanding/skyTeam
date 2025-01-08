package com.sierra.skyTeam.controller;

import com.sierra.skyTeam.MainGame;
import com.sierra.skyTeam.model.GameModel;
import com.sierra.skyTeam.model.Players;
import com.sierra.skyTeam.model.Airplane;
import com.sierra.skyTeam.view.AxisView;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sierra.skyTeam.view.FieldGenerator;
import com.sierra.skyTeam.view.FieldView;

import java.util.List;

public class AxisController {
    private final MainGame game;

    private final AxisView axisView;
    private final Airplane airplaneModel;

    private final Players pilot;
    private final Players copilot;

    private final FieldView pilotField;
    private final FieldView copilotField;
    private boolean pilotFieldSet;
    private boolean copilotFieldSet;
    private boolean axisChanged;


    public AxisController(GameModel gameModel, MainGame game) {
        this.game = game;

        this.airplaneModel = gameModel.getAirplane();
        this.axisView = new AxisView(airplaneModel.getAxis().getAxisValue());

        this.pilot = gameModel.getPilot();
        this.copilot = gameModel.getCoPilot();

        this.pilotField = FieldGenerator.getPilotAxisField();
        this.copilotField = FieldGenerator.getCopilotAxisField();

        this.pilotFieldSet = false;
        this.copilotFieldSet = false;
        this.axisChanged = false;
    }

    public AxisView getAxisView(){
        return axisView;
    }


    public void updateAxis(Players pilot, Players copilot, SpriteBatch batch){
        airplaneModel.getAxis().changeAxis(pilot,copilot,game);
    }

    public void draw(SpriteBatch batch) {
        if(pilotField.isFieldOccupied() && !pilotFieldSet){
            System.out.println("This works.");
            pilot.setAxis(pilotField.getDice());
            pilotFieldSet = true;
        }
        if(copilotField.isFieldOccupied() && !copilotFieldSet){
            copilot.setAxis(copilotField.getDice());
            copilotFieldSet = true;
        }
        if(pilotFieldSet && copilotFieldSet && !axisChanged){
            this.updateAxis(pilot,copilot,batch);
            axisChanged = true;
        }
        axisView.setAxisValue(airplaneModel.getAxis().getAxisValue());
    }

}
