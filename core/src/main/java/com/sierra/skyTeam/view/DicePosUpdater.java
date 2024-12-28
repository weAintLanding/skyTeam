package com.sierra.skyTeam.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.List;

public class DicePosUpdater {
    //more efficient than booleans apparently
    enum State { SELECTING, PLACING }
    private State currentState = State.SELECTING;
    Sprite[] diceSprites;
    Dice dice;
    List<Field> fields;
    Sprite selectedDice;
    private int lastClickedDiceValue = -1;
    Viewport viewport;
    boolean[] isDiceMovable;

    public DicePosUpdater(Dice dice, List<Field> fields, Viewport viewport, boolean isPilot) {
        this.dice = dice;
        this.diceSprites = isPilot ? dice.getCurrentPilotDiceSprites() : dice.getCurrentCopilotDiceSprites();
        this.isDiceMovable = isPilot ? dice.getIsPilotDiceMovable() : dice.getIsCopilotDiceMovable();
        this.fields = fields;
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
            Sprite sprite = diceSprites[i];

            if(isDiceMovable[i]) {
                if (sprite.getBoundingRectangle().contains(touchX, touchY)) {
                    selectedDice = sprite;
                    lastClickedDiceValue = dice.getDiceValue(touchX, touchY);
                    System.out.println(lastClickedDiceValue + " dice selected");
                    currentState = State.PLACING;
                    break;
                }
            }
        }
    }

    private void handleFieldPlacement(float touchX, float touchY) {
        for (Field field : fields) {
            if (field.getBounds().contains(touchX, touchY)) {
                if(field.hasSwitch){
                    if (field.isDiceAllowed(lastClickedDiceValue)) {
                        if (!field.isOccupied) {
                            field.placeDiceOnField(selectedDice);
                            System.out.println("Placing dice");

                            for (int i = 0; i < diceSprites.length; i++) {
                                if (diceSprites[i] == selectedDice) {
                                    isDiceMovable[i] = false; // No need to recheck isPilot
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
                                isDiceMovable[i] = false; // No need to recheck isPilot
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
}
