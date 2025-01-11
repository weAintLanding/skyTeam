package com.sierra.skyTeam.model;

public class Engine {
    private final Airplane airplane;
    private int blueAeroMarker = 4;
    private int orangeAeroMarker = 8;
    private int redBrakeMarker = 1;

    public Engine(Airplane airplane) {
        this.airplane = airplane;
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

    public void setOrangeAeroMarker(int orangeAeroMarker) {
        this.orangeAeroMarker = orangeAeroMarker;
    }

    public int getRedBrakeMarker() {
        return redBrakeMarker;
    }

    public void setRedBrakeMarker(int redBrakeMarker) {
        this.redBrakeMarker = redBrakeMarker;
    }

    public void movePlane(int pilotValue, int copilotValue, ApproachTrackModel trackManager) {
        int engineSum = pilotValue + copilotValue;

        if (engineSum <= blueAeroMarker) {
            System.out.println("Plane does not move");
        } else if (engineSum <= orangeAeroMarker) {
            if(airplane.getGame().checkCrashMove(1)){
                System.out.println("Plane crashed. ");
                System.exit(0);
            }
            System.out.println("Plane moves 1 position.");
            trackManager.updateTrackBy1();
            airplane.setApproachPosition(airplane.getApproachPosition() + 1);
        } else {
            if(airplane.getGame().checkCrashMove(2)){
                System.out.println("Plane crashed. ");
                System.exit(0);
            }
            System.out.println("Plane moves 2 positions.");
            trackManager.updateTrackBy2();
            airplane.setApproachPosition(airplane.getApproachPosition() + 2);
        }
    }

    public void landPlane(int pilotValue, int copilotValue) {
        int engineSum = pilotValue + copilotValue;
        if (engineSum <= redBrakeMarker) {
            System.out.println("Plane stopped successfully.");
        }else{
            System.out.println("Plane not able to break. Plane Crashed.");
            System.exit(0);
        }
    }
}
