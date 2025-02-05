package com.sierra.skyTeam.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sierra.skyTeam.view.RerollToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Die {@code RerollTokenModel}-Klasse enthält die Reroll-token im Spiel.
 * Sie kümmert sich um das Erstellen, Hinzufügen und Verwalten von Token, die auf dem Spielfeld angezeigt werden.
 * Diese Token ermöglichen es den Spielern, ihre Würfel zu neuwürfeln und helfen bei der Positionierung der Token auf dem Spielfeld.
 */
public class RerollTokenModel {

    private final List<RerollToken> rerollTokens;
    private int index;

    /**
     * Konstruktor für die {@code RerollTokenModel}-Klasse.
     * Initialisiert die Liste der Wiederholtoken und setzt den Index auf -1.
     */
    public RerollTokenModel() {
        rerollTokens = new ArrayList<>();
        index = -1;
    }

    /**
     * Erzeugt ein neues Wiederholtoken an der angegebenen Position.
     *
     * @param x Die x-Koordinate der Position des Tokens.
     * @param y Die y-Koordinate der Position des Tokens.
     */
    public void generateToken(float x, float y) {
        RerollToken rerollToken = new RerollToken();
        rerollToken.setPosition(x, y);
        rerollTokens.add(rerollToken);
    }

    /**
     * Fügt ein neues Token hinzu, das auf dem Spielfeld angezeigt wird.
     * Die Position des Tokens wird basierend auf der Anzahl der bereits hinzugefügten Token angepasst.
     */
    public void addToken() {
        float x = 355;
        float y = 562;
        for(int i = 0; i < rerollTokens.size(); i++){
            RerollToken rerollToken = rerollTokens.get(i);
            if(!rerollToken.isUsed() && !rerollToken.isOnBoard()){
                //only applies new positioning if the previous token was rendered and not used
                for(int j = 0; j < i; j++){
                    RerollToken prevToken = rerollTokens.get(j);
                    if(prevToken.isOnBoard() && !prevToken.isUsed()) {
                        x += 3;
                        y += 10;
                    }
                }

                rerollToken.setPosition(x, y);
                rerollToken.setOnBoard(true);
                index = i;
                break;
            }
        }
    }

    /**
     * Markiert das aktuelle Token als verwendet und entfernt es vom Spielfeld.
     * Verringert den Index des Tokens, um das nächste Token zu markieren.
     */
    public void markTokenAsUsed() {
        if(index >= 0 && index < rerollTokens.size()) {
            RerollToken rerollToken = rerollTokens.get(index);
            rerollToken.setUsed();
            rerollToken.setOnBoard(false);
            index -= 1;
        }
    }

    /**
     * Gibt eine Liste zurück, die angibt, welche Tokens auf dem Spielfeld angezeigt werden.
     *
     * @return Eine Liste von {@code Boolean}-Werten, die für jedes Token angeben, ob es auf dem Spielfeld angezeigt wird.
     */
    public List<Boolean> tokensOnBoard() {
        List<Boolean> tokensOnBoard = new ArrayList<>();
        for (RerollToken rerollToken : rerollTokens) {
            tokensOnBoard.add(rerollToken.isOnBoard());
        }
        return tokensOnBoard;
    }

    /**
     * Zeichnet alle Wiederholtoken auf dem gegebenen {@code SpriteBatch}.
     *
     * @param batch Das {@code SpriteBatch}, auf dem die Token gezeichnet werden.
     */
    public void draw(SpriteBatch batch) {
        for(RerollToken rerollToken : rerollTokens){
            rerollToken.draw(batch);
        }
    }
}
