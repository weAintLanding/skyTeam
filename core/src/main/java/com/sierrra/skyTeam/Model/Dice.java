import java.util.Random;

public class Dice {
    private int diceValue;
    Random random = new Random();

    public Dice() {
        this.diceValue = random.nextInt(6)+1;
    }

    public int getDiceValue() {
        return diceValue;
    }

    void setDiceValue(int newDiceValue){
        this.diceValue = newDiceValue;
    }
}
