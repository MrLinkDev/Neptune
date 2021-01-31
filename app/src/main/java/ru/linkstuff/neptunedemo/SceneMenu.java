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
import ru.linkstuff.neptune.UI.Widgets.Button;
import ru.linkstuff.neptune.UI.Widgets.Image;

import static ru.linkstuff.neptune.UI.Utils.Metric.unit;

public class SceneMenu extends GLScene {
    Grid grid;
    FPS fps;

    Image image;
    Button button;
    Button buttonDisabled;

    boolean pressed = false;

    float time = 0;

    public SceneMenu(Game game) {
        super(game);

        Metric.setUnitSize(18, 32, width, height);

        grid = new Grid(width, height);

        fps = new FPS(width, height, Neptune.FPS_POSITION_TOP_LEFT, false);

        image = new Image(0, unit * 8, unit * 8, unit * 2);
        button = new Button(0, 0, unit * 8, unit * 2);
        buttonDisabled = new Button(0, unit * -3, unit * 8, unit * 2);
        buttonDisabled.setTouchable(false);
    }

    @Override
    public void update(float deltaTime) {
        fps.update(deltaTime);

        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        for (Input.TouchEvent event : touchEvents){
            button.checkTouch(event);
            buttonDisabled.checkTouch(event);
            if (event.type == Input.TouchEvent.TOUCH_DOWN){
                pressed = true;
            }

            if (event.type == Input.TouchEvent.TOUCH_UP){
                time = 0;
                pressed = false;
            }
        }
    }

    @Override
    public void present(float deltaTime) {
        super.present(deltaTime);

        grid.draw();
        fps.draw();
        image.draw();
        button.draw();
        buttonDisabled.draw();
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
