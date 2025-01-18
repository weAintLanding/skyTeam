package com.sierra.skyTeam.model;

import com.sierra.skyTeam.MainGame;
import com.sierra.skyTeam.screens.CrashScreen;

public class Engine {
    private final Airplane airplane;
    private int blueAeroMarker = 4;
    private int orangeAeroMarker = 8;
    private int redBrakeMarker = 1;
    private boolean planeLanded;
    private ApproachTrackModel trackManager;

    public Engine(Airplane airplane) {
        this.airplane = airplane;
        this.planeLanded = false;
    }

    //Getter and Setter for AeroMarkers
    public int getBlueAeroMarker() {
        return blueAeroMarker;
    }

    public void setBlueAeroMarker(int blueAeroMarker) {
        this.blueAeroMarker = blueAeroMarker;
    }

    public int getOrangeAeroMarker() {
        return orangeAeroMarker;
    }

    public void setTrackManager(ApproachTrackModel trackManager) {

        this.trackManager = trackManager;

    }

    public void setOrangeAeroMarker(int orangeAeroMarker) {
        this.orangeAeroMarker = orangeAeroMarker;
    }

    public int getRedBrakeMarker() {
        return redBrakeMarker;
    }

    public void setRedBrakeMarker(int redBrakeMarker) {
        this.redBrakeMarker = redBrakeMarker;
    }



    public void movePlane(int pilotValue, int copilotValue, ApproachTrackModel trackManager, MainGame game) {
        int engineSum = pilotValue + copilotValue;

        if (engineSum <= blueAeroMarker) {
        } else if (engineSum <= orangeAeroMarker) {
            if(airplane.getGame().checkCrashMove(1)){
                game.setScreen(new CrashScreen(game));
            }
            if((airplane.getApproachPosition() + 1) > 6){
                game.setScreen(new CrashScreen(game));
            }
            trackManager.updateTrackBy1();
            airplane.setApproachPosition(airplane.getApproachPosition() + 1);
        } else {
            if(airplane.getGame().checkCrashMove(2)){
                game.setScreen(new CrashScreen(game));
            }
            if((airplane.getApproachPosition() + 2) > 6){
                game.setScreen(new CrashScreen(game));
            }
            trackManager.updateTrackBy2();
            airplane.setApproachPosition(airplane.getApproachPosition() + 2);
        }
    }

    public void landPlane(int pilotValue, int copilotValue, MainGame game) {
        int engineSum = pilotValue + copilotValue;
        if (engineSum <= redBrakeMarker) {
            planeLanded = true;
        }else{
            game.setScreen(new CrashScreen(game));
        }
    }

    public boolean isPlaneLanded() {
        return planeLanded;
    }
}
