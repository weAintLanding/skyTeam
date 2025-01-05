package com.sierra.skyTeam.view;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public class CoffeeManager {
    List<CoffeeView> coffees = new ArrayList<>();
    List<FieldView> coffeeFields;
    int nextAvailableSlot = 0;
    public CoffeeManager(List<FieldView> coffeeFields) {
        this.coffeeFields = coffeeFields;
        coffees.add(new CoffeeView(412, 75));
        coffees.add(new CoffeeView(412, 30));
        coffees.add(new CoffeeView(461, 30));
    }

    public void placeCoffee() {
        if(nextAvailableSlot < coffees.size()){
            nextAvailableSlot++;
        }
    }

    public List<CoffeeView> getActiveCoffees() {
        List<CoffeeView> activeCoffees = new ArrayList<>();
        for (int i = 0; i < nextAvailableSlot; i++) {
            activeCoffees.add(coffees.get(i));
        }
        return activeCoffees;
    }

    public void draw(SpriteBatch batch){
        for(int i = 0; i < nextAvailableSlot; i++){
            coffees.get(i).render(batch);
        }
    }
}
