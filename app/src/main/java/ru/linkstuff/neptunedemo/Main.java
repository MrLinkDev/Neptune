package ru.linkstuff.neptunedemo;

import ru.linkstuff.neptune.Framework.Graphics;
import ru.linkstuff.neptune.Framework.Scene;
import ru.linkstuff.neptune.OpenGL.GLActivity;

import ru.linkstuff.neptune.OpenGL.ProgramManager;
import ru.linkstuff.neptune.OpenGL.TextureManager;

public class Main extends GLActivity {
    @Override
    public Graphics getGraphics() {
        return null;
    }

    @Override
    public Scene getStartScene() {
        return new SceneMenu(this);
    }
}