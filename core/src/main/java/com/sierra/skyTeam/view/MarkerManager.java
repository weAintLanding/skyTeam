package com.sierra.skyTeam.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sierra.skyTeam.model.Engine;

public class MarkerManager {
    private final Marker blueMarker;
    private final Marker orangeMarker;
    private final Marker redMarker;

    private Engine engineModel;

    public MarkerManager(Engine engineModel) {
        this.engineModel = engineModel;

        blueMarker = new Marker(new Sprite(new Texture("blueMarker.png")),engineModel.getBlueAeroMarker(), 538, 389, -38);
        orangeMarker = new Marker(new Sprite(new Texture("orangeMarker.png")), engineModel.getOrangeAeroMarker(), 681, 371, 24);
        redMarker = new Marker(new Sprite(new Texture("redMarker.png")), engineModel.getRedBrakeMarker(), 539, 259, -43);
    }

    public void update(String markerName){
        switch(markerName.toLowerCase()){
            case "blue": //will change to their actual names like landing and flaps
                blueMarker.landingUpdate(engineModel.getBlueAeroMarker());
                break;
            case "orange":
                orangeMarker.flapsUpdate(engineModel.getOrangeAeroMarker());
                break;
            case "red":
                redMarker.brakesUpdate(engineModel.getRedBrakeMarker());
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
