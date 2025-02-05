package com.sierra.skyTeam.model;

/**
 * Die Klasse {@code Concentration} verwaltet den Kaffeebestand des Flugzeugs und ermöglicht
 * es dem Spieler, Kaffee hinzuzufügen und zu verwenden, um den Würfelwert zu modifizieren.
 * Es gibt Methoden zum Überprüfen des verfügbaren Kaffees und zum Hinzufügen von Kaffee,
 * wenn noch Platz vorhanden ist.
 */
public class Concentration {
    private int coffeeAvailable = 0;

    /**
     * Gibt die Anzahl des verfügbaren Kaffees zurück.
     *
     * @return Die Anzahl des verfügbaren Kaffees.
     */
    public int getCoffeeAvailable() {
        return coffeeAvailable;
    }

    /**
     * Überprüft, ob Kaffee verfügbar ist.
     *
     * @return {@code true}, wenn Kaffee verfügbar ist, andernfalls {@code false}.
     */
    public boolean checkCoffeeAvailable() {
        return coffeeAvailable > 0;
    }

    /**
     * Fügt dem Kaffeevorrat einen weiteren Kaffee hinzu, solange der maximale Kaffeevorrat
     * (3) nicht überschritten wird.
     *
     * @return {@code true}, wenn Kaffee hinzugefügt wurde, andernfalls {@code false}.
     */
    public boolean addCoffee() {
        int maxCoffee = 3;
        if(coffeeAvailable < maxCoffee) {
            coffeeAvailable++;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Verwendet eine bestimmte Menge Kaffee, um den Würfelwert zu modifizieren.
     * Der Würfelwert wird um den angegebenen {@code coffeeAmount} erhöht, solange der
     * resultierende Wert zwischen 1 und 6 liegt.
     *
     * @param dice Der Würfel, dessen Wert modifiziert werden soll.
     * @param coffeeAmount Die Menge an Kaffee, die verwendet werden soll.
     * @return {@code true}, wenn der Kaffee erfolgreich verwendet wurde, andernfalls {@code false}.
     */
    public boolean useCoffee(Dice dice, int coffeeAmount) {
        int diceValue = dice.getDiceValue();
        int modifiedDiceValue = diceValue + coffeeAmount;

        if(modifiedDiceValue >= 1 && modifiedDiceValue <= 6) {
            coffeeAvailable -= Math.abs(coffeeAmount);
            dice.setDiceValue(modifiedDiceValue);
            return true;
        } else {
            return false;
        }
    }
}
