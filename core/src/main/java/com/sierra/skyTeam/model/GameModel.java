package com.sierra.skyTeam.model;

import com.sierra.skyTeam.MainGame;
import com.sierra.skyTeam.screens.CrashScreen;

import java.util.Scanner;

public class GameModel {
    MainGame game;
    private final Airplane airplane;
    private final Pilot pilot;
    private final CoPilot copilot;
    private int rerollsAvailable;
    private final ApproachTrack approachTrack;
    private final AltitudeTrack altitudeTrack;
    private Players currentPlayer;
    boolean axisChanged;
    boolean throttleChanged;
    boolean endOfGame;

    public GameModel(MainGame game) {
        this.game = game;
        this.airplane = new Airplane();
        this.pilot = new Pilot(this);
        this.copilot = new CoPilot(this);
        boolean axisChanged = false;
        boolean throttleChanged = false;
        boolean endOfGame = false;
        this.rerollsAvailable = 0;
        this.currentPlayer = pilot;
        this.approachTrack = new ApproachTrack();
        this.altitudeTrack = new AltitudeTrack(this);

        airplane.setGame(this);
    }

    public Pilot getPilot() {
        return pilot;
    }
    public CoPilot getCoPilot() {
        return copilot;
    }
    public Airplane getAirplane() {
        return airplane;
    }

    public Players getCurrentPlayer() {
        return currentPlayer;
    }
    public void switchPlayer() {
        currentPlayer = (currentPlayer == pilot) ? copilot : pilot;
    }

    public ApproachTrack getApproachTrack() {
        return approachTrack;
    }
    public AltitudeTrack getAltitudeTrack() {
        return altitudeTrack;
    }

    public void setRerollsAvailable() {
        this.rerollsAvailable++;
    }
    public void decreaseRerollsAvailable() {
        if (rerollsAvailable > 0) {
            rerollsAvailable--;
        }
    }
    public int getRerollsAvailable() {
        return rerollsAvailable;
    }

    public int countDicePlaced(Dice[] diceList){
        int dicePlaced = 0;
        for (Dice dice : diceList) {
            if(dice.isPlaced()) {
                dicePlaced++;
            }
        }
        return dicePlaced;
    }

    public int pilotDicePlaced() {
        return countDicePlaced(pilot.getDiceList());
    }
    public int copilotDicePlaced() {
        return countDicePlaced(copilot.getDiceList());
    }

    public boolean checkCrash() {
        if (airplane.getAltitude() < 0) {
            System.out.println("Crash: Altitude below safe levels");
            return true;
        }
        return false;
    }

    public boolean checkCrashMove(int moves) {
        if(approachTrack.hasAirplanesAt(airplane.getApproachPosition())){
            return true;
        }
        if(moves == 2){
            if(approachTrack.hasAirplanesAt(airplane.getApproachPosition()+1)){
                return true;
            }
        }
        return false;
    }

    public boolean checkCrashAxis(){
        if (airplane.getAxis().getAxisValue() < -2 || airplane.getAxis().getAxisValue() > 2) {
            return true;
        }
        return false;
    }

    public void checkRoundConditions(){
        if(!pilot.isAxis() || !copilot.isAxis() || !pilot.isThrottle() || !copilot.isThrottle()){
            game.setScreen(new CrashScreen(game));
        }
    }

    public boolean checkWin() {
        if (airplane.getApproachPosition() == 6 // Final position in the approach track
                && airplane.getAltitude() == 0 // Altitude must be 0
                && airplane.getEngine().isPlaneLanded()
                && airplane.getAxis().getAxisValue() == 0 // axisModel must be balanced
                && airplane.getLandingGear().getActivatedLandingGearFields() == 3 // Landing gear engaged
                && airplane.getFlaps().getActivatedFlapFields() == 4) { // Flaps fully engaged
            return true;
        }
        return false;
    }

    public boolean checkEndOfGame() {
        return altitudeTrack.getCurrentAltitude() == 0 && airplane.getApproachPosition() == 6;
    }

    public boolean checkLandingConditions() {
        if (airplane.getLandingGear().getActivatedLandingGearFields() < 3) {
            System.out.println("Landing conditions not met: Landing gear incomplete.");
            return false;
        }
        if (airplane.getFlaps().getActivatedFlapFields() < 4) {
            System.out.println("Landing conditions not met: Landing gear incomplete.");
            return false;
        }
        if (airplane.getAxis().getAxisValue() != 0) {
            System.out.println("Landing conditions not met: axisModel not balanced.");
            return false;
        }
        if (airplane.getAltitude() > 0) {
            System.out.println("Landing conditions not met: Altitude too high.");
            return false;
        }
        return true;
    }

    public void turnChecker(){
        if(pilot.isAxis() && copilot.isAxis() && !axisChanged){
            //airplane.getAxis().changeAxis(pilot,copilot);
            System.out.println("Current axisModel Value: " + airplane.getAxis().getAxisValue());
            axisChanged = true;
        }
        if (pilot.isThrottle() && copilot.isThrottle() && !throttleChanged) {
            if(endOfGame){
                //airplane.getEngine().landPlane(pilot.getThrottle(), copilot.getThrottle());
            }else {
                //airplane.getEngine().movePlane(pilot.getThrottle(), copilot.getThrottle());
                throttleChanged = true;
            }
        }
        if(endOfGame){
            this.checkWin();
        }
    }

    public Dice diceAndCoffee(Scanner input) {
        while(true) {
            System.out.print("Enter dice to play: ");
            int diceValue = input.nextInt();
            if (this.getCurrentPlayer().isDiceThere(diceValue)) {
                while(true) {
                    Dice dice = this.getCurrentPlayer().getDice(diceValue);
                    int availableCoffee = airplane.getConcentration().getCoffeeAvailable();
                    if (availableCoffee > 0) {
                        System.out.println("You have " + availableCoffee + " coffee token" + (availableCoffee == 1 ? "" : "s") + " available");
                        while (true){
                            System.out.println("How many coffee tokens do you want to play? ");
                            int coffeeAmount = input.nextInt();
                            if (coffeeAmount > availableCoffee) {
                                System.out.println("Enter a valid number of coffee tokens.");
                                break;
                            }
                            if (coffeeAmount <= 0){
                                return dice;
                            }
                            System.out.println("Do you want to subtract from dice value(y/n): ");
                            if(input.next().equalsIgnoreCase("y")){
                                coffeeAmount = coffeeAmount * -1;
                            }
                            if(airplane.getConcentration().useCoffee(dice, coffeeAmount)){
                                return dice;
                            }
                        }
                    } else {
                        return dice;
                    }
                }
            } else {
                System.out.println("Enter a valid dice.");
            }
        }
    }

    public boolean brakes(Scanner input, Dice dice){
        int diceValue = dice.getDiceValue();
        airplane.getBrakes().displayBrakeFields();
        System.out.println("Go back to Component Selection");
        System.out.println();
        while (true){
            System.out.print("Enter field to place dice on: ");
            int fieldInput = input.nextInt();
            boolean dicePlaced = false;
            if(fieldInput == 4) return false;
            if(fieldInput > 0 && fieldInput <= 4){
                //dicePlaced = airplane.getBrakes().setBrakeFieldsTrue(fieldInput-1, diceValue);
            } else {
                System.out.println("Enter Valid fieldView.");
            }

            if(dicePlaced){
                pilot.removeDice(dice.getDiceValue());
                return true;
            }
        }
    }

    public boolean gearAndFlaps(Scanner input, Dice dice) {
        int diceValue = dice.getDiceValue();
        Players currentPlayer = this.getCurrentPlayer();
        if(currentPlayer==pilot){
            airplane.getLandingGear().displayFlapFields();
            System.out.println("4. Go back to Component Selection");
            System.out.println();
            while(true){
                System.out.print("Enter field to place dice on: ");
                int fieldInput = input.nextInt();
                boolean dicePlaced = false;
                if(fieldInput == 4) return false;
                if(fieldInput>0 && fieldInput<4){
                    //dicePlaced = airplane.getLandingGear().setLandingGearFieldsTrue(fieldInput - 1, diceValue);
                } else {
                    System.out.println("Enter Valid fieldView.");
                }

                if(dicePlaced) {
                    pilot.removeDice(dice.getDiceValue());
                    return true;
                }
            }

        }
        if(currentPlayer==copilot){
            airplane.getFlaps().displayFlapFields();
            System.out.println("5. Go back to Component Selection");
            System.out.println();
            while(true){
                System.out.print("Enter field to place dice on: ");
                int fieldInput = input.nextInt();
                boolean dicePlaced = false;
                if(fieldInput == 5) return false;
                if(fieldInput>0 && fieldInput<5){
                    //dicePlaced = airplane.getFlaps().setFlapFieldsTrue(fieldInput - 1, diceValue);
                } else {
                    System.out.println("Enter Valid fieldView.");
                }

                if(dicePlaced) {
                    copilot.removeDice(dice.getDiceValue());
                    return true;
                }
            }
        }
        return false;
    }

    public void roundReset(){
        //Reset Radio Slots

        this.getPilot().clearRadio();
        this.getCoPilot().clearRadio();

        //Remove Dice from Landing Gear, Flaps, Brakes

        this.getAirplane().getLandingGear().clearField();
        this.getAirplane().getFlaps().clearField();
        this.getAirplane().getBrakes().clearField();

        axisChanged = false;
        throttleChanged = false;

        //Engine axisModel

        this.getPilot().clearSlots();
        this.getCoPilot().clearSlots();

        //Altitude and Reroll

        this.getAltitudeTrack().descend();
        pilot.rollDice();
        copilot.rollDice();

        if(this.checkEndOfGame()) endOfGame = true;
    }

}





