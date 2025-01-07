package com.sierra.skyTeam.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sierra.skyTeam.model.*;
import com.sierra.skyTeam.view.*;

import java.util.List;

public class GameController {

    private GameModel gameModel;

    private AxisController axisController;
    private PlayerController playerController;
    private DiceController diceController;

    private List<FieldView> fieldsView;

    public GameController(){
        this.gameModel = new GameModel();
        this.fieldsView = FieldGenerator.generateFields();

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

    public void draw(SpriteBatch batch) {
        for (FieldView field : fieldsView) {
            field.switchRenderer(batch);
        }
    }
}
