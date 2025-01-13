package com.sierra.skyTeam.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import com.sierra.skyTeam.model.RerollTokenModel;

public class RerollController {
    private final DiceController diceController;
    private final Rectangle rerollButtonBounds;
    private final RerollTokenModel rerollTokenModel;
    private final RoundController roundController;

    public RerollController(DiceController diceController, RerollTokenModel rerollTokenModel, RoundController roundController) {
        this.diceController = diceController;
        this.rerollTokenModel = rerollTokenModel;
        this.roundController = roundController;

        rerollButtonBounds = new Rectangle(235, 30, 150, 50);
    }

    public void handleClick(float touchX, float touchY) {
        if(rerollButtonBounds.contains(touchX, touchY) && Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
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
        if(tokenOnBoard) {
            return rerollButtonBounds.contains(touchX, touchY);
        }
        return false;
    }
}
