import java.util.ArrayList;
import java.util.Random;
import java.util.List;

public class Players {
    private final Game game;
    private final List<Dice> diceRolls;
    private Integer axisSlot = null;
    private Integer throttle = null;
    //private int radioSlots;
    //private Integer radio = null;
    //private static final int maxRadioSlots = 2;
    protected int radioSlots;
    protected Field[] radioPlayer;
/*    private static int coffee = 0;
    private static final int maxCoffee = 3;*/
    Random random = new Random();


    public Players(Game game) {
        this.game = game;
        diceRolls = new ArrayList<>();
    }

    public Airplane getAirplane(){
        return game.getAirplane();
    }

    public void rollDice () {
        for(int i = 0; i < 4; i++){
            diceRolls.add(new Dice());
        }
    }
    public String getDiceRolls () {
        List<Integer> diceArray = new ArrayList<>();
        for(int i = 0; i < diceRolls.size(); i++){
            diceArray.add(diceRolls.get(i).getDiceValue());
        }
        return diceArray.toString();
    }
    public boolean isDiceThere (int diceValue){
        for(Dice dice : diceRolls){
            if(dice.getDiceValue() == diceValue) return true;
        }
        return false;
    }
    public Dice getDice (int diceValue){
        for(Dice dice : diceRolls){
            if(dice.getDiceValue() == diceValue){
                return dice;
            }
        }
        return null;
    }
    public void removeDice (int diceValue){
        for (Dice dice : diceRolls){
            if (dice.getDiceValue() == diceValue){
                diceRolls.remove(dice);
                return;
            }
        }
    }
    public void reroll() {
        if (game.getRerollsAvailable() > 0){
            for (int i = 0; i < diceRolls.size(); i++) {
                Dice dice = diceRolls.get(i);
                int newValue = random.nextInt(6)+1;
                dice.setDiceValue(newValue);
            }
            game.decreaseRerollsAvailable();
            System.out.println("Dice Rerolled. Remaining rerolls: " + game.getRerollsAvailable());
        } else {
            System.out.println("No rerolls available.");
        }
    }

    public boolean setAxis(Dice dice){
        int diceValue = dice.getDiceValue();
        if (axisSlot != null) {
            System.out.println("Axis slot is already occupied");
            return false;
        }
        this.axisSlot = diceValue;
        removeDice(diceValue);
        System.out.println(this.getClass().getSimpleName() + " placed " + diceValue + " on their Axis slot");
        return true;
    }
    public Integer getAxis() {
        return axisSlot;
    }
    public boolean isAxis(){
        return axisSlot != null;
    }

    public boolean setThrottle(Dice dice){
        int diceValue = dice.getDiceValue();
        if (throttle != null) {
            System.out.println("Throttle slot is already occupied.");
            return false;
        }
        this.throttle = diceValue;
        removeDice(diceValue);
        System.out.println(this.getClass().getSimpleName() + " placed " + diceValue + " on their Throttle slot.");
        return true;
    }
    public Integer getThrottle() {
        return throttle;
    }
    public boolean isThrottle(){
        return throttle != null;
    }

    /*public void setRadio (int diceValue){
        if (radioSlots < maxRadioSlots){
            if (isDiceThere(diceValue)){
                radioSlots++;
                radio = diceValue;
                removeDice(diceValue);
                System.out.println(this.getClass().getSimpleName() + " placed " + diceValue + " on their Radio slot");
            }else System.out.println("No such dice exists for the player");
        }else System.out.println(this.getClass().getSimpleName() + " cannot place more dice in the Radio slot");
    }
    public int getRadioSlots(){
        return radioSlots;
    }
    public void getRadio() {
        if (radio != null) System.out.println("Radio for " + this.getClass().getSimpleName() + " is: " + radio);
        else System.out.println("Radio for " + this.getClass().getSimpleName() + " is: empty");
    }*/

    public boolean setRadio(Dice dice){
        int diceValue = dice.getDiceValue();
        for(Field field: radioPlayer){
            if(!field.isOccupied()){
                field.placeDice(diceValue);
                removeDice(diceValue);
                game.getApproachTrack().removeAirplaneWithRadio(this.getAirplane().getApproachPosition(), diceValue);
                return true;
            }
        }
        System.out.println("No more radios available.");
        return false;
    }
    public void clearRadio(){
        for(Field field: radioPlayer){
            field.resetField();
        }
    }

    public boolean setCoffee (Dice dice){
        boolean coffeeAdded = this.getAirplane().getConcentration().addCoffee();
        this.removeDice(dice.getDiceValue());
        return coffeeAdded;
    }
/*    public void useCoffee (int diceValue, int coffeeValue){
        int coffeeNeeded = Math.abs(coffeeValue);
        if (coffeeValue == 0) {
            System.out.println("Invalid: Coffee can not be 0");
            return;
        }

        if (coffee < coffeeNeeded){
            System.out.println("Invalid: Not enough coffees available");
            return;
        }

        if(!isDiceThere(diceValue)){
            System.out.println("Invalid: No such dice exists for the player");
            return;
        }

        for(Dice dice : diceRolls){
            if(dice.getDiceValue() == diceValue) {
                int index = diceRolls.indexOf(dice);
                int adjustedValue = diceValue + coffeeValue;
                if (adjustedValue >= 0 && adjustedValue <= 6) {
                    dice.setDiceValue(adjustedValue);
                    coffee -= coffeeNeeded;
                    return;
                } else System.out.println("Invalid number: Out of bounds");
            }
        }
    }
    public void getCoffee() {
        System.out.println("Total no. of Coffees: " + coffee);
    }*/

    public void clearSlots(){
        this.axisSlot = null;
        this.throttle = null;
    }
}

