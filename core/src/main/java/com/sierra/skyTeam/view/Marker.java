package com.sierra.skyTeam.view;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Marker {
    private int value;
    Sprite markerSprite;
    float x, y, rotation;

    public Marker (Sprite markerSprite, int initialValue, float x, float y, float rotation){
        this.markerSprite = markerSprite;
        this.value = initialValue;
        this.x = x;
        this.y = y;
        this.rotation = rotation;
        this.markerSprite.setPosition(x, y);
        this.markerSprite.setRotation(rotation);
        this.markerSprite.setScale(0.7F);
    }

    public void landingUpdate (int value){
        switch(value){
            case 5:
                markerSprite.setPosition(x + 33, y - 19);
                markerSprite.setRotation(rotation + 12);
                break;
            case 6:
                markerSprite.setPosition(x + 69, y - 30);
                markerSprite.setRotation(rotation + 30);
                break;
            case 7:
                markerSprite.setPosition(x + 108, y - 30);
                markerSprite.setRotation(rotation + 45);
                break;
            default:
                System.out.println("Invalid Value");
        }
    }

    public void flapsUpdate (int value){
        switch(value){
            case 9:
                markerSprite.setPosition(x + 33, y + 21);
                markerSprite.setRotation(rotation + 18);
                break;
            case 10:
                markerSprite.setPosition(x + 58, y + 51);
                markerSprite.setRotation(rotation + 33);
                break;
            case 11:
                markerSprite.setPosition(x + 74, y + 85);
                markerSprite.setRotation(rotation + 45);
                break;
            case 12:
                markerSprite.setPosition(x + 80, y + 123);
                markerSprite.setRotation(rotation + 64);
                break;
            default:
                System.out.println("Invalid Value");
        }
    }

    public void brakesUpdate(int value){
        switch(value){
            case 3:
                markerSprite.setPosition(x + 33, y - 20);
                markerSprite.setRotation(rotation + 18);
                break;
            case 4:
                markerSprite.setPosition(x + 108, y - 30);
                markerSprite.setRotation(rotation + 50);
                break;
            case 6:
                markerSprite.setPosition(x + 176, y + 4);
                markerSprite.setRotation(rotation + 85);
                break;
            default:
                System.out.println("Invalid Value");
                //no 5 or 6, idk. the board is stupid.
        }
    }

    public void draw(SpriteBatch batch){
        markerSprite.draw(batch);
    }

    public void setValue(int value) {
        this.value = value;
    }
}
