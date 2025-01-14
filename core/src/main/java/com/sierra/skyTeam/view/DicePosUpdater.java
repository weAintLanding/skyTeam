package com.sierra.skyTeam.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.sierra.skyTeam.controller.RoundController;
import com.sierra.skyTeam.model.Dice;
import com.sierra.skyTeam.model.FieldModel;

import java.util.List;

public class DicePosUpdater {
    enum State { SELECTING, PLACING }
    private State currentState = State.SELECTING;
    private final CoffeeManager coffeeManager;
    private final boolean isPilot;
    private final Sprite[] diceSprites;
    private final Dice[] diceArray;
    private final List<FieldView> FieldViews;
    private final Viewport viewport;
    private Sprite selectedDice;
    private int lastClickedDiceValue = -1;
    private CoffeeView selectedCoffee = null;
    DiceValueUpdater diceValueUpdater;
    RoundController roundController;
    private boolean dicePlayed;

    public DicePosUpdater(DiceView diceView, Dice[] diceArray, List<FieldView> FieldViews, Viewport viewport, CoffeeManager coffeeManager, DiceValueUpdater diceValueUpdater, boolean isPilot, RoundController roundController) {
        this.diceArray = diceArray;
        this.isPilot = isPilot;
        this.diceSprites = isPilot ? diceView.getCurrentPilotDiceSprites() : diceView.getCurrentCopilotDiceSprites();
        this.FieldViews = FieldViews;
        this.viewport = viewport;
        this.coffeeManager = coffeeManager;
        this.diceValueUpdater = diceValueUpdater;
        this.roundController = roundController;
        this.dicePlayed = isPilot ? roundController.getPilotDicePlaced() : roundController.getCopilotDicePlaced();
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
            handleInput(touchX, touchY);
        }
    }

    private void handleInput(float touchX, float touchY) {
        if(isPilot){
            this.dicePlayed = roundController.getPilotDicePlaced();
        } else {
            this.dicePlayed = roundController.getCopilotDicePlaced();
        }
        if (dicePlayed || (roundController.getTurn() && !isPilot) || (!(roundController.getTurn()) && isPilot)) {
            return;
        }

        for (CoffeeView coffee : coffeeManager.getCoffees()) {
            if (coffee.getSprite().getBoundingRectangle().contains(touchX, touchY)  && coffee.isAvailable()) {
                selectedCoffee = coffee;
                System.out.println("Coffee clicked");
            }
        }

        for (int i = 0; i < diceSprites.length; i++) {
            if (!diceArray[i].isPlaced() && diceSprites[i].getBoundingRectangle().contains(touchX, touchY)) {
                selectedDice = diceSprites[i];
                lastClickedDiceValue = diceArray[i].getDiceValue();
                currentState = State.PLACING;
                selectedDice.setColor(1, 1, 1, 1);
                break;
            }
        }
        if (selectedCoffee != null && selectedDice != null) {
            diceValueUpdater.setSelectedDice(selectedDice);
            diceValueUpdater.setSelectedCoffee(selectedCoffee);
            diceValueUpdater.showOptions();
        }

        if (currentState == State.PLACING) {
            handleFieldPlacement(touchX, touchY);
        }
    }

    private void handleHoverEffect(float touchX, float touchY) {
        if(isPilot){
            this.dicePlayed = roundController.getPilotDicePlaced();
        } else {
            this.dicePlayed = roundController.getCopilotDicePlaced();
        }

        for (int i = 0; i < diceSprites.length; i++) {
            if (diceArray[i].isPlaced()) {
                continue;
            }

            if((roundController.getTurn() && isPilot && (!roundController.getPilotDicePlaced())) || (!(roundController.getTurn()) && !isPilot && (!roundController.getCopilotDicePlaced())) ) {
                if (diceSprites[i].getBoundingRectangle().contains(touchX, touchY)  && !dicePlayed) {
                    diceSprites[i].setColor(1, 1, 1, 1);
                } else {
                    diceSprites[i].setColor(1, 1, 1, 0.7F);
                }
            } else {
                diceSprites[i].setColor(1, 1, 1, 0F);
            }
        }
    }

    private void handleSelectedEffect() {
        selectedDice.setColor(1, 1, 1, 1);
    }

    private Dice getDiceFromSprite(Sprite sprite) {
        for (int i = 0; i < diceSprites.length; i++) {
            if (diceSprites[i] == sprite) {
                return diceArray[i];
            }
        }
        return null;
    }

    private void handleFieldPlacement(float touchX, float touchY) {
        for (FieldView field : FieldViews) {
            if (field.getBounds().contains(touchX, touchY)) {
                FieldModel fieldModel = field.getFieldModel();
                if(fieldModel.hasSwitch()){
                    System.out.println("Field with switch");
                    if (fieldModel.isDiceAllowed(lastClickedDiceValue, isPilot)){
                        if (!fieldModel.isOccupied()) {
                            fieldModel.placeDice(getDiceFromSprite(selectedDice), isPilot, field);
                            System.out.println("Placing dice");
                            this.disableDice();
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
                    if (fieldModel.isDiceAllowed(lastClickedDiceValue, isPilot)) {
                        if (!fieldModel.isOccupied()) {
                            if (isCoffeeField(field)) {
                                handleCoffeeFieldPlacement(fieldModel);
                            }
                            System.out.println("Placing dice");
                            this.disableDice();
                            if (fieldModel.placeDice(getDiceFromSprite(selectedDice), isPilot, field)) {
                                for (int i = 0; i < diceSprites.length; i++) {
                                    if (diceSprites[i] == selectedDice) {
                                        diceArray[i].setPlaced(true); // No need to recheck isPilot
                                        break;
                                    }
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
                }
            }
        }
    }

    private void handleCoffeeFieldPlacement(FieldModel fieldModel){
        if(!fieldModel.isOccupied()){
            coffeeManager.placeCoffee();
            System.out.println("Dice placed in coffee field");
        }
    }

    private boolean isCoffeeField(FieldView field) {
        return coffeeManager.coffeeFields.contains(field);
    }

    public void resetSelection() {
        System.out.println("Resetting selection");
        selectedDice = null;
        lastClickedDiceValue = -1;
        selectedCoffee = null;
        currentState = State.SELECTING;
    }

    public void disableDice() {
        if(isPilot){
            roundController.setPilotDicePlacedTrue();
        } else {
            roundController.setCopilotDicePlacedTrue();
        }
    }
}
