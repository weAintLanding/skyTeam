package com.sierrra.skyTeam.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class StartScreen implements Screen {
    BoardGame game;
    SpriteBatch batch;
    Texture background;
    Viewport viewport;
    Rectangle playButton;
    Rectangle quitButton;

    public StartScreen(BoardGame game){
        this.game = game;
        batch = new SpriteBatch();
        background = new Texture("landingPage.png");
        viewport = new FitViewport(1280, 720);
        playButton = new Rectangle(475, 410, 330, 60);
        quitButton = new Rectangle(475, 310,330,60);
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
        float touchX = Gdx.input.getX();
        float touchY = Gdx.graphics.getHeight() - Gdx.input.getY();
        float scaleX = viewport.getWorldWidth() / Gdx.graphics.getWidth();
        float scaleY = viewport.getWorldHeight() / Gdx.graphics.getHeight();
        touchX *= scaleX;
        touchY *= scaleY;

        boolean isPlay = playButton.contains(touchX, touchY);
        boolean isQuit = quitButton.contains(touchX, touchY);

        if (isPlay || isQuit) {
            Gdx.graphics.setSystemCursor(com.badlogic.gdx.graphics.Cursor.SystemCursor.Hand);
        } else {
            Gdx.graphics.setSystemCursor(com.badlogic.gdx.graphics.Cursor.SystemCursor.Arrow);
        }

        if (Gdx.input.isTouched()){
            if (isPlay){
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
