package ru.linkstuff.neptunedemo;

import android.opengl.GLES20;

import java.util.List;

import ru.linkstuff.neptune.Framework.Game;
import ru.linkstuff.neptune.Framework.Input;
import ru.linkstuff.neptune.OpenGL.Camera;
import ru.linkstuff.neptune.OpenGL.Debug.Grid;
import ru.linkstuff.neptune.OpenGL.GLScene;
import ru.linkstuff.neptune.OpenGL.ProgramManager;
import ru.linkstuff.neptune.OpenGL.Shader;

public class SceneMenu extends GLScene {
    Camera camera;
    Grid grid;

    final int programIndex;

    public SceneMenu(Game game) {
        super(game);

        camera = new Camera(width, height);
        grid = new Grid(0, 0, width, height, 9, 16, glActivity);

        programIndex = ProgramManager.createProgram(glActivity.getApplicationContext(), Shader.DEFAULT_VERTEX_TEXTURE_SHADER, R.raw.fragment_all_purple_shader);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        for (Input.TouchEvent event : touchEvents){
            if (event.type == Input.TouchEvent.TOUCH_DOWN){
                ProgramManager.program(programIndex).use();
            }

            if (event.type == Input.TouchEvent.TOUCH_UP){
                ProgramManager.defaultTextureProgram().use();
            }
        }
    }

    @Override
    public void present(float deltaTime) {
        GLES20.glClearColor(0f, 0f, 0f, 1);

        camera.setCamera();

        grid.draw();
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
