package com.sierrra.skyTeam;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.*;

public class BoardGame extends ApplicationAdapter {
    SpriteBatch batch;
    Texture background;
    Stage stage;
    Viewport viewport;
    Dice dice;
    boolean isDiceRolled = false;

    @Override
    public void create () {
        stage = new Stage(new ScreenViewport());
        batch = new SpriteBatch();
        background = new Texture("skyteam.png");
        viewport = new FitViewport(1280, 720);
        dice = new Dice();
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        viewport.apply();
        batch.begin();
        batch.draw(background, 0, 0,
            Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        if (!isDiceRolled){
            dice.rollDice();
            isDiceRolled = true;
        }
        dice.renderDice(batch, true, 100);
        dice.renderDice(batch, false, 100);
        if(Gdx.input.isTouched()){
            float touchX = Gdx.input.getX();
            float touchY = Gdx.input.getY();
            touchY = Gdx.graphics.getHeight() - touchY;

            if(dice.isDiceClicked(dice.getCurrentPilotDiceSprites(), dice.getCurrentPilotDiceValues(), touchX, touchY, true)){
                System.out.println("Pilot dice");
            }
            if(dice.isDiceClicked(dice.getCurrentCopilotDiceSprites(), dice.getCurrentCoPilotDiceValues(), touchX, touchY, false)){
                System.out.println("Copilot dice");
            }
        }
        batch.end();
    }
    @Override
    public void resize (int width, int height) {
        viewport.update(width, height, true);
        //batch.getProjectionMatrix().setToOrtho2D(0, 0, width, height);
    }
}
