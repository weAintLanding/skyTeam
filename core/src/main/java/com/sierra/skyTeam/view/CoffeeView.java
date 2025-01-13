package com.sierra.skyTeam.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CoffeeView {
    Sprite coffee;
    Texture coffeeTexture;
    private boolean isAvailable;
    public CoffeeView(float x, float y) {
        coffeeTexture = new Texture("coffee.png");
        coffee = new Sprite(coffeeTexture);
        coffee.setPosition(x, y);
        coffee.setScale(0.37F);
        this.isAvailable = false;
    }

    public Sprite getSprite(){
        return coffee;
    }

    public void render(SpriteBatch batch) {
        coffee.draw(batch);
    }

    public boolean isAvailable() {
        return this.isAvailable;
    }
    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }
}
