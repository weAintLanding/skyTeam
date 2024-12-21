public class Brakes {
    private final Airplane airplane;
    private final Field[] brakeFields = new Field[3];
    private int activatedBrakeFields = 0;
    private final int[][] brakeConstraints = {
            {2},
            {4},
            {6}
    };

    public Brakes(Airplane airplane) {
        this.airplane = airplane;
        for (int i = 0; i < brakeConstraints.length; i++) {
            brakeFields[i] = new Field("Brakes");
        }
    }

    public int getActivatedBrakeFields() {
        return activatedBrakeFields;
    }

    //Brake fields
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
            System.out.println("Brake field " + (index + 1) + " activated successfully. ");
            if (activatedBrakeFields == 1) {
                airplane.getEngine().setRedBrakeMarker((airplane.getEngine().getRedBrakeMarker()) + 1);
            } else if (activatedBrakeFields <= 3) {
                airplane.getEngine().setRedBrakeMarker((airplane.getEngine().getRedBrakeMarker()) + 2);
            }
            return true;
        }
        System.out.println("Brake Field already activated.");
        return false;
    }

    private boolean isValidDiceValue(int index, int diceValue) {
        return diceValue == brakeConstraints[index][0];
    }

    public void displayBrakeFields() {
        System.out.println("Brake Field Status: ");
        for (int i = 0; i < brakeFields.length; i++) {
            String status;
            if (brakeFields[i].isSwitchedOn()){
                status = "Activated";
            } else {
                status = "Not Activated";
            }
            System.out.println("Field " + (i + 1) + ". (" + brakeConstraints[i][0] + "): " + status);
        }
    }

    public void clearField(){
        for (Field brakeField : brakeFields) {
            brakeField.resetField();
        }
    }
}
