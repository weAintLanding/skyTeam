package com.sierra.skyTeam.model;

import java.util.Scanner;

public class tempGame {
    GameModel gameModel;
    private Airplane airplane;
    private Pilot pilot;
    private CoPilot copilot;
    boolean axisChanged = false;
    boolean throttleChanged = false;
    boolean endOfGame = false;

    public void startGame(){
        //gameModel = new GameModel();
        printOptions(gameModel);
    }

    public void printOptions(GameModel gameModel){
        pilot = gameModel.getPilot();
        copilot = gameModel.getCoPilot();
        airplane = gameModel.getAirplane();
        roundReset(gameModel);
        Scanner input = new Scanner(System.in);
        int turns = 0;
        while(true) {
            if(turns == 8){
                System.out.println("Round Over.");
                gameModel.checkRoundConditions();
                roundReset(gameModel);
                turns = 0;
            }
            Players currentPlayer = gameModel.getCurrentPlayer();
            System.out.println("Current Player: "+currentPlayer.getClass().getName());
            System.out.println(
                "1. axisModel\n"
                +"2. Engine\n"
                +"3. Radio\n"
                +"4. Coffee\n"
                +"5. "+((currentPlayer==pilot) ? "Landing gear\n" : "Flaps\n")
                +"6. Re-roll Dice\n"
                +((currentPlayer==pilot) ? "7. Brakes":"")
            );
            System.out.println("Your current available dice: " + currentPlayer.getDiceRollsString());
            while (true){
                System.out.print("Enter number for field: ");
                int UsrInput;
                UsrInput = input.nextInt();
                Dice dice;
                boolean validInput = true;

                switch(UsrInput){
                    case 1:
                        dice = diceAndCoffee(gameModel, input);
                        if(currentPlayer.setAxis(dice)){
                            turns++;
                            break;
                        }
                        validInput = false;
                        break;
                    case 2:
                        dice = diceAndCoffee(gameModel, input);
                        if(currentPlayer.setThrottle(dice)){
                            turns++;
                            break;
                        }
                        validInput = false;
                        break;
                    case 3:
                        //dice = diceAndCoffee(gameModel, input);
                        //if(currentPlayer.setRadio(dice)){
                        //    turns++;
                        //    break;
                        //}
                        validInput = false;
                        break;
                    case 4:
                        dice = diceAndCoffee(gameModel, input);
                        if(currentPlayer.setCoffee(dice)){
                            turns++;
                            break;
                        }
                        validInput = false;
                        break;
                    case 5:
                        dice = diceAndCoffee(gameModel, input);
                        if(gearAndFlaps(gameModel,input,dice)){
                            turns++;
                            break;
                        }
                        validInput = false;
                        break;
                    case 6:
                        currentPlayer.reroll();
                        validInput = false;
                        System.out.println("Your current available dice: " + currentPlayer.getDiceRollsString());
                        break;
                    case 7:
                        dice = diceAndCoffee(gameModel, input);
                        if(currentPlayer==copilot){
                            validInput = false;
                            break;
                        }
                        if(brakes(input,dice)){
                            turns++;
                            break;
                        }
                        validInput = false;
                        break;
                    default:
                        System.out.println("Enter valid field.");
                        validInput = false;
                }

                if(validInput){
                    turnChecker();
                    gameModel.switchPlayer();
                    System.out.println();
                    break;
                }
            }
        }
    }

    private void turnChecker(){
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
            gameModel.checkWin();
        }
    }

    private Dice diceAndCoffee(GameModel gameModel, Scanner input) {
        while(true) {
            System.out.print("Enter dice to play: ");
            int diceValue = input.nextInt();
            if (gameModel.getCurrentPlayer().isDiceThere(diceValue)) {
                while(true) {
                    Dice dice = gameModel.getCurrentPlayer().getDice(diceValue);
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

    private boolean brakes(Scanner input, Dice dice){
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

    private boolean gearAndFlaps(GameModel gameModel, Scanner input, Dice dice) {
        int diceValue = dice.getDiceValue();
        Players currentPlayer = gameModel.getCurrentPlayer();
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

    private void roundReset(GameModel gameModel){
        //Reset Radio Slots

        gameModel.getPilot().clearRadio();
        gameModel.getCoPilot().clearRadio();

        //Remove Dice from Landing Gear, Flaps, Brakes

        gameModel.getAirplane().getLandingGear().clearField();
        gameModel.getAirplane().getFlaps().clearField();
        gameModel.getAirplane().getBrakes().clearField();

        axisChanged = false;
        throttleChanged = false;

        //Engine axisModel

        gameModel.getPilot().clearSlots();
        gameModel.getCoPilot().clearSlots();

        //Altitude and Reroll

        gameModel.getAltitudeTrack().descend();
        pilot.rollDice();
        copilot.rollDice();

        if(gameModel.checkEndOfGame()) endOfGame = true;
    }
}
