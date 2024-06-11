package com.esark.roboticarm;

import android.content.Context;

import com.esark.framework.Game;
import com.esark.framework.Graphics;
import com.esark.framework.Screen;
import com.esark.framework.Graphics.PixmapFormat;

public class LoadingScreen extends Screen {
    public LoadingScreen(Game game) {
        super(game);
    }
    public GameScreen gameScreen = new GameScreen(game);
    public void update(float deltaTime, Context context) {
        Graphics g = game.getGraphics();
        Assets.robotPortraitBackground = g.newPixmap("robotPortraitBackground.png", PixmapFormat.ARGB4444);
        Assets.whiteSphere = g.newPixmap("wsphere.png", PixmapFormat.ARGB4444);
        game.setScreen(gameScreen);
    }
    public void present(float deltaTime) {

    }

    public void pause() {

    }

    public void resume() {

    }

    public void dispose() {

    }
}