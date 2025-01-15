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
    private final RerollMessageView rerollMessage;

    public RerollController(DiceController diceController, RerollTokenModel rerollTokenModel, RoundController roundController) {
        this.diceController = diceController;
        this.rerollTokenModel = rerollTokenModel;
        this.roundController = roundController;
        this.rerollButton = new RerollButtonView();
        this.rerollMessage = new RerollMessageView();
        //rerollMessage.setPosition(-128, 525); pilot
        rerollMessage.setPosition(938,525);
    }

    public void toggleVisibility() {
        boolean tokenOnBoard = rerollTokenModel.tokensOnBoard().contains(true);
        rerollButton.setVisibility(tokenOnBoard);
    }

    public void handleClick(float touchX, float touchY) {
        if(rerollButton.getBoundingRectangle().contains(touchX, touchY) && Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && rerollButton.isVisible()){
            boolean tokenOnBoard = rerollTokenModel.tokensOnBoard().contains(true);
            if(tokenOnBoard){
                rerollTokenModel.markTokenAsUsed();
                diceController.rerollDice(roundController.getTurn());
                //this needs to change according to end turn, which I can try implementing tomorrow.
            }
        }
    }

    public boolean isHovered(float touchX, float touchY) {
        boolean tokenOnBoard = rerollTokenModel.tokensOnBoard().contains(true);
        if(tokenOnBoard && rerollButton.isVisible()) {
            return rerollButton.getBoundingRectangle().contains(touchX, touchY);
        }
        return false;
    }

    public void draw(SpriteBatch batch) {
        rerollButton.draw(batch);
        rerollMessage.draw(batch);
    }
}
