package com.sierra.skyTeam.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sierra.skyTeam.model.Dice;

public class diceView {
    private final Sprite[] pilotDiceSprites; // Preloaded textures for Pilot dice faces
    private final Sprite[] copilotDiceSprites; // Preloaded textures for CoPilot dice faces
    private final Sprite[] currentPilotDiceSprites; // Sprites for currently displayed Pilot dice
    private final Sprite[] currentCopilotDiceSprites; // Sprites for currently displayed CoPilot dice

    public diceView() {
        pilotDiceSprites = new Sprite[6];
        copilotDiceSprites = new Sprite[6];
        currentPilotDiceSprites = new Sprite[4];
        currentCopilotDiceSprites = new Sprite[4];

        // Load dice textures
        loadDiceTextures();

        // Initialize current dice sprites
        initializeCurrentDiceSprites();
    }

    private void loadDiceTextures() {
        for (int i = 0; i < 6; i++) {
            pilotDiceSprites[i] = new Sprite(new Texture("bluedice_" + (i + 1) + ".png"));
            copilotDiceSprites[i] = new Sprite(new Texture("orangedice_" + (i + 1) + ".png"));
        }
    }

    private void initializeCurrentDiceSprites() {
        for (int i = 0; i < 4; i++) {
            currentPilotDiceSprites[i] = new Sprite(pilotDiceSprites[0].getTexture()); // Default texture
            currentCopilotDiceSprites[i] = new Sprite(copilotDiceSprites[0].getTexture()); // Default texture
        }
    }

    public void updateSprites(Dice[] pilotDice, Dice[] copilotDice) {
        for (int i = 0; i < pilotDice.length; i++) {
            int pilotDiceValue = pilotDice[i].getDiceValue() - 1;
            currentPilotDiceSprites[i].setTexture(pilotDiceSprites[pilotDiceValue].getTexture());
        }

        for (int i = 0; i < copilotDice.length; i++) {
            int copilotDiceValue = copilotDice[i].getDiceValue() - 1;
            currentCopilotDiceSprites[i].setTexture(copilotDiceSprites[copilotDiceValue].getTexture());
        }
    }

    public void render(SpriteBatch batch, boolean isPilot, float startX, float startY) {
        Sprite[] diceSprites = isPilot ? currentPilotDiceSprites : currentCopilotDiceSprites;
        for (int i = 0; i < diceSprites.length; i++) {
            Sprite sprite = diceSprites[i];
            sprite.setPosition(startX, startY + i * 75); // Space dice vertically
            sprite.setSize(50, 50); // Set size for rendering
            sprite.draw(batch);
        }
    }

    // Getter for current Pilot dice sprites
    public Sprite[] getCurrentPilotDiceSprites() {
        return currentPilotDiceSprites;
    }

    // Getter for current CoPilot dice sprites
    public Sprite[] getCurrentCopilotDiceSprites() {
        return currentCopilotDiceSprites;
    }

    public void dispose() {
        // Dispose textures for all dice sprites
        for (Sprite sprite : pilotDiceSprites) {
            sprite.getTexture().dispose();
        }
        for (Sprite sprite : copilotDiceSprites) {
            sprite.getTexture().dispose();
        }
    }
}
