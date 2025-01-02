package com.sierra.skyTeam.view;

import java.util.ArrayList;
import java.util.List;

public class FieldGenerator {
    static int leftColX = 418; static int rightColX = 815;
    public static List<fieldView> generateFields() {
        List<fieldView> fieldViews = new ArrayList<>();
        fieldView pilotRadio = generatePilotRadio();
        fieldView rerollTokenFieldView = generateRerollTokenField();
        List<fieldView> copilotRadio = generateCopilotRadio();
        List<fieldView> landingGear = generateLandingGear();
        List<fieldView> flaps = generateFlaps();
        List<fieldView> axisFieldViews = generateAxisFields();
        List<fieldView> throttleFieldViews = generateThrottleFields();
        List<fieldView> brakeFieldViews = generateBrakeFields();
        List<fieldView> coffeeFieldViews = generateCoffeeFields();
        fieldViews.add(pilotRadio);
        fieldViews.add(rerollTokenFieldView);
        fieldViews.addAll(copilotRadio);
        fieldViews.addAll(landingGear);
        fieldViews.addAll(flaps);
        fieldViews.addAll(axisFieldViews);
        fieldViews.addAll(throttleFieldViews);
        fieldViews.addAll(brakeFieldViews);
        fieldViews.addAll(coffeeFieldViews);
        return fieldViews;
    }

    private static fieldView generateRerollTokenField() {
        return new fieldView((leftColX+2), 630);
    }

    private static fieldView generatePilotRadio() {
        return new fieldView(leftColX, 565);
    }

    private static List<fieldView> generateCopilotRadio() {
        List<fieldView> copilotRadio = new ArrayList<>();
        copilotRadio.add(new fieldView(rightColX, 635));
        copilotRadio.add(new fieldView(rightColX, 565));
        return copilotRadio;
    }

    private static List<fieldView> generateLandingGear(){
        List<fieldView> landingGear = new ArrayList<>();
        int [] allowedValues1 = {1,2};
        int [] allowedValues2 = {3,4};
        int [] allowedValues3 = {5,6};
        landingGear.add(new fieldView(leftColX, 414, true, allowedValues1));
        landingGear.add(new fieldView(leftColX, 307, true, allowedValues2));
        landingGear.add(new fieldView(leftColX, 200, true, allowedValues3));
        return landingGear;
    }

    private static List<fieldView> generateFlaps(){
        List<fieldView> flaps = new ArrayList<>();
        int [] allowedValues1 = {1,2};
        int [] allowedValues2 = {2,3};
        int [] allowedValues3 = {4,5};
        int [] allowedValues4 = {5,6};
        flaps.add(new fieldView(rightColX,414, true, allowedValues1));
        flaps.add(new fieldView(rightColX, 307, true, allowedValues2));
        flaps.add(new fieldView(rightColX, 200, true, allowedValues3));
        flaps.add(new fieldView(rightColX, 93, true, allowedValues4));
        return flaps;
    }

    private static List<fieldView> generateAxisFields() {
        List<fieldView> axisFieldViews = new ArrayList<>();
        axisFieldViews.add(new fieldView(493, 555));
        axisFieldViews.add(new fieldView(740, 555));
        return axisFieldViews;
    }

    private static List<fieldView> generateThrottleFields() {
        List<fieldView> throttleFieldViews = new ArrayList<>();
        throttleFieldViews.add(new fieldView(543,315));
        throttleFieldViews.add(new fieldView(690,315));
        return throttleFieldViews;
    }

    private static List<fieldView> generateBrakeFields() {
        List<fieldView> brakeFieldViews = new ArrayList<>();
        int[] allowedValues1 = {2};
        int[] allowedValues2 = {4};
        int[] allowedValues3 = {6};
        brakeFieldViews.add(new fieldView(545, 175, true, allowedValues1));
        brakeFieldViews.add(new fieldView(617, 175, true, allowedValues2));
        brakeFieldViews.add(new fieldView(689, 175, true, allowedValues3));
        return brakeFieldViews;
    }

    private static List<fieldView> generateCoffeeFields(){
        List<fieldView> coffeeFieldViews = new ArrayList<>();
        coffeeFieldViews.add(new fieldView(545, 58));
        coffeeFieldViews.add(new fieldView(617, 58));
        coffeeFieldViews.add(new fieldView(689, 58));
        return coffeeFieldViews;
    }
}
