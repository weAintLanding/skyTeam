package com.sierra.skyTeam.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sierra.skyTeam.MainGame;
import com.sierra.skyTeam.model.*;
import com.sierra.skyTeam.view.*;

import java.util.List;

public class GameController {
    private final MainGame game;

    private final GameModel gameModel;

    private final AxisController axisController;
    private final PlayerController playerController;
    private final DiceController diceController;
    private final RadioController radioController;
    private final AltitudeController altitudeController;

    private final List<FieldView> fieldsView;

    public GameController(MainGame game){
        this.game = game;

        this.gameModel = new GameModel();
        this.fieldsView = FieldGenerator.generateFields();

        this.axisController = new AxisController(gameModel, game);
        this.playerController = new PlayerController(gameModel);
        this.diceController = new DiceController(gameModel, this, fieldsView);
        this.radioController = new RadioController();
        this.altitudeController = new AltitudeController();
        altitudeController.setRound(2);
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
        axisController.draw();
        radioController.draw(batch);
        altitudeController.draw(batch);
    }
}
