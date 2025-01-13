package com.sierra.skyTeam.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sierra.skyTeam.model.AltitudeTrackModel;
import com.sierra.skyTeam.model.RerollTokenModel;

public class AltitudeController {
    private final AltitudeTrackModel trackManager;
    private final RerollTokenModel rerollToken;
    private int round;

    public AltitudeController() {
        this.trackManager = new AltitudeTrackModel();
        this.rerollToken = new RerollTokenModel();
        this.round = 1;
        drawTokens();
    }

    public void setRound(int round) {
        this.round = round;
        if (round == 1){
            trackManager.setStartingPosition();
        }else {
            int index = round - 1;
            trackManager.updateTrackerPosition(index);
        }
        addTokens();
    }

    public void nextRound() {
        this.round = this.round + 1;
        if (round == 1){
            trackManager.setStartingPosition();
        }else {
            int index = round - 1;
            trackManager.updateTrackerPosition(index);
        }
        addTokens();
    }

    public void addTokens(){
        if (round == 1 || round == 5){
            rerollToken.addToken();
        }
    }

    public void drawTokens(){
        rerollToken.generateToken(920, 110);
        rerollToken.generateToken(920,395);
    }

    public void draw(SpriteBatch batch) {
        trackManager.draw(batch);
        rerollToken.draw(batch);
    }

    public int getRound() {
        return this.round;
    }

    public RerollTokenModel getRerollToken() {
        return rerollToken;
    }
}
