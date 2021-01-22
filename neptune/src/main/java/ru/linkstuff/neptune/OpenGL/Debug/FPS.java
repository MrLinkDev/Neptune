package ru.linkstuff.neptune.OpenGL.Debug;

import android.util.Log;

import ru.linkstuff.neptune.OpenGL.Artist;
import ru.linkstuff.neptune.OpenGL.Sprite;
import ru.linkstuff.neptune.OpenGL.TextureManager;

public class FPS {
    public static final int POSITION_TOP_LEFT = 0;
    public static final int POSITION_TOP_RIGHT = 1;
    public static final int POSITION_BOTTOM_LEFT = 2;
    public static final int POSITION_BOTTOM_RIGHT = 3;

    private final int SPRITE_WIDTH = 6;
    private final int SPRITE_HEIGHT = 8;

    private final int TEXT_WIDTH = 16;

    private Sprite[] font = new Sprite[12];

    private Artist artist;

    private float time = 0f;
    private int count = 0;
    private int lastCount = 0;

    private float drawX;
    private float drawY;

    private float multiplier;

    private boolean withText;

    public FPS(float screenWidth, float screenHeight, int position, boolean withText){
        loadTexture();
        artist = new Artist(3);

        if (screenHeight > screenWidth) multiplier = screenWidth * 0.02f / SPRITE_WIDTH;
        else multiplier = screenHeight * 0.02f / SPRITE_HEIGHT;

        switch (position){
            case POSITION_TOP_LEFT:
                drawX = (-screenWidth + SPRITE_WIDTH * multiplier) / 2;
                drawY = (screenHeight - SPRITE_HEIGHT * multiplier) / 2;
                break;

            case POSITION_TOP_RIGHT:
                if (withText) drawX = (screenWidth / 2) - TEXT_WIDTH * multiplier - 1.5f * SPRITE_WIDTH * multiplier;
                else drawX = (screenWidth / 2) - 1.5f * SPRITE_WIDTH * multiplier;
                drawY = (screenHeight - SPRITE_HEIGHT * multiplier) / 2;
                break;

            case POSITION_BOTTOM_LEFT:
                drawX = (-screenWidth + SPRITE_WIDTH * multiplier) / 2;
                drawY = (-screenHeight + SPRITE_HEIGHT * multiplier) / 2;
                break;

            case POSITION_BOTTOM_RIGHT:
                if (withText) drawX = (screenWidth / 2) - TEXT_WIDTH * multiplier - 1.5f * SPRITE_WIDTH * multiplier;
                else drawX = (screenWidth / 2) - 1.5f * SPRITE_WIDTH * multiplier;
                drawY = (-screenHeight + SPRITE_HEIGHT * multiplier) / 2;
                break;
        }

        this.withText = withText;
    }

    public void draw(float deltaTime){
        time += deltaTime;
        ++count;

        if (time >= 1f){
            time = 0;
            lastCount = count;
            count = 0;

            Log.d("FPS", lastCount + "");
        }

        artist.begin(TextureManager.getDebug());
        if (lastCount < 10){
            artist.draw(drawX, drawY, SPRITE_WIDTH * multiplier, SPRITE_HEIGHT * multiplier, font[0]);
            artist.draw(drawX + (SPRITE_WIDTH * multiplier), drawY, SPRITE_WIDTH * multiplier, SPRITE_HEIGHT * multiplier, font[lastCount]);
        } else {
            artist.draw(drawX, drawY, SPRITE_WIDTH * multiplier, SPRITE_HEIGHT * multiplier, font[(lastCount - (lastCount % 10)) / 10]);
            artist.draw(drawX + (SPRITE_WIDTH * multiplier), drawY, SPRITE_WIDTH * multiplier, SPRITE_HEIGHT * multiplier, font[lastCount % 10]);
        }
        if (withText) artist.draw(drawX + 3f * (SPRITE_WIDTH * multiplier), drawY, TEXT_WIDTH * multiplier, SPRITE_HEIGHT * multiplier, font[11]);
        artist.end();
    }

    private void loadTexture(){
        font[0] = new Sprite(TextureManager.getDebug(), 0, 0, SPRITE_WIDTH, SPRITE_HEIGHT);
        font[1] = new Sprite(TextureManager.getDebug(), 5, 0, SPRITE_WIDTH, SPRITE_HEIGHT);
        font[2] = new Sprite(TextureManager.getDebug(), 10, 0, SPRITE_WIDTH, SPRITE_HEIGHT);
        font[3] = new Sprite(TextureManager.getDebug(), 16, 0, SPRITE_WIDTH, SPRITE_HEIGHT);
        font[4] = new Sprite(TextureManager.getDebug(), 22, 0, SPRITE_WIDTH, SPRITE_HEIGHT);
        font[5] = new Sprite(TextureManager.getDebug(), 0, 8, SPRITE_WIDTH, SPRITE_HEIGHT);
        font[6] = new Sprite(TextureManager.getDebug(), 6, 8, SPRITE_WIDTH, SPRITE_HEIGHT);
        font[7] = new Sprite(TextureManager.getDebug(), 12, 8, SPRITE_WIDTH, SPRITE_HEIGHT);
        font[8] = new Sprite(TextureManager.getDebug(), 18, 8, SPRITE_WIDTH, SPRITE_HEIGHT);
        font[9] = new Sprite(TextureManager.getDebug(), 24, 8, SPRITE_WIDTH, SPRITE_HEIGHT);
        font[10] = new Sprite(TextureManager.getDebug(), 0, 24, SPRITE_WIDTH, SPRITE_HEIGHT);
        font[11] = new Sprite(TextureManager.getDebug(), 0, 16, TEXT_WIDTH, SPRITE_HEIGHT);
    }
}
