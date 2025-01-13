package com.sierra.skyTeam.view;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public class CoffeeManager {
    List<CoffeeView> coffees = new ArrayList<>();
    //List<CoffeeView> activeCoffees = new ArrayList<>();
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
            for (CoffeeView coffee : coffees) {
                if(!coffee.isAvailable()){
                    coffee.setAvailable(true);
                    break;
                }
            }
            //activeCoffees.add(coffees.get(nextAvailableSlot));
            //nextAvailableSlot = (nextAvailableSlot + 1) % coffees.size();
        }
    }

    public List<CoffeeView> getCoffees() {
        return coffees;
    }

    public void removeCoffee(CoffeeView coffee) {
        //coffees.remove(coffee);
        coffee.setAvailable(false);
    }

    public void draw(SpriteBatch batch){
        for(CoffeeView coffee : coffees){
            if(coffee.isAvailable()){
                coffee.render(batch);
            }
        }
    }
}
