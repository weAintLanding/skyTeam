package com.sierra.skyTeam.Controller;

import com.sierra.skyTeam.Model.Players;
import com.sierra.skyTeam.Model.axisModel;
import com.sierra.skyTeam.View.Axis;

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
