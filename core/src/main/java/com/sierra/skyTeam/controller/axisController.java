package com.sierra.skyTeam.controller;

import com.sierra.skyTeam.model.Players;
import com.sierra.skyTeam.model.axisModel;
import com.sierra.skyTeam.view.Axis;

public class axisController {
    private final Axis axisView;
    private final axisModel axisModel;

    public axisController(axisModel axisModel, Axis axisView) {
        this.axisModel = axisModel;
        this.axisView = axisView;
    }

    public void getAxis(){
        int currentAxisValue = axisModel.getAxisValue();

        axisView.setAxisValue(currentAxisValue);
    }

    public void updateAxis(Players pilot, Players copilot){
        axisModel.changeAxis(pilot,copilot);

        this.getAxis();
    }


}
