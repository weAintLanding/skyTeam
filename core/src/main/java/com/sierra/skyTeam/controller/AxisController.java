package com.sierra.skyTeam.controller;

import com.sierra.skyTeam.model.GameModel;
import com.sierra.skyTeam.model.Players;
import com.sierra.skyTeam.model.Airplane;
import com.sierra.skyTeam.view.AxisView;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AxisController {
    private final AxisView axisView;
    private final Airplane airplaneModel;

    SpriteBatch batch;


    public AxisController(GameModel gameModel) {
        this.airplaneModel = gameModel.getAirplane();
        this.axisView = new AxisView(airplaneModel.getAxis().getAxisValue());

        batch = new SpriteBatch();
    }

    public int getAxisValue(){
        return airplaneModel.getAxis().getAxisValue();
    }
    public AxisView getAxisView(){
        return axisView;
    }


    public void updateAxis(Players pilot, Players copilot){
        airplaneModel.getAxis().changeAxis(pilot,copilot);
        axisView.setAxisValue(airplaneModel.getAxis().getAxisValue());
        axisView.render(batch);
    }

}
