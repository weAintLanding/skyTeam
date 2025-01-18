package com.sierra.skyTeam.controller;


import com.sierra.skyTeam.model.*;

import java.util.List;

public class PlayerController {

    private GameModel gameModel;

    public PlayerController(GameModel gameModel){
        this.gameModel = gameModel;
    }

    public Dice[] getPilotDice(){
        return gameModel.getPilot().getDiceList();
    }
    public Dice[] getCoPilotDice(){
        return gameModel.getCoPilot().getDiceList();
    }
}
