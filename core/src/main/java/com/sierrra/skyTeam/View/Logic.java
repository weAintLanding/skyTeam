package com.sierrra.skyTeam.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.List;

public class Logic {
    Viewport viewport;
    Dice dice;
    List<Field> fields;
    private int lastClickedDiceValue = -1;

    public Logic(Dice dice, List<Field> fields, Viewport viewport){
        this.dice = dice;
        this.fields = fields;
        this.viewport = viewport;
    }

    public void handleInput() {
        Vector2 coordinates = Something.scaledInput(viewport);
        float touchX = coordinates.x;
        float touchY = coordinates.y;

        //test for dice and field clicking
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            if (lastClickedDiceValue == -1) {
                int diceValue = dice.getDiceValue(touchX, touchY);
                if (diceValue != -1) {
                    lastClickedDiceValue = diceValue;
                    System.out.println("Selected Dice Value: " + lastClickedDiceValue);
                }
            } else {
                for (Field field : fields) {
                    Field clickedField = field.getClickedField(touchX, touchY);
                    if (clickedField != null) {
                        clickedField.isDiceAllowed(lastClickedDiceValue);
                        System.out.println("Used Dice Value: " + lastClickedDiceValue);
                        lastClickedDiceValue = -1;  // Reset after usage
                        break;
                    }
                }
            }
        }
    }
}
