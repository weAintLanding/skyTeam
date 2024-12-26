package com.sierra.skyTeam.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MarkerManager {
    private Marker blueMarker;
    private Marker orangeMarker;
    private Marker redMarker;

    public MarkerManager() {
        blueMarker = new Marker(new Sprite(new Texture("blueMarker.png")),4, 538, 389, -38);
        orangeMarker = new Marker(new Sprite(new Texture("orangeMarker.png")),9, 681, 371, 24);
        redMarker = new Marker(new Sprite(new Texture("redMarker.png")),2, 539, 259, -43);
    }

    public void update(String markerName, int value){
        switch(markerName.toLowerCase()){
            case "blue": //will change to their actual names like landing and flaps
                blueMarker.landingUpdate(value);
                break;
            case "orange":
                orangeMarker.flapsUpdate(value);
                break;
            case "red":
                redMarker.brakesUpdate(value);
                break;
            default:
                System.out.println("wrong value");
        }
    }

    public void draw(SpriteBatch batch) {
        blueMarker.draw(batch);
        orangeMarker.draw(batch);
        redMarker.draw(batch);
    }

}
