package com.sierrra.skyTeam;

import com.badlogic.gdx.math.Rectangle;
import org.w3c.dom.css.Rect;

import java.util.ArrayList;

public class Field {
    private Rectangle bounds;
    private int fieldHeight = 35; private int fieldWidth = 35;
    private boolean isOccupied;

    public Field (float x, float y){
        this.bounds = new Rectangle(x, y, fieldWidth, fieldHeight);
        this.isOccupied = false;
    }

    public Rectangle getBounds() {
        return bounds;
    }
    public boolean isOccupied() {
        return isOccupied;
    }
    public void renderer(com.badlogic.gdx.graphics.glutils.ShapeRenderer shapeRenderer) {
        shapeRenderer.rect(bounds.x, bounds.y, bounds.width, bounds.height);
    }
}
