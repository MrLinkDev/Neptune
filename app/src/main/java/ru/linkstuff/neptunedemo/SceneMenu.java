package ru.linkstuff.neptunedemo;

import android.util.Log;

import java.util.List;

import ru.linkstuff.neptune.Framework.Game;
import ru.linkstuff.neptune.Framework.Input;
import ru.linkstuff.neptune.Neptune;
import ru.linkstuff.neptune.OpenGL.Debug.FPS;
import ru.linkstuff.neptune.OpenGL.Debug.Grid;
import ru.linkstuff.neptune.OpenGL.GLScene;
import ru.linkstuff.neptune.OpenGL.Sprite;
import ru.linkstuff.neptune.OpenGL.Texture;
import ru.linkstuff.neptune.OpenGL.TextureManager;
import ru.linkstuff.neptune.UI.Utils.Metric;
import ru.linkstuff.neptune.UI.Utils.UIAssets;
import ru.linkstuff.neptune.UI.Widgets.Button;
import ru.linkstuff.neptune.UI.Widgets.Image;

import static ru.linkstuff.neptune.Neptune.BUTTON_CLICKED;
import static ru.linkstuff.neptune.UI.Utils.Metric.unit;

public class SceneMenu extends GLScene {
    Grid grid;
    FPS fps;

    Image image;
    Button button;
    Button buttonSquare;
    Button buttonDisabled;

    boolean pressed = false;

    float time = 0;

    int w = 18;
    int h = 32;

    public SceneMenu(Game game) {
        super(game);

        Metric.setUnitSize(18, 32, width, height);
        Neptune.setDebug(false);

        UIAssets.setButtonSprites(new UIAssets.Sprites(){
            @Override
            public void loadButtonSprites() {
                super.loadButtonSprites();

                int slot = TextureManager.loadTexture(glActivity, "widgets.png");

                UIAssets.normalButtonSprites.add(new Sprite(TextureManager.get(slot), 0, 0, 8, 8));
                UIAssets.normalButtonSprites.add(new Sprite(TextureManager.get(slot), 8, 0, 8, 8));
                UIAssets.normalButtonSprites.add(new Sprite(TextureManager.get(slot), 16, 0, 8, 8));
                UIAssets.normalButtonSprites.add(new Sprite(TextureManager.get(slot), 0, 8, 8, 8));
                UIAssets.normalButtonSprites.add(new Sprite(TextureManager.get(slot), 8, 8, 8, 8));
                UIAssets.normalButtonSprites.add(new Sprite(TextureManager.get(slot), 16, 8, 8, 8));
                UIAssets.normalButtonSprites.add(new Sprite(TextureManager.get(slot), 0, 16, 8, 8));
                UIAssets.normalButtonSprites.add(new Sprite(TextureManager.get(slot), 8, 16, 8, 8));
                UIAssets.normalButtonSprites.add(new Sprite(TextureManager.get(slot), 16, 16, 8, 8));

                UIAssets.pressedButtonSprites.add(new Sprite(TextureManager.get(slot), 0, 24, 8, 8));
                UIAssets.pressedButtonSprites.add(new Sprite(TextureManager.get(slot), 8, 24, 8, 8));
                UIAssets.pressedButtonSprites.add(new Sprite(TextureManager.get(slot), 16, 24, 8, 8));
                UIAssets.pressedButtonSprites.add(new Sprite(TextureManager.get(slot), 0, 32, 8, 8));
                UIAssets.pressedButtonSprites.add(new Sprite(TextureManager.get(slot), 8, 32, 8, 8));
                UIAssets.pressedButtonSprites.add(new Sprite(TextureManager.get(slot), 16, 32, 8, 8));
                UIAssets.pressedButtonSprites.add(new Sprite(TextureManager.get(slot), 0, 40, 8, 8));
                UIAssets.pressedButtonSprites.add(new Sprite(TextureManager.get(slot), 8, 40, 8, 8));
                UIAssets.pressedButtonSprites.add(new Sprite(TextureManager.get(slot), 16, 40, 8, 8));

                UIAssets.disabledButtonSprites.add(new Sprite(TextureManager.get(slot), 24, 0, 8, 8));
                UIAssets.disabledButtonSprites.add(new Sprite(TextureManager.get(slot), 32, 0, 8, 8));
                UIAssets.disabledButtonSprites.add(new Sprite(TextureManager.get(slot), 40, 0, 8, 8));
                UIAssets.disabledButtonSprites.add(new Sprite(TextureManager.get(slot), 24, 8, 8, 8));
                UIAssets.disabledButtonSprites.add(new Sprite(TextureManager.get(slot), 32, 8, 8, 8));
                UIAssets.disabledButtonSprites.add(new Sprite(TextureManager.get(slot), 40, 8, 8, 8));
                UIAssets.disabledButtonSprites.add(new Sprite(TextureManager.get(slot), 24, 16, 8, 8));
                UIAssets.disabledButtonSprites.add(new Sprite(TextureManager.get(slot), 32, 16, 8, 8));
                UIAssets.disabledButtonSprites.add(new Sprite(TextureManager.get(slot), 40, 16, 8, 8));
            }
        });

        grid = new Grid(width, height);

        fps = new FPS(width, height, Neptune.FPS_POSITION_TOP_LEFT, false);

        image = new Image(0, unit * 8, unit * 8, unit * 2);
        button = new Button(0, 0.5f * unit, unit * 10, unit * 3);
        buttonSquare = new Button(7.5f * unit, 14.5f * unit, unit * 3, unit * 3);
        buttonDisabled = new Button(0, unit * -3.5f, unit * 12, unit * 3);
        buttonDisabled.setTouchable(false);
    }

    @Override
    public void update(float deltaTime) {
        fps.update(deltaTime);

        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        for (Input.TouchEvent event : touchEvents){
            button.checkTouch(event);
            buttonSquare.checkTouch(event);
            buttonDisabled.checkTouch(event);
            if (event.type == Input.TouchEvent.TOUCH_DOWN){
                pressed = true;
            }

            if (event.type == Input.TouchEvent.TOUCH_UP){
                //time = 0;
                pressed = false;
            }
        }

        image.setScale((float) Math.abs(Math.cos(time += deltaTime)));
    }

    @Override
    public void present(float deltaTime) {
        super.present(deltaTime);

        grid.draw();
        fps.draw();
        image.draw();
        button.draw();
        buttonSquare.draw();
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
