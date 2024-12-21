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

    public Flaps(Airplane airplane) {
        this.airplane = airplane;
        for (int i = 0; i < flapConstraints.length; i++) {
            flapFields[i] = new Field("Flaps");
        }
    }

    public int getActivatedFlapFields() {
        return activatedFlapFields;
    }

    public boolean setFlapFieldsTrue(int index, int diceValue) {
        /*if (index < 0 || index >= flapFields.length) {
            System.out.println("Invalid flap field index.");
            return false;
        }*/
        if (index > 0 && !flapFields[index - 1].isSwitchedOn()) {
            System.out.println("Previous flap field not activated yet.");
            return false;
        }
        if (!isValidDiceValue(index, diceValue)) {
            System.out.println("Invalid dice value. Expected: " + flapConstraints[index][0] + " or " + flapConstraints[index][1]);
            return false;
        }
        if (flapFields[index].placeDice(diceValue) && !flapFields[index].isSwitchedOn()) {
            activatedFlapFields++;
            flapFields[index].setSwitchOn();
            System.out.println("Flap field " + (index + 1) + " activated successfully.");
            airplane.getEngine().setOrangeAeroMarker(airplane.getEngine().getOrangeAeroMarker() + 1);
            return true;
        }
        System.out.println("Flap field already activated.");
        return false;
    }

    private boolean isValidDiceValue(int index, int diceValue) {
        return diceValue == flapConstraints[index][0] || diceValue == flapConstraints[index][1];
    }

    public void displayFlapFields() {
        System.out.println("Flap Fields Status:");
        for (int i = 0; i < flapFields.length; i++) {
            String status;
            if (flapFields[i].isSwitchedOn()) {
                status = "Activated.";
            } else {
                status = "Not Activated.";
            }
            System.out.println("Field " + (i+1) + ". (" + flapConstraints[i][0] + "|" + flapConstraints[i][1] + "): " + status);
        }
    }

    public void clearField(){
        for (Field flapField : flapFields) {
            flapField.resetField();
        }
    }
}