package com.sierra.skyTeam.model;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Die Klasse {@code ApproachTrack} repräsentiert einen ApproachTrack mit mehreren Positionen,
 * der die Anzahl der Flugzeuge an jeder Position erfasst.
 */
public class ApproachTrack {
    private final int length = 7; // Total length of the approach track
    private final ArrayList<Integer> track; // Number of airplanes at each Index

    /**
     * Konstruktor: Initialisiert die ApproachTrack mit vordefinierten Flugzeugzahlen.
     */
    public ApproachTrack() {
        this.track = new ArrayList<>(Arrays.asList(0, 0, 1, 2, 1, 3, 2));
    }

    /**
     * Gibt die Gesamtlänge der ApproachTrack zurück.
     *
     * @return Die Länge der ApproachTrack.
     */
    public int getLength() {
        return length;
    }

    /**
     * Gibt die Anzahl der Flugzeuge an einer bestimmten Position zurück.
     *
     * @param index Die Position, für die die Anzahl der Flugzeuge abgerufen werden soll.
     * @return Die Anzahl der Flugzeuge an der angegebenen Position oder -1, wenn die Position ungültig ist.
     */
    public int getAirplanesAt(int index) {
        if (index >= 0 && index < length) {
            return track.get(index);
        }
        return -1;
    }

    /**
     * Entfernt ein Flugzeug von einer bestimmten Position.
     *
     * @param position Die Position, von der ein Flugzeug entfernt werden soll.
     * @return {@code true}, wenn das Flugzeug erfolgreich entfernt wurde, {@code false},
     * wenn keine Flugzeuge an der Position vorhanden sind oder die Position ungültig ist.
     */
    public boolean removeAirplane(int position) {
        if (position >= 0 && position < length && track.get(position) > 0) {
            track.set(position, track.get(position) - 1); // Decrease airplane count
            return true; // Successfully removed
        }
        return false; // Nothing to remove
    }

    /**
     * Überprüft, ob an einer bestimmten Position Flugzeuge vorhanden sind.
     *
     * @param position Die zu überprüfende Position.
     * @return {@code true}, wenn Flugzeuge an der Position vorhanden sind, sonst {@code false}.
     */
    public boolean hasAirplanesAt(int position) {
        if (position >= 0 && position < length) {
            return track.get(position) > 0;
        }
        return false;
    }

    /**
     * Entfernt ein Flugzeug an einer durch Radio berechneten Position.
     *
     * @param currentPosition Die aktuelle Position des Flugzeugs.
     * @param diceValue       Der Würfelwert, der die Zielposition bestimmt.
     * @return {@code true}, wenn das Flugzeug erfolgreich entfernt wurde,
     * {@code false}, wenn die Zielposition ungültig ist oder kein Flugzeug vorhanden ist.
     */
    public boolean removeAirplaneWithRadio(int currentPosition, int diceValue) {
        int targetPosition = currentPosition + diceValue - 1; // Adjust for 1-based counting
        if (targetPosition >= 0 && targetPosition < length) {
            return removeAirplane(targetPosition);
        } else {
            System.out.println("Target position out of bounds: " + targetPosition);
            return false;
        }
    }
}
