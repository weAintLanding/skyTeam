package com.sierra.skyTeam.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sierra.skyTeam.model.Dice;

public class DiceValueUpdater {
    private boolean showOptions = false;
    private final Sprite diceChangerPlusSprite;
    private final Sprite diceChangerMinusSprite;
    public CoffeeView selectedCoffee;
    private Sprite selectedDice;
    Viewport viewport;
    DiceView diceView;
    private final Dice[] pilotDice;
    private final Dice[] copilotDice;
    int selectedIndex = -1;
    private boolean isConfirmationPending = false;
    private boolean lastActionWasIncrease;
    private boolean lastActionWasDecrease;
    private boolean lastActionWasIncreaseBy2;
    private boolean lastActionWasDecreaseBy2;
    boolean hasIncreasedFrom1 = false;
    boolean hasDecreasedFrom6 = false;
    private boolean showPlusSprite;
    private boolean showMinusSprite;
    private final Runnable onDiceValueChangedCallback;
    private final Runnable onCoffeeInteractionCallBack;

    public DiceValueUpdater(Viewport viewport, DiceView diceView,
                            Dice[] pilotDice, Dice[] copilotDice,
                            Runnable onDiceValueChangedCallBack,
                            Runnable onCoffeeInteractionCallBack){
        diceChangerPlusSprite = new Sprite(new Texture("diceChangerPlus.png"));
        diceChangerMinusSprite = new Sprite(new Texture("diceChangerMinus.png"));
        this.viewport = viewport;
        this.diceView = diceView;
        this.pilotDice = pilotDice;
        this.copilotDice = copilotDice;
        this.onDiceValueChangedCallback = onDiceValueChangedCallBack;
        this.onCoffeeInteractionCallBack = onCoffeeInteractionCallBack;
    }

    public void showOptions() {
        showOptions = true;
        int diceValue = getDiceValueFromSelectedIndex(selectedIndex);
        if(diceValue == 1) {
            showPlusSprite = true;
            showMinusSprite = false;
        } else if (diceValue == 6) {
            showPlusSprite = false;
            showMinusSprite = true;
        } else {
            showPlusSprite = true;
            showMinusSprite = true;
        }
        resetSelection();
    }

    public void hideOptions() {
        showOptions = false;
        resetAllBooleans();
    }

    public void setSelectedDice(Sprite selectedDice){
        this.selectedDice = selectedDice;
        selectedIndex = getSelectedDiceIndex(selectedDice);
    }

    public void setSelectedCoffee(CoffeeView selectedCoffee) {
        this.selectedCoffee = selectedCoffee;
    }

    private int getSelectedDiceIndex(Sprite selectedDice) {
        Sprite[] pilotDiceSprites = diceView.getCurrentPilotDiceSprites();
        Sprite[] copilotDiceSprites = diceView.getCurrentCopilotDiceSprites();
        Sprite[] allSprites = new Sprite[pilotDiceSprites.length + copilotDiceSprites.length];
        System.arraycopy(pilotDiceSprites, 0, allSprites, 0, pilotDiceSprites.length);
        System.arraycopy(copilotDiceSprites, 0, allSprites, pilotDiceSprites.length, copilotDiceSprites.length);

        for (int i = 0; i < allSprites.length; i++) {
            if (allSprites[i] == selectedDice) {
                return i;
            }
        }
        return -1;
    }
    public void render(SpriteBatch batch) {
        if (showOptions) {
            if (showPlusSprite) {
                diceChangerPlusSprite.setScale(0.25F);
                diceChangerPlusSprite.setPosition(selectedDice.getX() - 55, selectedDice.getY() - 15);
                diceChangerPlusSprite.draw(batch);
            }

            if (showMinusSprite) {
                diceChangerMinusSprite.setScale(0.25F);
                diceChangerMinusSprite.setPosition(selectedDice.getX() + 40, selectedDice.getY() - 15);
                diceChangerMinusSprite.draw(batch);
            }
            changeDiceValue();
            valueChecker();
            if(isConfirmationPending) waitForConfirmation();
        }
    }

    private void changeDiceValue() {
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            Vector2 coordinates = InputHandler.scaledInput(viewport);
            float touchX = coordinates.x;
            float touchY = coordinates.y;

            if (showPlusSprite && diceChangerPlusSprite.getBoundingRectangle().contains(touchX, touchY)) {
                if(getDiceValueFromSelectedIndex(selectedIndex) == 1){
                    changeDiceValue(1);
                    lastActionWasIncrease = true;
                    hasIncreasedFrom1 = true;

                } else if (isConfirmationPending && lastActionWasDecreaseBy2) {
                    //if the last action was -2, we do this
                    changeDiceValue(2);
                    lastActionWasIncreaseBy2 = true; // last action was +2
                    lastActionWasDecreaseBy2 = false; // reset -2 boolean
                    lastActionWasDecrease = false; // reset -1 boolean

                } else if (isConfirmationPending && lastActionWasDecrease) {
                    changeDiceValue(2);
                    lastActionWasIncreaseBy2 = true;
                    lastActionWasDecrease = false;
                    lastActionWasDecreaseBy2 = false;

                } else {
                    //default action is +1
                    changeDiceValue(1);
                    lastActionWasIncrease = true;
                }
                showPlusSprite = false;
                showMinusSprite = !hasIncreasedFrom1;
            }

            if (showMinusSprite && diceChangerMinusSprite.getBoundingRectangle().contains(touchX, touchY)) {
                if(getDiceValueFromSelectedIndex(selectedIndex) == 6){
                    changeDiceValue(-1);
                    lastActionWasIncrease = true;
                    hasDecreasedFrom6 = true;

                }else if (isConfirmationPending && lastActionWasIncreaseBy2) {
                    //if the last action was +2, we do this
                    changeDiceValue(-2);
                    lastActionWasDecreaseBy2 = true; // last action was -2
                    lastActionWasIncreaseBy2 = false; // reset -2 boolean
                    lastActionWasIncrease = false; // reset -1 boolean

                } else if (isConfirmationPending && lastActionWasIncrease) {
                    changeDiceValue(-2);
                    lastActionWasDecreaseBy2 = true;
                    lastActionWasIncrease = false;
                    lastActionWasIncreaseBy2 = false;

                } else {
                    changeDiceValue(-1);
                    lastActionWasDecrease = true;
                }
                showMinusSprite = false;
                showPlusSprite = !hasDecreasedFrom6;
            }
            isConfirmationPending = true;
        }
    }

    public void updateDiceValue(Dice dice, int changeAmount) {
        if(dice != null && ((dice.getDiceValue() + changeAmount) <= 6) &&
            ((dice.getDiceValue() + changeAmount) >= 1)){
            dice.setDiceValue(dice.getDiceValue() + changeAmount);
            diceView.updateSprites(pilotDice, copilotDice);
        }
    }

    public void changeDiceValue(int changeAmount) {
        if (selectedIndex != -1) {
            boolean isPilotDice = selectedIndex < diceView.getCurrentPilotDiceSprites().length;
            int diceArrayIndex = selectedIndex % 4;

            if (isPilotDice) {
                Dice selectedPilotDice = pilotDice[diceArrayIndex];
                updateDiceValue(selectedPilotDice, changeAmount);
            } else {
                Dice selectedCopilotDice = copilotDice[diceArrayIndex];
                updateDiceValue(selectedCopilotDice, changeAmount);
            }
        }
    }

    private int getDiceValueFromSelectedIndex(int selectedIndex) {
        boolean isPilotDice = selectedIndex < diceView.getCurrentPilotDiceSprites().length;
        int diceArrayIndex = selectedIndex % 4;

        if (isPilotDice) {
            Dice selectedPilotDice = pilotDice[diceArrayIndex];
            return selectedPilotDice.getDiceValue();
        } else {
            Dice selectedCopilotDice = copilotDice[diceArrayIndex];
            return selectedCopilotDice.getDiceValue();
        }
    }

    private void waitForConfirmation() {
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            Vector2 coordinates = InputHandler.scaledInput(viewport);
            float touchX = coordinates.x;
            float touchY = coordinates.y;

            if (selectedDice.getBoundingRectangle().contains(touchX, touchY)) {
                resetSelection();
                removeCoffee();
                hideOptions();
            }
        }
    }

    private void valueChecker() {
        if (hasDecreasedFrom6 || hasIncreasedFrom1) {
            showMinusSprite = false;
            showPlusSprite = false;
        }
    }

    private void resetAllBooleans() {
        lastActionWasIncrease = false;
        lastActionWasDecrease = false;
        lastActionWasIncreaseBy2 = false;
        lastActionWasDecreaseBy2 = false;
        hasIncreasedFrom1 = false;
        hasDecreasedFrom6 = false;
    }

    private void resetSelection() {
        if (onDiceValueChangedCallback != null) {
            onDiceValueChangedCallback.run();
        }
    }

    public void removeCoffee() {
        if(onCoffeeInteractionCallBack != null){
            onCoffeeInteractionCallBack.run();
        }
    }

    public Sprite getDiceChangerPlusSprite() {
        return diceChangerPlusSprite;
    }

    public Sprite getDiceChangerMinusSprite() {
        return diceChangerMinusSprite;
    }

    public boolean showPlusSprite() {
        return showPlusSprite;
    }

    public boolean showMinusSprite() {
        return showMinusSprite;
    }
}
