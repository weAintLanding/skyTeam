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
        gameModel.roundReset();
        Scanner input = new Scanner(System.in);
        int turns = 0;
        while(true) {
            if(turns == 8){
                System.out.println("Round Over.");
                gameModel.checkRoundConditions();
                gameModel.roundReset();
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
                        dice = gameModel.diceAndCoffee(input);
                        if(currentPlayer.setAxis(dice)){
                            turns++;
                            break;
                        }
                        validInput = false;
                        break;
                    case 2:
                        dice = gameModel.diceAndCoffee(input);
                        if(currentPlayer.setThrottle(dice)){
                            turns++;
                            break;
                        }
                        validInput = false;
                        break;
                    case 3:
                        /*dice = diceAndCoffee(gameModel, input);
                        if(currentPlayer.setRadio(dice)){
                            turns++;
                            break;
                        }*/
                        validInput = false;
                        break;
                    case 4:
                        dice = gameModel.diceAndCoffee(input);
                        if(currentPlayer.setCoffee(dice)){
                            turns++;
                            break;
                        }
                        validInput = false;
                        break;
                    case 5:
                        dice = gameModel.diceAndCoffee(input);
                        if(gameModel.gearAndFlaps(input,dice)){
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
                        dice = gameModel.diceAndCoffee(input);
                        if(currentPlayer==copilot){
                            validInput = false;
                            break;
                        }
                        if(gameModel.brakes(input,dice)){
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
                    gameModel.turnChecker();
                    gameModel.switchPlayer();
                    System.out.println();
                    break;
                }
            }
        }
    }
}
