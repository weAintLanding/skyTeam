package com.sierra.skyTeam.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

public class InputHandler {
    public static Vector2 scaledInput(Viewport viewport) {
        Vector2 coordinates = viewport.unproject(new Vector2(Gdx.input.getX(), Gdx.input.getY()));
        return new Vector2(coordinates);
    }
}
