package com.sierra.skyTeam.model;

public class AltitudeTrack {
    private final int[] altitudes = {6000, 5000, 4000, 3000, 2000, 1000, 0};
    private final int[] rerollTokens = {1,0,0,0,1,0,0};
    private int currentAltitudeIndex;
    private final GameModel gameModel;

    public AltitudeTrack(GameModel gameModel) {
        currentAltitudeIndex = -1;
        this.gameModel = gameModel;
    }

    public int getCurrentAltitude() {
        return altitudes[currentAltitudeIndex];
    }

    public int getRerollTokens() {
        return rerollTokens[currentAltitudeIndex];
    }

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
    public void displayAltitudeStatus() {
        System.out.print("Current Altitude: " + getCurrentAltitude());
        if (getRerollTokens() > 0) {
            System.out.println(" | Reroll Tokens: " + getRerollTokens());
        } else {
            System.out.println();
        }
    }
}


