package com.sierrra.skyTeam;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;
import java.util.List;

public class FieldGenerator {
    ShapeRenderer shapeRenderer;
    static int leftColX = 505; static int rightColX = 744; //tba
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
        return new Field((leftColX+2), 385);
    }

    private static Field generatePilotRadio() {
        return new Field(leftColX, 342);
    }

    private static List<Field> generateCopilotRadio() {
        List<Field> copilotRadio = new ArrayList<>();
        copilotRadio.add(new Field(rightColX, 385));
        copilotRadio.add(new Field(rightColX, 342));
        return copilotRadio;
    }

    private static List<Field> generateLandingGear(){
        List<Field> landingGear = new ArrayList<>();
        landingGear.add(new Field(leftColX, 252));
        landingGear.add(new Field(leftColX, 188));
        landingGear.add(new Field(leftColX, 124));
        return landingGear;
    }

    private static List<Field> generateFlaps(){
        List<Field> flaps = new ArrayList<>();
        flaps.add(new Field(rightColX,252));
        flaps.add(new Field(rightColX, 188));
        flaps.add(new Field(rightColX, 124));
        flaps.add(new Field(rightColX, 60));
        return flaps;
    }

    private static List<Field> generateAxisFields() {
        List<Field> axisFields = new ArrayList<>();
        axisFields.add(new Field(550, 335));
        axisFields.add(new Field(700, 335));
        return axisFields;
    }

    private static List<Field> generateThrottleFields() {
        List<Field> throttleFields = new ArrayList<>();
        throttleFields.add(new Field(582,192));
        throttleFields.add(new Field(668,192));
        return throttleFields;
    }

    private static List<Field> generateBrakeFields() {
        List<Field> brakeFields = new ArrayList<>();
        brakeFields.add(new Field(582, 110));
        brakeFields.add(new Field(625, 110));
        brakeFields.add(new Field(668, 110));
        return brakeFields;
    }

    private static List<Field> generateCoffeeFields(){
        List<Field> coffeeFields = new ArrayList<>();
        coffeeFields.add(new Field(582, 40));
        coffeeFields.add(new Field(625, 40));
        coffeeFields.add(new Field(668, 40));
        return coffeeFields;
    }
}
