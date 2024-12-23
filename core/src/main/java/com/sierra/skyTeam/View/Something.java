package com.sierra.skyTeam.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Something {
    public static Vector2 scaledInput(Viewport viewport) {
        float touchX = Gdx.input.getX();
        float touchY = Gdx.graphics.getHeight() - Gdx.input.getY();
        float scaleX = viewport.getWorldWidth() / Gdx.graphics.getWidth();
        float scaleY = viewport.getWorldHeight() / Gdx.graphics.getHeight();
        touchX *= scaleX;
        touchY *= scaleY;
        return new Vector2(touchX, touchY);
    }
}
