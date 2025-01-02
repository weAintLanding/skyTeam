package com.sierra.skyTeam.controller;

import com.sierra.skyTeam.model.Players;
import com.sierra.skyTeam.model.axisModel;
import com.sierra.skyTeam.view.axisView;

public class axisController {
    private final axisView axisView;
    private final axisModel axisModel;

    public axisController(axisModel axisModel, axisView axisView) {
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
