public class Concentration {
    private int coffeeAvailable = 0;

    public int getCoffeeAvailable() {
        return coffeeAvailable;
    }

    public boolean checkCoffeeAvailable() {
        return coffeeAvailable > 0;
    }

    public boolean addCoffee() {
        int maxCoffee = 3;
        if(coffeeAvailable < maxCoffee) {
            coffeeAvailable++;
            System.out.println("Coffee Added. Available: " + coffeeAvailable);
            return true;
        } else {
            System.out.println("Cannot add more Coffee tokens. Maximum Limit reached.");
            return false;
        }
    }

    public boolean useCoffee(Dice dice, int coffeeAmount) {
        int diceValue = dice.getDiceValue();
        int modifiedDiceValue = diceValue + coffeeAmount;

        if(modifiedDiceValue >= 1 && modifiedDiceValue <= 6) {
            coffeeAvailable -= Math.abs(coffeeAmount);
            System.out.println("Dice value modified to: " + modifiedDiceValue);
            dice.setDiceValue(modifiedDiceValue);
            return true;
        } else {
            System.out.println("Invalid dice value after using coffee. Use less Coffee.");
            return false;
        }
    }
}
