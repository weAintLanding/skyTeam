package com.sierra.skyTeam.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.List;

public class DicePosUpdater {
    Sprite[] diceSprites;
    List<Field> fields;
    Sprite selectedDice;
    Viewport viewport;

    public DicePosUpdater(Sprite[] diceSprites, List<Field> fields, Viewport viewport) {
        this.diceSprites = diceSprites;
        this.fields = fields;
        this.viewport = viewport;
    }

    public void update() {
        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
            Vector2 coordinates = InputHandler.scaledInput(viewport);
            float touchX = coordinates.x;
            float touchY = coordinates.y;

            for(Sprite sprite : diceSprites){
                if(sprite.getBoundingRectangle().contains(touchX, touchY)){
                    selectedDice = sprite;
                    System.out.println("Dice for placing selected");
                    return;
                }
            }

            if (selectedDice != null) {
                for(Field field : fields){
                    if(field.getBounds().contains(touchX, touchY)){
                        field.placeDiceOnField(selectedDice);
                        System.out.println("Placing dice on field");
                        break;
                    }
                }
                selectedDice = null;
            }
        }
    }
}
