package ru.linkstuff.neptune.UI.Utils;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ru.linkstuff.neptune.OpenGL.Sprite;
import ru.linkstuff.neptune.OpenGL.TextureManager;

import static ru.linkstuff.neptune.Neptune.debug;

public class UIAssets {
    //Button assets
    public static List<Sprite> normalButtonSprites = new ArrayList<>();
    public static List<Sprite> pressedButtonSprites = new ArrayList<>();
    public static List<Sprite> disabledButtonSprites = new ArrayList<>();

    public static Sprite debugNormalButtonSprite;
    public static Sprite debugPressedButtonSprite;
    public static Sprite debugDisabledButtonSprite;

    public static void loadDebugButtonSprites() {
        normalButtonSprites.add(new Sprite(TextureManager.getDebug(), 1, 32, 1, 1));
        pressedButtonSprites.add(new Sprite(TextureManager.getDebug(), 1, 34, 1, 1));
        disabledButtonSprites.add(new Sprite(TextureManager.getDebug(), 1, 36, 1, 1));
    }

    public static void setButtonSprites(Sprites sprites){
        if (debug) return;

        sprites.loadButtonSprites();
    }

    public static class Sprites {
        public void loadButtonSprites(){
            normalButtonSprites = new ArrayList<>();
            pressedButtonSprites = new ArrayList<>();
            disabledButtonSprites = new ArrayList<>();

            Log.d("BUTTON_SPRITES", "LOAD_BUTTON_SPRITES");
        }
    }
}
