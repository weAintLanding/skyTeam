package com.sierra.skyTeam.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sierra.skyTeam.model.ApproachTrackModel;
import com.sierra.skyTeam.model.FieldModel;
import com.sierra.skyTeam.model.GameModel;
import com.sierra.skyTeam.view.FieldGenerator;
import com.sierra.skyTeam.view.FieldView;

import java.util.ArrayList;
import java.util.List;

public class RadioController {
    private final List<FieldModel> radioFieldModels;
    private final ApproachTrackModel trackManager;
    private final GameModel gameModel;

    public RadioController(GameModel gameModel){
        this.gameModel = gameModel;
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
                trackManager.removeAirplane(placedDiceValue, gameModel);
                fieldModel.setDiceProcessed(true);
            }
        }
    }

    public void draw(SpriteBatch batch){
        trackManager.draw(batch);
        handleDicePlacement();
    }

    public void roundReset() {
        for (FieldModel fieldModel : radioFieldModels) {
            fieldModel.setDiceProcessed(false);
        }
    }
}
