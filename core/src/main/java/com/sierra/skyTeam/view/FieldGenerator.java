package com.sierra.skyTeam.view;

import com.sierra.skyTeam.model.FieldModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Diese Klasse enthält Methoden zum Generieren von verschiedenen Feldern für das Spiel.
 * Sie umfasst das Erstellen von Feldern für verschiedene Komponenten wie Landing-Gear, Axis,
 * Flaps und mehr, sowie deren Positionierung.
 */
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

    /**
     * Generiert eine Liste von Feldern für das Spiel.
     * Diese Methode kombiniert alle notwendigen Felder in einer Liste.
     *
     * @return Eine Liste von FieldView-Objekten, die die Felder repräsentieren.
     */
    public static List<FieldView> generateFields() {
        List<FieldView> FieldViews = new ArrayList<>();
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

    /**
     * Generiert das Feld für das Pilot-Radio.
     *
     * @return Ein FieldView-Objekt, das das Pilot-Radio darstellt.
     */
    private static FieldView generatePilotRadio() {
        FieldModel pilotRadioFieldModel = new FieldModel(true, false, false, allAllowedValues);
        return new FieldView(leftColX, 565, pilotRadioFieldModel);
    }

    /**
     * Generiert das Feld für das Copilot-Radio.
     *
     * @return Ein FieldView-Objekt, das das Copilot-Radio darstellt.
     */
    private static List<FieldView> generateCopilotRadio() {
        FieldModel copilotRadioFieldModel1 = new FieldModel(false, false, false, allAllowedValues);
        FieldModel copilotRadioFieldModel2 = new FieldModel(false, false, false, allAllowedValues);
        List<FieldView> copilotRadio = new ArrayList<>();
        copilotRadio.add(new FieldView(rightColX, 635, copilotRadioFieldModel1));
        copilotRadio.add(new FieldView(rightColX, 565, copilotRadioFieldModel2));
        return copilotRadio;
    }

    /**
     * Generiert die Felder für die Landing-Gear.
     *
     * @return Eine Liste von FieldView-Objekten, die die Landing-Gear-Felder darstellen.
     */
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

    /**
     * Generiert die Felder für die Flaps.
     *
     * @return Eine Liste von FieldView-Objekten, die die Flaps-Felder darstellen.
     */
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

    /**
     * Generiert das Feld für die Pilot-Axis.
     *
     * @return Eine Liste von FieldView-Objekten, die das Pilot-Axis-Feld darstellen.
     */
    private static FieldView generatePilotAxisField() {
        FieldModel pilotAxisFieldModel = new FieldModel(true, false, false, allAllowedValues);
        return new FieldView(493, 555, pilotAxisFieldModel);
    }

    /**
     * Generiert das Feld für die Copilot-Axis.
     *
     * @return Eine Liste von FieldView-Objekten, die das Copilot-Axis-Feld darstellen.
     */
    private static FieldView generateCopilotAxisField() {
        FieldModel copilotAxisFieldModel = new FieldModel(false, false, false, allAllowedValues);
        return new FieldView(740, 555, copilotAxisFieldModel);
    }

    /**
     * Generiert das Feld für den Throttle.
     *
     * @return Eine Liste von FieldView-Objekten, die das Pilot-Throttle-Feld darstellen.
     */
    private static FieldView generatePilotThrottleField() {
        FieldModel pilotThrottleFieldModel = new FieldModel(true, false, false, allAllowedValues);
        return new FieldView(543,315, pilotThrottleFieldModel);
    }

    /**
     * Generiert das Feld für den Throttle.
     *
     * @return Eine Liste von FieldView-Objekten, die das Copilot-Throttle-Feld darstellen.
     */
    private static FieldView generateCopilotThrottleField() {
        FieldModel copilotThrottleFieldModel = new FieldModel(false, false, false, allAllowedValues);
        return new FieldView(690,315, copilotThrottleFieldModel);
    }

    /**
     * Generiert die Felder für die Bremsen.
     *
     * @return Eine Liste von FieldView-Objekten, die die Bremsen-Felder darstellen.
     */
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

    /**
     * Generiert die Felder für den Kaffee.
     *
     * @return Eine Liste von FieldView-Objekten, die die Kaffee-Felder darstellen.
     */
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

    /**
     * Gibt das Pilot-Axis-Feld zurück.
     *
     * @return Eine `FieldView`-Objekt, das das Pilot-Axis-Feld darstellt.
     */
    public static FieldView getPilotAxisFieldView() {
        return pilotAxisFieldView;
    }

    /**
     * Gibt das Copilot-Axis-Feld zurück.
     *
     * @return Eine `FieldView`-Objekt, das das Copilot-Axis-Feld darstellt.
     */
    public static FieldView getCopilotAxisFieldView() {
        return copilotAxisFieldView;
    }

    /**
     * Gibt das Pilot-Throttle-Feld zurück.
     *
     * @return Eine `FieldView`-Objekt, das das Pilot-Throttle-Feld darstellt.
     */
    public static FieldView getPilotThrottleFieldView() {
        return pilotThrottleFieldView;
    }

    /**
     * Gibt das Copilot-Throttle-Feld zurück.
     *
     * @return Eine `FieldView`-Objekt, das das Copilot-Throttle-Feld darstellt.
     */
    public static FieldView getCopilotThrottleFieldView(){
        return copilotThrottleFieldView;
    }

    /**
     * Gibt die Kaffeefelder zurück.
     *
     * @return Eine Liste von `FieldView`-Objekten, die die Kaffeefelder darstellen.
     */
    public static List<FieldView> getCoffeeFieldViews() {
        if(coffeeFieldViews == null) {
            System.out.println("It dont work");
        }
        return coffeeFieldViews;
    }

    /**
     * Gibt die Landing-Gear-Felder zurück.
     *
     * @return Eine Liste von `FieldView`-Objekten, die die Landing-Gear-Felder darstellen.
     */
    public static List<FieldView> getLandingGearFieldViews() {
        return landingGearFieldViews;
    }

    /**
     * Gibt die Flaps-Felder zurück.
     *
     * @return Eine Liste von `FieldView`-Objekten, die die Flaps-Felder darstellen.
     */
    public static List<FieldView> getFlapsFieldViews() {
        return flapsFieldViews;
    }

    /**
     * Gibt die Liste der Bremse-Felder zurück.
     *
     * @return Eine Liste von `FieldView`-Objekten, die die Bremse-Felder darstellen.
     */
    public static List<FieldView> getBrakeFieldViews() {
        return brakeFieldViews;
    }

    /**
     * Gibt das Pilot-Radio-Feld zurück.
     *
     * @return Eine `FieldView`-Objekt, das das Pilot-Radio-Feld darstellt.
     */
    public static FieldView getPilotRadio() {
        return pilotRadio;
    }

    /**
     * Gibt die Liste der Copilot-Radio-Felder zurück.
     *
     * @return Eine Liste von `FieldView`-Objekten, die die Copilot-Radio-Felder darstellen.
     */
    public static List<FieldView> getCopilotRadio() {
        return copilotRadio;
    }
}
