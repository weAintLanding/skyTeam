package com.sierra.skyTeam.controller;

import com.sierra.skyTeam.model.Airplane;

public class GameController {

    private Airplane airplaneModel;
    private AxisController axisController;

    public GameController(){
        this.airplaneModel = new Airplane();
        this.axisController = new AxisController(airplaneModel);
    }

    public AxisController getAxisController() {
        return axisController;
    }
}
