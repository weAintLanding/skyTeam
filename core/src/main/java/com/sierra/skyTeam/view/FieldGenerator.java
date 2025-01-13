package com.sierra.skyTeam.view;

import com.sierra.skyTeam.model.FieldModel;

import java.util.ArrayList;
import java.util.List;

public class FieldGenerator {
    private static final int[] allAllowedValues = {1,2,3,4,5,6};
    static int leftColX = 418; static int rightColX = 815;
    private static List<FieldView> coffeeFieldViews;
    private static FieldView pilotRadio;
    private static List<FieldView> copilotRadio;
    private static FieldView pilotAxisFieldView;
    private static FieldView copilotAxisFieldView;
    private static FieldView pilotThrottleFieldView;
    private static FieldView copilotThrottleFieldView;
    private static List<FieldView> landingGearFieldViews;
    private static List<FieldView> flapsFieldViews;
    private static List<FieldView> brakeFieldViews;

    public static List<FieldView> generateFields() {
        List<FieldView> FieldViews = new ArrayList<>();
        FieldView rerollTokenFieldView = generateRerollTokenField();
        landingGearFieldViews = generateLandingGear();
        flapsFieldViews = generateFlaps();
        pilotAxisFieldView = generatePilotAxisField();
        copilotAxisFieldView = generateCopilotAxisField();
        pilotThrottleFieldView = generatePilotThrottleField();
        copilotThrottleFieldView = generateCopilotThrottleField();
        brakeFieldViews = generateBrakeFields();

        pilotRadio = generatePilotRadio();
        copilotRadio = generateCopilotRadio();
        coffeeFieldViews = generateCoffeeFields();

        FieldViews.add(pilotRadio);
        FieldViews.add(rerollTokenFieldView);
        FieldViews.addAll(copilotRadio);
        FieldViews.addAll(landingGearFieldViews);
        FieldViews.addAll(flapsFieldViews);
        FieldViews.add(pilotAxisFieldView);
        FieldViews.add(copilotAxisFieldView);
        FieldViews.add(pilotThrottleFieldView);
        FieldViews.add(copilotThrottleFieldView);
        FieldViews.addAll(brakeFieldViews);
        FieldViews.addAll(coffeeFieldViews);
        return FieldViews;
    }

    private static FieldView generateRerollTokenField() {
        FieldModel token = new FieldModel(false, false, false, null);
        return new FieldView((leftColX+2), 630, token);
    }

    private static FieldView generatePilotRadio() {
        FieldModel pilotRadioFieldModel = new FieldModel(true, false, false, allAllowedValues);
        return new FieldView(leftColX, 565, pilotRadioFieldModel);
    }

    private static List<FieldView> generateCopilotRadio() {
        FieldModel copilotRadioFieldModel1 = new FieldModel(false, false, false, allAllowedValues);
        FieldModel copilotRadioFieldModel2 = new FieldModel(false, false, false, allAllowedValues);
        List<FieldView> copilotRadio = new ArrayList<>();
        copilotRadio.add(new FieldView(rightColX, 635, copilotRadioFieldModel1));
        copilotRadio.add(new FieldView(rightColX, 565, copilotRadioFieldModel2));
        return copilotRadio;
    }

    private static List<FieldView> generateLandingGear(){
        List<FieldView> landingGear = new ArrayList<>();
        int [] allowedValues1 = {1,2};
        int [] allowedValues2 = {3,4};
        int [] allowedValues3 = {5,6};
        FieldModel landingGearFieldModel1 = new FieldModel(true, false, true, allowedValues1);
        FieldModel landingGearFieldModel2 = new FieldModel(true, false, true, allowedValues2);
        FieldModel landingGearFieldModel3 = new FieldModel(true, false, true, allowedValues3);
        landingGear.add(new FieldView(leftColX, 414, landingGearFieldModel1));
        landingGear.add(new FieldView(leftColX, 307, landingGearFieldModel2));
        landingGear.add(new FieldView(leftColX, 200, landingGearFieldModel3));
        return landingGear;
    }

    private static List<FieldView> generateFlaps(){
        List<FieldView> flaps = new ArrayList<>();
        int [] allowedValues1 = {1,2};
        int [] allowedValues2 = {2,3};
        int [] allowedValues3 = {4,5};
        int [] allowedValues4 = {5,6};

        FieldModel flapFieldModel1 = new FieldModel(false, false, true, allowedValues1);
        FieldModel flapFieldModel2 = new FieldModel(false, false, true, allowedValues2);
        FieldModel flapFieldModel3 = new FieldModel(false, false, true, allowedValues3);
        FieldModel flapFieldModel4 = new FieldModel(false, false, true, allowedValues4);

        FieldView flapField1 = new FieldView(rightColX, 414, flapFieldModel1);
        FieldView flapField2 = new FieldView(rightColX, 307, flapFieldModel2);
        FieldView flapField3 = new FieldView(rightColX, 200, flapFieldModel3);
        FieldView flapField4 = new FieldView(rightColX, 93, flapFieldModel4);

        flapFieldModel2.setPreviousField(flapFieldModel1);
        flapFieldModel3.setPreviousField(flapFieldModel2);
        flapFieldModel4.setPreviousField(flapFieldModel3);

        flaps.add(flapField1);
        flaps.add(flapField2);
        flaps.add(flapField3);
        flaps.add(flapField4);

        return flaps;
    }

    private static FieldView generatePilotAxisField() {
        FieldModel pilotAxisFieldModel = new FieldModel(true, false, false, allAllowedValues);
        return new FieldView(493, 555, pilotAxisFieldModel);
    }

    private static FieldView generateCopilotAxisField() {
        FieldModel copilotAxisFieldModel = new FieldModel(false, false, false, allAllowedValues);
        return new FieldView(740, 555, copilotAxisFieldModel);
    }

    private static FieldView generatePilotThrottleField() {
        FieldModel pilotThrottleFieldModel = new FieldModel(true, false, false, allAllowedValues);
        return new FieldView(543,315, pilotThrottleFieldModel);
    }

    private static FieldView generateCopilotThrottleField() {
        FieldModel copilotThrottleFieldModel = new FieldModel(false, false, false, allAllowedValues);
        return new FieldView(690,315, copilotThrottleFieldModel);
    }

    public static FieldView getPilotAxisFieldView() {
        return pilotAxisFieldView;
    }

    public static FieldView getCopilotAxisFieldView() {
        return copilotAxisFieldView;
    }

    public static FieldView getPilotThrottleFieldView() {
        return pilotThrottleFieldView;
    }

    public static FieldView getCopilotThrottleFieldView(){
        return copilotThrottleFieldView;
    }

    private static List<FieldView> generateBrakeFields() {
        List<FieldView> brakeFieldViews = new ArrayList<>();
        int[] allowedValues1 = {2};
        int[] allowedValues2 = {4};
        int[] allowedValues3 = {6};

        FieldModel brakeFieldModel1 = new FieldModel(true, false, true, allowedValues1);
        FieldModel brakeFieldModel2 = new FieldModel(true, false, true, allowedValues2);
        FieldModel brakeFieldModel3 = new FieldModel(true, false, true, allowedValues3);

        FieldView brakeField1 = new FieldView(545, 175, brakeFieldModel1);
        FieldView brakeField2 = new FieldView(617, 175, brakeFieldModel2);
        FieldView brakeField3 = new FieldView(689, 175, brakeFieldModel3);

        brakeFieldModel2.setPreviousField(brakeFieldModel1);
        brakeFieldModel3.setPreviousField(brakeFieldModel2);

        brakeFieldViews.add(brakeField1);
        brakeFieldViews.add(brakeField2);
        brakeFieldViews.add(brakeField3);
        return brakeFieldViews;
    }

    private static List<FieldView> generateCoffeeFields(){
        FieldModel coffeeFieldModel1 = new FieldModel(false, true, false, allAllowedValues);
        FieldModel coffeeFieldModel2 = new FieldModel(false, true, false, allAllowedValues);
        FieldModel coffeeFieldModel3 = new FieldModel(false, true, false, allAllowedValues);
        List<FieldView> coffeeFieldViews = new ArrayList<>();
        coffeeFieldViews.add(new FieldView(545, 58, coffeeFieldModel1));
        coffeeFieldViews.add(new FieldView(617, 58, coffeeFieldModel2));
        coffeeFieldViews.add(new FieldView(689, 58, coffeeFieldModel3));
        return coffeeFieldViews;
    }

    public static List<FieldView> getCoffeeFieldViews() {
        if(coffeeFieldViews == null) {
            System.out.println("It dont work");
        }
        return coffeeFieldViews;
    }

    public static List<FieldView> getLandingGearFieldViews() {
        return landingGearFieldViews;
    }

    public static List<FieldView> getFlapsFieldViews() {
        return flapsFieldViews;
    }

    public static List<FieldView> getBrakeFieldViews() {
        return brakeFieldViews;
    }

    public static FieldView getPilotRadio() {
        return pilotRadio;
    }

    public static List<FieldView> getCopilotRadio() {
        return copilotRadio;
    }
}
