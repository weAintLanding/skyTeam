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
    private final EngineController engineController;
    private final LandingGearController landingGearController;
    private final FlapsController flapsController;
    private final BrakesController brakesController;
    private final RerollController rerollController;
    private final RoundController roundController;

    MarkerManager markerManager;

    private final List<FieldView> fieldsView;

    public GameController(MainGame game){
        this.game = game;

        this.gameModel = new GameModel(game);
        this.fieldsView = FieldGenerator.generateFields();
        this.axisController = new AxisController(gameModel, game);
        this.playerController = new PlayerController(gameModel);
        this.roundController = new RoundController(this, gameModel, fieldsView, game);
        this.diceController = new DiceController(gameModel, this, fieldsView);
        this.radioController = new RadioController(gameModel);
        this.altitudeController = new AltitudeController();
        this.rerollController = new RerollController(diceController, altitudeController.getRerollToken(), roundController);
        this.engineController = new EngineController(gameModel, radioController.getTrackManager(), game, this);
        this.markerManager = new MarkerManager(gameModel.getAirplane().getEngine());
        this.landingGearController = new LandingGearController(gameModel, markerManager);
        this.flapsController = new FlapsController(gameModel, markerManager);
        this.brakesController = new BrakesController(gameModel, markerManager);

        altitudeController.setRound(1);
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
    public RerollController getRerollController() {
        return rerollController;
    }
    public RoundController getRoundController() {
        return roundController;
    }
    public AltitudeController getAltitudeController() {
        return altitudeController;
    }
    public EngineController getEngineController() {
        return engineController;
    }
    public RadioController getRadioController() {
        return radioController;
    }

    public void draw(SpriteBatch batch) {
        for (FieldView field : fieldsView) {
            field.switchRenderer(batch);
        }
        axisController.draw();
        engineController.draw();
        radioController.draw(batch);
        altitudeController.draw(batch);

        markerManager.draw(batch);

        landingGearController.draw();
        flapsController.draw();
        brakesController.draw();
        rerollController.toggleVisibility();
        rerollController.draw(batch);

        roundController.draw(batch);
    }
}
