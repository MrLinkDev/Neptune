package ru.linkstuff.neptune.OpenGL;

import ru.linkstuff.neptune.Framework.Game;
import ru.linkstuff.neptune.Framework.Scene;

public class GLScene extends Scene {
    protected final GLGraphics glGraphics;
    protected final GLActivity glActivity;

    protected final int width;
    protected final int height;

    public GLScene(Game game) {
        super(game);

        glActivity = (GLActivity)game;
        glGraphics = glActivity.getGlGraphics();

        width = glGraphics.getWidth();
        height = glGraphics.getHeight();
    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void present(float deltaTime) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
