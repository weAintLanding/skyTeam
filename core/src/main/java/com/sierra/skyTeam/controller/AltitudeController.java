package com.sierra.skyTeam.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sierra.skyTeam.model.AltitudeTrackModel;

public class AltitudeController {
    private final AltitudeTrackModel trackManager;

    public AltitudeController() {
        this.trackManager = new AltitudeTrackModel();
    }

    public void setRound(int round) {
        if (round == 1){
            trackManager.setStartingPosition();
        }else {
            int index = round - 1;
            trackManager.updateTrackerPosition(index);
        }
    }

    public void draw(SpriteBatch batch) {
        trackManager.draw(batch);
    }
}
