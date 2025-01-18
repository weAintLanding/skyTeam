package com.sierra.skyTeam.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.sierra.skyTeam.model.FieldModel;

/**
 * Diese Klasse repräsentiert eine Ansicht für ein Feld auf der Benutzeroberfläche.
 * Sie enthält Rectangle-Bounds und ein optionales Switch, das je nach Zustand des Feldes verschoben wird.
 */
public class FieldView {
    private final Rectangle bounds;
    private Sprite switchImg;
    private final FieldModel fieldModel;
    private boolean switchMoved;

    /**
     * Konstruktor für ein `FieldView`-Objekt.
     *
     * @param x             Die x-Koordinate des Feldes.
     * @param y             Die y-Koordinate des Feldes.
     * @param fieldModel    Das Modell des Feldes, das Informationen über das Feld enthält.
     */
    public FieldView(float x, float y, FieldModel fieldModel){
        int fieldSize = 45;
        this.bounds = new Rectangle(x, y, fieldSize, fieldSize);
        this.fieldModel = fieldModel;

        if(fieldModel.hasSwitch()){
            switchImg = new Sprite(new Texture("switch.png"));
            switchImg.setScale(0.6F);
            switchImg.setPosition(x+14, y-45);
        }
    }

    /**
     * Zeichnet das Switch, wenn ein Feld ein Switch enthält, mit dem angegebenen {@code SpriteBatch}.
     *
     * @param batch Der {@code SpriteBatch}, der zum Rendern verwendet wird.
     */
    public void switchRenderer(SpriteBatch batch) {
        if(fieldModel.hasSwitch()){
            switchImg.draw(batch);
        }
        updateSwitch();
    }

    /**
     * Aktualisiert die Position des Schalters basierend auf dem Zustand des Switch.
     */
    public void updateSwitch() {
        if (fieldModel.getIsSwitchOn() && !switchMoved) {
            switchImg.setX(switchImg.getX() - 24);
            switchMoved = true;
        } else if (!fieldModel.getIsSwitchOn() && switchMoved) {
            switchImg.setX(switchImg.getX() + 24);
            switchMoved = false;
        }
    }

    /**
     * Gibt die Bounds zurück, das die Position und Größe des Feldes definiert.
     *
     * @return Das `Rectangle`, das die Bounds des Feldes angibt.
     */
    public Rectangle getBounds() {
        return bounds;
    }

    /**
     * Gibt das `FieldModel` des Feldes zurück.
     *
     * @return Das `FieldModel`, das alle Informationen über das Feld enthält.
     */
    public FieldModel getFieldModel() {
        return fieldModel;
    }
}
