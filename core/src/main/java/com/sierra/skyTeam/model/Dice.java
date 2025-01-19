package com.sierra.skyTeam.model;

import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.Random;

/**
 * Die Klasse {@code Dice} repräsentiert einen Würfel im Spiel.
 * Sie enthält Methoden zum Würfeln, Neuwürfeln und Abfragen von Würfelwerten.
 * Zusätzlich wird die Sprite-Darstellung des Würfels und die Verwaltung von Reroll- und Platzierungszuständen unterstützt.
 */
public class Dice {
    private int diceValue;
    private final Random random;
    private boolean isPlaced;
    private Sprite diceSprite;
    private boolean selectedForReroll;

    /**
     * Konstruktor für die Klasse {@code Dice}.
     * Initialisiert den Würfel mit einem zufälligen Wert zwischen 1 und 6.
     */
    public Dice() {
        this.random = new Random();
        this.diceValue = random.nextInt(6) + 1;
        this.isPlaced = false;
        this.selectedForReroll = false;
    }

    /**
     * Gibt den aktuellen Wert des Würfels zurück.
     *
     * @return Der Wert des Würfels (zwischen 1 und 6).
     */
    public int getDiceValue() {
        return diceValue;
    }

    /**
     * Würfelt den Würfel erneut und setzt den Wert auf eine zufällige Zahl zwischen 1 und 6.
     */
    public void reroll() {
        this.diceValue = random.nextInt(6) + 1;
    }

    /**
     * Setzt den Wert des Würfels auf einen angegebenen Wert.
     *
     * @param newDiceValue Der neue Wert des Würfels (zwischen 1 und 6).
     */
    public void setDiceValue(int newDiceValue) {
        this.diceValue = newDiceValue;
    }

    /**
     * Setzt die Sprite-Darstellung des Würfels.
     *
     * @param diceSprite Das Sprite-Objekt, das den Würfel darstellt.
     */
    public void setDiceSprite(Sprite diceSprite){
        this.diceSprite = diceSprite;
    }

    /**
     * Gibt das Sprite des Würfels zurück.
     *
     * @return Das Sprite-Objekt, das den Würfel darstellt.
     */
    public Sprite getDiceSprite() {
        return diceSprite;
    }

    /**
     * Gibt den Status zurück, ob der Würfel platziert wurde.
     *
     * @return {@code true}, wenn der Würfel platziert wurde, sonst {@code false}.
     */
    public boolean isPlaced() {
        return isPlaced;
    }

    /**
     * Setzt den Platzierungsstatus des Würfels.
     *
     * @param placed Der Platzierungsstatus. {@code true}, wenn der Würfel platziert wurde, sonst {@code false}.
     */
    public void setPlaced(boolean placed) {
        isPlaced = placed;
    }

    /**
     * Gibt den Status zurück, ob der Würfel für ein Reroll ausgewählt wurde.
     *
     * @return {@code true}, wenn der Würfel für ein Reroll ausgewählt wurde, sonst {@code false}.
     */
    public boolean isSelectedForReroll() {
        return selectedForReroll;
    }

    /**
     * Setzt den Status des Würfels für ein Reroll.
     *
     * @param selectedForReroll {@code true}, wenn der Würfel für ein Reroll ausgewählt wurde, sonst {@code false}.
     */
    public void setSelectedForReroll(boolean selectedForReroll) {
        this.selectedForReroll = selectedForReroll;
    }
}
