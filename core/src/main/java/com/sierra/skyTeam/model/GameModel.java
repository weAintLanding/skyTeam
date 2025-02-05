package com.sierra.skyTeam.model;

import com.sierra.skyTeam.MainGame;
import com.sierra.skyTeam.screens.CrashScreen;

import java.util.Scanner;

/**
 * Die {@code GameModel}-Klasse verwaltet die Spiellogik und den Zustand des Spiels.
 * Sie stellt sicher, dass alle Spielfunktionen korrekt ausgeführt werden,
 * einschließlich der Verwaltung von Spielern, Würfeln, Flugzeugen und verschiedenen Bedingungen im Spiel.
 */
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

    /**
     * Konstruktor für die {@code GameModel}-Klasse.
     * Initialisiert das Spielmodell und setzt die Standardwerte für Flugzeug, Piloten, Co-Piloten, Wiederholungen und Tracks.
     *
     * @param game Das MainGame-Objekt, das das Spiel steuert.
     */
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

    /**
     * Gibt den aktuellen Piloten zurück.
     *
     * @return Der Pilot.
     */
    public Pilot getPilot() {
        return pilot;
    }
    /**
     * Gibt den aktuellen Co-Piloten zurück.
     *
     * @return Der Co-Pilot.
     */
    public CoPilot getCoPilot() {
        return copilot;
    }
    /**
     * Gibt das Flugzeug zurück.
     *
     * @return Das Airplane.
     */
    public Airplane getAirplane() {
        return airplane;
    }

    /**
     * Gibt den aktuellen Spieler zurück.
     *
     * @return Der aktuelle Spieler.
     */
    public Players getCurrentPlayer() {
        return currentPlayer;
    }
    /**
     * Wechselt den aktuellen Spieler (Pilot oder Co-Pilot).
     */
    public void switchPlayer() {
        currentPlayer = (currentPlayer == pilot) ? copilot : pilot;
    }

    /**
     * Gibt die ApproachTrack zurück.
     *
     * @return Die ApproachTrack.
     */
    public ApproachTrack getApproachTrack() {
        return approachTrack;
    }
    /**
     * Gibt die AltitudeTrack zurück.
     *
     * @return Die AltitudeTrack.
     */
    public AltitudeTrack getAltitudeTrack() {
        return altitudeTrack;
    }

    /**
     * Erhöht die Anzahl der Reroll-Token um eins.
     */
    public void setRerollsAvailable() {
        this.rerollsAvailable++;
    }
    /**
     * Verringert die Anzahl der Reroll-Token um eins, wenn Token verfügbar sind.
     */
    public void decreaseRerollsAvailable() {
        if (rerollsAvailable > 0) {
            rerollsAvailable--;
        }
    }
    /**
     * Gibt die Anzahl der verfügbaren Rerolls zurück.
     *
     * @return Die Anzahl der verfügbaren Rerolls.
     */
    public int getRerollsAvailable() {
        return rerollsAvailable;
    }

    /**
     * Zählt die Anzahl der platzierten Würfel in einer Liste von Würfeln.
     *
     * @param diceList Die Liste von Würfeln.
     * @return Die Anzahl der platzierten Würfel.
     */
    public int countDicePlaced(Dice[] diceList){
        int dicePlaced = 0;
        for (Dice dice : diceList) {
            if(dice.isPlaced()) {
                dicePlaced++;
            }
        }
        return dicePlaced;
    }
    /**
     * Gibt die Anzahl der vom Piloten platzierten Würfel zurück.
     *
     * @return Die Anzahl der vom Piloten platzierten Würfel.
     */
    public int pilotDicePlaced() {
        return countDicePlaced(pilot.getDiceList());
    }
    /**
     * Gibt die Anzahl der vom Co-Piloten platzierten Würfel zurück.
     *
     * @return Die Anzahl der vom Co-Piloten platzierten Würfel.
     */
    public int copilotDicePlaced() {
        return countDicePlaced(copilot.getDiceList());
    }

    /**
     * Überprüft, ob ein Absturz vorliegt (z.B. wenn die Flughöhe unter null liegt).
     *
     * @return {@code true} wenn ein Absturz vorliegt, sonst {@code false}.
     */
    public boolean checkCrash() {
        if (airplane.getAltitude() < 0) {
            System.out.println("Crash: Altitude below safe levels");
            return true;
        }
        return false;
    }

    /**
     * Überprüft, ob ein Absturz beim Bewegen des Flugzeugs vorliegt.
     *
     * @param moves Die Anzahl der Bewegungen.
     * @return {@code true} wenn ein Absturz vorliegt, sonst {@code false}.
     */
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

    /**
     * Überprüft, ob der Flugzeug-AxisWert außerhalb des sicheren Bereichs liegt.
     *
     * @return {@code true} wenn die Axis außerhalb des sicheren Bereichs liegt, sonst {@code false}.
     */
    public boolean checkCrashAxis(){
        if (airplane.getAxis().getAxisValue() < -2 || airplane.getAxis().getAxisValue() > 2) {
            return true;
        }
        return false;
    }

    /**
     * Überprüft, ob alle Bedingungen für die Runde erfüllt sind und wechselt
     * zum Absturzbildschirm, wenn dies nicht der Fall ist.
     */
    public void checkRoundConditions(){
        if(!pilot.isAxis() || !copilot.isAxis() || !pilot.isThrottle() || !copilot.isThrottle()){
            game.setScreen(new CrashScreen(game));
        }
    }

    /**
     * Überprüft, ob der Spieler gewonnen hat, basierend auf den Landebedingungen.
     *
     * @return {@code true} wenn die Landebedingungen erfüllt sind, sonst {@code false}.
     */
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

    /**
     * Setzt alle Spielkomponenten zurück und bereitet das Spiel für die nächste Runde vor.
     * Dies umfasst das Zurücksetzen der Radios, das Entfernen von Würfeln von den Landeklappen-, Fahrwerks- und Bremsfeldern,
     * das Zurücksetzen von Axis und Throttle, das Rollen von Würfeln für Pilot und Co-Pilot sowie das Überprüfen des Spielstatus.
     */
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

    //Dies sind Funktionen, die für die Nicht-GUI-Version des Spiels (Terminal-Version) verwendet werden.

    /**
     * Überprüft, ob das Spiel zu Ende ist, basierend auf der Flughöhe und der Position des Flugzeugs.
     *
     * @return {@code true} wenn das Spiel zu Ende ist, sonst {@code false}.
     */
    public boolean checkEndOfGame() {
        return altitudeTrack.getCurrentAltitude() == 0 && airplane.getApproachPosition() == 6;
    }

    /**
     * Überprüft die Landebedingungen des Flugzeugs.
     *
     * @return {@code true} wenn alle Landebedingungen erfüllt sind, sonst {@code false}.
     */
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

    /**
     * Führt eine Rundenprüfung durch, um sicherzustellen, dass alle Aktionen der Spieler korrekt ausgeführt wurden.
     */
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

    /**
     * Verwaltet die Interaktion des Spielers mit den Würfeln und Kaffeetokens.
     *
     * @param input Das Scanner-Objekt für die Eingabe des Spielers.
     * @return Der Würfel, der vom Spieler gewählt wurde.
     */
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

    /**
     * Verarbeitet die Brakes-komponente des Spiels, indem ein Würfel auf eines der verfügbaren Felder gesetzt wird.
     * Der Spieler wählt das Feld, auf dem er den Würfel platzieren möchte.
     *
     * @param input Das Scanner-Objekt für die Eingabe des Spielers.
     * @param dice Das Würfeln, das auf ein Feld gesetzt wird.
     * @return {@code true}, wenn der Würfel erfolgreich platziert wurde, andernfalls {@code false}.
     */
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

    /**
     * Verarbeitet die Klappen- und Fahrwerkskomponenten des Spiels.
     * Der Spieler wählt ein Feld für das Platzieren des Würfels auf den Klappen oder das Fahrwerk, je nachdem, ob er Pilot oder Co-Pilot ist.
     *
     * @param input Das Scanner-Objekt für die Eingabe des Spielers.
     * @param dice Das Würfeln, das auf ein Feld gesetzt wird.
     * @return {@code true}, wenn der Würfel erfolgreich platziert wurde, andernfalls {@code false}.
     */
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
}





