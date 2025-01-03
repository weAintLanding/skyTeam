package com.sierra.skyTeam.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.sierra.skyTeam.model.Dice;

import java.util.List;

public class DicePosUpdater {
    enum State { SELECTING, PLACING }
    private State currentState = State.SELECTING;
    private final DiceView diceView;
    private final Sprite[] diceSprites;
    private final Dice[] diceArray;
    private final List<FieldView> FieldViews;
    private final Viewport viewport;
    private Sprite selectedDice;
    private int lastClickedDiceValue = -1;

    public DicePosUpdater(DiceView diceView, Dice[] diceArray, List<FieldView> FieldViews, Viewport viewport, boolean isPilot) {
        this.diceView = diceView;
        this.diceArray = diceArray;
        this.diceSprites = isPilot ? diceView.getCurrentPilotDiceSprites() : diceView.getCurrentCopilotDiceSprites();
        this.FieldViews = FieldViews;
        this.viewport = viewport;
    }

    public void update() {
        Vector2 coordinates = InputHandler.scaledInput(viewport);
        float touchX = coordinates.x;
        float touchY = coordinates.y;

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            switch (currentState) {
                case SELECTING:
                    handleDiceSelection(touchX, touchY);
                    break;
                case PLACING:
                    handleFieldPlacement(touchX, touchY);
                    break;
            }
        }
    }

    private void handleDiceSelection(float touchX, float touchY) {
        for (int i = 0; i < diceSprites.length; i++) {
            if (!diceArray[i].isPlaced() && diceSprites[i].getBoundingRectangle().contains(touchX, touchY)) {
                selectedDice = diceSprites[i];
                lastClickedDiceValue = diceArray[i].getDiceValue();
                System.out.println(lastClickedDiceValue + " dice selected");
                System.out.println(diceArray[i].isPlaced());
                currentState = State.PLACING;
                break;
            }
        }
    }

    private void handleFieldPlacement(float touchX, float touchY) {
        for (FieldView field : FieldViews) {
            if (field.getBounds().contains(touchX, touchY)) {
                if(field.hasSwitch){
                    System.out.println("Field with switch");
                    if (field.isDiceAllowed(lastClickedDiceValue)) {
                        if (!field.isOccupied) {
                            field.placeDiceOnField(selectedDice);
                            System.out.println("Placing dice");
                            if (field.hasSwitch() && !field.isSwitchOn()) { // Toggle the switch only if it has one and it's off
                                field.toggleSwitch();
                            }
                            for (int i = 0; i < diceSprites.length; i++) {
                                if (diceSprites[i] == selectedDice) {
                                    diceArray[i].setPlaced(true); // No need to recheck isPilot
                                    System.out.println(diceArray[i].isPlaced());
                                    break;
                                }
                            }

                            selectedDice = null;
                            lastClickedDiceValue = -1;
                            currentState = State.SELECTING;
                            break;
                        }
                    } else {
                        System.out.println("Dice value not allowed in this field.");
                    }
                } else {
                    if (!field.isOccupied) {
                        field.placeDiceOnField(selectedDice);
                        System.out.println("Placing dice");

                        for (int i = 0; i < diceSprites.length; i++) {
                            if (diceSprites[i] == selectedDice) {
                                diceArray[i].setPlaced(true); // No need to recheck isPilot
                                break;
                            }
                        }

                        selectedDice = null;
                        lastClickedDiceValue = -1;
                        currentState = State.SELECTING;
                        break;
                    } else {
                        System.out.println("Dice value not allowed in this field.");
                    }
                }
            }
        }
    }

    private void resetSelection() {
        selectedDice = null;
        lastClickedDiceValue = -1;
        currentState = State.SELECTING;
    }
}
