package com.sierra.skyTeam.controller;

import com.sierra.skyTeam.model.Airplane;
import com.sierra.skyTeam.model.GameModel;
import com.sierra.skyTeam.view.FieldGenerator;

public class GameController {

    private GameModel gameModel;
    private Airplane airplaneModel;

    private AxisController axisController;
    private PlayerController playerController;
    private DiceController diceController;

    public GameController(){
        this.gameModel = new GameModel();
        this.airplaneModel = gameModel.getAirplane();

        this.axisController = new AxisController(gameModel);
        this.playerController = new PlayerController(gameModel);
        this.diceController = new DiceController(gameModel, this);
    }

    public AxisController getAxisController() {
        return axisController;
    }
    public PlayerController getPlayerController() {
        return playerController;
    }
    public DiceController getDiceController() {
        return diceController;
    }
}
