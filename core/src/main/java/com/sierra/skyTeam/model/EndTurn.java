package com.sierra.skyTeam.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sierra.skyTeam.controller.RoundController;
import com.sierra.skyTeam.view.EndTurnView;

import java.util.ArrayList;
import java.util.List;

public class EndTurn {
    private final RoundController roundController;
    private final List<EndTurnView> endTurnButtons;

    public EndTurn(RoundController roundController) {
        this.roundController = roundController;
        endTurnButtons = new ArrayList<>();
        endTurnButtons.add(new EndTurnView(-100, -40));     //pilot
        endTurnButtons.add(new EndTurnView(965, -40));   //copilot
        //can use index value to figure out which button was pressed
    }
    //endRound rectangle bounds are 900, 30, 150, 50;

    public boolean isHovered(float touchX, float touchY) {
        for(EndTurnView endTurnView : endTurnButtons) {
            if(endTurnView.getBoundingRectangle().contains(touchX, touchY) && endTurnView.isVisible()){
                return true;
            }
        }
        return false;
    }

    public void handleClick(float touchX, float touchY) {
        for(int i = 0; i < endTurnButtons.size(); i++){
            EndTurnView endTurnView = endTurnButtons.get(i);
            if(endTurnView.getBoundingRectangle().contains(touchX, touchY) && Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && endTurnView.isVisible()) {
                int otherIndex = (i == 0) ? 1 : 0;
                EndTurnView otherButton = endTurnButtons.get(otherIndex);

                if(roundController.getTurn()) {
                    roundController.pilotEndTurn(endTurnView, otherButton);

                } else {
                    roundController.copilotEndTurn(endTurnView, otherButton);
                }
            }
        }
    }

    public List<EndTurnView> getEndTurnButtons() {
        return endTurnButtons;
    }

    public void draw(SpriteBatch batch) {
        for(EndTurnView endTurn : endTurnButtons) {
            if(endTurn.isVisible()){
                endTurn.draw(batch);
            }
        }
    }
}
