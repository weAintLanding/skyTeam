package com.sierra.skyTeam.model;

/**
 * Die {@code Flaps}-Klasse enthält die Klappen des Flugzeugs, einschließlich der Aktivierung
 * von Klappenfeldern basierend auf Würfelergebnissen und der Überprüfung der gültigen Würfelwerte.
 * Diese Klasse stellt sicher, dass die Klappen in der richtigen Reihenfolge aktiviert werden und
 * verfolgt die Anzahl der aktivierten Klappenfelder.
 */
public class Flaps {
    private final Airplane airplane;
    private final Field[] flapFields = new Field[4];
    private int activatedFlapFields = 0;
    private final int[][] flapConstraints = {
            {1, 2},
            {2, 3},
            {4, 5},
            {5, 6}
    };

    /**
     * Konstruktor für die {@code Flaps}-Klasse.
     *
     * @param airplane Das Flugzeug, das mit den Klappenfeldern verbunden ist.
     */
    public Flaps(Airplane airplane) {
        this.airplane = airplane;
        for (int i = 0; i < flapConstraints.length; i++) {
            flapFields[i] = new Field("Flaps");
        }
    }

    /**
     * Gibt die Anzahl der aktivierten Klappenfelder zurück.
     *
     * @return Die Anzahl der aktivierten Klappenfelder.
     */
    public int getActivatedFlapFields() {
        return activatedFlapFields;
    }

    /**
     * Aktiviert das Klappenfeld an der angegebenen Position, wenn die Bedingungen erfüllt sind.
     *
     * @param index Der Index des Klappenfelds (0 bis 3).
     * @param diceValue Der Würfelwert, der auf das Klappenfeld angewendet wird.
     */
    public void setFlapFieldsTrue(int index, int diceValue) {
        if (index > 0 && !flapFields[index - 1].isSwitchedOn()) {
            System.out.println("Previous flap field not activated yet.");
            return;
        }
        if (!isValidDiceValue(index, diceValue)) {
            System.out.println("Invalid dice value. Expected: " + flapConstraints[index][0] + " or " + flapConstraints[index][1]);
            return;
        }
        if (flapFields[index].placeDice(diceValue) && !flapFields[index].isSwitchedOn()) {
            activatedFlapFields++;
            flapFields[index].setSwitchOn();
            airplane.getEngine().setOrangeAeroMarker(airplane.getEngine().getOrangeAeroMarker() + 1);
            return;
        }
        System.out.println("Flap field already activated.");
    }

    /**
     * Überprüft, ob der angegebene Würfelwert für das Klappenfeld an der angegebenen Position gültig ist.
     *
     * @param index Der Index des Klappenfelds (0 bis 3).
     * @param diceValue Der Würfelwert.
     * @return {@code true}, wenn der Würfelwert gültig ist, andernfalls {@code false}.
     */
    private boolean isValidDiceValue(int index, int diceValue) {
        return diceValue == flapConstraints[index][0] || diceValue == flapConstraints[index][1];
    }

    /**
     * Gibt den Status der Klappenfelder aus, einschließlich der möglichen Werte und des Aktivierungsstatus.
     */
    public void displayFlapFields() {
        System.out.println("Flap Fields Status:");
        for (int i = 0; i < flapFields.length; i++) {
            String status;
            if (flapFields[i].isSwitchedOn()) {
                status = "Activated.";
            } else {
                status = "Not Activated.";
            }
            System.out.println("fieldView " + (i+1) + ". (" + flapConstraints[i][0] + "|" + flapConstraints[i][1] + "): " + status);
        }
    }

    /**
     * Entfernt den Würfel von allen Klappenfeldern und setzt ihn als nicht besetzt.
     */
    public void clearField(){
        for (Field flapField : flapFields) {
            flapField.resetField();
        }
    }
}
