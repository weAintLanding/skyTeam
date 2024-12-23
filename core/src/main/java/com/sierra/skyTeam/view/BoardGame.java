package com.sierra.skyTeam.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BoardGame extends Game {
    SpriteBatch batch;

    @Override
    public void create () {
        batch = new SpriteBatch();
        setScreen(new StartScreen(this));
    }
    @Override
    public void render() {
        super.render();
    }
    @Override
    public void dispose() {
        batch.dispose();
    }
}
