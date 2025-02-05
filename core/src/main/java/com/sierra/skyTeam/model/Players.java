package com.sierra.skyTeam.model;

import java.util.ArrayList;
import java.util.Random;
import java.util.List;

/**
 * Die {@code Players}-Klasse stellt einen Spieler im Spiel dar.
 * Sie verwaltet die Würfelergebnisse des Spielers, die Slots für die Steuerung (Axis, Throttle(Engine)) und Radiofelder.
 * Die Klasse ermöglicht es dem Spieler, Würfel zu rollen, Würfel neu zu werfen, und mit verschiedenen Spielmechanismen zu interagieren.
 */
public class Players {
    private final GameModel gameModel;
    private final Dice[] diceRolls;
    private Integer axisSlot = null;
    private Integer throttle = null;
    protected int radioSlots;
    protected Field[] radioPlayer;
    Random random = new Random();

    /**
     * Konstruktor für die {@code Players}-Klasse. Initialisiert das Player-objekt und würfelt die Würfel.
     *
     * @param gameModel Das Spielmodell, das mit diesem Spieler verbunden ist.
     */
    public Players(GameModel gameModel) {
        this.gameModel = gameModel;
        diceRolls = new Dice[4];
        this.rollDice();
    }

    /**
     * Gibt das Airplane-Model des Spiels zurück.
     *
     * @return Das Airplane-Model.
     */
    public Airplane getAirplane(){
        return gameModel.getAirplane();
    }

    /**
     * Würfelt die Würfel des Spielers.
     */
    public void rollDice () {
        for(int i = 0; i < 4; i++){
            diceRolls[i] = new Dice();
        }
    }
    /**
     * Gibt eine Liste der Würfel des Spielers zurück.
     *
     * @return Eine Liste der {@link Dice}-Objekte.
     */
    public Dice[] getDiceList() {
        return diceRolls;
    }
    /**
     * Gibt eine String-Darstellung der Würfelergebnisse des Spielers zurück.
     *
     * @return Eine String-Darstellung der Würfelergebnisse.
     */
    public String getDiceRollsString() {
        List<Integer> diceArray = new ArrayList<>();
        for(Dice dice : diceRolls){
            if(dice != null){
                diceArray.add(dice.getDiceValue());
            }
        }
        return diceArray.toString();
    }

    /**
     * Überprüft, ob ein bestimmter Würfelwert vorhanden ist.
     *
     * @param diceValue Der zu überprüfende Würfelwert.
     * @return {@code true}, wenn der Würfelwert vorhanden ist, sonst {@code false}.
     */
    public boolean isDiceThere (int diceValue){
        for(Dice dice : diceRolls){
            if(dice != null && dice.getDiceValue() == diceValue) return true;
        }
        return false;
    }

    /**
     * Gibt das {@link Dice}-Objekt für einen bestimmten Würfelwert zurück.
     *
     * @param diceValue Der gesuchte Würfelwert.
     * @return Das {@link Dice}-Objekt oder {@code null}, wenn es nicht gefunden wurde.
     */
    public Dice getDice (int diceValue){
        for(Dice dice : diceRolls){
            if(dice.getDiceValue() == diceValue){
                return dice;
            }
        }
        return null;
    }
    /**
     * Entfernt einen Würfel mit einem bestimmten Wert.
     *
     * @param diceValue Der zu entfernende Würfelwert.
     */
    public void removeDice (int diceValue){
        for (Dice dice : diceRolls){
            if (dice != null && dice.getDiceValue() == diceValue){
                dice = null;
                return;
            }
        }
    }
    /**
     * Würfelt die Würfel des Spielers neu, wenn noch Reroll-Token verfügbar sind.
     */
    public void reroll() {
        if (gameModel.getRerollsAvailable() > 0){
            for (int i = 0; i < diceRolls.length; i++) {
                if (diceRolls[i] != null) {
                    int newValue = random.nextInt(6)+1;
                    diceRolls[i].setDiceValue(newValue);
                }
            }
            gameModel.decreaseRerollsAvailable();
        } else {
            System.out.println("No rerolls available.");
        }
    }

    /**
     * Setzt einen Würfelwert für den Axis-Slot, wenn noch keiner gesetzt wurde.
     *
     * @param dice Der zu setzende Würfel.
     * @return {@code true}, wenn der Würfel erfolgreich gesetzt wurde, sonst {@code false}.
     */
    public boolean setAxis(Dice dice){
        int diceValue = dice.getDiceValue();
        if (axisSlot != null) {
            return false;
        }
        this.axisSlot = diceValue;
        removeDice(diceValue);
        return true;
    }
    /**
     * Gibt den aktuellen Wert des Axis-Slots zurück.
     *
     * @return Der Wert des Axis-Slots oder {@code null}, wenn er nicht gesetzt wurde.
     */
    public Integer getAxis() {
        return axisSlot;
    }
    /**
     * Überprüft, ob der Axis-Slot gesetzt ist.
     *
     * @return {@code true}, wenn der Axis-Slot gesetzt ist, sonst {@code false}.
     */
    public boolean isAxis(){
        return axisSlot != null;
    }

    /**
     * Setzt einen Würfelwert für den Throttle-Slot, wenn noch keiner gesetzt wurde.
     *
     * @param dice Der zu setzende Würfel.
     * @return {@code true}, wenn der Würfel erfolgreich gesetzt wurde, sonst {@code false}.
     */
    public boolean setThrottle(Dice dice){
        int diceValue = dice.getDiceValue();
        if (throttle != null) {
            return false;
        }
        this.throttle = diceValue;
        removeDice(diceValue);
        return true;
    }
    /**
     * Gibt den aktuellen Wert des Throttle-Slots zurück.
     *
     * @return Der Wert des Throttle-Slots oder {@code null}, wenn er nicht gesetzt wurde.
     */
    public Integer getThrottle() {
        return throttle;
    }
    /**
     * Überprüft, ob der Throttle-Slot gesetzt ist.
     *
     * @return {@code true}, wenn der Throttle-Slot gesetzt ist, sonst {@code false}.
     */
    public boolean isThrottle(){
        return throttle != null;
    }

    /**
     * Setzt einen Würfel für den Radio-Slot, wenn ein Funkfeld verfügbar ist.
     *
     * @param dice Der zu setzende Würfel.
     * @return {@code true}, wenn der Würfel erfolgreich im Radio-Slot platziert wurde, sonst {@code false}.
     */
    public boolean setRadio(Dice dice){
        int diceValue = dice.getDiceValue();
        for(Field field: radioPlayer){
            if(!field.isOccupied()){
                field.placeDice(diceValue);
                removeDice(diceValue);
                return true;
            }
        }
        return false;
    }
    /**
     * Setzt alle Funkfelder des Spielers zurück.
     */
    public void clearRadio(){
        for(Field field: radioPlayer){
            field.resetField();
        }
    }

    /**
     * Setzt einen Kaffee im Flugzeug des Spielers, wenn es verfügbar ist.
     *
     * @param dice Der zu verwendende Würfel.
     * @return {@code true}, wenn der Kaffee erfolgreich hinzugefügt wurde, sonst {@code false}.
     */
    public boolean setCoffee (Dice dice){
        boolean coffeeAdded = this.getAirplane().getConcentration().addCoffee();
        this.removeDice(dice.getDiceValue());
        return coffeeAdded;
    }

    /**
     * Setzt alle Slots (Axis, Throttle) des Spielers zurück.
     */
    public void clearSlots(){
        this.axisSlot = null;
        this.throttle = null;
    }
}

