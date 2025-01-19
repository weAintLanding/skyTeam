package com.sierra.skyTeam.model;

/**
 * Die Klasse {@code AltitudeTrack} enthält die Flughöhe des Flugzeugs sowie die verfügbaren
 * Reroll-Token auf verschiedenen Höhenstufen.
 */
public class AltitudeTrack {
    private final int[] altitudes = {6000, 5000, 4000, 3000, 2000, 1000, 0};
    private final int[] rerollTokens = {1,0,0,0,1,0,0};
    private int currentAltitudeIndex;
    private final GameModel gameModel;

    /**
     * Konstruktor für die Klasse {@code AltitudeTrack}.
     * Initialisiert die Flughöhe und verknüpft sie mit dem {@code GameModel}.
     *
     * @param gameModel Das GameModel, das mit dieser AltitudeTrack verknüpft ist.
     */
    public AltitudeTrack(GameModel gameModel) {
        currentAltitudeIndex = -1;
        this.gameModel = gameModel;
    }

    /**
     * Gibt die aktuelle Flughöhe zurück.
     *
     * @return Die aktuelle Flughöhe.
     */
    public int getCurrentAltitude() {
        return altitudes[currentAltitudeIndex];
    }

    /**
     * Gibt die Anzahl der verfügbaren Reroll-Token auf der aktuellen Flughöhe zurück.
     *
     * @return Die Anzahl der Reroll-Token.
     */
    public int getRerollTokens() {
        return rerollTokens[currentAltitudeIndex];
    }

    /**
     * Senkt die Flughöhe, indem der Index zur nächsten Höhenstufe erhöht wird.
     * Falls Reroll-Token auf der neuen Flughöhe verfügbar sind, werden sie dem GameModel hinzugefügt.
     */
    public void descend() {
        if (currentAltitudeIndex < altitudes.length - 1) {
            currentAltitudeIndex++;
            System.out.print("Descending and Current Altitude is: " + getCurrentAltitude());
            if (getRerollTokens() > 0) {
                gameModel.setRerollsAvailable();
            }
            System.out.println(" | Reroll Tokens: " + gameModel.getRerollsAvailable());
        } else {
            System.out.println("Already at the final altitude (landed).");
            System.out.println(" | Reroll Tokens: " + gameModel.getRerollsAvailable());
        }
    }

    /**
     * Zeigt den aktuellen Höhenstatus an, einschließlich der verfügbaren Reroll-Token.
     */
    public void displayAltitudeStatus() {
        System.out.print("Current Altitude: " + getCurrentAltitude());
        if (getRerollTokens() > 0) {
            System.out.println(" | Reroll Tokens: " + getRerollTokens());
        } else {
            System.out.println();
        }
    }
}
