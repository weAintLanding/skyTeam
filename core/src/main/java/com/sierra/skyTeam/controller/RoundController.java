package com.sierra.skyTeam.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sierra.skyTeam.MainGame;
import com.sierra.skyTeam.model.Dice;
import com.sierra.skyTeam.model.EndRound;
import com.sierra.skyTeam.model.EndTurn;
import com.sierra.skyTeam.model.GameModel;
import com.sierra.skyTeam.screens.CrashScreen;
import com.sierra.skyTeam.screens.LandedScreen;
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

    private final EndTurn endTurn;
    private final EndTurnView endTurnPilot;
    private final EndTurnView endTurnCopilot;
    private final EndRound endRound;

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

        this.endTurn = new EndTurn(this);

        endTurnPilot = endTurn.getEndTurnButtons().get(0);
        endTurnCopilot = endTurn.getEndTurnButtons().get(1);

        this.endRound = new EndRound(this);

        this.gameModel.getAltitudeTrack().descend();
    }

    public void pilotEndTurn() {
        if (numberOfPilotDicePlayed < gameModel.pilotDicePlaced()) {
            numberOfPilotDicePlayed++;
            if(gameModel.copilotDicePlaced() != 4){
                RerollController rerollController = gameController.getRerollController();
                this.switchTurn();
                this.copilotDicePlaced = false;

                if (rerollController.getRerollJustPlayed() && !rerollController.getPilotRerolled()) {
                    rerollController.setRollJustPlayed(false);
                    rerollController.getRerollMessagePilot().setVisibility(false);
                }

                endTurnPilot.setVisibility(isPilotTurn);
            } else {
                this.pilotDicePlaced = false;

                endTurnPilot.setVisibility(!isPilotTurn);
                endRound.getEndRoundView().setVisibility(true);
            }
        } else {
            System.out.println("Place a dice to end turn.");
        }
    }

    public void copilotEndTurn() {
        if (numberOfCopilotDicePlayed < gameModel.copilotDicePlaced()) {
            numberOfCopilotDicePlayed++;
            if(gameModel.pilotDicePlaced() != 4){
                RerollController rerollController = gameController.getRerollController();
                this.switchTurn();
                this.pilotDicePlaced = false;

                if (rerollController.getRerollJustPlayed() && rerollController.getPilotRerolled()) {
                    rerollController.setRollJustPlayed(false);
                    rerollController.getRerollMessageCopilot().setVisibility(false);
                }

                endTurnCopilot.setVisibility(!isPilotTurn);
            } else {
                this.copilotDicePlaced = false;

                endTurnCopilot.setVisibility(isPilotTurn);
                endRound.getEndRoundView().setVisibility(true);
            }
        } else {
            System.out.println("Place a dice to end turn.");
        }
    }

    public void switchTurn() {
        isPilotTurn = !isPilotTurn;
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
        endTurnPilot.setVisibility(true);
    }
    public void setCopilotDicePlacedTrue() {
        copilotDicePlaced = true;
        endTurnCopilot.setVisibility(true);
    }


    public void endRound() {
        if(gameModel.pilotDicePlaced() == 4 && gameModel.copilotDicePlaced() == 4){
            System.out.println("Round has been ended.");
            gameModel.checkRoundConditions();
            gameModel.roundReset();
            this.roundViewReset();
            if(gameController.getAltitudeController().getRound() == 7){
                if(gameModel.getAirplane().getApproachPosition() == 6) {
                    System.out.println("Last Round");
                    if (gameModel.checkWin()) {
                        mainGame.setScreen(new LandedScreen(mainGame));
                    }
                } else {
                    System.out.println("Crash-landed before reaching airport.");
                    mainGame.setScreen(new CrashScreen(mainGame));
                }
            }
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

        endRound.getEndRoundView().setVisibility(false);
    }

    public EndTurn getEndTurn() {
        return endTurn;
    }
    public EndRound getEndRound() {
        return endRound;
    }

    public void draw(SpriteBatch batch) {
        endTurn.draw(batch);
        endRound.draw(batch);
    }
}
