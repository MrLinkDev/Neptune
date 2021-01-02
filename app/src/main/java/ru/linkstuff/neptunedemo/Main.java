package ru.linkstuff.neptunedemo;

import android.opengl.GLES20;

import java.util.List;

import ru.linkstuff.neptune.Framework.Game;
import ru.linkstuff.neptune.Framework.Graphics;
import ru.linkstuff.neptune.Framework.Input;
import ru.linkstuff.neptune.Framework.Scene;
import ru.linkstuff.neptune.OpenGL.Artist;
import ru.linkstuff.neptune.OpenGL.Camera;
import ru.linkstuff.neptune.OpenGL.Debug.Grid;
import ru.linkstuff.neptune.OpenGL.GLActivity;
import ru.linkstuff.neptune.OpenGL.GLScene;
import ru.linkstuff.neptune.OpenGL.Shader;
import ru.linkstuff.neptune.OpenGL.Sprite;
import ru.linkstuff.neptune.OpenGL.Texture;
import ru.linkstuff.neptune.Widgets.Button;
import ru.linkstuff.neptune.Widgets.Framework.AssetManager;
import ru.linkstuff.neptune.Widgets.Framework.Font;
import ru.linkstuff.neptune.Widgets.Framework.Unit;
import ru.linkstuff.neptune.Widgets.TextView;

import static ru.linkstuff.neptune.Widgets.Framework.Unit.unitSize;

public class Main extends GLActivity {
    @Override
    public Graphics getGraphics() {
        return null;
    }

    @Override
    public Scene getStartScene() {
        shader = new Shader();
        shader.createAndUseProgram(this, Shader.DEFAULT_VERTEX_SHADER, Shader.DEFAULT_FRAGMENT_SHADER);
        return new DemoScene(this);
    }
}

class DemoScene extends GLScene {
    Texture demoTexture;
    Texture testTexture;
    Sprite neptune;

    Camera camera;
    Artist artist;

    Grid grid;
    TextView testText;
    TextView testText2;
    Button button;

    float time = 0;

    protected DemoScene(Game game) {
        super(game);

        demoTexture = new Texture(glActivity, "neptune.png", GLES20.GL_TEXTURE0);
        testTexture = new Texture(glActivity, "test.png", GLES20.GL_TEXTURE0);
        AssetManager.customButton = new Sprite[9][3];
        AssetManager.customButton[0][0] = new Sprite(testTexture, 0, 0, 8, 8);
        AssetManager.customButton[1][0] = new Sprite(testTexture, 8, 0, 8, 8);
        AssetManager.customButton[2][0] = new Sprite(testTexture, 16, 0, 8, 8);
        AssetManager.customButton[3][0] = new Sprite(testTexture, 0,  8, 8, 8);
        AssetManager.customButton[4][0] = new Sprite(testTexture, 8,  8, 8, 8);
        AssetManager.customButton[5][0] = new Sprite(testTexture, 16, 8, 8, 8);
        AssetManager.customButton[6][0] = new Sprite(testTexture, 0,  16, 8, 8);
        AssetManager.customButton[7][0] = new Sprite(testTexture, 8,  16, 8, 8);
        AssetManager.customButton[8][0] = new Sprite(testTexture, 16, 16, 8, 8);

        AssetManager.customButton[0][1] = new Sprite(testTexture, 0, 24, 8, 8);
        AssetManager.customButton[1][1] = new Sprite(testTexture, 8, 24, 8, 8);
        AssetManager.customButton[2][1] = new Sprite(testTexture, 16, 24, 8, 8);
        AssetManager.customButton[3][1] = new Sprite(testTexture, 0, 32, 8, 8);
        AssetManager.customButton[4][1] = new Sprite(testTexture, 8, 32, 8, 8);
        AssetManager.customButton[5][1] = new Sprite(testTexture, 16, 32, 8, 8);
        AssetManager.customButton[6][1] = new Sprite(testTexture, 0, 40, 8, 8);
        AssetManager.customButton[7][1] = new Sprite(testTexture, 8, 40, 8, 8);
        AssetManager.customButton[8][1] = new Sprite(testTexture, 16, 40, 8, 8);

        AssetManager.customButton[0][2] = new Sprite(testTexture, 24, 0, 8, 8);
        AssetManager.customButton[1][2] = new Sprite(testTexture, 32, 0, 8, 8);
        AssetManager.customButton[2][2] = new Sprite(testTexture, 40, 0, 8, 8);
        AssetManager.customButton[3][2] = new Sprite(testTexture, 24,  8, 8, 8);
        AssetManager.customButton[4][2] = new Sprite(testTexture, 32,  8, 8, 8);
        AssetManager.customButton[5][2] = new Sprite(testTexture, 40, 8, 8, 8);
        AssetManager.customButton[6][2] = new Sprite(testTexture, 24,  16, 8, 8);
        AssetManager.customButton[7][2] = new Sprite(testTexture, 32,  16, 8, 8);
        AssetManager.customButton[8][2] = new Sprite(testTexture, 40, 16, 8, 8);

        neptune = new Sprite(demoTexture, 0, 0, 64, 64);

        camera = new Camera(width, height);
        artist = new Artist(1);

        grid = new Grid(0, 0, width, height, 18, 32, glActivity);
        Unit.setUnitSize(9, 16, width, height);

        new Font(glActivity, null);
        testText = new TextView(0, 0, width, height / 16, "NEPTUNE");
        testText2 = new TextView(0, -height / 16, width, height / 16, "NEPTUNE");
        button = new Button(0, -height/16 * 4, unitSize * 4, unitSize * 1);
        button.setText("buttonbuttonbutton");
    }

    @Override
    public void present(float deltaTime) {
        GLES20.glClearColor(0f, 0f, 0f, 1);

        camera.setCamera();

        artist.begin(demoTexture);
        artist.draw(0, 0, width / 2, width / 2, neptune);
        artist.end();

        grid.draw();
        testText.draw();
        testText2.draw();
        button.draw(testTexture);
    }

    @Override
    public void update(float deltaTime) {
        time += deltaTime;
        testText2.setText(String.valueOf((int) time));
        testText.setScale(Math.abs((float) Math.sin(time)));
        //button.setScale(Math.abs((float) Math.cos(time)));
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        for (Input.TouchEvent event : touchEvents) button.onClick(event);
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void dispose() {

    }
}
