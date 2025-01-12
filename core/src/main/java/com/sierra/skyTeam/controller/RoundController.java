package com.sierra.skyTeam.controller;

import com.sierra.skyTeam.model.Dice;
import com.sierra.skyTeam.model.GameModel;
import com.sierra.skyTeam.view.EndRound;
import com.sierra.skyTeam.view.EndTurn;
import com.sierra.skyTeam.view.FieldView;

import java.util.List;

public class RoundController {
    private final GameController gameController;
    private final GameModel gameModel;
    private final List<FieldView> fieldViews;

    private int numberOfPilotDicePlayed;
    private int numberOfCopilotDicePlayed;

    private boolean isPilotTurn;

    private boolean pilotDicePlaced;
    private boolean copilotDicePlaced;

    public RoundController(GameController gameController, GameModel gameModel, List<FieldView> fieldViews) {
        this.gameController = gameController;
        this.gameModel = gameModel;
        this.fieldViews = fieldViews;

        this.numberOfPilotDicePlayed = 0;
        this.numberOfCopilotDicePlayed = 0;

        this.isPilotTurn = true;

        this.pilotDicePlaced = false;
        this.copilotDicePlaced = false;

        gameModel.getAltitudeTrack().descend();
    }

    public void pilotEndTurn() {
        if (numberOfPilotDicePlayed < gameModel.pilotDicePlaced()) {
            numberOfPilotDicePlayed++;
            System.out.println("Pilot Ended Turn");
            if(gameModel.copilotDicePlaced() != 4){
                this.switchTurn();
                this.copilotDicePlaced = false;
            } else {
                this.pilotDicePlaced = false;
            }
        } else {
            System.out.println("Place a dice to end turn.");
        }
    }

    public void copilotEndTurn() {
        if (numberOfCopilotDicePlayed < gameModel.copilotDicePlaced()) {
            numberOfCopilotDicePlayed++;
            System.out.println("Co-Pilot Ended Turn");
            if(gameModel.pilotDicePlaced() != 4){
                this.switchTurn();
                this.pilotDicePlaced = false;
            } else {
                this.copilotDicePlaced = false;
            }
        } else {
            System.out.println("Place a dice to end turn.");
        }
    }

    public void switchTurn() {
        isPilotTurn = !isPilotTurn;
        System.out.println(isPilotTurn ? "Pilot's Turn" : "Co-Pilot's Turn");
    }

    public boolean getTurn() {
        return isPilotTurn;
    }

    public boolean getPilotDicePlaced() {
        return pilotDicePlaced;
    }
    public boolean getCopilotDicePlaced() {
        return copilotDicePlaced;
    }

    public void setPilotDicePlacedTrue() {
        pilotDicePlaced = true;
    }
    public void setCopilotDicePlacedTrue() {
        copilotDicePlaced = true;
    }


    public void endRound() {
        if(gameModel.pilotDicePlaced() == 4 && gameModel.copilotDicePlaced() == 4){
            System.out.println("Round has been ended.");
            gameModel.checkRoundConditions();
            gameModel.roundReset();
            this.roundViewReset();
            this.numberOfPilotDicePlayed = 0;
            this.numberOfCopilotDicePlayed = 0;
        } else {
            System.out.println("Place all your dice to end round.");
        }
    }

    public void roundViewReset() {
        for (Dice dice: gameModel.getPilot().getDiceList()) {
            dice.setPlaced(false);
        }
        for (Dice dice: gameModel.getCoPilot().getDiceList()) {
            dice.setPlaced(false);
        }
        gameController.getDiceController().updateView();

        gameController.getAltitudeController().nextRound();

        for(FieldView fieldView: fieldViews){
            fieldView.getFieldModel().removeDice();
        }

        gameController.getAxisController().roundReset();
        gameController.getEngineController().roundReset();
        gameController.getRadioController().roundReset();
    }
}
