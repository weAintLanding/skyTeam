public class LandingGear {
    private final Airplane airplane;
    private final Field[] landingGearFields = new Field[3];
    private int activatedLandingGearFields = 0;
    private final int[][] landingGearConstraints = {
            {1, 2},
            {3, 4},
            {5, 6},
    };

    public LandingGear(Airplane airplane) {
        this.airplane = airplane;
        for (int i = 0; i < landingGearConstraints.length; i++) {
            landingGearFields[i] = new Field("Landing Gear");
        }
    }

    public int getActivatedLandingGearFields() {
        return activatedLandingGearFields;
    }

    public boolean setLandingGearFieldsTrue(int index, int diceValue) {
        /*if (index < 0 || index >= landingGearFields.length) {
            System.out.println("Invalid landing gear field index.");
            return false;
        }*/
        if (!isValidDiceValue(index, diceValue)) {
            System.out.println("Invalid dice value. Expected: " + landingGearConstraints[index][0] + " or " + landingGearConstraints[index][1]);
            return false;
        }
        if (landingGearFields[index].placeDice(diceValue) && !landingGearFields[index].isSwitchedOn()) {
            activatedLandingGearFields++;
            landingGearFields[index].setSwitchOn();
            System.out.println("Landing Gear Field " + (index + 1) + " activated successfully.");
            airplane.getEngine().setBlueAeroMarker((airplane.getEngine().getBlueAeroMarker()) + 1);
            return true;
        }
        System.out.println("Landing Gear Field already activated.");
        return false;
    }

    private boolean isValidDiceValue(int index, int diceValue) {
        return diceValue == landingGearConstraints[index][0] || diceValue == landingGearConstraints[index][1];
    }

    public void displayFlapFields(){
        System.out.println("Landing Gear Fields Status:");
        for (int i = 0; i < landingGearFields.length; i++) {
            String status;
            if (landingGearFields[i].isSwitchedOn()) {
                status = "Activated";
            } else {
                status = "Not Activated";
            }
            System.out.println("Field " + (i+1) + ". (" + landingGearConstraints[i][0] + "|" + landingGearConstraints[i][1] + "): " + status);
        }
    }

    public void clearField(){
        for (Field landingGearField : landingGearFields) {
            landingGearField.resetField();
        }
    }
}
