package com.sierra.skyTeam.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class EndTurn {
    private final List<Rectangle> bounds;
    public EndTurn() {
        bounds = new ArrayList<>();
        bounds.add(new Rectangle(32, 30, 150, 50));     //pilot
        bounds.add(new Rectangle(1100, 30, 150, 50));   //copilot
        //can use index value to figure out which button was pressed
    }
    //endRound rectangle bounds are 900, 30, 150, 50;

    public boolean isHovered(float touchX, float touchY) {
        for(Rectangle rect : bounds) {
            if(rect.contains(touchX, touchY)){
                return true;
            }
        }
        return false;
    }

    public void handleClick(float touchX, float touchY) {
        for(int i = 0; i < bounds.size(); i++){
            Rectangle rect = bounds.get(i);
            if(rect.contains(touchX, touchY) && Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                if(i == 0) System.out.println("Pilot has ended their turn");
                else System.out.println("Copilot has ended their turn");
            }
        }
    }
}
