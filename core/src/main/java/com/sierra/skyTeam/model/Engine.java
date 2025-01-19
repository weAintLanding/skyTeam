package com.sierra.skyTeam.model;

import com.sierra.skyTeam.MainGame;
import com.sierra.skyTeam.screens.CrashScreen;

/**
 * Die Klasse {@code Engine} enthält die Logik des Engine-Component, einschließlich der Marker für Aero und Brake und die Bewegungen des Flugzeugs.
 * Sie überprüft auch, ob das Flugzeug landet oder abstürzt, basierend auf den Eingabewerten des Piloten und Copiloten und der Position des Flugzeugs.
 */
public class Engine {
    private final Airplane airplane;
    private int blueAeroMarker = 4;
    private int orangeAeroMarker = 8;
    private int redBrakeMarker = 1;
    private boolean planeLanded;

    /**
     * Konstruktor für die {@code Engine}-Klasse.
     *
     * @param airplane Das Flugzeug, das mit dieser Engine verbunden ist.
     */
    public Engine(Airplane airplane) {
        this.airplane = airplane;
        this.planeLanded = false;
    }

    //Getter and Setter for Aero- and Brake- Markers

    /**
     * Gibt den blauen Aero-Marker-Wert zurück.
     *
     * @return Der Wert des blauen Aero-Markers.
     */
    public int getBlueAeroMarker() {
        return blueAeroMarker;
    }
    /**
     * Setzt den Wert des blauen Aero-Markers.
     *
     * @param blueAeroMarker Der neue Wert des blauen Aero-Markers.
     */
    public void setBlueAeroMarker(int blueAeroMarker) {
        this.blueAeroMarker = blueAeroMarker;
    }

    /**
     * Gibt den orangefarbenen Aero-Marker-Wert zurück.
     *
     * @return Der Wert des orangefarbenen Aero-Markers.
     */
    public int getOrangeAeroMarker() {
        return orangeAeroMarker;
    }
    /**
     * Setzt den Wert des orangefarbenen Aero-Markers.
     *
     * @param orangeAeroMarker Der neue Wert des orangefarbenen Aero-Markers.
     */
    public void setOrangeAeroMarker(int orangeAeroMarker) {
        this.orangeAeroMarker = orangeAeroMarker;
    }

    /**
     * Gibt den roten Bremsmarker-Wert zurück.
     *
     * @return Der Wert des roten Bremsmarkers.
     */
    public int getRedBrakeMarker() {
        return redBrakeMarker;
    }
    /**
     * Setzt den Wert des roten Bremsmarkers.
     *
     * @param redBrakeMarker Der neue Wert des roten Bremsmarkers.
     */
    public void setRedBrakeMarker(int redBrakeMarker) {
        this.redBrakeMarker = redBrakeMarker;
    }


    /**
     * Bewegt das Flugzeug basierend auf den Throttle-Werten des Piloten und Copiloten.
     * Wenn das Flugzeug abstürzt, wird der Crash-Bildschirm angezeigt.
     *
     * @param pilotValue Der Wert des Piloten.
     * @param copilotValue Der Wert des Copiloten.
     * @param trackManager Das Modell für die Flugbahnverfolgung.
     * @param game Das MainGame-Objekt, das den Crash-Bildschirm verwaltet.
     */
    public void movePlane(int pilotValue, int copilotValue, ApproachTrackModel trackManager, MainGame game) {
        int engineSum = pilotValue + copilotValue;

        if (engineSum <= blueAeroMarker) {
        } else if (engineSum <= orangeAeroMarker) {
            if(airplane.getGame().checkCrashMove(1)){
                game.setScreen(new CrashScreen(game));
            }
            if((airplane.getApproachPosition() + 1) > 6){
                game.setScreen(new CrashScreen(game));
            }
            trackManager.updateTrackBy1();
            airplane.setApproachPosition(airplane.getApproachPosition() + 1);
        } else {
            if(airplane.getGame().checkCrashMove(2)){
                game.setScreen(new CrashScreen(game));
            }
            if((airplane.getApproachPosition() + 2) > 6){
                game.setScreen(new CrashScreen(game));
            }
            trackManager.updateTrackBy2();
            airplane.setApproachPosition(airplane.getApproachPosition() + 2);
        }
    }

    /**
     * Landet das Flugzeug basierend auf den Throttle-Werten des Piloten und Copiloten.
     * Wenn die Landung erfolgreich ist, wird das Flugzeug als gelandet markiert.
     * Wenn die Landung fehlschlägt, wird der Crash-Bildschirm angezeigt.
     *
     * @param pilotValue Der Wert des Piloten.
     * @param copilotValue Der Wert des Copiloten.
     * @param game Das Spielobjekt, das den Crash-Bildschirm verwaltet.
     */
    public void landPlane(int pilotValue, int copilotValue, MainGame game) {
        int engineSum = pilotValue + copilotValue;
        if (engineSum <= redBrakeMarker) {
            planeLanded = true;
        }else{
            game.setScreen(new CrashScreen(game));
        }
    }

    /**
     * Gibt zurück, ob das Flugzeug erfolgreich gelandet ist.
     *
     * @return {@code true}, wenn das Flugzeug gelandet ist, sonst {@code false}.
     */
    public boolean isPlaneLanded() {
        return planeLanded;
    }
}
