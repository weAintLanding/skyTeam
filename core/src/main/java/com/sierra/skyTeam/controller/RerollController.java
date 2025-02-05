package com.sierra.skyTeam.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sierra.skyTeam.model.RerollTokenModel;
import com.sierra.skyTeam.view.RerollButtonView;
import com.sierra.skyTeam.view.RerollMessageView;

/**
 * Der RerollController verwaltet die Logik für das Reroll von Würfeln im Spiel.
 * Er zeigt die Taste zum Neuwürfeln an, verarbeitet Klicks auf diese Taste
 * und verwaltet die Anzeige von Nachrichten für den Piloten und den Co-Piloten.
 */
public class RerollController {
    private final DiceController diceController;
    private final RerollTokenModel rerollTokenModel;
    private final RoundController roundController;
    private final RerollButtonView rerollButton;
    private final RerollMessageView rerollMessagePilot;
    private final RerollMessageView rerollMessageCopilot;
    private boolean isSelectingDice = false;
    private boolean rerollJustPlayed;
    private boolean pilotRerolled;

    /**
     * Konstruktor: Initialisiert den RerollController mit den benötigten Modellen und Views.
     *
     * @param diceController Der Controller für die Würfel.
     * @param rerollTokenModel Das Modell für den Reroll-Token.
     * @param roundController Der Controller für die Runde.
     */
    public RerollController(DiceController diceController, RerollTokenModel rerollTokenModel, RoundController roundController) {
        this.diceController = diceController;
        this.rerollTokenModel = rerollTokenModel;
        this.roundController = roundController;

        this.rerollButton = new RerollButtonView(this);

        this.rerollMessagePilot = new RerollMessageView();
        rerollMessagePilot.setPosition(-128,525);
        this.rerollMessageCopilot = new RerollMessageView();
        rerollMessageCopilot.setPosition(938,525);

        this.rerollJustPlayed = false;
    }

    /**
     * Schaltet die Sichtbarkeit der Reroll-Taste basierend auf dem aktuellen Status des Reroll-Tokens.
     */
    public void toggleVisibility() {
        boolean tokenOnBoard = rerollTokenModel.tokensOnBoard().contains(true);
        rerollButton.setVisibility(tokenOnBoard || rerollJustPlayed);
    }

    /**
     * Behandelt einen Klick auf die Neuwürfeln-Schaltfläche.
     *
     * @param touchX Die X-Position des Klicks.
     * @param touchY Die Y-Position des Klicks.
     */
    public void handleClick(float touchX, float touchY) {
        if(rerollButton.getBoundingRectangle().contains(touchX, touchY) && Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && rerollButton.isVisible()){
            if (!isSelectingDice) {
                if(rerollJustPlayed){
                    if (((roundController.getTurn() || pilotRerolled) && !(roundController.getTurn() && pilotRerolled))) {
                        isSelectingDice = true;
                        rerollButton.setVisibility(true);
                    }
                } else {
                    isSelectingDice = true;
                    rerollButton.setVisibility(true);
                }
            } else {
                if (diceController.isRerolling(roundController.getTurn())){
                    if(!rerollJustPlayed){
                        rerollTokenModel.markTokenAsUsed();
                    }
                    diceController.rerollDice(roundController.getTurn());
                    if (rerollJustPlayed) {
                        rerollMessagePilot.setVisibility(false);
                        rerollMessageCopilot.setVisibility(false);
                    }
                    rerollJustPlayed = !rerollJustPlayed;
                    if (rerollJustPlayed){
                        if(roundController.getTurn()){
                            rerollMessageCopilot.setVisibility(true);
                            pilotRerolled = true;
                        } else {
                            rerollMessagePilot.setVisibility(true);
                            pilotRerolled = false;
                        }
                    }
                    isSelectingDice = false;
                } else {
                    isSelectingDice = false;
                }
            }
        }
    }

    /**
     * Überprüft, ob der Cursor über der Reroll-Taste schwebt.
     *
     * @param touchX Die X-Position des Cursors.
     * @param touchY Die Y-Position des Cursors.
     * @return true, wenn der Cursor über der Schaltfläche schwebt, andernfalls false.
     */
    public boolean isHovered(float touchX, float touchY) {
        boolean tokenOnBoard = rerollTokenModel.tokensOnBoard().contains(true);
        if((tokenOnBoard || rerollJustPlayed) && rerollButton.isVisible()) {
            return rerollButton.getBoundingRectangle().contains(touchX, touchY);
        }
        return false;
    }

    /**
     * Gibt zurück, ob der Benutzer gerade Würfel auswählt.
     *
     * @return true, wenn der Benutzer Würfel auswählt, andernfalls false.
     */
    public boolean getSelectingDice() {
        return isSelectingDice;
    }

    /**
     * Gibt zurück, ob gerade ein Neuwurf gespielt wurde.
     *
     * @return true, wenn ein Neuwurf stattgefunden hat, andernfalls false.
     */
    public boolean getRerollJustPlayed() {
        return rerollJustPlayed;
    }

    /**
     * Setzt den Status für "rerollJustPlayed".
     *
     * @param rerollJustPlayed Der Status des Neuwurfs.
     */
    public void setRollJustPlayed(boolean rerollJustPlayed) {
        this.rerollJustPlayed = rerollJustPlayed;
    }

    /**
     * Gibt zurück, ob der Pilot den Neuwurf gemacht hat.
     *
     * @return true, wenn der Pilot den Neuwurf gemacht hat, andernfalls false.
     */
    public boolean getPilotRerolled() {
        return pilotRerolled;
    }

    /**
     * Gibt die Nachricht für den Piloten zurück.
     *
     * @return Die Nachricht für den Piloten.
     */
    public RerollMessageView getRerollMessagePilot() {
        return rerollMessagePilot;
    }

    /**
     * Gibt die Nachricht für den Co-Piloten zurück.
     *
     * @return Die Nachricht für den Co-Piloten.
     */
    public RerollMessageView getRerollMessageCopilot() {
        return rerollMessageCopilot;
    }

    /**
     * Zeichnet die Reroll-Taste und die entsprechenden Nachrichten für den Piloten und Co-Piloten.
     *
     * @param batch Das SpriteBatch, das für das Zeichnen der Grafiken verwendet wird.
     */
    public void draw(SpriteBatch batch) {
        rerollButton.draw(batch);
        if (rerollMessagePilot.isVisible()){
            rerollMessagePilot.draw(batch);
        }
        if (rerollMessageCopilot.isVisible()){
            rerollMessageCopilot.draw(batch);
        }
    }
}
