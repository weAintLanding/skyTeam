package com.sierra.skyTeam.controller;

import com.sierra.skyTeam.model.Dice;
import com.sierra.skyTeam.view.FieldGenerator;
import com.sierra.skyTeam.view.FieldView;
import com.sierra.skyTeam.view.TrackManager;

import java.util.List;

public class RadioController {
    private FieldView pilotRadioField = FieldGenerator.getPilotRadio();
    private List<FieldView> copilotRadioFields = FieldGenerator.getCopilotRadio();
    private Dice dice;
    private TrackManager trackManager;

    public RadioController(TrackManager trackManager){
        this.trackManager = trackManager;
    }

    public void handleDicePlacement(Dice placedDice, boolean isPilot){
        int diceValue = dice.getDiceValue();
    }
}
