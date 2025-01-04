package com.sierra.skyTeam.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.sierra.skyTeam.model.Dice;

import java.util.ArrayList;
import java.util.List;

public class CoffeeManager {
    List<CoffeeView> coffees = new ArrayList<>();
    List<FieldView> coffeeFields;
    private Dice[] diceList;
    private int originalValue;
    int nextAvailableSlot = 0;
    public CoffeeManager(List<FieldView> coffeeFields) {
        this.coffeeFields = coffeeFields;
        coffees.add(new CoffeeView(412, 75));
        coffees.add(new CoffeeView(412, 30));
        coffees.add(new CoffeeView(461, 30));
    }

    public void placeCoffee() {
        if(nextAvailableSlot < coffees.size()){
            CoffeeView coffee = coffees.get(nextAvailableSlot);
            nextAvailableSlot++;
            System.out.println(nextAvailableSlot);
        }
    }

    public void selectDice(DiceView dice){

    }

    public void draw(SpriteBatch batch){
        for(int i = 0; i < nextAvailableSlot; i++){
            coffees.get(i).render(batch);
        }
    }
}
