package ru.linkstuff.neptunedemo;

import ru.linkstuff.neptune.Framework.Graphics;
import ru.linkstuff.neptune.Framework.Scene;
import ru.linkstuff.neptune.OpenGL.GLActivity;

import ru.linkstuff.neptune.OpenGL.ProgramManager;

public class Main extends GLActivity {
    @Override
    public Graphics getGraphics() {
        return null;
    }

    @Override
    public Scene getStartScene() {
        ProgramManager.create(getApplicationContext());
        ProgramManager.defaultProgram().use();

        return new SceneMenu(this);
    }
}