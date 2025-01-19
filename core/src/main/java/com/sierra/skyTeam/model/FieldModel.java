package com.sierra.skyTeam.model;

import com.sierra.skyTeam.view.FieldView;

/**
 * Die {@code FieldModel}-Klasse repräsentiert das Modell eines Spielfelds,
 * auf dem Würfel platziert werden können. Sie verwaltet die Platzierung von
 * Würfeln, die Überprüfung der erlaubten Würfelwerte und den Zustand des
 * Spielfelds, einschließlich eines möglichen Schalters und der Besetzung des Felds.
 */
public class FieldModel {
    private Dice placedDice;
    private int diceValue;
    private boolean isOccupied;
    private final boolean pilotOnly;
    private final boolean bothPilots;
    private final boolean hasSwitch;
    private boolean diceProcessed;
    private boolean switchOn;
    private final int[] allowedValues;
    private FieldModel previousField;

    /**
     * Konstruktor für die {@code FieldModel}-Klasse.
     *
     * @param pilotOnly Gibt an, ob das Feld nur für den Piloten zugänglich ist.
     * @param bothPilots Gibt an, ob das Feld von beiden Piloten gleichzeitig genutzt werden kann.
     * @param hasSwitch Gibt an, ob das Feld einen Schalter hat.
     * @param allowedValues Ein Array von erlaubten Würfelwerten für dieses Feld.
     */
    public FieldModel(boolean pilotOnly, boolean bothPilots, boolean hasSwitch, int[] allowedValues) {
        this.pilotOnly = pilotOnly;
        this.bothPilots = bothPilots;
        this.hasSwitch = hasSwitch;
        this.allowedValues = allowedValues;
        this.switchOn = false;
        this.isOccupied = false;
    }

    /**
     * Überprüft, ob ein Würfel auf diesem Spielfeld platziert werden kann.
     *
     * @param isPilot Gibt an, ob der Spieler ein Pilot ist.
     * @return {@code true}, wenn der Würfel platziert werden kann, andernfalls {@code false}.
     */
    public boolean canPlaceDice (boolean isPilot) {
        if (pilotOnly && isPilot) return false;
        if (!pilotOnly && !isPilot) return false;
        return !bothPilots;
    }

    /**
     * Überprüft, ob der angegebene Würfelwert auf diesem Spielfeld platziert werden kann.
     *
     * @param diceValue Der Wert des Würfels.
     * @param isPilot Gibt an, ob der Spieler ein Pilot ist.
     * @return {@code true}, wenn der Würfelwert erlaubt ist und auf diesem Spielfeld platziert werden kann, andernfalls {@code false}.
     */
    public boolean isDiceAllowed (int diceValue,boolean isPilot) {
        if(allowedValues == null){
            System.out.println("Dice Not Allowed.");
            return false;
        }
        if (canPlaceDice(isPilot)) {
            System.out.println("Player not allowed to place dice in this field.");
            return false;
        }

        if (previousField != null && !previousField.switchOn) {
            System.out.println("Previous field must be activated first.");
            return false;
        }

        for(int allowedValue : allowedValues){
            if(diceValue == allowedValue){
                this.diceValue = diceValue;
                toggleSwitch();
                return true;
            }
        }
        return false;
    }

    /**
     * Platziert einen Würfel auf diesem Spielfeld, wenn dies erlaubt ist.
     *
     * @param dice Der Würfel, der platziert werden soll.
     * @param isPilot Gibt an, ob der Spieler ein Pilot ist.
     * @param fieldView Die Ansicht des Spielfelds, auf dem der Würfel platziert werden soll.
     * @return {@code true}, wenn der Würfel erfolgreich platziert wurde, andernfalls {@code false}.
     */
    public boolean placeDice(Dice dice, boolean isPilot, FieldView fieldView) {
        if (canPlaceDice(isPilot)) {
            return false;
        }
        this.placedDice = dice;
        this.isOccupied = true;

        float centerX = fieldView.getBounds().x + (fieldView.getBounds().width - placedDice.getDiceSprite().getWidth()) / 2;
        float centerY = fieldView.getBounds().y + (fieldView.getBounds().height - placedDice.getDiceSprite().getHeight()) / 2;
        placedDice.getDiceSprite().setPosition(centerX, centerY);

        toggleSwitch();
        return true;
    }

    /**
     * Entfernt den platzierten Würfel vom Spielfeld.
     */
    public void removeDice() {
        this.placedDice = null;
        this.isOccupied = false;
    }

    /**
     * Gibt den auf diesem Spielfeld platzierten Würfel zurück.
     *
     * @return Der platzierte Würfel.
     */
    public Dice getPlacedDice() {
        return placedDice;
    }

    /**
     * Gibt zurück, ob der Würfel auf diesem Spielfeld bereits verarbeitet wurde.
     *
     * @return {@code true}, wenn der Würfel verarbeitet wurde, andernfalls {@code false}.
     */
    public boolean isDiceProcessed() {
        return diceProcessed;
    }

    /**
     * Setzt den Verarbeitungsstatus des Würfels auf den angegebenen Wert.
     *
     * @param diceProcessed Der neue Verarbeitungsstatus des Würfels.
     */
    public void setDiceProcessed(boolean diceProcessed) {
        this.diceProcessed = diceProcessed;
    }

    /**
     * Schaltet den Schalter des Spielfelds um, falls das Feld einen Schalter hat.
     */
    public void toggleSwitch() {
        if (hasSwitch && !switchOn) {
            switchOn = true;
        }
    }

    /**
     * Gibt zurück, ob dieses Spielfeld einen Schalter hat.
     *
     * @return {@code true}, wenn das Spielfeld einen Schalter hat, andernfalls {@code false}.
     */
    public boolean hasSwitch() {
        return hasSwitch;
    }

    /**
     * Gibt zurück, ob der Schalter dieses Spielfelds eingeschaltet ist.
     *
     * @return {@code true}, wenn der Schalter eingeschaltet ist, andernfalls {@code false}.
     */
    public boolean getIsSwitchOn() {
        return switchOn;
    }

    /**
     * Gibt zurück, ob dieses Spielfeld besetzt ist.
     *
     * @return {@code true}, wenn das Spielfeld besetzt ist, andernfalls {@code false}.
     */
    public boolean isOccupied() {
        return isOccupied;
    }

    /**
     * Setzt das vorherige Spielfeld für dieses Spielfeld.
     *
     * @param previousField Das vorherige Spielfeld.
     */
    public void setPreviousField(FieldModel previousField) {
        this.previousField = previousField;
    }
}
