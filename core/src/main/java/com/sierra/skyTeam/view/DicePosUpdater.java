package com.sierra.skyTeam.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.sierra.skyTeam.controller.GameController;
import com.sierra.skyTeam.controller.RerollController;
import com.sierra.skyTeam.controller.RoundController;
import com.sierra.skyTeam.model.Dice;
import com.sierra.skyTeam.model.FieldModel;

import java.util.List;

/**
 * Verwalten und Aktualisieren der Position von Würfeln auf dem Spielfeld.
 * Diese Klasse steuert das Auswählen und Platzierung von Würfeln sowie deren Interaktionen mit anderen Objekten wie Kaffee und Feldern.
 */
public class DicePosUpdater {
    /**
     * Enum, das den aktuellen Zustand des Würfels beschreibt:
     * entweder "SELECTING" oder "PLACING".
     */
    enum State { SELECTING, PLACING }
    private State currentState = State.SELECTING;
    private final CoffeeManager coffeeManager;
    private final boolean isPilot;
    private final Sprite[] diceSprites;
    private final Dice[] diceArray;
    private final List<FieldView> FieldViews;
    private final Viewport viewport;
    private Sprite selectedDice;
    private int lastClickedDiceValue = -1;
    private CoffeeView selectedCoffee = null;
    DiceValueUpdater diceValueUpdater;
    RoundController roundController;
    private boolean dicePlayed;
    RerollController rerollController;

    /**
     * Konstruktor für die DicePosUpdater-Klasse.
     *
     * @param diceView Das View-Objekt für die Würfelansicht.
     * @param diceArray Das Array der Würfelobjekte.
     * @param FieldViews Die Liste der Felder auf dem Spielfeld.
     * @param viewport Das Viewport-Objekt für die Anzeige.
     * @param coffeeManager Der Manager, der die Kaffees verwaltet.
     * @param diceValueUpdater Der Würfelwert-Updater.
     * @param isPilot Boolean, der angibt, ob der Spieler ein Pilot oder Copilot ist.
     * @param roundController Der Controller für die Spielrunde.
     * @param gameController Der Controller für das gesamte Spiel.
     */
    public DicePosUpdater(DiceView diceView, Dice[] diceArray, List<FieldView> FieldViews, Viewport viewport, CoffeeManager coffeeManager, DiceValueUpdater diceValueUpdater, boolean isPilot, RoundController roundController, GameController gameController) {
        this.diceArray = diceArray;
        this.isPilot = isPilot;
        this.diceSprites = isPilot ? diceView.getCurrentPilotDiceSprites() : diceView.getCurrentCopilotDiceSprites();
        this.FieldViews = FieldViews;
        this.viewport = viewport;
        this.coffeeManager = coffeeManager;
        this.diceValueUpdater = diceValueUpdater;
        this.roundController = roundController;
        this.dicePlayed = isPilot ? roundController.getPilotDicePlaced() : roundController.getCopilotDicePlaced();
        this.rerollController = gameController.getRerollController();
    }

    /**
     * Aktualisiert den Zustand der Würfel.
     * Überprüft, ob auf einen Würfel oder ein Feld geklickt wurde und führt die entsprechende Aktion aus.
     */
    public void update() {
        Vector2 coordinates = InputHandler.scaledInput(viewport);
        float touchX = coordinates.x;
        float touchY = coordinates.y;

        handleHoverEffect(touchX, touchY);
        if (!rerollController.getSelectingDice()){
            if (selectedDice != null) {
                handleSelectedEffect();
            }
        } else {
            if (selectedDice != null) {
                selectedDice.setColor(1,1,1,0.7f);
                selectedDice = null;
            }
        }

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            handleInput(touchX, touchY);
        }
    }

    /**
     * Verwaltet die Benutzereingaben für das Auswählen und Platzieren von Würfeln sowie deren Interaktionen mit Kaffees und Feldern.
     *
     * @param touchX Die x-Koordinate des Berührungsereignisses.
     * @param touchY Die y-Koordinate des Berührungsereignisses.
     */
    private void handleInput(float touchX, float touchY) {
        if(isPilot){
            this.dicePlayed = roundController.getPilotDicePlaced();
        } else {
            this.dicePlayed = roundController.getCopilotDicePlaced();
        }
        if (dicePlayed || (roundController.getTurn() && !isPilot) || (!(roundController.getTurn()) && isPilot)) {
            return;
        }

        if(rerollController.getSelectingDice()) {
            for (int i = 0; i < diceSprites.length; i++) {
                if (diceSprites[i].getBoundingRectangle().contains(touchX, touchY)) {
                    Dice dice = diceArray[i];
                    dice.setSelectedForReroll(!dice.isSelectedForReroll());
                    if (dice.isSelectedForReroll()) {
                        diceSprites[i].setColor(1,1,1, 1);
                        System.out.println("1");
                    } else {
                        diceSprites[i].setColor(1,1,1, 0.7f);
                        System.out.println("0.7");
                    }
                    System.out.println("Reroll Dice");
                    break;
                }
            }
            return;
        }

        for (CoffeeView coffee : coffeeManager.getCoffees()) {
            if (coffee.getSprite().getBoundingRectangle().contains(touchX, touchY)  && coffee.isAvailable()) {
                selectedCoffee = coffee;
                System.out.println("Coffee clicked");
            }
        }

        for (int i = 0; i < diceSprites.length; i++) {
            if (!diceArray[i].isPlaced() && diceSprites[i].getBoundingRectangle().contains(touchX, touchY)) {
                selectedDice = diceSprites[i];
                lastClickedDiceValue = diceArray[i].getDiceValue();
                currentState = State.PLACING;
                selectedDice.setColor(1, 1, 1, 1);
                break;
            }
        }
        if (selectedCoffee != null && selectedDice != null) {
            diceValueUpdater.setSelectedDice(selectedDice);
            diceValueUpdater.setSelectedCoffee(selectedCoffee);
            diceValueUpdater.showOptions();
        }

        if (currentState == State.PLACING) {
            handleFieldPlacement(touchX, touchY);
        }
    }

    /**
     * Verwaltet den Hover-Effekt, wenn der Benutzer mit der Maus über Würfel fährt.
     *
     * @param touchX Die x-Koordinate des Hover-Ereignisses.
     * @param touchY Die y-Koordinate des Hover-Ereignisses.
     */
    private void handleHoverEffect(float touchX, float touchY) {
        if(isPilot){
            this.dicePlayed = roundController.getPilotDicePlaced();
        } else {
            this.dicePlayed = roundController.getCopilotDicePlaced();
        }

        if(rerollController.getSelectingDice()) {
            for (int i = 0; i < diceSprites.length; i++) {
                if (diceArray[i].isPlaced()) {
                    continue;
                }

                Dice dice = diceArray[i];

                if(((roundController.getTurn() && isPilot && (!roundController.getPilotDicePlaced())) || (!(roundController.getTurn()) && !isPilot && (!roundController.getCopilotDicePlaced())))) {
                    if (diceSprites[i].getBoundingRectangle().contains(touchX, touchY) && dice.isSelectedForReroll()) {
                        diceSprites[i].setColor(1, 1, 1, 1);
                    }
                } else {
                    diceSprites[i].setColor(1, 1, 1, 0F);
                }
            }
        } else {
            for (int i = 0; i < diceSprites.length; i++) {
                if (diceArray[i].isPlaced()) {
                    continue;
                }

                if (((roundController.getTurn() && isPilot && (!roundController.getPilotDicePlaced())) || (!(roundController.getTurn()) && !isPilot && (!roundController.getCopilotDicePlaced()))) && !rerollController.getSelectingDice()) {
                    if (diceSprites[i].getBoundingRectangle().contains(touchX, touchY) && !dicePlayed) {
                        diceSprites[i].setColor(1, 1, 1, 1);
                    } else {
                        diceSprites[i].setColor(1, 1, 1, 0.7F);
                    }
                } else {
                    diceSprites[i].setColor(1, 1, 1, 0F);
                }
            }
        }
    }

    /**
     * Handhabt die Opacity des Würfels, wenn er ausgewählt wurde, um ihn hervorzuheben.
     */
    private void handleSelectedEffect() {
        selectedDice.setColor(1, 1, 1, 1);
    }

    /**
     * Gibt der Würfel basierend auf dem angegebenen Sprite zurück.
     *
     * @param sprite Die Sprite, dessen Würfel gesucht wird.
     * @return Das zugehörige Würfelobjekt.
     */
    private Dice getDiceFromSprite(Sprite sprite) {
        for (int i = 0; i < diceSprites.length; i++) {
            if (diceSprites[i] == sprite) {
                return diceArray[i];
            }
        }
        return null;
    }

    /**
     * Verwaltet die Platzierung eines Würfels auf einem Feld.
     *
     * @param touchX Die x-Koordinate des Klicks.
     * @param touchY Die y-Koordinate des Klicks.
     */
    private void handleFieldPlacement(float touchX, float touchY) {
        for (FieldView field : FieldViews) {
            if (field.getBounds().contains(touchX, touchY)) {
                FieldModel fieldModel = field.getFieldModel();
                if(fieldModel.hasSwitch()){
                    System.out.println("Field with switch");
                    if (fieldModel.isDiceAllowed(lastClickedDiceValue, isPilot)){
                        if (!fieldModel.isOccupied()) {
                            fieldModel.placeDice(getDiceFromSprite(selectedDice), isPilot, field);
                            System.out.println("Placing dice");
                            this.disableDice();
                            for (int i = 0; i < diceSprites.length; i++) {
                                if (diceSprites[i] == selectedDice) {
                                    diceArray[i].setPlaced(true); // No need to recheck isPilot
                                    System.out.println(diceArray[i].isPlaced());
                                    break;
                                }
                            }
                            resetSelection();
                            break;
                        } else {
                            System.out.println("Field is occupied");
                        }
                    } else {
                        System.out.println("Dice value not allowed in this field.");
                        resetSelection();
                        break;
                    }
                } else {
                    if (fieldModel.isDiceAllowed(lastClickedDiceValue, isPilot)) {
                        if (!fieldModel.isOccupied()) {
                            if (isCoffeeField(field)) {
                                handleCoffeeFieldPlacement(fieldModel);
                            }
                            System.out.println("Placing dice");
                            this.disableDice();
                            if (fieldModel.placeDice(getDiceFromSprite(selectedDice), isPilot, field)) {
                                for (int i = 0; i < diceSprites.length; i++) {
                                    if (diceSprites[i] == selectedDice) {
                                        diceArray[i].setPlaced(true); // No need to recheck isPilot
                                        break;
                                    }
                                }
                            }
                            resetSelection();
                            break;
                        } else {
                            System.out.println("Field is occupied");
                        }
                    } else {
                        System.out.println("Dice value not allowed in this field.");
                        resetSelection();
                        break;
                    }
                }
            }
        }
    }

    /**
     * Verwaltet die Platzierung eines Würfels auf einem Kaffee-Feld.
     *
     * @param fieldModel Das Kaffee-Feld, auf dem der Würfel platziert wird.
     */
    private void handleCoffeeFieldPlacement(FieldModel fieldModel){
        if(!fieldModel.isOccupied()){
            coffeeManager.placeCoffee();
            System.out.println("Dice placed in coffee field");
        }
    }

    /**
     * Überprüft, ob das angegebene Feld ein Kaffee-Feld ist.
     *
     * @param field Das Feld, das überprüft wird.
     * @return true, wenn es ein Kaffee-Feld ist, andernfalls false.
     */
    private boolean isCoffeeField(FieldView field) {
        return coffeeManager.coffeeFields.contains(field);
    }

    /**
     * Setzt die Auswahl zurück.
     */
    public void resetSelection() {
        System.out.println("Resetting selection");
        selectedDice = null;
        lastClickedDiceValue = -1;
        selectedCoffee = null;
        currentState = State.SELECTING;
    }

    /**
     * Deaktiviert die Verwendung von Würfeln, nachdem sie platziert wurden.
     */
    public void disableDice() {
        if(isPilot){
            roundController.setPilotDicePlacedTrue();
        } else {
            roundController.setCopilotDicePlacedTrue();
        }
    }
}
