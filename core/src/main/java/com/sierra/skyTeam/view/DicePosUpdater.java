package com.sierra.skyTeam.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sierra.skyTeam.model.Dice;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.List;

public class DicePosUpdater {
    enum State { SELECTING, PLACING }
    private State currentState = State.SELECTING;
    private final Sprite[] diceSprites;
    private final Dice[] diceArray;
    private final boolean[] isDiceMovable;
    private final List<Field> fields;
    private final Viewport viewport;
    private Sprite selectedDice;
    private int lastClickedDiceValue = -1;

    public DicePosUpdater(Dice[] diceArray, Sprite[] diceSprites, List<Field> fields, Viewport viewport, boolean[] isDiceMovable) {
        this.diceArray = diceArray;
        this.diceSprites = diceSprites;
        this.fields = fields;
        this.viewport = viewport;
        this.isDiceMovable = isDiceMovable;
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
            if (isDiceMovable[i] && diceSprites[i].getBoundingRectangle().contains(touchX, touchY)) {
                selectedDice = diceSprites[i];
                lastClickedDiceValue = diceArray[i].getDiceValue();
                currentState = State.PLACING;
                break;
            }
        }
    }

    private void handleFieldPlacement(float touchX, float touchY) {
        for (Field field : fields) {
            if (field.getBounds().contains(touchX, touchY) && !field.isOccupied) {
                field.placeDiceOnField(selectedDice);
                for (int i = 0; i < diceSprites.length; i++) {
                    if (diceSprites[i] == selectedDice) {
                        isDiceMovable[i] = false;
                        break;
                    }
                }
                resetSelection();
                break;
            }
        }
    }

    private void resetSelection() {
        selectedDice = null;
        lastClickedDiceValue = -1;
        currentState = State.SELECTING;
    }
}
