package com.sierrra.skyTeam;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.List;

public class GameScreen implements Screen {
    BoardGame game;
    SpriteBatch batch;
    Texture background;
    Viewport viewport;
    Dice dice;
    List<Field> fields;
    ShapeRenderer shapeRenderer;
    boolean isDiceRolled = false;
    Axis axis;
    public GameScreen (BoardGame game) {
        this.game = game;
        batch = new SpriteBatch();
        background = new Texture("board.png");
        viewport = new FitViewport(1280, 720);
        dice = new Dice();
        shapeRenderer = new ShapeRenderer();
        fields = FieldGenerator.generateFields();
        axis = new Axis();
    }
    public void show() {}
    public void render(float delta) {
        draw();
        input();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        for (Field field : fields){
            field.renderer(shapeRenderer);
        }
        shapeRenderer.end();
    }

    public void draw() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        batch.draw(background, 0, 0,
            viewport.getWorldWidth(), viewport.getWorldHeight());
        if (!isDiceRolled){
            dice.rollDice();
            isDiceRolled = true;
        }
        axis.render(batch);
        axis.setAxisValue(-1);
        dice.renderDice(batch, true);
        dice.renderDice(batch, false);
        batch.end();
    }

    public void input() {
        float touchX = Gdx.input.getX();
        float touchY = Gdx.graphics.getHeight() - Gdx.input.getY();

        boolean isHovered = false;

        for(int i = 0; i < dice.getCurrentPilotDiceSprites().length; i++){
            Sprite sprite = dice.getCurrentPilotDiceSprites()[i];
            if(sprite.getBoundingRectangle().contains(touchX,touchY)){
                isHovered = true;
                break;
            }
        }
        for (int i = 0; i < dice.getCurrentCopilotDiceSprites().length; i++) {
            Sprite sprite = dice.getCurrentCopilotDiceSprites()[i];
            if (sprite.getBoundingRectangle().contains(touchX, touchY)) {
                isHovered = true;
                break;
            }
        }

        if (isHovered) {
            Gdx.graphics.setSystemCursor(com.badlogic.gdx.graphics.Cursor.SystemCursor.Hand);
        } else {
            Gdx.graphics.setSystemCursor(com.badlogic.gdx.graphics.Cursor.SystemCursor.Arrow);
        }

        if(Gdx.input.isTouched()){
            if(dice.isDiceClicked(dice.getCurrentPilotDiceSprites(), dice.getCurrentPilotDiceValues(), touchX, touchY, true)){
                System.out.println("Pilot dice");
            }
            if(dice.isDiceClicked(dice.getCurrentCopilotDiceSprites(), dice.getCurrentCoPilotDiceValues(), touchX, touchY, false)){
                System.out.println("Copilot dice");
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
        shapeRenderer.dispose();
    }
}
