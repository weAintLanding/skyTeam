package com.sierra.skyTeam.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sierra.skyTeam.model.Dice;
import com.sierra.skyTeam.model.GameModel;
import com.sierra.skyTeam.view.*;

import java.util.List;

public class DiceController {
    private final Dice[] pilotDice;
    private final Dice[] copilotDice;
    private final DiceView diceView;

    List<FieldView> fieldsView;
    Viewport viewport;

    //the handlers/managers
    DicePosUpdater pilotHandler;
    DicePosUpdater copilotHandler;
    CoffeeManager coffeeManager;
    DiceValueUpdater diceValueUpdater;
    RoundController roundController;

    public DiceController(GameModel gameModel, GameController gameController, List<FieldView> fieldsView) {
        this.pilotDice = gameModel.getPilot().getDiceList();
        this.copilotDice = gameModel.getCoPilot().getDiceList();
        this.coffeeManager = new CoffeeManager(FieldGenerator.getCoffeeFieldViews());

        this.roundController = gameController.getRoundController();

        this.diceView = new DiceView();
        this.fieldsView = fieldsView;
        updateView();
    }

    public void setViewport(Viewport viewport) {
        this.viewport = viewport;
        this.diceValueUpdater = new DiceValueUpdater(viewport, diceView, pilotDice, copilotDice, this::onDiceValueChanged, this::onCoffeeInteraction);

        pilotHandler = new DicePosUpdater(diceView, pilotDice, fieldsView, viewport, coffeeManager, diceValueUpdater, true, roundController);
        copilotHandler = new DicePosUpdater(diceView, copilotDice, fieldsView,viewport, coffeeManager, diceValueUpdater, false, roundController);
    }

    public DiceView getDiceView(){
        return diceView;
    }

    public DiceValueUpdater getDiceValueUpdater() {
        return diceValueUpdater;
    }

    public void rerollDice(boolean isPilot) {
        Dice[] diceToReroll = isPilot ? pilotDice : copilotDice;
        for (Dice dice : diceToReroll) {
            if(!dice.isPlaced()) {
                dice.reroll();
            }
        }
        updateView();
    }

    private void onDiceValueChanged() {
        resetSelections();
    }
    private void onCoffeeInteraction() {
        coffeeManager.removeCoffee(diceValueUpdater.selectedCoffee);
    }

    public void resetSelections() {
        pilotHandler.resetSelection();
        copilotHandler.resetSelection();
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
        //Coffee render
        coffeeManager.draw(batch);
        // Pop-up render
        diceValueUpdater.render(batch);
    }

    public void dispose(){
    }
}
