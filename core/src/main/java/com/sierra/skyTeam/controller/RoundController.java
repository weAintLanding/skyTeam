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

/**
 * Der RoundController verwaltet die Logik für den Ablauf eines Runden im Spiel.
 * Er steuert die Platzierung von Würfeln durch den Piloten und Co-Piloten, das Ende der Runde
 * und den Wechsel der Spielrunden.
 */
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

    /**
     * Konstruktor: Initialisiert den RoundController mit den erforderlichen Modellen und Views.
     *
     * @param gameController Die Controller des Spiels.
     * @param gameModel Das Modell des Spiels.
     * @param fieldViews Die Ansichten der Felder.
     * @param game Die MainGame-Instanz.
     */
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

    /**
     * Beendet den Turn des Piloten und wechselt zu dem des Co-Piloten.
     */
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

    /**
     * Beendet den Turn des Co-Piloten und wechselt zu dem des Piloten.
     */
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

    /**
     * Wechselt den Turn von Pilot zu Co-Pilot und umgekehrt.
     */
    public void switchTurn() {
        isPilotTurn = !isPilotTurn;
    }

    /**
     * Gibt zurück, ob der Pilot am Zug ist.
     *
     * @return true, wenn der Pilot am Zug ist, andernfalls false.
     */
    public boolean getTurn() {
        return isPilotTurn;
    }

    /**
     * Gibt zurück, ob der Pilot einen Würfel platziert hat.
     *
     * @return true, wenn der Pilot einen Würfel platziert hat, andernfalls false.
     */
    public boolean getPilotDicePlaced() {
        return pilotDicePlaced;
    }

    /**
     * Gibt zurück, ob der Co-Pilot einen Würfel platziert hat.
     *
     * @return true, wenn der Co-Pilot einen Würfel platziert hat, andernfalls false.
     */
    public boolean getCopilotDicePlaced() {
        return copilotDicePlaced;
    }

    /**
     * Setzt den Status für das Platzieren eines Würfels durch den Piloten.
     */
    public void setPilotDicePlacedTrue() {
        pilotDicePlaced = true;
        endTurnPilot.setVisibility(true);
    }

    /**
     * Setzt den Status für das Platzieren eines Würfels durch den Co-Piloten.
     */
    public void setCopilotDicePlacedTrue() {
        copilotDicePlaced = true;
        endTurnCopilot.setVisibility(true);
    }

    /**
     * Beendet die aktuelle Runde, prüft, ob alle Rundenbedingungen erfüllt sind
     * und setzt alle relevanten Spielzustände zurück.
     */
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

    /**
     * Setzt alle relevanten Views und Modelle für die nächste Runde zurück.
     */
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

    /**
     * Gibt das Objekt für die EndTurn-Taste zurück.
     *
     * @return Das EndTurn-Objekt.
     */
    public EndTurn getEndTurn() {
        return endTurn;
    }

    /**
     * Gibt das Objekt für die EndRound-Taste zurück.
     *
     * @return Das EndRound-Objekt.
     */
    public EndRound getEndRound() {
        return endRound;
    }

    /**
     * Zeichnet die EndTurn- und EndRound-Ansichten.
     *
     * @param batch Das SpriteBatch für die Zeichnung.
     */
    public void draw(SpriteBatch batch) {
        endTurn.draw(batch);
        endRound.draw(batch);
    }
}
