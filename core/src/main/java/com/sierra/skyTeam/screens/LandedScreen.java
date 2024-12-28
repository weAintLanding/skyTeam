package com.sierra.skyTeam.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sierra.skyTeam.MainGame;
import com.sierra.skyTeam.view.InputHandler;

public class LandedScreen implements Screen {
    MainGame game;
    SpriteBatch batch;
    Texture background;
    Viewport viewport;
    Rectangle playAgainButton;
    Rectangle quitButton;

    public LandedScreen(MainGame game){
        this.game = game;
        batch = new SpriteBatch();
        background = new Texture("landedPage.png");
        viewport = new FitViewport(1280, 720);
        playAgainButton = new Rectangle(385, 375, 510, 70);
        quitButton = new Rectangle(385, 245,510,70);
    }

    public void show() {}
    public void render(float delta) {
        draw();
        input();
    }

    public void draw(){
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        viewport.apply();
        batch.begin();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.draw(background, 0, 0,
            viewport.getWorldWidth(), viewport.getWorldHeight());
        batch.end();
    }

    public void input(){
        Vector2 coordinates = InputHandler.scaledInput(viewport);
        float touchX = coordinates.x;
        float touchY = coordinates.y;

        boolean isPlayAgain = playAgainButton.contains(touchX, touchY);
        boolean isQuit = quitButton.contains(touchX, touchY);

        if (isPlayAgain || isQuit) {
            Gdx.graphics.setSystemCursor(com.badlogic.gdx.graphics.Cursor.SystemCursor.Hand);
        } else {
            Gdx.graphics.setSystemCursor(com.badlogic.gdx.graphics.Cursor.SystemCursor.Arrow);
        }

        if (Gdx.input.isTouched()){
            if (isPlayAgain){
                game.setScreen(new GameScreen(game));
                Gdx.graphics.setSystemCursor(com.badlogic.gdx.graphics.Cursor.SystemCursor.Arrow);
            }
            if (isQuit){
                Gdx.app.exit();
            }
        }
    }

    public void resize (int width, int height) {
        viewport.update(width, height, true);
    }
    @Override
    public void pause() {}
    @Override
    public void resume() {}
    @Override
    public void hide() {}
    public void dispose() {
        batch.dispose();
        background.dispose();
    }
}
