package ru.linkstuff.neptune.OpenGL.Debug;

import android.util.Log;

import ru.linkstuff.neptune.OpenGL.Artist;
import ru.linkstuff.neptune.OpenGL.Sprite;
import ru.linkstuff.neptune.OpenGL.TextureManager;

import static ru.linkstuff.neptune.Neptune.DEBUG_FPS_LABEL_WIDTH;
import static ru.linkstuff.neptune.Neptune.DEBUG_SPRITE_HEIGHT;
import static ru.linkstuff.neptune.Neptune.DEBUG_SPRITE_WIDTH;
import static ru.linkstuff.neptune.Neptune.FPS_POSITION_BOTTOM_LEFT;
import static ru.linkstuff.neptune.Neptune.FPS_POSITION_BOTTOM_RIGHT;
import static ru.linkstuff.neptune.Neptune.FPS_POSITION_TOP_LEFT;
import static ru.linkstuff.neptune.Neptune.FPS_POSITION_TOP_RIGHT;

public class FPS {
    private final Sprite[] font = new Sprite[12];

    private final Artist artist;

    private float time = 0f;
    private int count = 0;
    private int lastCount = 0;

    private float drawX;
    private float drawY;

    private final float multiplier;

    private final boolean withText;

    public FPS(float screenWidth, float screenHeight, int position, boolean withText){
        loadSprites();
        artist = new Artist(3);

        if (screenHeight > screenWidth) multiplier = screenWidth * 0.02f / DEBUG_SPRITE_WIDTH;
        else multiplier = screenHeight * 0.02f / DEBUG_SPRITE_HEIGHT;

        switch (position){
            case FPS_POSITION_TOP_LEFT:
                drawX = (-screenWidth + DEBUG_SPRITE_WIDTH * multiplier) / 2;
                drawY = (screenHeight - DEBUG_SPRITE_HEIGHT * multiplier) / 2;
                break;

            case FPS_POSITION_TOP_RIGHT:
                if (withText) drawX = (screenWidth / 2) - DEBUG_FPS_LABEL_WIDTH * multiplier - 1.5f * DEBUG_SPRITE_WIDTH * multiplier;
                else drawX = (screenWidth / 2) - 1.5f * DEBUG_SPRITE_WIDTH * multiplier;
                drawY = (screenHeight - DEBUG_SPRITE_HEIGHT * multiplier) / 2;
                break;

            case FPS_POSITION_BOTTOM_LEFT:
                drawX = (-screenWidth + DEBUG_SPRITE_WIDTH * multiplier) / 2;
                drawY = (-screenHeight + DEBUG_SPRITE_HEIGHT * multiplier) / 2;
                break;

            case FPS_POSITION_BOTTOM_RIGHT:
                if (withText) drawX = (screenWidth / 2) - DEBUG_FPS_LABEL_WIDTH * multiplier - 1.5f * DEBUG_SPRITE_WIDTH * multiplier;
                else drawX = (screenWidth / 2) - 1.5f * DEBUG_SPRITE_WIDTH * multiplier;
                drawY = (-screenHeight + DEBUG_SPRITE_HEIGHT * multiplier) / 2;
                break;
        }

        this.withText = withText;
    }

    public void draw(float deltaTime){
        time += deltaTime;
        ++count;

        if (time >= 1f){
            time = 0;

            if (lastCount != count){
                lastCount = count;
                Log.d("FPS", lastCount + "");
            }

            count = 0;

        }

        artist.begin(TextureManager.getDebug());
        if (lastCount < 10){
            artist.draw(drawX, drawY, DEBUG_SPRITE_WIDTH * multiplier, DEBUG_SPRITE_HEIGHT * multiplier, font[0]);
            artist.draw(drawX + (DEBUG_SPRITE_WIDTH * multiplier), drawY, DEBUG_SPRITE_WIDTH * multiplier, DEBUG_SPRITE_HEIGHT * multiplier, font[lastCount]);
        } else {
            artist.draw(drawX, drawY, DEBUG_SPRITE_WIDTH * multiplier, DEBUG_SPRITE_HEIGHT * multiplier, font[(lastCount - (lastCount % 10)) / 10]);
            artist.draw(drawX + (DEBUG_SPRITE_WIDTH * multiplier), drawY, DEBUG_SPRITE_WIDTH * multiplier, DEBUG_SPRITE_HEIGHT * multiplier, font[lastCount % 10]);
        }
        if (withText) artist.draw(drawX + 3f * (DEBUG_SPRITE_WIDTH * multiplier), drawY, DEBUG_FPS_LABEL_WIDTH * multiplier, DEBUG_SPRITE_HEIGHT * multiplier, font[11]);
        artist.end();
    }

    private void loadSprites(){
        font[0] = new Sprite(TextureManager.getDebug(), 0, 0, DEBUG_SPRITE_WIDTH, DEBUG_SPRITE_HEIGHT);
        font[1] = new Sprite(TextureManager.getDebug(), 5, 0, DEBUG_SPRITE_WIDTH, DEBUG_SPRITE_HEIGHT);
        font[2] = new Sprite(TextureManager.getDebug(), 10, 0, DEBUG_SPRITE_WIDTH, DEBUG_SPRITE_HEIGHT);
        font[3] = new Sprite(TextureManager.getDebug(), 16, 0, DEBUG_SPRITE_WIDTH, DEBUG_SPRITE_HEIGHT);
        font[4] = new Sprite(TextureManager.getDebug(), 22, 0, DEBUG_SPRITE_WIDTH, DEBUG_SPRITE_HEIGHT);
        font[5] = new Sprite(TextureManager.getDebug(), 0, 8, DEBUG_SPRITE_WIDTH, DEBUG_SPRITE_HEIGHT);
        font[6] = new Sprite(TextureManager.getDebug(), 6, 8, DEBUG_SPRITE_WIDTH, DEBUG_SPRITE_HEIGHT);
        font[7] = new Sprite(TextureManager.getDebug(), 12, 8, DEBUG_SPRITE_WIDTH, DEBUG_SPRITE_HEIGHT);
        font[8] = new Sprite(TextureManager.getDebug(), 18, 8, DEBUG_SPRITE_WIDTH, DEBUG_SPRITE_HEIGHT);
        font[9] = new Sprite(TextureManager.getDebug(), 24, 8, DEBUG_SPRITE_WIDTH, DEBUG_SPRITE_HEIGHT);
        font[10] = new Sprite(TextureManager.getDebug(), 0, 24, DEBUG_SPRITE_WIDTH, DEBUG_SPRITE_HEIGHT);
        font[11] = new Sprite(TextureManager.getDebug(), 0, 16, DEBUG_FPS_LABEL_WIDTH, DEBUG_SPRITE_HEIGHT);
    }
}
