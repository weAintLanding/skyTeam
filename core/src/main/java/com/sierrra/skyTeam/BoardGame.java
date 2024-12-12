package com.sierrra.skyTeam;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.IsometricStaggeredTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.*;

public class BoardGame extends ApplicationAdapter {
    SpriteBatch batch;
    Texture background;
    Stage stage;
    Viewport viewport;

    @Override
    public void create () {
        stage = new Stage(new ScreenViewport());
        batch = new SpriteBatch();
        background = new Texture("skyteam.png");
        viewport = new FitViewport(1280, 720);
    }

    @Override
    public void render() {
        viewport.apply();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clear screen to avoid flickering
        batch.begin();
        batch.draw(background, 0, 0,
            Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
    }
    @Override
    public void resize (int width, int height) {
        viewport.update(width, height, true);
        batch.getProjectionMatrix().setToOrtho2D(0, 0, width, height);
    }
}
