package com.sierra.skyTeam.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.sierra.skyTeam.model.Dice;

/**
 * Die Klasse {@code DiceValueUpdater} verwaltet die Aktualisierung von Würfelwerten,
 * die Interaktionen mit Würfeln und Kaffee sowie die Anzeige von UI-Elementen zur Änderung der Würfelwerte.
 */
public class DiceValueUpdater {
    private boolean showOptions = false;
    private final Sprite diceChangerPlusSprite;
    private final Sprite diceChangerMinusSprite;
    public CoffeeView selectedCoffee;
    private Sprite selectedDice;
    Viewport viewport;
    DiceView diceView;
    private final Dice[] pilotDice;
    private final Dice[] copilotDice;
    int selectedIndex = -1;
    int initialDiceValue;
    private boolean isConfirmationPending = false;
    private boolean lastActionWasIncrease;
    private boolean lastActionWasDecrease;
    private boolean lastActionWasIncreaseBy2;
    private boolean lastActionWasDecreaseBy2;
    boolean hasIncreasedFrom1 = false;
    boolean hasDecreasedFrom6 = false;
    private boolean showPlusSprite;
    private boolean showMinusSprite;
    private final Runnable onDiceValueChangedCallback;
    private final Runnable onCoffeeInteractionCallBack;

    /**
     * Konstruktor zum Erstellen einer Instanz von {@code DiceValueUpdater}.
     *
     * @param viewport                   Das Viewport, das für das Skalieren von Eingaben und das Rendern von Elementen verwendet wird.
     * @param diceView                   Die Ansicht, die für die Anzeige und Aktualisierung der Würfel-Sprites verantwortlich ist.
     * @param pilotDice                  Das Array der Pilot-Würfel.
     * @param copilotDice                Das Array der Co-Pilot-Würfel.
     * @param onDiceValueChangedCallBack Die Callback-Methode, die aufgerufen wird, wenn sich ein Würfelwert ändert.
     * @param onCoffeeInteractionCallBack Die Callback-Methode, die bei Interaktionen mit Kaffee aufgerufen wird.
     */
    public DiceValueUpdater(Viewport viewport, DiceView diceView,
                            Dice[] pilotDice, Dice[] copilotDice,
                            Runnable onDiceValueChangedCallBack,
                            Runnable onCoffeeInteractionCallBack){
        diceChangerPlusSprite = new Sprite(new Texture("diceChangerPlus.png"));
        diceChangerMinusSprite = new Sprite(new Texture("diceChangerMinus.png"));
        this.viewport = viewport;
        this.diceView = diceView;
        this.pilotDice = pilotDice;
        this.copilotDice = copilotDice;
        this.onDiceValueChangedCallback = onDiceValueChangedCallBack;
        this.onCoffeeInteractionCallBack = onCoffeeInteractionCallBack;
    }

    /**
     * Zeigt die Optionen zum Ändern eines Würfelwerts an.
     */
    public void showOptions() {
        showOptions = true;
        int diceValue = getDiceValueFromSelectedIndex(selectedIndex);
        if(diceValue == 1) {
            showPlusSprite = true;
            showMinusSprite = false;
        } else if (diceValue == 6) {
            showPlusSprite = false;
            showMinusSprite = true;
        } else {
            showPlusSprite = true;
            showMinusSprite = true;
        }
        resetSelection();
    }

    /**
     * Verbirgt die Optionen zum Ändern eines Würfelwerts.
     */
    public void hideOptions() {
        showOptions = false;
        resetAllBooleans();
    }

    /**
     * Setzt den ausgewählten Würfel.
     *
     * @param selectedDice Der Sprite des ausgewählten Würfels.
     */
    public void setSelectedDice(Sprite selectedDice){
        this.selectedDice = selectedDice;
        selectedIndex = getSelectedDiceIndex(selectedDice);

        boolean isPilotDice = selectedIndex < diceView.getCurrentPilotDiceSprites().length;
        int diceArrayIndex = selectedIndex % 4;
        Dice dice = isPilotDice ? pilotDice[diceArrayIndex] : copilotDice[diceArrayIndex];
        initialDiceValue = dice.getDiceValue();
    }

    /**
     * Setzt der ausgewählte Kaffee.
     *
     * @param selectedCoffee Der ausgewählte Kaffee.
     */
    public void setSelectedCoffee(CoffeeView selectedCoffee) {
        this.selectedCoffee = selectedCoffee;
    }

    /**
     * Gibt den Index des ausgewählten Würfels zurück, basierend auf dem übergebenen Sprite.
     *
     * @param selectedDice Der Sprite des ausgewählten Würfels.
     * @return Der Index des ausgewählten Würfels oder -1, wenn der Sprite nicht gefunden wurde.
     */
    private int getSelectedDiceIndex(Sprite selectedDice) {
        Sprite[] pilotDiceSprites = diceView.getCurrentPilotDiceSprites();
        Sprite[] copilotDiceSprites = diceView.getCurrentCopilotDiceSprites();
        Sprite[] allSprites = new Sprite[pilotDiceSprites.length + copilotDiceSprites.length];
        System.arraycopy(pilotDiceSprites, 0, allSprites, 0, pilotDiceSprites.length);
        System.arraycopy(copilotDiceSprites, 0, allSprites, pilotDiceSprites.length, copilotDiceSprites.length);

        for (int i = 0; i < allSprites.length; i++) {
            if (allSprites[i] == selectedDice) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Gibt den Wert des Würfels basierend auf dem ausgewählten Index zurück.
     *
     * @param selectedIndex Der Index des ausgewählten Würfels.
     * @return Der Wert des Würfels, der dem ausgewählten Index entspricht.
     */
    private int getDiceValueFromSelectedIndex(int selectedIndex) {
        boolean isPilotDice = selectedIndex < diceView.getCurrentPilotDiceSprites().length;
        int diceArrayIndex = selectedIndex % 4;

        if (isPilotDice) {
            Dice selectedPilotDice = pilotDice[diceArrayIndex];
            return selectedPilotDice.getDiceValue();
        } else {
            Dice selectedCopilotDice = copilotDice[diceArrayIndex];
            return selectedCopilotDice.getDiceValue();
        }
    }

    /**
     * Rendert die Optionen und Sprites für die Würfeländerung.
     *
     * @param batch Der {@code SpriteBatch}, der zum Rendern verwendet wird.
     */
    public void render(SpriteBatch batch) {
        if (showOptions) {
            if (showPlusSprite) {
                diceChangerPlusSprite.setScale(0.25F);
                diceChangerPlusSprite.setPosition(selectedDice.getX() - 55, selectedDice.getY() - 15);
                diceChangerPlusSprite.draw(batch);
            }

            if (showMinusSprite) {
                diceChangerMinusSprite.setScale(0.25F);
                diceChangerMinusSprite.setPosition(selectedDice.getX() + 40, selectedDice.getY() - 15);
                diceChangerMinusSprite.draw(batch);
            }
            changeDiceValue();
            valueChecker();
            if(isConfirmationPending) waitForConfirmation();
        }
    }

    /**
     * Ändert den Wert des ausgewählten Würfels basierend auf der Benutzerinteraktion.
     * Die Methode prüft, ob der Benutzer auf den Würfel geklickt hat, und passt den Würfelwert entsprechend an.
     */
    private void changeDiceValue() {
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            Vector2 coordinates = InputHandler.scaledInput(viewport);
            float touchX = coordinates.x;
            float touchY = coordinates.y;

            if (showPlusSprite && diceChangerPlusSprite.getBoundingRectangle().contains(touchX, touchY)) {
                if(initialDiceValue == 1){
                    changeDiceValue(1);
                    lastActionWasIncrease = true;
                    hasIncreasedFrom1 = true;

                } else if (isConfirmationPending && lastActionWasDecreaseBy2) {
                    //if the last action was -2, we do this
                    changeDiceValue(2);
                    lastActionWasIncreaseBy2 = true; // last action was +2
                    lastActionWasDecreaseBy2 = false; // reset -2 boolean
                    lastActionWasDecrease = false; // reset -1 boolean

                } else if (isConfirmationPending && lastActionWasDecrease) {
                    changeDiceValue(2);
                    lastActionWasIncreaseBy2 = true;
                    lastActionWasDecrease = false;
                    lastActionWasDecreaseBy2 = false;

                } else {
                    //default action is +1
                    changeDiceValue(1);
                    lastActionWasIncrease = true;
                }
                showPlusSprite = false;
                showMinusSprite = !hasIncreasedFrom1;
            }

            if (showMinusSprite && diceChangerMinusSprite.getBoundingRectangle().contains(touchX, touchY)) {
                if(initialDiceValue == 6){
                    changeDiceValue(-1);
                    lastActionWasIncrease = true;
                    hasDecreasedFrom6 = true;

                }else if (isConfirmationPending && lastActionWasIncreaseBy2) {
                    //if the last action was +2, we do this
                    changeDiceValue(-2);
                    lastActionWasDecreaseBy2 = true; // last action was -2
                    lastActionWasIncreaseBy2 = false; // reset -2 boolean
                    lastActionWasIncrease = false; // reset -1 boolean

                } else if (isConfirmationPending && lastActionWasIncrease) {
                    changeDiceValue(-2);
                    lastActionWasDecreaseBy2 = true;
                    lastActionWasIncrease = false;
                    lastActionWasIncreaseBy2 = false;

                } else {
                    changeDiceValue(-1);
                    lastActionWasDecrease = true;
                }
                showMinusSprite = false;
                showPlusSprite = !hasDecreasedFrom6;
            }
            isConfirmationPending = true;
        }
    }

    /**
     * Aktualisiert den Wert eines Würfels basierend auf einer Änderung.
     *
     * @param dice          Der Würfel, dessen Wert geändert werden soll.
     * @param changeAmount  Die Änderungsmenge (positiv oder negativ).
     */
    public void updateDiceValue(Dice dice, int changeAmount) {
        if(dice != null && ((dice.getDiceValue() + changeAmount) <= 6) &&
            ((dice.getDiceValue() + changeAmount) >= 1)){
            dice.setDiceValue(dice.getDiceValue() + changeAmount);
            diceView.updateSprites(pilotDice, copilotDice);
        }
    }

    /**
     * Ändert den Wert des ausgewählten Würfels.
     *
     * @param changeAmount Die Änderungsmenge (positiv oder negativ).
     */
    public void changeDiceValue(int changeAmount) {
        if (selectedIndex != -1) {
            boolean isPilotDice = selectedIndex < diceView.getCurrentPilotDiceSprites().length;
            int diceArrayIndex = selectedIndex % 4;

            Dice selectedDice = isPilotDice ? pilotDice[diceArrayIndex] : copilotDice[diceArrayIndex];
            updateDiceValue(selectedDice, changeAmount);
        }
    }

    /**
     * Wartet auf die Bestätigung des Spielers, bevor die Änderungen übernommen werden.
     */
    private void waitForConfirmation() {
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            Vector2 coordinates = InputHandler.scaledInput(viewport);
            float touchX = coordinates.x;
            float touchY = coordinates.y;

            if (selectedDice.getBoundingRectangle().contains(touchX, touchY)) {
                resetSelection();
                boolean isPilotDice = selectedIndex < diceView.getCurrentPilotDiceSprites().length;
                int diceArrayIndex = selectedIndex % 4;
                Dice dice = isPilotDice ? pilotDice[diceArrayIndex] : copilotDice[diceArrayIndex];
                if(dice.getDiceValue() != initialDiceValue){
                    removeCoffee();
                }
                hideOptions();
            }
        }
    }

    /**
     * Überprüft, ob der Würfelwert auf den minimalen oder maximalen Wert eingestellt wurde.
     */
    private void valueChecker() {
        if (hasDecreasedFrom6 || hasIncreasedFrom1) {
            showMinusSprite = false;
            showPlusSprite = false;
        }
    }

    /**
     * Setzt alle booleans zurück.
     */
    private void resetAllBooleans() {
        lastActionWasIncrease = false;
        lastActionWasDecrease = false;
        lastActionWasIncreaseBy2 = false;
        lastActionWasDecreaseBy2 = false;
        hasIncreasedFrom1 = false;
        hasDecreasedFrom6 = false;
    }

    /**
     * Setzt die aktuelle Auswahl zurück und führt die Callback-Methode aus.
     */
    private void resetSelection() {
        if (onDiceValueChangedCallback != null) {
            onDiceValueChangedCallback.run();
        }
    }

    /**
     * Führt die Callback-Methode für das Entfernen von Kaffee aus.
     */
    public void removeCoffee() {
        if(onCoffeeInteractionCallBack != null){
            onCoffeeInteractionCallBack.run();
        }
    }

    /**
     * Gibt die Sprite für das Plus-Icon zurück.
     *
     * @return Der Sprite für das Plus-Icon.
     */
    public Sprite getDiceChangerPlusSprite() {
        return diceChangerPlusSprite;
    }

    /**
     * Gibt die Sprite für das Minus-Icon zurück.
     *
     * @return Der Sprite für das Plus-Icon.
     */
    public Sprite getDiceChangerMinusSprite() {
        return diceChangerMinusSprite;
    }

    /**
     * Überprüft, ob das Plus-Icon angezeigt ist.
     *
     * @return {@code true}, wenn das Plus-Icon angezeigt ist, andernfalls {@code false}.
     */
    public boolean showPlusSprite() {
        return showPlusSprite;
    }

    /**
     * Überprüft, ob das Minus-Icon angezeigt ist.
     *
     * @return {@code true}, wenn das Minus-Icon angezeigt ist, andernfalls {@code false}.
     */
    public boolean showMinusSprite() {
        return showMinusSprite;
    }
}
