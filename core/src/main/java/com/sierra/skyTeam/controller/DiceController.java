package com.sierra.skyTeam.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sierra.skyTeam.model.Dice;
import com.sierra.skyTeam.view.DiceView;

public class DiceController {
    private final Dice[] pilotDice;
    private final Dice[] coPilotDice;
    private final DiceView diceView;

    public DiceController(Dice[] pilotDice, Dice[] coPilotDice, DiceView diceView) {
        this.pilotDice = pilotDice;
        this.coPilotDice = coPilotDice;
        this.diceView = diceView;
        updateView(); // Sync initial state
    }

    public void rerollDice(boolean isPilot) {
        Dice[] diceToReroll = isPilot ? pilotDice : coPilotDice;
        for (Dice dice : diceToReroll) {
            dice.reroll();
        }
        updateView();
    }

    public void updateView() {
        diceView.updateSprites(pilotDice, coPilotDice);
    }

    public void render(SpriteBatch batch, float startX, float startY) {
        // Render Pilot dice
        diceView.render(batch, true, startX, startY);

        // Render CoPilot dice with an offset
        diceView.render(batch, false, startX + 1070, startY);
    }
}
