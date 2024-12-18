package com.sierrra.skyTeam;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import java.util.List;

public class Logic {
    Dice dice;
    List<Field> fields;
    private int lastClickedDiceValue = -1;

    public Logic(Dice dice, List<Field> fields){
        this.dice = dice;
        this.fields = fields;
    }
    public void handleInput() {
        float touchX = Gdx.input.getX();
        float touchY = Gdx.graphics.getHeight() - Gdx.input.getY();

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
