package com.sierrra.skyTeam;

import java.util.ArrayList;
import java.util.List;

public class FieldGenerator {
    static int leftColX = 418; static int rightColX = 815;
    public static List<Field> generateFields() {
        List<Field> fields = new ArrayList<>();
        Field pilotRadio = generatePilotRadio();
        Field rerollTokenField = generateRerollTokenField();
        List<Field> copilotRadio = generateCopilotRadio();
        List<Field> landingGear = generateLandingGear();
        List<Field> flaps = generateFlaps();
        List<Field> axisFields = generateAxisFields();
        List<Field> throttleFields = generateThrottleFields();
        List<Field> brakeFields = generateBrakeFields();
        List<Field> coffeeFields = generateCoffeeFields();
        fields.add(pilotRadio);
        fields.add(rerollTokenField);
        fields.addAll(copilotRadio);
        fields.addAll(landingGear);
        fields.addAll(flaps);
        fields.addAll(axisFields);
        fields.addAll(throttleFields);
        fields.addAll(brakeFields);
        fields.addAll(coffeeFields);
        return fields;
    }

    private static Field generateRerollTokenField() {
        return new Field((leftColX+2), 630);
    }

    private static Field generatePilotRadio() {
        return new Field(leftColX, 565);
    }

    private static List<Field> generateCopilotRadio() {
        List<Field> copilotRadio = new ArrayList<>();
        copilotRadio.add(new Field(rightColX, 635));
        copilotRadio.add(new Field(rightColX, 565));
        return copilotRadio;
    }

    private static List<Field> generateLandingGear(){
        List<Field> landingGear = new ArrayList<>();
        landingGear.add(new Field(leftColX, 414));
        landingGear.add(new Field(leftColX, 307));
        landingGear.add(new Field(leftColX, 200));
        return landingGear;
    }

    private static List<Field> generateFlaps(){
        List<Field> flaps = new ArrayList<>();
        flaps.add(new Field(rightColX,414));
        flaps.add(new Field(rightColX, 307));
        flaps.add(new Field(rightColX, 200));
        flaps.add(new Field(rightColX, 93));
        return flaps;
    }

    private static List<Field> generateAxisFields() {
        List<Field> axisFields = new ArrayList<>();
        axisFields.add(new Field(493, 555));
        axisFields.add(new Field(740, 555));
        return axisFields;
    }

    private static List<Field> generateThrottleFields() {
        List<Field> throttleFields = new ArrayList<>();
        throttleFields.add(new Field(543,315));
        throttleFields.add(new Field(690,315));
        return throttleFields;
    }

    private static List<Field> generateBrakeFields() {
        List<Field> brakeFields = new ArrayList<>();
        brakeFields.add(new Field(545, 175));
        brakeFields.add(new Field(617, 175));
        brakeFields.add(new Field(689, 175));
        return brakeFields;
    }

    private static List<Field> generateCoffeeFields(){
        List<Field> coffeeFields = new ArrayList<>();
        coffeeFields.add(new Field(545, 58));
        coffeeFields.add(new Field(617, 58));
        coffeeFields.add(new Field(689, 58));
        return coffeeFields;
    }
}
