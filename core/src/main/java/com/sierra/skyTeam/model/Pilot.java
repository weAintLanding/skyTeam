package com.sierra.skyTeam.model;
public class Pilot extends Players {
    public Pilot(GameModel gameModel) {
        super(gameModel);
        this.radioSlots = 1;
        this.radioPlayer = new Field[radioSlots];
        for (int i = 0; i < radioSlots; i++) {
            radioPlayer[i] = new Field("Radio Pilot");
        }
    }
}
