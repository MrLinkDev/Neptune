package ru.linkstuff.neptune.UI.Utils;

import java.util.ArrayList;
import java.util.List;

import ru.linkstuff.neptune.OpenGL.Sprite;
import ru.linkstuff.neptune.OpenGL.TextureManager;

public class UIAssets {
    //Button assets
    public static List<Sprite> normalButtonSprites = new ArrayList<>();
    public static List<Sprite> pressedButtonSprites = new ArrayList<>();
    public static List<Sprite> disabledButtonSprites = new ArrayList<>();

    public static void loadDebugButtonSprites(){
        normalButtonSprites.add(new Sprite(TextureManager.getDebug(), 1, 32, 1, 1));
        pressedButtonSprites.add(new Sprite(TextureManager.getDebug(), 1, 34, 1, 1));
        disabledButtonSprites.add(new Sprite(TextureManager.getDebug(), 1, 36, 1, 1));
    }
}
