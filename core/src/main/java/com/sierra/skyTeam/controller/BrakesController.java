package com.sierra.skyTeam.controller;

import com.sierra.skyTeam.model.Airplane;
import com.sierra.skyTeam.model.FieldModel;
import com.sierra.skyTeam.model.GameModel;
import com.sierra.skyTeam.view.FieldGenerator;
import com.sierra.skyTeam.view.FieldView;
import com.sierra.skyTeam.view.MarkerManager;

import java.util.List;

public class BrakesController {
    private Airplane airplaneModel;

    private MarkerManager markerManager;

    private List<FieldView> brakeFieldViews;

    private FieldModel brakeFieldModel1;
    private FieldModel brakeFieldModel2;
    private FieldModel brakeFieldModel3;

    private boolean field1;
    private boolean field2;
    private boolean field3;



    public BrakesController(GameModel gameModel, MarkerManager markerManager) {
        this.airplaneModel = gameModel.getAirplane();
        this.markerManager = markerManager;

        this.brakeFieldViews = FieldGenerator.getBrakeFieldViews();

        this.brakeFieldModel1 = brakeFieldViews.get(0).getFieldModel();
        this.brakeFieldModel2 = brakeFieldViews.get(1).getFieldModel();
        this.brakeFieldModel3 = brakeFieldViews.get(2).getFieldModel();

        this.field1 = false;
        this.field2 = false;
        this.field3 = false;
    }

    public void draw(){
        if(!field1 && brakeFieldModel1.isOccupied()){
            airplaneModel.getBrakes().setBrakeFieldsTrue(0, brakeFieldModel1.getPlacedDice().getDiceValue());
            field1 = true;
            markerManager.update("red");
        }
        if(!field2 && brakeFieldModel2.isOccupied()){
            airplaneModel.getBrakes().setBrakeFieldsTrue(1, brakeFieldModel2.getPlacedDice().getDiceValue());
            field2 = true;
            markerManager.update("red");
        }
        if(!field3 && brakeFieldModel3.isOccupied()){
            airplaneModel.getBrakes().setBrakeFieldsTrue(2, brakeFieldModel3.getPlacedDice().getDiceValue());
            field3 = true;
            markerManager.update("red");
        }
    }
}
