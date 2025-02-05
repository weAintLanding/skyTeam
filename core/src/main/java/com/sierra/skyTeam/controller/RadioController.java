package com.sierra.skyTeam.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sierra.skyTeam.model.ApproachTrackModel;
import com.sierra.skyTeam.model.FieldModel;
import com.sierra.skyTeam.model.GameModel;
import com.sierra.skyTeam.view.FieldGenerator;
import com.sierra.skyTeam.view.FieldView;

import java.util.ArrayList;
import java.util.List;

/**
 * Der RadioController verwaltet die Logik für die Radiofelder, die von den Spielern während des Spiels verwendet werden.
 * Er verwaltet die Platzierung der Würfel und das Entfernen der Flugzeuge auf der Anflugstrecke.
 */
public class RadioController {
    private final List<FieldModel> radioFieldModels;
    private final ApproachTrackModel trackManager;
    private final GameModel gameModel;

    /**
     * Konstruktor: Initialisiert den RadioController mit dem gegebenen Spielmodell.
     * Er initialisiert die Radiofelder des Piloten, des Co-Piloten und die Anflugstrecke.
     *
     * @param gameModel Das Modell des Spiels, das die Daten der Spieler und des Flugzeugs enthält.
     */
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

    /**
     * Gibt den Manager für die Anflugstrecke zurück.
     *
     * @return Der ApproachTrackModel, der die Anflugstrecke verwaltet.
     */
    public ApproachTrackModel getTrackManager() {
        return trackManager;
    }

    /**
     * Verarbeitet die Platzierung der Würfel auf den Radiofeldern.
     * Die Position des Flugzeugs auf der Anflugstrecke wird basierend auf dem Wert des
     * platzierten Würfels und der aktuellen Position des Flugzeugs aktualisiert.
     */
    public void handleDicePlacement(){
        for(FieldModel fieldModel : radioFieldModels){
            if(fieldModel.isOccupied() && !fieldModel.isDiceProcessed()){
                int placedDiceValue = fieldModel.getPlacedDice().getDiceValue() + trackManager.getCurrentPosition();
                trackManager.removeAirplane(placedDiceValue, gameModel);
                fieldModel.setDiceProcessed(true);
            }
        }
    }

    /**
     * Setzt die Verarbeitung der Würfel auf allen Radiofeldern zurück.
     * Wird zu Beginn jeder Runde aufgerufen.
     */
    public void roundReset() {
        for (FieldModel fieldModel : radioFieldModels) {
            fieldModel.setDiceProcessed(false);
        }
    }

    /**
     * Zeichnet die Anflugstrecke und verarbeitet die Platzierung der Würfel.
     *
     * @param batch Das SpriteBatch, das für das Zeichnen der Grafiken verwendet wird.
     */
    public void draw(SpriteBatch batch){
        trackManager.draw(batch);
        handleDicePlacement();
    }
}
