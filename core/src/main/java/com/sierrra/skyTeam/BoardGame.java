package com.sierrra.skyTeam;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;

public class BoardGame extends ApplicationAdapter {
    SpriteBatch batch;
    Texture background;
    float offsetX, offsetY;

    @Override
    public void create () {
        batch = new SpriteBatch();
        background = new Texture("skyteam.png");
    }

    @Override
    public void render() {
        float scaleX = (float) Gdx.graphics.getWidth() / background.getWidth();
        float scaleY = (float) Gdx.graphics.getHeight() / background.getHeight();
        float scale = Math.min(scaleX, scaleY);

        float scaledWidth = background.getWidth() * scale;
        float scaledHeight = background.getHeight() * scale;

        offsetX = (float) (Gdx.graphics.getWidth() - scaledWidth) / 2;
        offsetY = (float) (Gdx.graphics.getHeight() - scaledHeight) / 2;

        batch.begin();
        batch.draw(background, offsetX, offsetY, scaledWidth, scaledHeight);
        batch.end();
    }
}
