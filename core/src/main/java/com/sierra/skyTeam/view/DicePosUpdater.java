package com.sierra.skyTeam.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.sierra.skyTeam.model.Dice;

import java.util.List;

public class DicePosUpdater {
    enum State { SELECTING, PLACING }
    private State currentState = State.SELECTING;
    private final DiceView diceView;
    private boolean isPilot;
    private final Sprite[] diceSprites;
    private final Dice[] diceArray;
    private final List<FieldView> FieldViews;
    private final Viewport viewport;
    private Sprite selectedDice;
    private int lastClickedDiceValue = -1;
    private final CoffeeManager coffeeManager;

    public DicePosUpdater(DiceView diceView, Dice[] diceArray, List<FieldView> FieldViews,Viewport viewport, CoffeeManager coffeeManager, boolean isPilot) {
        this.diceView = diceView;
        this.diceArray = diceArray;
        this.isPilot = isPilot;
        this.diceSprites = isPilot ? diceView.getCurrentPilotDiceSprites() : diceView.getCurrentCopilotDiceSprites();
        this.FieldViews = FieldViews;
        this.viewport = viewport;
        this.coffeeManager = coffeeManager;
    }

    public void update() {
        Vector2 coordinates = InputHandler.scaledInput(viewport);
        float touchX = coordinates.x;
        float touchY = coordinates.y;

        handleHoverEffect(touchX, touchY);

        if (selectedDice != null) {
            handleSelectedEffect();
        }

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            switch (currentState) {
                case SELECTING:
                    handleDiceSelection(touchX, touchY);
                    break;
                case PLACING:
                    handleDiceSelection(touchX, touchY);
                    handleFieldPlacement(touchX, touchY);
                    break;
            }
        }
    }

    private void handleHoverEffect(float touchX, float touchY) {
        for (int i = 0; i < diceSprites.length; i++) {
            if (diceArray[i].isPlaced()) {
                continue;
            }

            if (diceSprites[i].getBoundingRectangle().contains(touchX, touchY)) {
                diceSprites[i].setColor(1, 1, 1, 1);
            } else {
                diceSprites[i].setColor(1, 1, 1, 0.7F);
            }
        }
    }

    private void handleSelectedEffect() {
        selectedDice.setColor(1, 1, 1, 1);
    }

    private void handleDiceSelection(float touchX, float touchY) {
        for (int i = 0; i < diceSprites.length; i++) {
            if (!diceArray[i].isPlaced() && diceSprites[i].getBoundingRectangle().contains(touchX, touchY)) {
                selectedDice = diceSprites[i];
                lastClickedDiceValue = diceArray[i].getDiceValue();
                System.out.println(lastClickedDiceValue + " dice selected");
                System.out.println(diceArray[i].isPlaced());
                selectedDice.setColor(1, 1, 1, 1);
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
                            for (int i = 0; i < diceSprites.length; i++) {
                                if (diceSprites[i] == selectedDice) {
                                    diceArray[i].setPlaced(true); // No need to recheck isPilot
                                    System.out.println(diceArray[i].isPlaced());
                                    break;
                                }
                            }
                            resetSelection();
                            break;
                        } else {
                            System.out.println("Field is occupied");
                        }
                    } else {
                        System.out.println("Dice value not allowed in this field.");
                        resetSelection();
                        break;
                    }
                } else {
                    if (!field.isOccupied) {
                        if(isCoffeeField(field)) {
                            handleCoffeeFieldPlacement(field);
                        }
                        field.placeDiceOnField(selectedDice);
                        System.out.println("Placing dice");

                        for (int i = 0; i < diceSprites.length; i++) {
                            if (diceSprites[i] == selectedDice) {
                                diceArray[i].setPlaced(true); // No need to recheck isPilot
                                break;
                            }
                        }
                        resetSelection();
                        break;
                    } else {
                        System.out.println("Dice value not allowed in this field.");
                    }
                }
            }
        }
    }

    private void handleCoffeeFieldPlacement(FieldView field){
        if(!field.isOccupied){
            coffeeManager.placeCoffee();
            System.out.println("Dice placed in coffee field");
        }
    }

    private boolean isCoffeeField(FieldView field) {
        return coffeeManager.coffeeFields.contains(field);
    }

    private void resetSelection() {
        selectedDice = null;
        lastClickedDiceValue = -1;
        currentState = State.SELECTING;
    }

    public void dispose() {
    }
}
