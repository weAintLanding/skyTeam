package com.sierra.skyTeam.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sierra.skyTeam.model.Dice;
import com.sierra.skyTeam.model.GameModel;
import com.sierra.skyTeam.view.DicePosUpdater;
import com.sierra.skyTeam.view.DiceView;
import com.sierra.skyTeam.view.FieldGenerator;
import com.sierra.skyTeam.view.FieldView;

import java.util.ArrayList;
import java.util.List;

public class DiceController {
    private final Dice[] pilotDice;
    private final Dice[] copilotDice;
    private final DiceView diceView;

    List<FieldView> fieldsView;
    Viewport viewport;

    DicePosUpdater pilotHandler;
    DicePosUpdater copilotHandler;

    public DiceController(GameModel gameModel, GameController gameController) {
        this.pilotDice = gameModel.getPilot().getDiceList();
        this.copilotDice = gameModel.getCoPilot().getDiceList();

        this.diceView = new DiceView();
        fieldsView = FieldGenerator.generateFields();
        viewport = new FitViewport(1280, 720);

        pilotHandler = new DicePosUpdater(diceView, pilotDice, fieldsView, viewport, true);
        copilotHandler = new DicePosUpdater(diceView, copilotDice, fieldsView, viewport, false);

        updateView(); // Sync initial state
    }

    public DiceView getDiceView(){
        return diceView;
    }

    public void rerollDice(boolean isPilot) {
        Dice[] diceToReroll = isPilot ? pilotDice : copilotDice;
        for (Dice dice : diceToReroll) {
            dice.reroll();
        }
        updateView();
    }

    public void updateView() {
        diceView.updateSprites(pilotDice, copilotDice);
    }

    public void updateHandler() {
        pilotHandler.update();
        copilotHandler.update();
    }

    public void render(SpriteBatch batch, float startX, float startY) {
        // Render Pilot dice
        diceView.render(batch, true, startX, startY, pilotDice);

        // Render CoPilot dice with an offset
        diceView.render(batch, false, startX + 1064, startY, copilotDice);
    }
}
