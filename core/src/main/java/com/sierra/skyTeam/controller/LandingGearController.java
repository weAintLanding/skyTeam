package com.sierra.skyTeam.controller;

import com.sierra.skyTeam.model.Airplane;
import com.sierra.skyTeam.model.FieldModel;
import com.sierra.skyTeam.model.GameModel;
import com.sierra.skyTeam.view.FieldGenerator;
import com.sierra.skyTeam.view.FieldView;
import com.sierra.skyTeam.view.MarkerManager;

import java.util.List;

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
