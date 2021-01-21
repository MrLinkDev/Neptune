package ru.linkstuff.neptunedemo;

import android.opengl.GLES20;

import java.util.List;

import ru.linkstuff.neptune.Framework.Game;
import ru.linkstuff.neptune.Framework.Input;
import ru.linkstuff.neptune.OpenGL.Camera;
import ru.linkstuff.neptune.OpenGL.Debug.FPS;
import ru.linkstuff.neptune.OpenGL.Debug.Grid;
import ru.linkstuff.neptune.OpenGL.GLScene;

public class SceneMenu extends GLScene {
    Camera camera;
    Grid grid;
    FPS fps;

    boolean pressed = false;

    public SceneMenu(Game game) {
        super(game);

        camera = new Camera(width, height);
        grid = new Grid(0, 0, width, height, 9, 16, glActivity);

        fps = new FPS(width, height, FPS.POSITION_TOP_LEFT, false, glActivity);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        for (Input.TouchEvent event : touchEvents){
            if (event.type == Input.TouchEvent.TOUCH_DOWN){
                pressed = true;
                //ProgramManager.program(programIndex).use();
            }

            if (event.type == Input.TouchEvent.TOUCH_UP){
                //ProgramManager.defaultTextureProgram().use();
                pressed = false;
            }
        }
    }

    @Override
    public void present(float deltaTime) {
        GLES20.glClearColor(0f, 0f, 0f, 1);

        camera.setCamera();

        grid.draw();

        fps.draw(deltaTime);
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
