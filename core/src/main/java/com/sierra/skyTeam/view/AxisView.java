package com.sierra.skyTeam.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Die Klasse {@code AxisView} repräsentiert das Axis im Spiel.
 * Sie verwaltet der Axis-Wert, berechnet die Drehungen und rendert das Axis.
 */
public class AxisView {
    private int axisValue;
    private final Sprite axis;

    /**
     * Konstruktor für {@code AxisView}. Initialisiert den Axis-Wert,
     * lädt die Axis-Textur und setzt die Standardposition und Skalierung.
     *
     * @param axisValue Der initiale Wert der Achse.
     */
    public AxisView(int axisValue) {
        this.axisValue = axisValue;
        Texture texture = new Texture("Axis.png");
        axis = new Sprite(texture);
        axis.setPosition(525, 410);
        axis.setScale(0.6F);
        axis.setOriginCenter();
    }

    /**
     * Setzt den Wert des Axis und aktualisiert die Rotation basierend auf diesem Wert.
     *
     * @param axisValue Der neue Wert der Achse.
     */
    public void setAxisValue(int axisValue) {
        this.axisValue = axisValue;
        rotateAxis();
    }

    /**
     * Berechnet und setzt die Rotation des Axis basierend auf dem Axis-Wert.
     * Die Rotation wird mit einer Animation aktualisiert, wenn der Zielwinkel und der aktuelle Winkel abweichen.
     */
    public void rotateAxis() {
        float animationSpeed = 2f;
        float deltaTime = Gdx.graphics.getDeltaTime();
        float targetAngle;
        if (Math.abs(axisValue) == 3){
            targetAngle = -axisValue * 25F;
        } else targetAngle = -axisValue * 27F;
        float currentAngle = axis.getRotation();
        float angleDifference = targetAngle - currentAngle;
        if(Math.abs(angleDifference) > 1F){
            axis.setRotation(currentAngle+angleDifference*animationSpeed*deltaTime);
        }else {
            axis.setRotation(targetAngle);
        }
    }

    /**
     * Zeichnet des Axis-Sprites auf dem Bildschirm unter Verwendung des angegebenen {@code SpriteBatch}.
     *
     * @param batch Der {@code SpriteBatch}, der zum Rendern des Sprites verwendet wird.
     */
    public void render(SpriteBatch batch) {
        axis.draw(batch);
    }
}
