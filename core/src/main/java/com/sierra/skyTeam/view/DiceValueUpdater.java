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
    private Sprite diceChangerPlusSprite;
    private Sprite diceChangerMinusSprite;
    private CoffeeView selectedCoffee;
    private Sprite selectedDice;
    Viewport viewport;
    DiceView diceView;
    private final Dice[] pilotDice;
    private final Dice[] copilotDice;
    int selectedIndex = -1;
    private Runnable onDiceValueChangedCallback;

    public DiceValueUpdater(Viewport viewport, DiceView diceView,
                            Dice[] pilotDice, Dice[] copilotDice,
                            Runnable onDiceValueChangedCallBack){
        diceChangerPlusSprite = new Sprite(new Texture("diceChangerPlus.png"));
        diceChangerMinusSprite = new Sprite(new Texture("diceChangerMinus.png"));
        this.viewport = viewport;
        this.diceView = diceView;
        this.pilotDice = pilotDice;
        this.copilotDice = copilotDice;
        this.onDiceValueChangedCallback = onDiceValueChangedCallBack;
    }

    public void showOptions() {
        showOptions = true;
    }

    public void hideOptions() {
        showOptions = false;
    }

    public void setSelectedDice(Sprite selectedDice){
        this.selectedDice = selectedDice;
        selectedIndex = getSelectedDiceIndex(selectedDice);
    }

    public void setSelectedCoffee(CoffeeView selectedCoffee) {
        this.selectedCoffee = selectedCoffee;
        selectedCoffee.used = true;
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
            diceChangerPlusSprite.setScale(0.25F);
            diceChangerPlusSprite.setPosition(selectedDice.getX() - 55, selectedDice.getY() - 15);
            diceChangerPlusSprite.draw(batch);
            diceChangerMinusSprite.setScale(0.25F);
            diceChangerMinusSprite.setPosition(selectedDice.getX() + 40, selectedDice.getY() - 15);
            diceChangerMinusSprite.draw(batch);
            increaseDiceValue();
            decreaseDiceValue();
        }
    }

    public void increaseDiceValue() {
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            Vector2 coordinates = InputHandler.scaledInput(viewport);
            float touchX = coordinates.x;
            float touchY = coordinates.y;
            if(diceChangerPlusSprite.getBoundingRectangle().contains(touchX, touchY)){
                if (selectedIndex != -1) {
                    boolean isPilotDice = selectedIndex < diceView.getCurrentPilotDiceSprites().length;
                    int diceArrayIndex = selectedIndex % 4; // Get the index within the pilot/copilot arrays

                    if (isPilotDice) {
                        Dice selectedPilotDice = pilotDice[diceArrayIndex];
                        if (selectedPilotDice.getDiceValue() < 6) {
                            selectedPilotDice.setDiceValue(selectedPilotDice.getDiceValue() + 1);
                            diceView.updateSprites(pilotDice, copilotDice);
                        }
                    } else {
                        Dice selectedCopilotDice = copilotDice[diceArrayIndex];
                        if (selectedCopilotDice.getDiceValue() < 6) {
                            selectedCopilotDice.setDiceValue(selectedCopilotDice.getDiceValue() + 1);
                            diceView.updateSprites(pilotDice, copilotDice);  // Update the sprites
                        }
                    }
                    if (onDiceValueChangedCallback != null) {
                        onDiceValueChangedCallback.run();
                    }
                    hideOptions();
                }
            }
        }
    }

    public void decreaseDiceValue() {
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            Vector2 coordinates = InputHandler.scaledInput(viewport);
            float touchX = coordinates.x;
            float touchY = coordinates.y;
            if(diceChangerMinusSprite.getBoundingRectangle().contains(touchX, touchY)){
                if (selectedIndex != -1) {
                    boolean isPilotDice = selectedIndex < diceView.getCurrentPilotDiceSprites().length;
                    int diceArrayIndex = selectedIndex % 4;

                    if (isPilotDice) {
                        Dice selectedPilotDice = pilotDice[diceArrayIndex];
                        if (selectedPilotDice.getDiceValue() > 1) {
                            selectedPilotDice.setDiceValue(selectedPilotDice.getDiceValue() - 1);
                            diceView.updateSprites(pilotDice, copilotDice);
                        }
                    } else {
                        Dice selectedCopilotDice = copilotDice[diceArrayIndex];
                        if (selectedCopilotDice.getDiceValue() > 1) {
                            selectedCopilotDice.setDiceValue(selectedCopilotDice.getDiceValue() - 1);
                            diceView.updateSprites(pilotDice, copilotDice);
                        }
                    }
                    if (onDiceValueChangedCallback != null) {
                        onDiceValueChangedCallback.run();
                    }
                    hideOptions();
                }
            }
        }
    }

    public Sprite getDiceChangerPlusSprite() {
        return diceChangerPlusSprite;
    }

    public Sprite getDiceChangerMinusSprite() {
        return diceChangerMinusSprite;
    }

    public boolean getShowOptions() {
        return showOptions;
    }

    private void disposeDiceChangeSprites() {
        if (diceChangerPlusSprite != null) {
            diceChangerPlusSprite.getTexture().dispose(); // Dispose the texture
            diceChangerPlusSprite = null; // Set the sprite to null
        }
        if (diceChangerMinusSprite != null) {
            diceChangerMinusSprite.getTexture().dispose(); // Dispose the texture
            diceChangerMinusSprite = null; // Set the sprite to null
        }
    }
}
