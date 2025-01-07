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


    /*@Override
    public void setRadio(int diceValue) {
        if (getRadioSlots() < 1) {
            super.setRadio(diceValue);
        } else System.out.println(this.getClass().getSimpleName() + " cannot place more dice in the Radio slot");
    }*/

