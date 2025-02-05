package com.sierra.skyTeam.model;

/**
 * Die {@code LandingGear}-Klasse enthält die Fahrwerkfelder des Flugzeugs, einschließlich der Aktivierung
 * von Fahrwerkfeldern basierend auf Würfelergebnissen und der Überprüfung der gültigen Würfelwerte.
 * Diese Klasse verfolgt die Anzahl der aktivierten Fahrwerkfelder auch.
 */
public class LandingGear {
    private final Airplane airplane;
    private final Field[] landingGearFields = new Field[3];
    private int activatedLandingGearFields = 0;
    private final int[][] landingGearConstraints = {
            {1, 2},
            {3, 4},
            {5, 6},
    };

    /**
     * Konstruktor für die {@code LandingGear}-Klasse.
     *
     * @param airplane Das Flugzeug, das mit den Fahrwerkfeldern verbunden ist.
     */
    public LandingGear(Airplane airplane) {
        this.airplane = airplane;
        for (int i = 0; i < landingGearConstraints.length; i++) {
            landingGearFields[i] = new Field("Landing Gear");
        }
    }

    /**
     * Gibt die Anzahl der aktivierten Fahrwerkfelder zurück.
     *
     * @return Die Anzahl der aktivierten Fahrwerkfelder.
     */
    public int getActivatedLandingGearFields() {
        return activatedLandingGearFields;
    }

    /**
     * Aktiviert das Fahrwerkfeld an der angegebenen Position, wenn die Bedingungen erfüllt sind.
     *
     * @param index Der Index des Fahrwerkfelds (0 bis 2).
     * @param diceValue Der Würfelwert, der auf das Fahrwerkfeld angewendet wird.
     * @return {@code true}, wenn das Fahrwerkfeld erfolgreich aktiviert wurde, andernfalls {@code false}.
     */
    public boolean setLandingGearFieldsTrue(int index, int diceValue) {
        if (!isValidDiceValue(index, diceValue)) {
            System.out.println("Invalid dice value. Expected: " + landingGearConstraints[index][0] + " or " + landingGearConstraints[index][1]);
            return false;
        }
        if (landingGearFields[index].placeDice(diceValue) && !landingGearFields[index].isSwitchedOn()) {
            activatedLandingGearFields++;
            landingGearFields[index].setSwitchOn();
            airplane.getEngine().setBlueAeroMarker((airplane.getEngine().getBlueAeroMarker()) + 1);
            return true;
        }
        System.out.println("Landing Gear field already activated.");
        return false;
    }

    /**
     * Überprüft, ob der angegebene Würfelwert für das Fahrwerkfeld an der angegebenen Position gültig ist.
     *
     * @param index Der Index des Fahrwerkfelds (0 bis 2).
     * @param diceValue Der Würfelwert.
     * @return {@code true}, wenn der Würfelwert gültig ist, andernfalls {@code false}.
     */
    private boolean isValidDiceValue(int index, int diceValue) {
        return diceValue == landingGearConstraints[index][0] || diceValue == landingGearConstraints[index][1];
    }

    /**
     * Gibt den Status der Fahrwerkfelder aus, einschließlich der möglichen Werte und des Aktivierungsstatus.
     */
    public void displayFlapFields(){
        System.out.println("Landing Gear Fields Status:");
        for (int i = 0; i < landingGearFields.length; i++) {
            String status;
            if (landingGearFields[i].isSwitchedOn()) {
                status = "Activated";
            } else {
                status = "Not Activated";
            }
            System.out.println("fieldView " + (i+1) + ". (" + landingGearConstraints[i][0] + "|" + landingGearConstraints[i][1] + "): " + status);
        }
    }

    /**
     * Entfernt den Würfel von allen Fahrwerkfeldern und setzt ihn als nicht besetzt.
     */
    public void clearField(){
        for (Field landingGearField : landingGearFields) {
            landingGearField.resetField();
        }
    }
}
