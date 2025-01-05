package com.sierra.skyTeam.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CoffeeView {
    Sprite coffee;
    Texture coffeeTexture;
    boolean used;
    public CoffeeView(float x, float y) {
        coffeeTexture = new Texture("coffee.png");
        coffee = new Sprite(coffeeTexture);
        coffee.setPosition(x, y);
        coffee.setScale(0.37F);
        this.used = false;
    }

    public Sprite getSprite(){
        return coffee;
    }

    public void render(SpriteBatch batch) {
        if(!used) {
            coffee.draw(batch);
        }
    }

    public void dispose() {
        if(coffeeTexture != null){
            coffeeTexture.dispose();
        }
    }
}
