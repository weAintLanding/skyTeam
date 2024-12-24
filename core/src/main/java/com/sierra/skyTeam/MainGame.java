package com.sierra.skyTeam;

import com.badlogic.gdx.Game;
import com.sierra.skyTeam.screens.StartScreen;

public class MainGame extends Game {
    @Override
    public void create() {
        setScreen(new StartScreen(this));
    }

    @Override
    public void render(){
        super.render();
    }

    @Override
    public void dispose(){
        super.dispose();
    }

}
