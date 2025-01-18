package com.sierra.skyTeam.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sierra.skyTeam.controller.RoundController;
import com.sierra.skyTeam.view.EndRoundView;

public class EndRound {
    private final EndRoundView endRound;
    private final RoundController roundController;
    public EndRound(RoundController roundController) {
        this.roundController = roundController;
        endRound = new EndRoundView();
        endRound.setVisibility(false);
    }

    public boolean isHovered(float touchX, float touchY) {
        return (endRound.getBoundingRectangle().contains(touchX, touchY) && endRound.isVisible());
    }

    public void handleClick(float touchX, float touchY) {
        if(endRound.getBoundingRectangle().contains(touchX, touchY) && Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && endRound.isVisible()) {
            roundController.endRound();
        }
    }

    public EndRoundView getEndRoundView() {
        return endRound;
    }

    public void draw(SpriteBatch batch) {
        endRound.draw(batch);
    }
}
