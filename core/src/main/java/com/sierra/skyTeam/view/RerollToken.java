package com.sierra.skyTeam.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Stellt das Token dar, das verwendet wird, um anzuzeigen, dass das Reroll durchgeführt werden kann.
 * Das Token ist auf dem Spielfeld positioniert und es kann verwendet oder deaktiviert werden.
 */
public class RerollToken {
    Sprite rerollTokenSprite;
    boolean onBoard;
    boolean used;

    /**
     * Konstruktor für RerollToken.
     * Das Token ist anfänglich nicht auf dem Spielfeld und nicht verwendet.
     */
    public RerollToken () {
        rerollTokenSprite = new Sprite(new Texture("rerollToken.png"));
        rerollTokenSprite.setScale(0.3F);
        this.onBoard = false;
        this.used = false;
    }

    /**
     * Setzt die Position des RerollToken auf dem Spielfeld.
     *
     * @param x die x-Koordinate der Position
     * @param y die y-Koordinate der Position
     */
    public void setPosition(float x, float y){
        rerollTokenSprite.setPosition(x, y);
    }

    /**
     * Gibt zurück, ob das Token auf dem Spielfeld ist.
     *
     * @return true, wenn das Token auf dem Spielfeld ist, andernfalls false
     */
    public boolean isOnBoard() {
        return onBoard;
    }

    /**
     * Setzt den Status des Tokens auf "onBoard".
     *
     * @param onBoard true, wenn das Token auf dem Spielfeld platziert werden soll, andernfalls false
     */
    public void setOnBoard(boolean onBoard) {
        this.onBoard = onBoard;
    }

    /**
     * Gibt zurück, ob das Token bereits verwendet wurde.
     *
     * @return true, wenn das Token verwendet wurde, andernfalls false
     */
    public boolean isUsed() {
        return used;
    }

    /**
     * Setzt den Status des Tokens auf "verwendet".
     */
    public void setUsed() {
        this.used = true;
    }

    /**
     * Zeichnet den Token, wenn es noch nicht verwendet ist, mit dem angegebenen {@code SpriteBatch}.
     *
     * @param batch Der {@code SpriteBatch}, der zum Rendern verwendet wird.
     */
    public void draw(SpriteBatch batch) {
        if(!used) {
            rerollTokenSprite.draw(batch);
        }
    }
}
