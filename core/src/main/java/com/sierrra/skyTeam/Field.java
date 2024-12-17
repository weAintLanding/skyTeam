package com.sierrra.skyTeam;

import com.badlogic.gdx.math.Rectangle;

public class Field {
    private Rectangle bounds;
    private int fieldSize = 45;
    private boolean isOccupied;

    public Field (float x, float y){
        this.bounds = new Rectangle(x, y, fieldSize, fieldSize);
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
