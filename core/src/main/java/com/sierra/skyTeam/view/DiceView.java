package com.sierra.skyTeam.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sierra.skyTeam.model.Dice;

/**
 * Diese Klasse stellt die Ansicht für die Würfel dar, die für Pilot und CoPilot verwendet werden.
 * Sie lädt die Texturen der Würfel, zeigt die entsprechenden Sprites an und aktualisiert die
 * Darstellung der Würfel basierend auf den Werten der Würfel des Piloten und CoPiloten.
 */
public class DiceView {
    private final Sprite[] pilotDiceSprites; // Preloaded textures for Pilot dice faces
    private final Sprite[] copilotDiceSprites; // Preloaded textures for CoPilot dice faces
    private final Sprite[] currentPilotDiceSprites; // Sprites for currently displayed Pilot dice
    private final Sprite[] currentCopilotDiceSprites; // Sprites for currently displayed CoPilot dice

    /**
     * Konstruktor für die Klasse DiceView.
     * Initialisiert die Arrays für die Sprites und lädt die Texturen für die Würfel.
     */
    public DiceView() {
        pilotDiceSprites = new Sprite[6];
        copilotDiceSprites = new Sprite[6];
        currentPilotDiceSprites = new Sprite[4];
        currentCopilotDiceSprites = new Sprite[4];

        // Load dice textures
        loadDiceTextures();

        // Initialize current dice sprites
        initializeCurrentDiceSprites();
    }

    /**
     * Lädt alle Texturen für Pilot und CoPilot.
     */
    private void loadDiceTextures() {
        for (int i = 0; i < 6; i++) {
            pilotDiceSprites[i] = new Sprite(new Texture("bluedice_" + (i + 1) + ".png"));
            copilotDiceSprites[i] = new Sprite(new Texture("orangedice_" + (i + 1) + ".png"));
        }
    }

    /**
     * Initialisiert die Sprites der aktuellen Würfel mit den Texturen.
     */
    private void initializeCurrentDiceSprites() {
        for (int i = 0; i < 4; i++) {
            currentPilotDiceSprites[i] = new Sprite(pilotDiceSprites[0].getTexture()); // Default texture
            currentCopilotDiceSprites[i] = new Sprite(copilotDiceSprites[0].getTexture()); // Default texture
        }
    }

    /**
     * Aktualisiert die Sprites der Würfel basierend auf den aktuellen Würfeln des Piloten und CoPiloten.
     *
     * @param pilotDice     Die Würfel des Piloten
     * @param copilotDice   Die Würfel des CoPiloten
     */
    public void updateSprites(Dice[] pilotDice, Dice[] copilotDice) {
        for (int i = 0; i < pilotDice.length; i++) {
            int pilotDiceValue = pilotDice[i].getDiceValue() - 1;
            currentPilotDiceSprites[i].setTexture(pilotDiceSprites[pilotDiceValue].getTexture());
            pilotDice[i].setDiceSprite(currentPilotDiceSprites[i]);
        }

        for (int i = 0; i < copilotDice.length; i++) {
            int copilotDiceValue = copilotDice[i].getDiceValue() - 1;
            currentCopilotDiceSprites[i].setTexture(copilotDiceSprites[copilotDiceValue].getTexture());
            copilotDice[i].setDiceSprite(currentCopilotDiceSprites[i]);
        }
    }

    /**
     * Zeichnet die Würfel auf dem Bildschirm.
     * Die Würfel werden basierend darauf angezeigt, ob es sich um die Pilot- oder CoPilot-Würfel handelt.
     *
     * @param batch     Das SpriteBatch-Objekt zum Zeichnen der Sprites
     * @param isPilot   Ein Wahrheitswert, der angibt, ob die Würfel des Piloten oder CoPiloten angezeigt werden
     * @param startX    Die Startposition des Würfels auf der X-Axis
     * @param startY    Die Startposition des Würfels auf der Y-Axis
     * @param diceArray Das Array der Würfel, die gerendert werden sollen
     */
    public void render(SpriteBatch batch, boolean isPilot, float startX, float startY, Dice[] diceArray) {
        Sprite[] diceSprites = isPilot ? currentPilotDiceSprites : currentCopilotDiceSprites;
        for (int i = 0; i < diceSprites.length; i++) {
            Sprite sprite = diceSprites[i];
            if(!diceArray[i].isPlaced()){
                sprite.setPosition(startX, startY + i * 75); // Space dice vertically
            }
            sprite.setSize(50, 50); // Set size for rendering
            sprite.draw(batch);
        }
    }

    /**
     * Gibt die Sprites der aktuellen Pilot-Würfel zurück.
     *
     * @return Die Sprites der aktuellen Pilot-Würfel
     */
    public Sprite[] getCurrentPilotDiceSprites() {
        return currentPilotDiceSprites;
    }

    /**
     * Gibt die Sprites der aktuellen Copilot-Würfel zurück.
     *
     * @return Die Sprites der aktuellen Copilot-Würfel
     */
    public Sprite[] getCurrentCopilotDiceSprites() {
        return currentCopilotDiceSprites;
    }

    /**
     * Gibt die verwendeten Texturen für alle Würfel frei.
     */
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
