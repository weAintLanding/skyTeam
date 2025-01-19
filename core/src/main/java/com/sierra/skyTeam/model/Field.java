package com.sierra.skyTeam.model;

/**
 * Die {@code Field}-Klasse stellt ein Spielfeld dar, auf dem Würfel platziert werden können.
 * Sie enthält Methoden zum Platzieren von Würfeln, Zurücksetzen des Feldes und Anzeigen des Zustands des Feldes.
 */
public class Field {
    private final String fieldType;
    private int diceValue;
    private boolean occupied;
    private boolean switchLight;

    /**
     * Konstruktor für die {@code Field}-Klasse.
     *
     * @param fieldType Der Typ des Spielfelds.
     */
    public Field(String fieldType) {
        this.fieldType = fieldType;
        this.occupied = false;
        this.diceValue = -1;
        this.switchLight = false;
    }

    /**
     * Platziert einen Würfel auf diesem Spielfeld, wenn es noch nicht besetzt ist.
     *
     * @param diceValue Der Wert des Würfels, der auf dem Spielfeld platziert wird.
     * @return {@code true}, wenn der Würfel erfolgreich platziert wurde, andernfalls {@code false}.
     */
    public boolean placeDice(int diceValue) {
        if (occupied) {
            return false;
        }
        this.diceValue = diceValue;
        this.occupied = true;
        return true;
    }

    /**
     * Setzt das Spielfeld zurück, indem der Würfel entfernt und das Feld als unbesetzt markiert wird.
     */
    public void resetField() {
        this.diceValue = -1;
        this.occupied = false;
    }

    /**
     * Gibt zurück, ob das Spielfeld besetzt ist.
     *
     * @return {@code true}, wenn das Spielfeld besetzt ist, andernfalls {@code false}.
     */
    public boolean isOccupied() {
        return occupied;
    }

    /**
     * Gibt den Zustand des Spielfelds aus, einschließlich des Typs des Feldes, ob es besetzt ist,
     * des Würfelwerts (falls besetzt) und der Switch-status.
     */
    public void displayState() {
        System.out.println("fieldView Type: " + fieldType);
        System.out.println("Occupied: " + occupied);
        if (occupied) {
            System.out.println("Dice Value: " + diceValue);
        } else {
            System.out.println("No dice placed yet.");
        }
        if (switchLight) {
            System.out.println("Turned On.");
        }else{
            System.out.println("Not Turned On.");
        }
    }

    /**
     * Gibt zurück, ob das Spielfeld eingeschaltet ist.
     *
     * @return {@code true}, wenn das Spielfeld eingeschaltet ist, andernfalls {@code false}.
     */
    public boolean isSwitchedOn(){
        return switchLight;
    }

    /**
     * Schaltet das Spielfeld ein.
     */
    public void setSwitchOn(){
        this.switchLight = true;
    }

    /**
     * Gibt den Typ des Spielfelds zurück.
     *
     * @return Der Typ des Spielfelds.
     */
    public String getFieldType() {
        return fieldType;
    }

    /**
     * Gibt den Wert des auf dem Spielfeld platzierten Würfels zurück.
     *
     * @return Der Wert des Würfels oder {@code -1}, wenn kein Würfel platziert wurde.
     */
    public int getDiceValue() {
        return diceValue;
    }
}
