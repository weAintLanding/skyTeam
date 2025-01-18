package com.sierra.skyTeam.model;

public class CoPilot extends Players {
    public CoPilot(GameModel gameModel){
        super(gameModel);
        this.radioSlots = 2;
        this.radioPlayer = new Field[radioSlots];
        for (int i = 0; i < radioSlots; i++) {
            radioPlayer[i] = new Field("Radio Copilot");
        }
    }
}
