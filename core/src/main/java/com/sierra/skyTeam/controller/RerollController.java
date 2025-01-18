package com.sierra.skyTeam.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sierra.skyTeam.model.RerollTokenModel;
import com.sierra.skyTeam.view.RerollButtonView;
import com.sierra.skyTeam.view.RerollMessageView;

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

    public void toggleVisibility() {
        boolean tokenOnBoard = rerollTokenModel.tokensOnBoard().contains(true);
        rerollButton.setVisibility(tokenOnBoard || rerollJustPlayed);
    }

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

    public boolean isHovered(float touchX, float touchY) {
        boolean tokenOnBoard = rerollTokenModel.tokensOnBoard().contains(true);
        if((tokenOnBoard || rerollJustPlayed) && rerollButton.isVisible()) {
            return rerollButton.getBoundingRectangle().contains(touchX, touchY);
        }
        return false;
    }

    public boolean getSelectingDice() {
        return isSelectingDice;
    }

    public void draw(SpriteBatch batch) {
        rerollButton.draw(batch);
        if (rerollMessagePilot.isVisible()){
            rerollMessagePilot.draw(batch);
        }
        if (rerollMessageCopilot.isVisible()){
            rerollMessageCopilot.draw(batch);
        }
    }

    public boolean getRerollJustPlayed() {
        return rerollJustPlayed;
    }

    public boolean getPilotRerolled() {
        return pilotRerolled;
    }

    public void setRollJustPlayed(boolean rerollJustPlayed) {
        this.rerollJustPlayed = rerollJustPlayed;
    }

    public RerollMessageView getRerollMessagePilot() {
        return rerollMessagePilot;
    }
    public RerollMessageView getRerollMessageCopilot() {
        return rerollMessageCopilot;
    }
}
