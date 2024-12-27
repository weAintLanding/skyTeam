package com.sierra.skyTeam.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

public class Dice {
    Sprite[] pilotDice;
    Sprite[] copilotDice;
    private int[] currentPilotDiceValues;
    private int[] currentCoPilotDiceValues;
    private Sprite[] currentPilotDiceSprites;
    private Sprite[] currentCopilotDiceSprites;
    //Sprites are better for onclick events (such as the user clicking on the dice to select it)
    boolean[] isPilotDiceMovable = new boolean[4];
    boolean[] isCopilotDiceMovable = new boolean[4];
    Random random = new Random();

    public Dice() {
        addSprites();
        currentPilotDiceSprites = new Sprite[4];
        currentCopilotDiceSprites = new Sprite[4];
        currentCoPilotDiceValues = new int[4];
        currentPilotDiceValues = new int[4];

    }

    private void addSprites() {
        pilotDice = new Sprite[6];
        copilotDice = new Sprite[6];
        for(int i = 0; i < 6; i++){
            int diceValue = i+1;
            pilotDice[i] = new Sprite(new Texture("bluedice_" + diceValue + ".png"));
            copilotDice[i] = new Sprite(new Texture("orangedice_" + diceValue +".png"));
        }
    }
    public void rollDice() {
        for(int i = 0; i < 4; i++){
            //this is to set the current values of the dice. randomizes and picks a number
            //between 0 and 5. if I get a 0, it will pick the dice with 1 since at index 0 of
            //xyzDice, the xyzdice_1 img is stored.
            int pilotDiceValue = random.nextInt(6);
            currentPilotDiceValues[i] = pilotDiceValue + 1;
            currentPilotDiceSprites[i] = new Sprite(pilotDice[pilotDiceValue]);
            isPilotDiceMovable[i] = true;

            int copilotDiceValue = random.nextInt(6);
            currentCoPilotDiceValues[i] = copilotDiceValue + 1;
            //so it's better to make individual sprites for each or else if we roll the same
            //number, it'll not have any click functionality
            currentCopilotDiceSprites[i] = new Sprite(copilotDice[copilotDiceValue]);
            isCopilotDiceMovable[i] = true;
        }
    }

    public void renderDice(SpriteBatch batch, boolean isPilot) {
        Sprite[] dice = isPilot ? currentPilotDiceSprites : currentCopilotDiceSprites;
        boolean[] diceMovable = isPilot ? isPilotDiceMovable : isCopilotDiceMovable;
        float startX = isPilot ? 86 : 1150;
        float startY = 200;
        for(int i = 0; i < dice.length; i++){
            Sprite sprite = dice[i];
            if(diceMovable[i]){
                sprite.setPosition(startX, startY  + i*75);
            }
            sprite.setSize(50, 50);
            sprite.draw(batch);
        }
    }
//    public boolean isDiceClicked(Sprite[] diceSprites, int[] diceValues, float touchX, float touchY, boolean isPilot){
//        for(int i = 0; i < diceSprites.length; i++){
//            Sprite dice = diceSprites[i];
//            if(dice.getBoundingRectangle().contains(touchX, touchY)) {
//                int diceValue = diceValues[i];
//                if(isPilot){
//                    System.out.println("Pilot dice clicked, value: " + diceValue);
//                }else {
//                    System.out.println("Copilot dice clicked, value: " + diceValue);
//                }
//                return true;
//            }
//        }
//        return false;
//    }

    public int getDiceValue(float touchX, float touchY) {
        for(int i = 0; i < currentPilotDiceSprites.length; i++){
            Sprite sprite = currentPilotDiceSprites[i];
            if(sprite.getBoundingRectangle().contains(touchX, touchY)){
                return currentPilotDiceValues[i];
            }
        }
        for(int i = 0; i < currentCopilotDiceSprites.length; i++){
            Sprite sprite = currentCopilotDiceSprites[i];
            if(sprite.getBoundingRectangle().contains(touchX, touchY)){
                return currentCoPilotDiceValues[i];
            }
        }
        return -1;
    }

//    public int getDiceValueFromSprite(Sprite selectedDice) {
//        for (int i = 0; i < currentPilotDiceSprites.length; i++) {
//            if (selectedDice == currentPilotDiceSprites[i]) {
//                return currentPilotDiceValues[i];
//            }
//        }
//
//        for (int i = 0; i < currentCopilotDiceSprites.length; i++) {
//            if (selectedDice == currentCopilotDiceSprites[i]) {
//                return currentCoPilotDiceValues[i];
//            }
//        }
//
//        return -1;
//    }

    public boolean[] getIsPilotDiceMovable() {
        return isPilotDiceMovable;
    }
    public boolean[] getIsCopilotDiceMovable() {
        return isCopilotDiceMovable;
    }
    public Sprite[] getCurrentPilotDiceSprites(){
        return currentPilotDiceSprites;
    }
    public Sprite[] getCurrentCopilotDiceSprites() {
        return currentCopilotDiceSprites;
    }
    public int[] getCurrentPilotDiceValues(){
        return currentPilotDiceValues;
    }
    public int[] getCurrentCoPilotDiceValues(){
        return currentCoPilotDiceValues;
    }

    public void dispose() {
        for (Sprite sprite : pilotDice){
            sprite.getTexture().dispose();
        }
        for(Sprite sprite : copilotDice){
            sprite.getTexture().dispose();
        }
    }
}
