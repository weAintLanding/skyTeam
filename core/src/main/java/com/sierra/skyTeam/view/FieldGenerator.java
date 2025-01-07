package com.sierra.skyTeam.view;

import java.util.ArrayList;
import java.util.List;

public class FieldGenerator {
    static int leftColX = 418; static int rightColX = 815;
    private static List<FieldView> coffeeFieldViews;
    public static List<FieldView> generateFields() {
        List<FieldView> FieldViews = new ArrayList<>();
        FieldView pilotRadio = generatePilotRadio();
        FieldView rerollTokenFieldView = generateRerollTokenField();
        List<FieldView> copilotRadio = generateCopilotRadio();
        List<FieldView> landingGear = generateLandingGear();
        List<FieldView> flaps = generateFlaps();
        FieldView pilotAxisFieldView = generatePilotAxisField();
        FieldView copilotAxisFieldView = generateCopilotAxisField();
        FieldView pilotThrottleFieldView = generatePilotThrottleField();
        FieldView copilotThrottleFieldView = generateCopilotThrottleField();
        List<FieldView> brakeFieldViews = generateBrakeFields();

        coffeeFieldViews = generateCoffeeFields();

        FieldViews.add(pilotRadio);
        FieldViews.add(rerollTokenFieldView);
        FieldViews.addAll(copilotRadio);
        FieldViews.addAll(landingGear);
        FieldViews.addAll(flaps);
        FieldViews.add(pilotAxisFieldView);
        FieldViews.add(copilotAxisFieldView);
        FieldViews.add(pilotThrottleFieldView);
        FieldViews.add(copilotThrottleFieldView);
        FieldViews.addAll(brakeFieldViews);
        FieldViews.addAll(coffeeFieldViews);
        return FieldViews;
    }

    private static FieldView generateRerollTokenField() {
        return new FieldView((leftColX+2), 630, false, false);
    }

    private static FieldView generatePilotRadio() {
        return new FieldView(leftColX, 565, true, false);
    }

    private static List<FieldView> generateCopilotRadio() {
        List<FieldView> copilotRadio = new ArrayList<>();
        copilotRadio.add(new FieldView(rightColX, 635, false, false));
        copilotRadio.add(new FieldView(rightColX, 565, false, false));
        return copilotRadio;
    }

    private static List<FieldView> generateLandingGear(){
        List<FieldView> landingGear = new ArrayList<>();
        int [] allowedValues1 = {1,2};
        int [] allowedValues2 = {3,4};
        int [] allowedValues3 = {5,6};
        landingGear.add(new FieldView(leftColX, 414, true,true, allowedValues1));
        landingGear.add(new FieldView(leftColX, 307, true,true, allowedValues2));
        landingGear.add(new FieldView(leftColX, 200, true,true, allowedValues3));
        return landingGear;
    }

    private static List<FieldView> generateFlaps(){
        List<FieldView> flaps = new ArrayList<>();
        int [] allowedValues1 = {1,2};
        int [] allowedValues2 = {2,3};
        int [] allowedValues3 = {4,5};
        int [] allowedValues4 = {5,6};
        FieldView flapField1 = new FieldView(rightColX, 414, true, false, allowedValues1);
        FieldView flapField2 = new FieldView(rightColX, 307, true, false, allowedValues2);
        FieldView flapField3 = new FieldView(rightColX, 200, true, false, allowedValues3);
        FieldView flapField4 = new FieldView(rightColX, 93, true, false, allowedValues4);

        flapField2.setPreviousField(flapField1);
        flapField3.setPreviousField(flapField2);
        flapField4.setPreviousField(flapField3);

        flaps.add(flapField1);
        flaps.add(flapField2);
        flaps.add(flapField3);
        flaps.add(flapField4);

        return flaps;
    }

    private static FieldView generatePilotAxisField() {
        return new FieldView(493, 555, true, false);
    }

    private static FieldView generateCopilotAxisField() {
        return new FieldView(740, 555, false, false);
    }

    private static FieldView generatePilotThrottleField() {
        return new FieldView(543,315, true, false);
    }

    private static FieldView generateCopilotThrottleField() {
        return new FieldView(690,315, false, false);
    }

    private static List<FieldView> generateBrakeFields() {
        List<FieldView> brakeFieldViews = new ArrayList<>();
        int[] allowedValues1 = {2};
        int[] allowedValues2 = {4};
        int[] allowedValues3 = {6};
        FieldView brakeField1 = new FieldView(545, 175, true, true, allowedValues1);
        FieldView brakeField2 = new FieldView(617, 175, true, true, allowedValues2);
        FieldView brakeField3 = new FieldView(689, 175, true, true, allowedValues3);

        brakeField2.setPreviousField(brakeField1);
        brakeField3.setPreviousField(brakeField2);

        brakeFieldViews.add(brakeField1);
        brakeFieldViews.add(brakeField2);
        brakeFieldViews.add(brakeField3);
        return brakeFieldViews;
    }

    private static List<FieldView> generateCoffeeFields(){
        List<FieldView> coffeeFieldViews = new ArrayList<>();
        coffeeFieldViews.add(new FieldView(545, 58, false, true));
        coffeeFieldViews.add(new FieldView(617, 58, false, true));
        coffeeFieldViews.add(new FieldView(689, 58, false, true));
        return coffeeFieldViews;
    }

    public static List<FieldView> getCoffeeFieldViews() {
        if(coffeeFieldViews == null) {
            System.out.println("It dont work");
        }
        return coffeeFieldViews;
    }
}
