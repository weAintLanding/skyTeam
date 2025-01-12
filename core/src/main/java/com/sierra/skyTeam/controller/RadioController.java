package com.sierra.skyTeam.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sierra.skyTeam.model.ApproachTrackModel;
import com.sierra.skyTeam.model.FieldModel;
import com.sierra.skyTeam.view.FieldGenerator;
import com.sierra.skyTeam.view.FieldView;

import java.util.ArrayList;
import java.util.List;

public class RadioController {
    private final List<FieldModel> radioFieldModels;
    private final ApproachTrackModel trackManager;

    public RadioController(){
        this.radioFieldModels = new ArrayList<>();
        this.trackManager = new ApproachTrackModel();

        FieldView pilotRadioField = FieldGenerator.getPilotRadio();
        List<FieldView> copilotRadioFieldViews = FieldGenerator.getCopilotRadio();

        radioFieldModels.add(pilotRadioField.getFieldModel());
        for (FieldView fieldView : copilotRadioFieldViews) {
            radioFieldModels.add(fieldView.getFieldModel());
        }
    }

    public ApproachTrackModel getTrackManager() {
        return trackManager;
    }

    public void handleDicePlacement(){
        for(FieldModel fieldModel : radioFieldModels){
            if(fieldModel.isOccupied() && !fieldModel.isDiceProcessed()){
                int placedDiceValue = fieldModel.getPlacedDice().getDiceValue() + trackManager.getCurrentPosition();
                trackManager.removeAirplane(placedDiceValue);
                fieldModel.setDiceProcessed(true);
            }
        }
    }

    public void draw(SpriteBatch batch){
        trackManager.draw(batch);
        handleDicePlacement();
    }
}
