package com.sierra.skyTeam.model;

/**
 * Die Klasse {@code Brakes} verwaltet die Bremsfelder des Flugzeugs und deren Aktivierung basierend auf Würfelergebnissen.
 * Sie stellt Funktionen zum Aktivieren von Bremsfeldern, Überprüfen von Würfelergebnissen und Anzeigen des Status der Bremsfelder bereit.
 */
public class Brakes {
    private final Airplane airplane;
    private final Field[] brakeFields = new Field[3];
    private int activatedBrakeFields = 0;
    private final int[][] brakeConstraints = {
            {2},
            {4},
            {6}
    };

    /**
     * Konstruktor für die Klasse {@code Brakes}.
     * Initialisiert die Bremsfelder mit den entsprechenden Bremswerten.
     *
     * @param airplane Das zugehörige Flugzeug, das die Brakes verwaltet.
     */
    public Brakes(Airplane airplane) {
        this.airplane = airplane;
        for (int i = 0; i < brakeConstraints.length; i++) {
            brakeFields[i] = new Field("Brakes");
        }
    }

    /**
     * Gibt die Anzahl der aktivierten Bremsfelder zurück.
     *
     * @return Die Anzahl der aktivierten Bremsfelder.
     */
    public int getActivatedBrakeFields() {
        return activatedBrakeFields;
    }

    /**
     * Aktiviert ein Bremsfeld, wenn alle Bedingungen erfüllt sind (z. B. gültiger Würfelwert und vorheriges Feld aktiviert).
     *
     * @param index     Der Index des Bremsfelds, das aktiviert werden soll.
     * @param diceValue Der Würfelwert, der überprüft wird, um das Bremsfeld zu aktivieren.
     * @return {@code true}, wenn das Bremsfeld erfolgreich aktiviert wurde, andernfalls {@code false}.
     */
    public boolean setBrakeFieldsTrue(int index, int diceValue) {
        if (index < 0 || index >= brakeFields.length) {
            System.out.println("Invalid flap field index.");
            return false;
        }
        if (index>0 && !brakeFields[index-1].isSwitchedOn()) {
            System.out.println("Previous flap field not activated yet.");
            return false;
        }
        if (!isValidDiceValue(index, diceValue)) {
            System.out.println("Invalid dice value. Expected: " + brakeConstraints[index][0]);
            return false;
        }
        if(brakeFields[index].placeDice(diceValue) && !brakeFields[index].isSwitchedOn()) {
            activatedBrakeFields++;
            brakeFields[index].setSwitchOn();
            if (activatedBrakeFields == 1) {
                airplane.getEngine().setRedBrakeMarker((airplane.getEngine().getRedBrakeMarker()) + 1);
            } else if (activatedBrakeFields <= 3) {
                airplane.getEngine().setRedBrakeMarker((airplane.getEngine().getRedBrakeMarker()) + 2);
            }
            return true;
        }
        System.out.println("Brake fieldView already activated.");
        return false;
    }

    /**
     * Überprüft, ob der Würfelwert für das angegebene Bremsfeld gültig ist.
     *
     * @param index     Der Index des Bremsfelds.
     * @param diceValue Der Würfelwert, der überprüft wird.
     * @return {@code true}, wenn der Würfelwert gültig ist, andernfalls {@code false}.
     */
    private boolean isValidDiceValue(int index, int diceValue) {
        return diceValue == brakeConstraints[index][0];
    }

    /**
     * Zeigt den Status der Bremsfelder an (aktiviert oder nicht aktiviert).
     */
    public void displayBrakeFields() {
        System.out.println("Brake fieldView Status: ");
        for (int i = 0; i < brakeFields.length; i++) {
            String status;
            if (brakeFields[i].isSwitchedOn()){
                status = "Activated";
            } else {
                status = "Not Activated";
            }
            System.out.println("fieldView " + (i + 1) + ". (" + brakeConstraints[i][0] + "): " + status);
        }
    }

    /**
     * Entfernt den Würfel von allen Bremsfeldern und setzt ihn als nicht besetzt.
     */
    public void clearField(){
        for (Field brakeField : brakeFields) {
            brakeField.resetField();
        }
    }
}
