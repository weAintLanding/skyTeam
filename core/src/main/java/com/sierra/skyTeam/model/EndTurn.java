package com.sierra.skyTeam.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sierra.skyTeam.controller.RoundController;
import com.sierra.skyTeam.view.EndTurnView;

import java.util.ArrayList;
import java.util.List;

/**
 * Die Klasse {@code EndTurn} enthält die Logik für die End-Turn-Taste, die einen Turn für die Spieler (Pilot und Copilot) beendet.
 * Sie zeigt die Tasten für das Ende des Zuges und verarbeitet Mausklicks auf diese Tasten.
 */
public class EndTurn {
    private final RoundController roundController;
    private final List<EndTurnView> endTurnButtons;

    /**
     * Konstruktor für die {@code EndTurn}-Klasse.
     * Initialisiert die Tasten für das Ende des Zuges und weist sie den entsprechenden Positionen zu.
     *
     * @param roundController Der Controller für die aktuelle Runde.
     */
    public EndTurn(RoundController roundController) {
        this.roundController = roundController;
        endTurnButtons = new ArrayList<>();
        endTurnButtons.add(new EndTurnView(-100, -40));     //pilot
        endTurnButtons.add(new EndTurnView(965, -40));   //copilot
    }

    /**
     * Überprüft, ob der Mauszeiger über einer der End-Turn-Turn schwebt.
     *
     * @param touchX Die X-Position des Touch-Ereignisses.
     * @param touchY Die Y-Position des Touch-Ereignisses.
     * @return {@code true}, wenn der Mauszeiger über einer der Schaltflächen schwebt, sonst {@code false}.
     */
    public boolean isHovered(float touchX, float touchY) {
        for(EndTurnView endTurnView : endTurnButtons) {
            if(endTurnView.getBoundingRectangle().contains(touchX, touchY) && endTurnView.isVisible()){
                return true;
            }
        }
        return false;
    }

    /**
     * Behandelt einen Mausklick auf eine der End-Turn-Taste.
     * Wenn eine Taste angeklickt wird, endet der Zug für den entsprechenden Spieler (Pilot oder Copilot).
     *
     * @param touchX Die X-Position des Touch-Ereignisses.
     * @param touchY Die Y-Position des Touch-Ereignisses.
     */
    public void handleClick(float touchX, float touchY) {
        for(int i = 0; i < endTurnButtons.size(); i++){
            EndTurnView endTurnView = endTurnButtons.get(i);
            if(endTurnView.getBoundingRectangle().contains(touchX, touchY) && Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && endTurnView.isVisible()) {
                if(roundController.getTurn()) {
                    roundController.pilotEndTurn();

                } else {
                    roundController.copilotEndTurn();
                }
            }
        }
    }

    /**
     * Gibt die Liste der Tasten für das Ende des Zuges zurück.
     *
     * @return Die Liste der {@code EndTurnView}-Instanzen.
     */
    public List<EndTurnView> getEndTurnButtons() {
        return endTurnButtons;
    }

    /**
     * Zeichnet die Tasten für das Ende des Zuges.
     *
     * @param batch Der {@code SpriteBatch}, der zum Zeichnen der Tasten verwendet wird.
     */
    public void draw(SpriteBatch batch) {
        for(EndTurnView endTurn : endTurnButtons) {
            if(endTurn.isVisible()){
                endTurn.draw(batch);
            }
        }
    }
}
