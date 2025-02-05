package com.sierra.skyTeam.model;

/**
 * Die Klasse {@code CoPilot} repräsentiert den Co-Piloten im Spiel, der von der {@code Players}-Klasse erbt.
 * Der Co-Pilot hat die Fähigkeit, mit bestimmten Radiofeldern zu interagieren, die über zwei Slots verfügen.
 */
public class CoPilot extends Players {

    /**
     * Konstruktor für die Klasse {@code CoPilot}.
     * Initialisiert den Co-Piloten mit den entsprechenden Radio-Slots und ruft den Konstruktor der Elternklasse {@code Players} auf.
     *
     * @param gameModel Das {@code GameModel}, das das GameModel repräsentiert.
     */
    public CoPilot(GameModel gameModel){
        super(gameModel);
        this.radioSlots = 2;
        this.radioPlayer = new Field[radioSlots];
        for (int i = 0; i < radioSlots; i++) {
            radioPlayer[i] = new Field("Radio Copilot");
        }
    }
}
