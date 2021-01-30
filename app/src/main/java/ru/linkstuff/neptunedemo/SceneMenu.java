package ru.linkstuff.neptunedemo;

import android.opengl.GLES20;

import java.util.List;

import ru.linkstuff.neptune.Framework.Game;
import ru.linkstuff.neptune.Framework.Input;
import ru.linkstuff.neptune.Neptune;
import ru.linkstuff.neptune.OpenGL.Camera;
import ru.linkstuff.neptune.OpenGL.Debug.FPS;
import ru.linkstuff.neptune.OpenGL.Debug.Grid;
import ru.linkstuff.neptune.OpenGL.GLScene;
import ru.linkstuff.neptune.UI.Utils.Metric;
import ru.linkstuff.neptune.UI.Widgets.Image;

import static ru.linkstuff.neptune.UI.Utils.Metric.unit;

public class SceneMenu extends GLScene {
    Camera camera;
    Grid grid;
    FPS fps;

    Image image;

    boolean pressed = false;

    public SceneMenu(Game game) {
        super(game);

        Metric.setUnitSize(18, 32, width, height);

        camera = new Camera(width, height);
        grid = new Grid(width, height);

        fps = new FPS(width, height, Neptune.FPS_POSITION_TOP_LEFT, false);

        image = new Image(0, 0, unit * 8, unit * 2);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        for (Input.TouchEvent event : touchEvents){
            if (event.type == Input.TouchEvent.TOUCH_DOWN){
                pressed = true;
            }

            if (event.type == Input.TouchEvent.TOUCH_UP){
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
        image.draw(deltaTime);
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
