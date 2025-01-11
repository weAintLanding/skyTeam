package com.sierra.skyTeam.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sierra.skyTeam.MainGame;
import com.badlogic.gdx.math.Vector2;
import com.sierra.skyTeam.model.Dice;
import com.sierra.skyTeam.view.*;
import com.sierra.skyTeam.controller.DiceController;
import com.sierra.skyTeam.controller.GameController;

public class GameScreen implements Screen {
    MainGame game;
    GameController gameController;

    SpriteBatch batch;
    Texture background;
    Viewport viewport;
    OrthographicCamera camera;

    //MVC components
    Dice[] pilotDice; // Model for Pilot dice
    Dice[] coPilotDice; // Model for CoPilot dice
    DiceView diceView; // Dice View
    DiceController diceController; // Dice Controller
    AxisView axis;
    EndTurn endTurn;

    public GameScreen(MainGame game) {
        this.game = game;
        this.gameController = new GameController(game);

        batch = new SpriteBatch();
        background = new Texture("board2.png");
        //made two boards to make sure everyone is okay with the layout1

        camera = new OrthographicCamera();
        viewport = new FitViewport(1280, 720, camera);

        // Initialize Dice Model, View, and Controller
        axis = gameController.getAxisController().getAxisView();
        diceView = gameController.getDiceController().getDiceView();

        diceController = gameController.getDiceController();
        diceController.setViewport(viewport);
        pilotDice = gameController.getPlayerController().getPilotDice();
        coPilotDice = gameController.getPlayerController().getCoPilotDice();

        endTurn = new EndTurn();
    }

    public void show() {
        camera.position.set(640, 360, 0);
        camera.update();
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        diceController.updateView(); // Ensure dice view matches initial model state
    }

    public void render(float delta) {
        draw();
        gameController.getDiceController().updateHandler();
        handleHover();
    }

    public void draw() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        batch.draw(background, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());

        gameController.draw(batch);
        axis.render(batch);


        // Render Dice using DiceController
        diceController.render(batch, 86, 200); // Pass starting coordinates for Pilot dice
        batch.end();
    }

    public void handleHover() {
        Vector2 coordinates = InputHandler.scaledInput(viewport);
        float touchX = coordinates.x;
        float touchY = coordinates.y;

        boolean isHovered = false;

        // Check Pilot Dice for Hover
        for (Sprite sprite : diceView.getCurrentPilotDiceSprites()) {
            if (sprite.getBoundingRectangle().contains(touchX, touchY)) {
                isHovered = true;
                break;
            }
        }

        if(diceController.getDiceValueUpdater().getDiceChangerPlusSprite().getBoundingRectangle().contains(touchX, touchY)
            && diceController.getDiceValueUpdater().showPlusSprite()){
            isHovered = true;
        }

        if(diceController.getDiceValueUpdater().getDiceChangerMinusSprite().getBoundingRectangle().contains(touchX, touchY)
            && diceController.getDiceValueUpdater().showMinusSprite()){
            isHovered = true;
        }

        // Check CoPilot Dice for Hover
        for (Sprite sprite : diceView.getCurrentCopilotDiceSprites()) {
            if (sprite.getBoundingRectangle().contains(touchX, touchY)) {
                isHovered = true;
                break;
            }
        }

        isHovered = isHovered || endTurn.isHovered(touchX, touchY);

        endTurn.handleClicked(touchX, touchY);

        if (isHovered) {
            Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Hand);
        } else {
            Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
        }
    }

    public void resize(int width, int height) {
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
        diceView.dispose();
    }
}
