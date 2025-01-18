package com.sierra.skyTeam.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Diese Klasse enthält eine Hilfsmethode zur Skalierung der Benutzereingabe auf dem Bildschirm.
 * Sie wird verwendet, um die Eingabekoordinaten korrekt zu skalieren.
 */
public class InputHandler {

    /**
     * Skaliert die Eingabekoordinaten von der Bildschirmauflösung auf die Ansichtskameraauflösung.
     *
     * @param viewport Der `Viewport`, der der Fit-Viewport darstellt.
     * @return Ein `Vector2`, der die skalierten Koordinaten der Benutzereingabe enthält.
     */
    public static Vector2 scaledInput(Viewport viewport) {
        Vector2 coordinates = viewport.unproject(new Vector2(Gdx.input.getX(), Gdx.input.getY()));
        return new Vector2(coordinates);
    }
}
