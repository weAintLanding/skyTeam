package com.sierra.skyTeam.view;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

/**
 * Die Klasse {@code CoffeeManager} verwaltet die verfügbaren Kaffee-Objekte
 * und ihre Platzierung in definierten Feldern.
 */
public class CoffeeManager {
    List<CoffeeView> coffees = new ArrayList<>();
    List<FieldView> coffeeFields;
    int nextAvailableSlot = 0;

    /**
     * Konstruktor für {@code CoffeeManager}. Initialisiert die Kaffee-Objekte
     * und die Felder, in denen die Kaffees platziert werden können.
     *
     * @param coffeeFields Die Liste der Felder, in denen Kaffee platziert werden kann.
     */
    public CoffeeManager(List<FieldView> coffeeFields) {
        this.coffeeFields = coffeeFields;
        coffees.add(new CoffeeView(412, 75));
        coffees.add(new CoffeeView(412, 30));
        coffees.add(new CoffeeView(461, 30));
    }

    /**
    * Platziert ein verfügbares Kaffee-Objekt, falls ein freier Slot vorhanden ist.
    */
    public void placeCoffee() {
        if(nextAvailableSlot < coffees.size()) {
            for (CoffeeView coffee : coffees) {
                if (!coffee.isAvailable()) {
                    coffee.setAvailable(true);
                    break;
                }
            }
        }
    }

    /**
     * Gibt die Liste der verfügbaren Kaffee-Objekte zurück.
     *
     * @return Eine Liste der {@code CoffeeView}-Objekte.
     */
    public List<CoffeeView> getCoffees() {
        return coffees;
    }

    /**
     * Entfernt ein Kaffee-Objekt aus der verfügbaren Liste.
     *
     * @param coffee Das {@code CoffeeView}-Objekt, das entfernt werden soll.
     */
    public void removeCoffee(CoffeeView coffee) {
        coffee.setAvailable(false);
    }

    /**
     * Zeichnet alle verfügbaren Kaffee-Objekte mit dem angegebenen {@code SpriteBatch}.
     *
     * @param batch Der {@code SpriteBatch}, der zum Rendern verwendet wird.
     */
    public void draw(SpriteBatch batch){
        for(CoffeeView coffee : coffees){
            if(coffee.isAvailable()){
                coffee.render(batch);
            }
        }
    }
}
