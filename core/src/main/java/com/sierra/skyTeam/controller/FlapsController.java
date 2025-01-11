package com.sierra.skyTeam.controller;

import com.sierra.skyTeam.model.Airplane;
import com.sierra.skyTeam.model.FieldModel;
import com.sierra.skyTeam.model.GameModel;
import com.sierra.skyTeam.view.FieldGenerator;
import com.sierra.skyTeam.view.FieldView;
import com.sierra.skyTeam.view.MarkerManager;

import java.util.List;

public class FlapsController {
    private Airplane airplaneModel;

    private MarkerManager markerManager;

    private List<FieldView> flapsFieldViews;

    private FieldModel flapsFieldModel1;
    private FieldModel flapsFieldModel2;
    private FieldModel flapsFieldModel3;
    private FieldModel flapsFieldModel4;

    private boolean field1;
    private boolean field2;
    private boolean field3;
    private boolean field4;

    public FlapsController(GameModel gameModel, MarkerManager markerManager) {
        this.airplaneModel = gameModel.getAirplane();
        this.markerManager = markerManager;

        this.flapsFieldViews = FieldGenerator.getFlapsFieldViews();

        this.flapsFieldModel1 = flapsFieldViews.get(0).getFieldModel();
        this.flapsFieldModel2 = flapsFieldViews.get(1).getFieldModel();
        this.flapsFieldModel3 = flapsFieldViews.get(2).getFieldModel();
        this.flapsFieldModel4 = flapsFieldViews.get(3).getFieldModel();

        this.field1 = false;
        this.field2 = false;
        this.field3 = false;
        this.field4 = false;
    }

    public void draw(){
        if(!field1 && flapsFieldModel1.isOccupied()){
            airplaneModel.getFlaps().setFlapFieldsTrue(0, flapsFieldModel1.getPlacedDice().getDiceValue());
            field1 = true;
            markerManager.update("orange");
        }
        if(!field2 && flapsFieldModel2.isOccupied()){
            airplaneModel.getFlaps().setFlapFieldsTrue(1, flapsFieldModel2.getPlacedDice().getDiceValue());
            field2 = true;
            markerManager.update("orange");
        }
        if(!field3 && flapsFieldModel3.isOccupied()){
            airplaneModel.getFlaps().setFlapFieldsTrue(2, flapsFieldModel3.getPlacedDice().getDiceValue());
            field3 = true;
            markerManager.update("orange");
        }
        if(!field4 && flapsFieldModel4.isOccupied()){
            airplaneModel.getFlaps().setFlapFieldsTrue(3, flapsFieldModel4.getPlacedDice().getDiceValue());
            field4 = true;
            markerManager.update("orange");
        }
    }
}
