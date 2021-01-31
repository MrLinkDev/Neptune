package ru.linkstuff.neptune.OpenGL;

import android.opengl.GLES20;

import ru.linkstuff.neptune.Framework.Game;
import ru.linkstuff.neptune.Framework.Scene;

public class GLScene extends Scene {
    protected final GLGraphics glGraphics;
    protected final GLActivity glActivity;

    protected final int width;
    protected final int height;

    protected final Camera camera;

    public GLScene(Game game) {
        super(game);

        glActivity = (GLActivity)game;
        glGraphics = glActivity.getGlGraphics();

        width = glGraphics.getWidth();
        height = glGraphics.getHeight();

        camera = new Camera(width, height);
    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void present(float deltaTime) {
        GLES20.glClearColor(0f, 0f, 0f, 1);
        camera.setCamera();
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
