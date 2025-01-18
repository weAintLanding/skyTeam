package com.sierra.skyTeam.screens;

import com.badlogic.gdx.Screen;
import com.sierra.skyTeam.MainGame;

public interface ScreenFactory {
    Screen createCrashScreen(MainGame game);
}