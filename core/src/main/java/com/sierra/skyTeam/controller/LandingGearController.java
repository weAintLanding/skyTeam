package com.sierra.skyTeam.controller;

import com.sierra.skyTeam.model.Airplane;
import com.sierra.skyTeam.model.FieldModel;
import com.sierra.skyTeam.model.GameModel;
import com.sierra.skyTeam.view.FieldGenerator;
import com.sierra.skyTeam.view.FieldView;
import com.sierra.skyTeam.view.MarkerManager;

import java.util.List;

/**
 * Der LandingGearController verwaltet die Logik des LandingGears im Spiel.
 * Er sorgt dafür, dass das LandingGear korrekt gesetzt wird, wenn das entsprechende Feld belegt wird.
 */
public class LandingGearController {
    private Airplane airplaneModel;

    private MarkerManager markerManager;

    private List<FieldView> landingGearFieldViews;

    private FieldModel landingGearFieldModel1;
    private FieldModel landingGearFieldModel2;
    private FieldModel landingGearFieldModel3;

    private boolean field1;
    private boolean field2;
    private boolean field3;

    /**
     * Konstruktor: Initialisiert den LandingGearController mit den entsprechenden Modellen und Feldansichten.
     *
     * @param gameModel Das GameModel, das die Flugdaten enthält.
     * @param markerManager Der MarkerManager, der die verschiedenen Marker im Spiel verwaltet.
     */
    public LandingGearController(GameModel gameModel, MarkerManager markerManager) {
        this.airplaneModel = gameModel.getAirplane();
        this.markerManager = markerManager;

        this.landingGearFieldViews = FieldGenerator.getLandingGearFieldViews();

        this.landingGearFieldModel1 = landingGearFieldViews.get(0).getFieldModel();
        this.landingGearFieldModel2 = landingGearFieldViews.get(1).getFieldModel();
        this.landingGearFieldModel3 = landingGearFieldViews.get(2).getFieldModel();

        this.field1 = false;
        this.field2 = false;
        this.field3 = false;
    }

    /**
     * Überprüft, ob Felder für das LandingGear besetzt sind, und setzt die entsprechenden Werte im Flugzeugmodell.
     * Jedes belegte Feld aktualisiert den entsprechenden Marker, wenn der richtige Würfel gesetzt wird.
     */
    public void draw(){
        if(!field1 && landingGearFieldModel1.isOccupied()){
            airplaneModel.getLandingGear().setLandingGearFieldsTrue(0, landingGearFieldModel1.getPlacedDice().getDiceValue());
            field1 = true;
            markerManager.update("blue");
        }
        if(!field2 && landingGearFieldModel2.isOccupied()){
            airplaneModel.getLandingGear().setLandingGearFieldsTrue(1, landingGearFieldModel2.getPlacedDice().getDiceValue());
            field2 = true;
            markerManager.update("blue");
        }
        if(!field3 && landingGearFieldModel3.isOccupied()){
            airplaneModel.getLandingGear().setLandingGearFieldsTrue(2, landingGearFieldModel3.getPlacedDice().getDiceValue());
            field3 = true;
            markerManager.update("blue");
        }
    }
}
