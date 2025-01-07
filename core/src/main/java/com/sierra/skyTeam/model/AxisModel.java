package com.sierra.skyTeam.model;

public class AxisModel {
    private final Airplane airplane;
    private int axisValue = 0;

    public AxisModel(Airplane airplane) {
        this.airplane = airplane;
    }

    public void changeAxis(Players pilot, Players copilot) {
        Integer pilotValue = pilot.getAxis();
        Integer copilotValue = copilot.getAxis();
        int changeValue = copilotValue - pilotValue;
        axisValue = axisValue + changeValue;
        if(airplane.getGame().checkCrashAxis()){
            System.out.println("axisModel out of balance. Plane Crashed.");
            System.exit(0);
        }
    }

    public int getAxisValue() {
        return axisValue;
    }
}
