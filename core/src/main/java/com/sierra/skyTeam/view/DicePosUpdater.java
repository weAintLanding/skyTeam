package com.sierra.skyTeam.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.List;

public class DicePosUpdater {
    Sprite[] diceSprites;
    Dice dice;
    List<Field> fields;
    Sprite selectedDice;
    private int lastClickedDiceValue = -1;
    Viewport viewport;
    boolean isSelectingDice = true;
    boolean isPlacingDice = false;

    public DicePosUpdater(Dice dice, List<Field> fields, Viewport viewport, boolean isPilot) {
        this.dice = dice;
        this.diceSprites = isPilot ? dice.getCurrentPilotDiceSprites() : dice.getCurrentCopilotDiceSprites();
        this.fields = fields;
        this.viewport = viewport;
    }

    public void update() {
        Vector2 coordinates = InputHandler.scaledInput(viewport);
        float touchX = coordinates.x;
        float touchY = coordinates.y;
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            if (isSelectingDice) {
                handleDiceSelection(touchX, touchY);
            } else if (isPlacingDice) {
                handleFieldPlacement(touchX, touchY);
            }
        }
    }

    private void handleDiceSelection(float touchX, float touchY) {
        for(Sprite sprite : diceSprites) {
            if(sprite.getBoundingRectangle().contains(touchX, touchY)){
                selectedDice = sprite;
                lastClickedDiceValue = dice.getDiceValue(touchX, touchY);
                System.out.println(lastClickedDiceValue + " dice selected");
                isSelectingDice = false;
                isPlacingDice = true;
                break;
            }
        }
    }

    private void handleFieldPlacement(float touchX, float touchY) {
        for(Field field : fields){
            if(field.getBounds().contains(touchX, touchY)) {
                if(field.isDiceAllowed(lastClickedDiceValue)){
                    field.placeDiceOnField(selectedDice);
                    System.out.println("Placing dice");
                    selectedDice = null;
                    lastClickedDiceValue = -1;
                    isSelectingDice = true;
                    isPlacingDice = false;
                    break;
                } else {
                    System.out.println("Dice value not allowed in this field.");
                }
            }
        }
    }
}
