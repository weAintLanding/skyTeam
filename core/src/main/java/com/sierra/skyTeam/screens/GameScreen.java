package com.sierra.skyTeam.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sierra.skyTeam.MainGame;
import com.badlogic.gdx.math.Vector2;
import com.sierra.skyTeam.model.Dice;
import com.sierra.skyTeam.view.DiceView;
import com.sierra.skyTeam.view.AxisView;
import com.sierra.skyTeam.view.MarkerManager;
import com.sierra.skyTeam.view.FieldView;
import com.sierra.skyTeam.view.FieldGenerator;
import com.sierra.skyTeam.view.InputHandler;
import com.sierra.skyTeam.view.TrackerManager;
import com.sierra.skyTeam.controller.DiceController;
import com.sierra.skyTeam.controller.GameController;

import java.util.List;

public class GameScreen implements Screen {
    MainGame game;
    GameController gameController;

    SpriteBatch batch;
    Texture background;
    Viewport viewport;

    //MVC components
    Dice[] pilotDice; // Model for Pilot dice
    Dice[] coPilotDice; // Model for CoPilot dice
    DiceView diceView; // Dice View
    DiceController diceController; // Dice Controller

    List<FieldView> fields;
    AxisView axis;
    MarkerManager markerManager;
    TrackerManager trackerManager;

    public GameScreen(MainGame game) {
        this.game = game;
        fields = FieldGenerator.generateFields();
        this.gameController = new GameController(fields);
        batch = new SpriteBatch();
        background = new Texture("board.png");
        viewport = new FitViewport(1280, 720);

        // Initialize Dice Model, View, and Controller
        axis = gameController.getAxisController().getAxisView();
        diceView = gameController.getDiceController().getDiceView();

        diceController = gameController.getDiceController();
        pilotDice = gameController.getPlayerController().getPilotDice();
        coPilotDice = gameController.getPlayerController().getCoPilotDice();

        markerManager = new MarkerManager();
        trackerManager = new TrackerManager();
    }

    public void show() {
        diceController.updateView(); // Ensure dice view matches initial model state
    }

    public void render(float delta) {
        draw();
        gameController.getDiceController().updateHandler();
        handleHover();
        ShapeRenderer shapeRenderer = new ShapeRenderer();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        for(FieldView field : fields){
            field.renderer(shapeRenderer);
        }
        shapeRenderer.end();
    }

    public void draw() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        batch.draw(background, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());

        for (FieldView field : fields) {
            field.switchRenderer(batch);
        }

        //fields.get(4).toggleSwitch(); // Example field toggle
        axis.render(batch);
        axis.setAxisValue(0);

        // Render Dice using DiceController
        diceController.render(batch, 86, 200); // Pass starting coordinates for Pilot dice

        markerManager.draw(batch);
        trackerManager.draw(batch);
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

        // Check CoPilot Dice for Hover
        for (Sprite sprite : diceView.getCurrentCopilotDiceSprites()) {
            if (sprite.getBoundingRectangle().contains(touchX, touchY)) {
                isHovered = true;
                break;
            }
        }

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
