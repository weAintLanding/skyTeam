package com.sierra.skyTeam.view;

import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.List;

public class EndTurn {
    private final List<Rectangle> bounds;
    public EndTurn() {
        bounds = new ArrayList<>();
        bounds.add(new Rectangle(32, 30, 150, 50));
        bounds.add(new Rectangle(1100, 30, 150, 50));
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
}
