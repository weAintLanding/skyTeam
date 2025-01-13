package com.sierra.skyTeam.controller;

import com.sierra.skyTeam.MainGame;
import com.sierra.skyTeam.model.Dice;
import com.sierra.skyTeam.model.GameModel;
import com.sierra.skyTeam.screens.CrashScreen;
import com.sierra.skyTeam.screens.LandedScreen;
import com.sierra.skyTeam.view.EndRound;
import com.sierra.skyTeam.view.EndTurn;
import com.sierra.skyTeam.view.EndTurnView;
import com.sierra.skyTeam.view.FieldView;

import java.util.List;

public class RoundController {
    private final GameController gameController;
    private final GameModel gameModel;
    private final List<FieldView> fieldViews;
    private final MainGame mainGame;

    private int numberOfPilotDicePlayed;
    private int numberOfCopilotDicePlayed;

    private boolean isPilotTurn;

    private boolean pilotDicePlaced;
    private boolean copilotDicePlaced;

    public RoundController(GameController gameController, GameModel gameModel, List<FieldView> fieldViews, MainGame game) {
        this.gameController = gameController;
        this.gameModel = gameModel;
        this.fieldViews = fieldViews;
        this.mainGame = game;

        this.numberOfPilotDicePlayed = 0;
        this.numberOfCopilotDicePlayed = 0;

        this.isPilotTurn = true;

        this.pilotDicePlaced = false;
        this.copilotDicePlaced = false;
        this.

        gameModel.getAltitudeTrack().descend();
    }

    public void pilotEndTurn(EndTurnView currentButton, EndTurnView otherButton) {
        if (numberOfPilotDicePlayed < gameModel.pilotDicePlaced()) {
            numberOfPilotDicePlayed++;
            System.out.println("Pilot Ended Turn");
            if(gameModel.copilotDicePlaced() != 4){
                this.switchTurn();
                this.copilotDicePlaced = false;

                currentButton.setVisibility(isPilotTurn);
                otherButton.setVisibility(!isPilotTurn);
            } else {
                this.pilotDicePlaced = false;
                currentButton.setVisibility(isPilotTurn);
            }
        } else {
            System.out.println("Place a dice to end turn.");
        }
    }

    public void copilotEndTurn(EndTurnView currentButton, EndTurnView otherButton) {
        if (numberOfCopilotDicePlayed < gameModel.copilotDicePlaced()) {
            numberOfCopilotDicePlayed++;
            System.out.println("Co-Pilot Ended Turn");
            if(gameModel.pilotDicePlaced() != 4){
                this.switchTurn();
                this.pilotDicePlaced = false;

                currentButton.setVisibility(!isPilotTurn);
                otherButton.setVisibility(isPilotTurn);
            } else {
                this.copilotDicePlaced = false;
                currentButton.setVisibility(!isPilotTurn);
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
            if(gameController.getAltitudeController().getRound() == 7){
                if(gameModel.getAirplane().getApproachPosition() == 6) {
                    System.out.println("Last Round");
                    if (gameModel.checkWin()) {
                        mainGame.setScreen(new LandedScreen(mainGame));
                    }
                } else {
                    System.out.println("Landed before reaching airport.");
                    mainGame.setScreen(new CrashScreen(mainGame));
                }
            }
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
