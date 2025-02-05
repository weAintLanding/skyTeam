package com.sierra.skyTeam.model;

import com.sierra.skyTeam.MainGame;
import com.sierra.skyTeam.screens.CrashScreen;

/**
 * Die Klasse {@code AxisModel} verwaltet die Ausrichtung der Axis des Flugzeugs
 * und deren Änderungen basierend auf den Aktionen der Spieler.
 */
public class AxisModel {
    private final Airplane airplane;
    private int axisValue = 0;

    /**
     * Konstruktor für {@code AxisModel}.
     *
     * @param airplane Das zugehörige Flugzeug, dessen Axis verwaltet wird.
     */
    public AxisModel(Airplane airplane) {
        this.airplane = airplane;
    }

    /**
     * Ändert die Axis basierend auf den Werten der Spieler und überprüft, ob ein Absturz stattgefunden hat.
     * Wenn ein Absturz erkannt wird, wird der Spieler zur CrashScreen weitergeleitet.
     *
     * @param pilot   Der Spieler, der als Pilot agiert und seinen AxisWert beisteuert.
     * @param copilot Der Spieler, der als Copilot agiert und seinen AxisWert beisteuert.
     * @param game    Die MainGame-Instanz, um den Zustand der Spielszene zu ändern.
     */
    public void changeAxis(Players pilot, Players copilot, MainGame game) {
        Integer pilotValue = pilot.getAxis();
        Integer copilotValue = copilot.getAxis();
        int changeValue = copilotValue - pilotValue;
        axisValue = axisValue + changeValue;
        if(airplane.getGame().checkCrashAxis()){
            game.setScreen(new CrashScreen(game));
        }
    }

    /**
     * Gibt den aktuellen Axis-Wert des Flugzeugs zurück.
     *
     * @return Der aktuelle Axis-Wert.
     */
    public int getAxisValue() {
        return axisValue;
    }
}
