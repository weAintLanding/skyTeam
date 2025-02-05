package com.sierra.skyTeam.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sierra.skyTeam.model.Dice;
import com.sierra.skyTeam.model.GameModel;
import com.sierra.skyTeam.view.*;

import java.util.List;

/**
 * Die Klasse DiceController verwaltet die DIce-Logik im Spiel, einschließlich
 * der Anzeige, Positionierung und Interaktion von Würfeln für Pilot und Copilot.
 * Sie koordiniert auch die Interaktionen mit Feldern, Kaffeefeldern und
 * anderen Spielelementen.
 */
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
    GameController gameController;

    /**
     * Konstruktor: Initialisiert den Dice-Controller mit den Modellen und
     * zugehörigen Ansichten.
     *
     * @param gameModel    Das GameModel, das die relevanten Daten enthält.
     * @param gameController Die Controller des Spiels.
     * @param fieldsView   Die Liste der Feldansichten.
     */
    public DiceController(GameModel gameModel, GameController gameController, List<FieldView> fieldsView) {
        this.pilotDice = gameModel.getPilot().getDiceList();
        this.copilotDice = gameModel.getCoPilot().getDiceList();
        this.coffeeManager = new CoffeeManager(FieldGenerator.getCoffeeFieldViews());

        this.gameController = gameController;
        this.roundController = gameController.getRoundController();

        this.diceView = new DiceView();
        this.fieldsView = fieldsView;
        updateView();
    }

    /**
     * Setzt die Viewport-Instanz und initialisiert die zugehörigen Dice-Handler.
     *
     * @param viewport Der Viewport zur Darstellung.
     */
    public void setViewport(Viewport viewport) {
        this.viewport = viewport;
        this.diceValueUpdater = new DiceValueUpdater(viewport, diceView, pilotDice, copilotDice, this::onDiceValueChanged, this::onCoffeeInteraction);

        pilotHandler = new DicePosUpdater(diceView, pilotDice, fieldsView, viewport, coffeeManager, diceValueUpdater, true, roundController, gameController);
        copilotHandler = new DicePosUpdater(diceView, copilotDice, fieldsView,viewport, coffeeManager, diceValueUpdater, false, roundController, gameController);
    }

    /**
     * Gibt die Dice-View zurück.
     *
     * @return Die Instanz von {@link DiceView}.
     */
    public DiceView getDiceView(){
        return diceView;
    }

    /**
     * Gibt den Dice-Value-Updater zurück.
     *
     * @return Die Instanz von {@link DiceValueUpdater}.
     */
    public DiceValueUpdater getDiceValueUpdater() {
        return diceValueUpdater;
    }

    /**
     * Führt einen Würfelneuwurf für den Piloten oder Copiloten durch.
     *
     * @param isPilot Gibt an, ob der Neuwurf für den Piloten gilt.
     */
    public void rerollDice(boolean isPilot) {
        Dice[] diceToReroll = isPilot ? pilotDice : copilotDice;
        for (Dice dice : diceToReroll) {
            if (dice.isSelectedForReroll()) {
                dice.reroll();
                dice.setSelectedForReroll(false);
            }
        }
        updateView();
    }

    /**
     * Überprüft, ob Würfel für einen Neuwurf ausgewählt sind.
     *
     * @param isPilot Gibt an, ob die Überprüfung für den Pilotenwürfel gilt.
     * @return Wahr, wenn Würfel für einen Neuwurf ausgewählt sind.
     */
    public boolean isRerolling(boolean isPilot) {
        int numberOfDice = 0;
        Dice[] diceToReroll = isPilot ? pilotDice : copilotDice;
        for (Dice dice : diceToReroll) {
            if (dice.isSelectedForReroll()) {
                numberOfDice++;
            }
        }
        return numberOfDice!=0;
    }

    /**
     * Wird aufgerufen, wenn sich der Würfelwert geändert hat.
     */
    private void onDiceValueChanged() {
        resetSelections();
    }

    /**
     * Wird aufgerufen, wenn eine Interaktion mit Kaffeefeldern stattfindet.
     */
    private void onCoffeeInteraction() {
        coffeeManager.removeCoffee(diceValueUpdater.selectedCoffee);
    }

    /**
     * Setzt die Auswahl der Würfel zurück.
     */
    public void resetSelections() {
        pilotHandler.resetSelection();
        copilotHandler.resetSelection();
    }

    /**
     * Aktualisiert die Dice-View basierend auf den aktuellen Würfeldaten.
     */
    public void updateView() {
        diceView.updateSprites(pilotDice, copilotDice);
    }

    /**
     * Aktualisiert die Position der Dice.
     */
    public void updateHandler() {
        pilotHandler.update();
        copilotHandler.update();
    }

    /**
     * Zeichnet die Würfel und andere zugehörige Elemente auf dem Bildschirm.
     *
     * @param batch  Der SpriteBatch zur Darstellung.
     * @param startX Die Position in X-Richtung.
     * @param startY Die Position in Y-Richtung.
     */
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
}
