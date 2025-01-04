package com.sierra.skyTeam.controller;

import com.sierra.skyTeam.model.Airplane;
import com.sierra.skyTeam.model.GameModel;
import com.sierra.skyTeam.view.FieldGenerator;
import com.sierra.skyTeam.view.FieldView;

import java.util.List;

public class GameController {

    private GameModel gameModel;
    private Airplane airplaneModel;

    private AxisController axisController;
    private PlayerController playerController;
    private DiceController diceController;

    private List<FieldView> fieldsView;

    public GameController(List<FieldView> fieldsView){
        this.gameModel = new GameModel();
        this.airplaneModel = gameModel.getAirplane();
        this.fieldsView = fieldsView;

        this.axisController = new AxisController(gameModel);
        this.playerController = new PlayerController(gameModel);
        this.diceController = new DiceController(gameModel, this, fieldsView);
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
