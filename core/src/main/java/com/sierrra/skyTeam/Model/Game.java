public class Game {
    private final Airplane airplane;
    private final Pilot pilot;
    private final CoPilot copilot;
    //private int roundNumber;
    private int rerollsAvailable;
    //private int startingPlayer;
    private final ApproachTrack approachTrack;
    private final AltitudeTrack altitudeTrack;
    private Players currentPlayer;
    //private final int maxRounds = 5;

    public Game() {
        this.airplane = new Airplane();
        this.pilot = new Pilot(this);
        this.copilot = new CoPilot(this);
        //this.roundNumber = 1;
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
        System.out.println("Next turn.");
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

    public boolean checkCrash() {
        if (airplane.getAltitude() < 0) {
            System.out.println("Crash: Altitude below safe levels");
            return true;
        }
        /*int currentPosition = airplane.getApproachPosition();
        if (approachTrack.hasAirplanesAt(currentPosition)) {
            System.out.println("Crash: Collision detected at position " + currentPosition + ".");
            return true;
        }*/
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
            System.out.println("Crash: Axis out of balance");
            return true;
        }
        return false;
    }

    public boolean checkWin() {
        if (airplane.getApproachPosition() == 6 // Final position in the approach track
                && airplane.getAltitude() == 0 // Altitude must be 0
                && airplane.getAxis().getAxisValue() == 0 // Axis must be balanced
                && airplane.getLandingGear().getActivatedLandingGearFields() == 3 // Landing gear engaged
                && airplane.getBrakes().getActivatedBrakeFields() == 3 // Brakes engaged
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
            System.out.println("Landing conditions not met: Axis not balanced.");
            return false;
        }
        if (airplane.getAltitude() > 0) {
            System.out.println("Landing conditions not met: Altitude too high.");
            return false;
        }
        return true;
    }

    public void checkRoundConditions(){
        if(!pilot.isAxis() || !copilot.isAxis() || !pilot.isThrottle() || !copilot.isThrottle()){
            System.out.println("Round conditions not met. Plane Crashed.");
            System.exit(0);
        }
    }


}





